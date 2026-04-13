package com.cdac.E_Learning.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cdac.E_Learning.dto.EnrollRequestDTO;
import com.cdac.E_Learning.pojo.Course;

public interface ICourseService {
    List<Course> getAllCourses();
    Course getCourseById(Long id);
    Course createCourse(String name, String description, String author, Long categoryId, MultipartFile imageFile) throws IOException;
    Course updateCourse(Long id, String name, String description, String author, Long categoryId, MultipartFile imageFile);
    void deleteCourse(Long id);
	String getCourseImageUrl(Long courseId);
	
	
	//admin
	
	List<EnrollRequestDTO> getPendingRequests();
    void approveEnrollmentRequest(Long requestId);
    void rejectEnrollmentRequest(Long requestId);
}
