package com.cdac.E_Learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.E_Learning.pojo.User;
import com.cdac.E_Learning.repo.IUserRepository;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class IdController {
	
	@Autowired
    private IUserRepository userRepository;
	
	 @GetMapping("/id")
	    public Long getUserId() {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        String username = authentication.getName();
	        User user = userRepository.findByUsername(username)
	                                   .orElseThrow(() -> new RuntimeException("User not found"));
	        return user.getId();
	    }
}
