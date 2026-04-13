package com.cdac.E_Learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.E_Learning.dto.APIResponse;
import com.cdac.E_Learning.dto.EnrollmentRequestDTO;
import com.cdac.E_Learning.pojo.EnrollmentRequest;
import com.cdac.E_Learning.pojo.User;
import com.cdac.E_Learning.repo.IUserRepository;
import com.cdac.E_Learning.service.IUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/api/users")


public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/request-enrollment")
    public ResponseEntity<String> requestEnrollment(@RequestParam Long userId, @RequestParam Long courseId) {
        userService.createEnrollmentRequest(userId, courseId);
        return ResponseEntity.ok("Enrollment request submitted successfully.");
    }




    @PostMapping("/create")
    public ResponseEntity<APIResponse<User>> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new APIResponse<>("success", createdUser, "no error"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new APIResponse<>("error", null, e.getMessage()));
        }
    }
    
    @PostMapping("/createAdmin")
    public ResponseEntity<APIResponse<User>> createAdmin(@RequestBody User user) {
    	try {
            User createdAdmin = userService.createAdmin(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new APIResponse<>("success", createdAdmin, "no error"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new APIResponse<>("error", null, e.getMessage()));
        }
    }
    
    @GetMapping("/activeUsers")
    public ResponseEntity<List<User>> getActiveUsers() {
        List<User> activeUsers = userService.getActiveUsers();
        return ResponseEntity.ok(activeUsers);
    }
    
    @GetMapping("/currentUserId")
    public ResponseEntity<String> getCurrentUserId() {
        // Get the currently authenticated user's username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Fetch the user from the database using the username
        User user = userService.getUserByUsername(username);

        if (user != null) {
            // Return the userId as a response
            return ResponseEntity.ok(user.getId().toString());
        } else {
            // If no user is found, return a 404 error
            return ResponseEntity.status(404).body("User not found");
        }
    }

}
