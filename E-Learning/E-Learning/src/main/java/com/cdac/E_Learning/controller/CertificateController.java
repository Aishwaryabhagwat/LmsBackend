package com.cdac.E_Learning.controller;

import com.cdac.E_Learning.pojo.Course;
import com.cdac.E_Learning.pojo.User;
import com.cdac.E_Learning.repo.ICourseRepo;
import com.cdac.E_Learning.repo.IUserRepository;
import com.cdac.E_Learning.repo.IUserVideoProgressRepo;
import com.cdac.E_Learning.repo.UserQuizResultRepository;
import com.cdac.E_Learning.service.CertificateService;

import org.apache.commons.io.IOUtils;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/api/certificates")
public class CertificateController {

    @Autowired
    private CertificateService certificateService;

    @Autowired
    private IUserRepository userRepo;

    @Autowired
    private ICourseRepo courseRepo;

    @Autowired
    private UserQuizResultRepository quizResultRepo;
    
    @Autowired
    private IUserVideoProgressRepo userVideoProgressRepository;

    @GetMapping("/download/{userId}/{courseId}")
    public ResponseEntity<?> downloadCertificate(@PathVariable Long userId, @PathVariable Long courseId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        //  Check quiz completion
        boolean quizCompleted = quizResultRepo.existsByUserIdAndQuizCourseIdAndIsCompleted(userId, courseId, true);
        if (!quizCompleted) {
            return ResponseEntity.badRequest()
                    .body("❌ User has not completed the course quiz.");
        }
        
        Double progress = userVideoProgressRepository.getCourseProgress(userId, courseId);
        if (progress == null || progress < 100.0) {
            return ResponseEntity.badRequest().body("Course videos not yet fully completed");
        }

        ByteArrayInputStream bis = certificateService.generateCertificate(user, course);

        try {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=certificate.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(IOUtils.toByteArray(bis)); 
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("❌ Error generating certificate");
        }
    }
    
    
    
    @GetMapping("/check/{userId}/{courseId}")
    public ResponseEntity<Boolean> checkCertificate(@PathVariable Long userId, @PathVariable Long courseId) {
        // check quiz completion
        boolean quizCompleted = quizResultRepo.existsByUserIdAndQuizCourseIdAndIsCompleted(userId, courseId, true);
        if (!quizCompleted) {
            return ResponseEntity.ok(false);
        }

        // check video progress
        Double progress = userVideoProgressRepository.getCourseProgress(userId, courseId);
        if (progress == null || progress < 100.0) {
            return ResponseEntity.ok(false);
        }

        return ResponseEntity.ok(true); // ✅ certificate available
    }

}
