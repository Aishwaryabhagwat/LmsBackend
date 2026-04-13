package com.cdac.E_Learning.service;

import org.springframework.web.multipart.MultipartFile;

import com.cdac.E_Learning.dto.UserAssignmentResponse;
import com.cdac.E_Learning.pojo.Assignment;
import com.cdac.E_Learning.pojo.UserAssignment;

import java.util.List;

public interface IAssignmentService {
    Assignment createAssignment(String title, MultipartFile file, Long courseId);
    Assignment updateAssignment(Long assignmentId, String title, MultipartFile file);
    void deleteAssignment(Long assignmentId);
    List<Assignment> getActiveAssignmentsByCourseId(Long courseId);
    Assignment getAssignmentById(Long assignmentId);
    void markAssignmentComplete(Long userId, Long assignmentId);
	List<UserAssignment> getAssignmentsWithStatus(Long userId, Long courseId);
	List<UserAssignmentResponse> getAssignmentsForCourse(Long courseId, Long userId);
    UserAssignment uploadAssignmentAnswer(Long userId, Long assignmentId, MultipartFile file);
}
