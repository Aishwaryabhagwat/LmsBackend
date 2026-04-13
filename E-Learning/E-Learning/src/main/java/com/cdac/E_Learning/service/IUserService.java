package com.cdac.E_Learning.service;


import java.util.List;

import com.cdac.E_Learning.pojo.User;

public interface IUserService{

	
	    User createUser(User user);
	    User createAdmin(User user);
		List<User> getActiveUsers();
		 User getUserByUsername(String username);
		 
		  void createEnrollmentRequest(Long userId, Long courseId);
		
	    
}
