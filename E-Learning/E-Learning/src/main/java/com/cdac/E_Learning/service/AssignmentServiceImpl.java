package com.cdac.E_Learning.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cdac.E_Learning.controller.VideoController;
import com.cdac.E_Learning.dto.UserAssignmentResponse;
import com.cdac.E_Learning.pojo.Assignment;
import com.cdac.E_Learning.pojo.Course;
import com.cdac.E_Learning.pojo.User;
import com.cdac.E_Learning.pojo.UserAssignment;
import com.cdac.E_Learning.repo.IAssignmentRepo;
import com.cdac.E_Learning.repo.ICourseRepo;
import com.cdac.E_Learning.repo.IUserAssignmentRepo;
import com.cdac.E_Learning.repo.IUserRepository;
import com.cdac.E_Learning.util.FileValidator;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

@Service
public class AssignmentServiceImpl implements IAssignmentService {

	
	private static final Logger logger = LoggerFactory.getLogger(AssignmentServiceImpl.class);
    @Autowired
    private IAssignmentRepo assignmentRepo;
    
    @Autowired
    private IUserRepository userRepo; // Ensure this matches your repository interface name

    
    @Autowired
    private IUserAssignmentRepo userAssignmentRepo;

    @Autowired
    private ICourseRepo courseRepo;
    
    @Value("${upload.directory}")
    private String uploadDir;
    
    @Value("${assignment.answer.upload.dir}")
    private String answerUploadDir;

    @Override
    public Assignment createAssignment(String title, MultipartFile file, Long courseId) {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found"));

        // Save the file to a directory (you can configure this path)
//        String filePath = "opt/LMS/assignments/" + file.getOriginalFilename();
        FileValidator.validateDocument(file);

        String safeFileName = FileValidator.generateSafeFileName(file.getOriginalFilename());

        String filePath = uploadDir + "/" + safeFileName;

        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }

        Assignment assignment = new Assignment();
        assignment.setTitle(title);
        assignment.setUrl(filePath);
        assignment.setCourse(course);
        assignment.setIsActive(1);

        return assignmentRepo.save(assignment);
    }

    
    @Override
    public UserAssignment uploadAssignmentAnswer(Long userId, Long assignmentId, MultipartFile answerFile) {
        // Retrieve user and assignment
        Assignment assignment = assignmentRepo.findById(assignmentId)
            .orElseThrow(() -> new EntityNotFoundException("Assignment not found"));

        User user = userRepo.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Get user's email and sanitize it for directory name
        String userEmail = user.getEmail();
        String sanitizedEmail = userEmail.replaceAll("[@.]", "_"); // Replace '@' and '.' with '_'

        // Create a user-specific directory using email
//        String userDirectory = "opt/LMS/userAnswers/user_" + sanitizedEmail;
        String userDirectory = answerUploadDir + "/user_" + sanitizedEmail;

        File directory = new File(userDirectory);
        if (!directory.exists()) {
            directory.mkdirs(); // Create directory if not exists
        }

//         Store the file inside the user-specific directory
        String originalFilename = answerFile.getOriginalFilename();
        String filePath = userDirectory + "/" + originalFilename;
        
//        FileValidator.validate(answerFile);
//
//        String safeFileName = FileValidator.generateSafeFileName(answerFile.getOriginalFilename());
//
//        String filePath = userDirectory + "/" + safeFileName;

        try {
            answerFile.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
        
        try {
            answerFile.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }

        // Save file details to DB
        UserAssignment userAssignment = new UserAssignment();
        userAssignment.setUser(user);
        userAssignment.setAssignment(assignment);
        userAssignment.setAnswerFileUrl(filePath);
        userAssignment.setComplete(true);
        userAssignmentRepo.save(userAssignment);

        return userAssignment;
    }

    
    
    
    
    
//    @Override
//    public Assignment updateAssignment(Long assignmentId, String title, MultipartFile file) {
//        Assignment assignment = getAssignmentById(assignmentId);
//
//        // Update assignment metadata
//        assignment.setTitle(title);
//
//        // Update assignment file if provided
//        if (file != null && !file.isEmpty()) {
//            // Delete old assignment file
//            File oldFile = new File(assignment.getUrl());
//            if (oldFile.exists()) {
//                oldFile.delete();
//            }
//
//            // Save new assignment file
//            String filePath = "/opt/LMS/assignments/" + file.getOriginalFilename();
//            try {
//                file.transferTo(new File(filePath));
//            } catch (IOException e) {
//                throw new RuntimeException("Failed to store file", e);
//            }
//
//            // Update assignment URL
//            assignment.setUrl(filePath);
//        }
//
//        return assignmentRepo.save(assignment);
//    }

    
    @Override
    public Assignment updateAssignment(Long assignmentId, String title, MultipartFile file) {

        logger.info("Updating assignment with ID: {}", assignmentId);

        Assignment assignment = getAssignmentById(assignmentId);

        // Update metadata
        assignment.setTitle(title);

        if (file != null && !file.isEmpty()) {

            logger.info("New file received for assignment ID: {}", assignmentId);

            // ✅ VALIDATION (IMPORTANT)
            FileValidator.validateDocument(file);

            // Delete old file
            try {
                File oldFile = new File(assignment.getUrl());
                if (oldFile.exists()) {
                    oldFile.delete();
                    logger.info("Old assignment file deleted: {}", oldFile.getAbsolutePath());
                }
            } catch (Exception e) {
                logger.warn("Failed to delete old file: {}", e.getMessage());
            }

            // ✅ SAFE FILE NAME (avoid overwrite)
            String safeFileName = FileValidator.generateSafeFileName(file.getOriginalFilename());

            String filePath = uploadDir + "/" + safeFileName;

            try {
                file.transferTo(new File(filePath));
                logger.info("New assignment file saved at: {}", filePath);
            } catch (IOException e) {
                logger.error("File upload failed: {}", e.getMessage());
                throw new RuntimeException("Failed to store file", e);
            }

            assignment.setUrl(filePath);
        }

        Assignment updated = assignmentRepo.save(assignment);

        logger.info("Assignment updated successfully: {}", assignmentId);

        return updated;
    }
    @Override
    public void deleteAssignment(Long assignmentId) {
        Assignment assignment = getAssignmentById(assignmentId);
        if (assignment != null) {
            assignment.setIsActive(0);
            assignmentRepo.save(assignment);
        }
    }

    @Override
    public List<Assignment> getActiveAssignmentsByCourseId(Long courseId) {
        return assignmentRepo.findActiveAssignmentsByCourseId(courseId);
    }

    @Override
    public Assignment getAssignmentById(Long assignmentId) {
        return assignmentRepo.findById(assignmentId).orElseThrow(() -> new EntityNotFoundException("Assignment not found"));
    }
    

    
    @Override
    public void markAssignmentComplete(Long userId, Long assignmentId) {
        UserAssignment userAssignment = userAssignmentRepo.findByUserIdAndAssignmentId(userId, assignmentId)
            .orElseGet(() -> new UserAssignment(new User(userId), new Assignment(assignmentId)));
        userAssignment.setComplete(true); // Make sure to use the appropriate setter method
        userAssignmentRepo.save(userAssignment);
    }
    
    
    @Override
    public List<UserAssignment> getAssignmentsWithStatus(Long userId, Long courseId) {
        return userAssignmentRepo.findByUserIdAndAssignmentCourseId(userId, courseId);
    }
    
    @Override
    public List<UserAssignmentResponse> getAssignmentsForCourse(Long courseId, Long userId) {
        List<Assignment> assignments = assignmentRepo.findByCourseIdAndIsActive(courseId, 1);

        return assignments.stream()
            .map(assignment -> {
                UserAssignment userAssignment = userAssignmentRepo
                    .findByUserIdAndAssignmentId(userId, assignment.getId())
                    .orElse(null);

                boolean isComplete = userAssignment != null && userAssignment.isComplete();
                return new UserAssignmentResponse(assignment.getId(), assignment.getTitle(), 
                                          assignment.getUrl(), isComplete, assignment.getIsActive());
            })
            .collect(Collectors.toList());
    }
    



}
