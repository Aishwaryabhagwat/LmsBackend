package com.cdac.E_Learning.service;

import java.util.List;

import com.cdac.E_Learning.dto.AssignCourseRequest;
import com.cdac.E_Learning.pojo.Course;

public interface IUserCourseService {
	public void assignCourseToUser(AssignCourseRequest request);
    public List<Course> getAssignedCourses(Long userId);
	List<Course> filterUserCoursesByCategory(Long userId, Long categoryId);
}
