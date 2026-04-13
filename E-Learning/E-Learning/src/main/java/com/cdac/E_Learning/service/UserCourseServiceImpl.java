package com.cdac.E_Learning.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.cdac.E_Learning.dto.AssignCourseRequest;
import com.cdac.E_Learning.pojo.Course;
import com.cdac.E_Learning.pojo.User;
import com.cdac.E_Learning.repo.ICourseRepo;
import com.cdac.E_Learning.repo.IUserCourseRepo;


@Service
public class UserCourseServiceImpl implements IUserCourseService{
	 private final ICourseRepo courseRepository;
	 private final IUserCourseRepo userCourseRepo;
	 
	 
	
	 public UserCourseServiceImpl(ICourseRepo courseRepository, IUserCourseRepo userCourseRepo) {
		
		this.courseRepository = courseRepository;
		this.userCourseRepo = userCourseRepo;
	}



	public void assignCourseToUser(AssignCourseRequest request) {
	        Long userId = request.getUserId();
	        Long courseId = request.getCourseId();

	        User user = userCourseRepo.findById(userId)
	                .orElseThrow(() -> new EntityNotFoundException("User not found"));

	        Course course = courseRepository.findById(courseId)
	                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

	        // Check if the course is already assigned
	        if (user.getEnrolledCourses().contains(course)) {
	            throw new IllegalArgumentException("Course is already assigned to the user");
	        }

	        user.getEnrolledCourses().add(course);
	        userCourseRepo.save(user);
	    }
	    
	    
	    
	    public List<Course> getAssignedCourses(Long userId) {
	        User user = userCourseRepo.findById(userId)
	                .orElseThrow(() -> new EntityNotFoundException("User not found"));

	        // Fetching courses with category details
	        return user.getEnrolledCourses();
	    }
	    
	    public List<Course> filterUserCoursesByCategory(Long userId, Long categoryId) {
	        User user = userCourseRepo.findById(userId)
	                .orElseThrow(() -> new EntityNotFoundException("User not found"));

	        // Filter the user's courses by category
	        return user.getEnrolledCourses().stream()
	                .filter(course -> course.getCategory().getId().equals(categoryId))
	                .collect(Collectors.toList());
	    }
}
