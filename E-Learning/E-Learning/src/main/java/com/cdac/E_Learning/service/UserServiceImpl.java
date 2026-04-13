package com.cdac.E_Learning.service;

import com.cdac.E_Learning.pojo.Course;
import com.cdac.E_Learning.pojo.EnrollmentRequest;
import com.cdac.E_Learning.pojo.User;
import com.cdac.E_Learning.repo.ICourseRepo;
import com.cdac.E_Learning.repo.IEnrollmentRequestRepository;
import com.cdac.E_Learning.repo.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
   
    private final PasswordEncoder passwordEncoder;
    
    private final ICourseRepo courseRepository;
    private final IEnrollmentRequestRepository enrollmentRequestRepository;

//    @Autowired
//    public UserServiceImpl(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//		
//        this.passwordEncoder = passwordEncoder;
//    }
    
    @Autowired
    public UserServiceImpl(
            IUserRepository userRepository,
            ICourseRepo courseRepository,
            IEnrollmentRequestRepository enrollmentRequestRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRequestRepository = enrollmentRequestRepository;
        this.passwordEncoder = passwordEncoder;
    }

    
    
    
    private void checkUsernameExists(String username) {
        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
    }

    @Override
    public User createUser(User user) {
        checkUsernameExists(user.getUsername());
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setRoles(Arrays.asList("ROLE_USER"));
        user.setIsActive(1);
        return userRepository.save(user);
    }

    @Override
    public User createAdmin(User user) {
    	checkUsernameExists(user.getUsername());
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setRoles(Arrays.asList("ROLE_ADMIN"));
        user.setIsActive(1);
        return userRepository.save(user);
    }
    
    @Override
    public List<User> getActiveUsers() {
        return userRepository.findByIsActive(1);
    }
    
    @Override
    public User getUserByUsername(String username) {
    	  Optional<User> userOptional = userRepository.findByUsername(username);
    	    
    	    // Throw an exception or handle the case where the user is not found
    	    return userOptional.orElseThrow(() -> 
    	        new UsernameNotFoundException("User with username " + username + " not found"));
    }
    
    @Override
    public void createEnrollmentRequest(Long userId, Long courseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        // Prevent duplicate pending request
        List<EnrollmentRequest> existing = enrollmentRequestRepository
                .findByStatus("PENDING")
                .stream()
                .filter(r -> r.getUser().getId().equals(userId) && r.getCourse().getId().equals(courseId))
                .collect(Collectors.toList());

        if (!existing.isEmpty()) {
            throw new IllegalArgumentException("You have already requested to enroll in this course.");
        }

        EnrollmentRequest request = new EnrollmentRequest();
        request.setUser(user);
        request.setCourse(course);
        request.setStatus("PENDING");
        request.setRequestDate(LocalDateTime.now());

        enrollmentRequestRepository.save(request);
    }


}
