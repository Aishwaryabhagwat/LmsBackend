package com.cdac.E_Learning.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cdac.E_Learning.dto.EnrollRequestDTO;
import com.cdac.E_Learning.pojo.Category;
import com.cdac.E_Learning.pojo.Course;
import com.cdac.E_Learning.pojo.EnrollmentRequest;
import com.cdac.E_Learning.pojo.User;
import com.cdac.E_Learning.repo.CategoryRepository;
import com.cdac.E_Learning.repo.EnrollmentRequestRepository;
import com.cdac.E_Learning.repo.ICourseRepo;
import com.cdac.E_Learning.repo.IUserRepository;
import com.cdac.E_Learning.util.FileValidator;

import org.springframework.util.StringUtils; 

import io.jsonwebtoken.lang.Objects;

@Service
public class CourseServiceImpl implements ICourseService {
	
	 @Autowired
	    private EnrollmentRequestRepository enrollmentRequestRepository;
	 
	 @Autowired
	 private EmailService emailService;

    private final ICourseRepo courseRepo;
    private final CategoryRepository categoryRepo ;
    private final IUserRepository userRepository;
    
    @Value("${images.upload.directory}")
    private String uploadDirectory;
    
//    private static final String UPLOAD_DIR = "opt/LMS/images/";


    @Autowired
    public CourseServiceImpl(ICourseRepo courseRepo, CategoryRepository categoryRepo, IUserRepository userRepository) {
        this.courseRepo = courseRepo;
		this.categoryRepo = categoryRepo;
		this.userRepository = userRepository;
		
    }

    @Override
    public List<Course> getAllCourses() { 
        return courseRepo.findByIsActive(1);
    }
 
    @Override
    public Course getCourseById(Long id) {
        Optional<Course> course = courseRepo.findById(id);
        return course.orElse(null);
    }

  


    @Override
    public Course createCourse(String name, String description, String author, Long categoryId, MultipartFile imageFile) throws IOException {
    	 Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
    	Course course = new Course();
        course.setName(name);
        course.setDescription(description);
        course.setAuthor(author);
        course.setCategory(category);
        course.setIsActive(1);
        // Save the file locally
        
//        String fileName = imageFile.getOriginalFilename();
//        Path path = Paths.get(uploadDirectory, fileName);
//        Files.copy(imageFile.getInputStream(), path);
//        course.setImageUrl(path.toString());

        FileValidator.validateImage(imageFile);

        String safeFileName = FileValidator.generateSafeFileName(imageFile.getOriginalFilename());

        Path path = Paths.get(uploadDirectory, safeFileName);

        Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        // Better to store relative path instead of full system path
        course.setImageUrl("/images/" + safeFileName);
        
        
        
        
        
        
        
        
        // Save the course in the database
        return courseRepo.save(course);
    }

    @Override
    public String getCourseImageUrl(Long courseId) {
        Course course = courseRepo.findById(courseId).orElse(null);
        if (course != null && course.getImageUrl() != null) {
            return course.getImageUrl();
        }
        return null;
    }

    private String saveImage(MultipartFile imageFile) throws IOException {
        // Define the path where images will be stored
        
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(uploadDirectory + "/" + imageFile.getOriginalFilename());
        Files.write(path, bytes);
        return path.toString(); // Store path in database
    }
    

  
    
    @Override
    public Course updateCourse(Long id, String name, String description, String author, Long categoryId, MultipartFile imageFile) {
        Optional<Course> courseOpt = courseRepo.findById(id);
        if (!courseOpt.isPresent()) {
            throw new IllegalArgumentException("Course with ID: " + id + " not found");
        }
        Course existingCourse = courseOpt.get();
        existingCourse.setName(name);
        existingCourse.setDescription(description);
        existingCourse.setAuthor(author);

        if (categoryId != null) {
            Category category = new Category();
            category.setId(categoryId);
            existingCourse.setCategory(category);
        }

        if (imageFile != null && !imageFile.isEmpty()) {
            try {
//                String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
//                Path filePath = Paths.get(uploadDirectory , fileName);
//                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//                String imageUrl = "/images/" + fileName;
//                existingCourse.setImageUrl(imageUrl);
            	
            	FileValidator.validateImage(imageFile);

            	String safeFileName = FileValidator.generateSafeFileName(imageFile.getOriginalFilename());

            	Path filePath = Paths.get(uploadDirectory, safeFileName);

            	Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            	existingCourse.setImageUrl("/images/" + safeFileName);
            	
            } catch (IOException e) {
                throw new RuntimeException("Failed to store image file. Error: " + e.getMessage());
            }
        }
//        if (imageFile != null && !imageFile.isEmpty()) {
//            try {
//                String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
//                Path filePath = Paths.get(uploadDirectory, fileName);
//                Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//
//                // ✅ Save full file path in DB
//                existingCourse.setImageUrl(filePath.toString());
//
//            } catch (IOException e) {
//                throw new RuntimeException("Failed to store image file. Error: " + e.getMessage());
//            }
//        }


        return courseRepo.save(existingCourse);
    }

    public void deleteCourse(Long id) {
        Optional<Course> courseOpt = courseRepo.findById(id);
        if (!courseOpt.isPresent()) {
            throw new IllegalArgumentException("Course with ID: " + id + " not found");
        }
        Course course = courseOpt.get();
        course.setIsActive(0);
        courseRepo.save(course);
    }

  
    


    // Method to generate unique file name using UUID
    private String generateUniqueFileName(String originalFileName) {
        // Generate a unique identifier for the file name
        String uniqueIdentifier = UUID.randomUUID().toString();
        // Extract file extension from original file name
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        // Combine unique identifier and file extension to get unique file name
        return uniqueIdentifier + fileExtension;
    }
    
    
    
    
    @Override
    public List<EnrollRequestDTO> getPendingRequests() {
        return enrollmentRequestRepository.findByStatus("PENDING")
                .stream()
                .map(r -> new EnrollRequestDTO(
                        r.getId(),
                        r.getUser().getId(),
                        r.getUser().getUsername(),
                        r.getCourse().getId(),
                        r.getCourse().getName(),
                        r.getStatus()))
                .collect(Collectors.toList());
    }

    @Override
    public void approveEnrollmentRequest(Long requestId) {
        EnrollmentRequest request = enrollmentRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Enrollment request not found"));

        if (!"PENDING".equalsIgnoreCase(request.getStatus())) {
            throw new IllegalStateException("Request already processed");
        }

        User user = request.getUser();
        Course course = request.getCourse();

        // Assign course if not already enrolled
        if (!user.getEnrolledCourses().contains(course)) {
            user.getEnrolledCourses().add(course);
        }

        userRepository.save(user);

        request.setStatus("ACCEPTED");
        enrollmentRequestRepository.save(request);
        
        
        //  Send notification email to user
        emailService.sendEnrollmentApprovalEmail(user.getEmail(), user.getUsername(), course.getName());
    
    }



    @Override
    public void rejectEnrollmentRequest(Long requestId) {
        EnrollmentRequest request = enrollmentRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Enrollment request not found"));

        if (!"PENDING".equalsIgnoreCase(request.getStatus())) {
            throw new IllegalStateException("Request already processed");
        }

        request.setStatus("REJECTED");
        enrollmentRequestRepository.save(request);
    }

    
    
    
    
    
}
