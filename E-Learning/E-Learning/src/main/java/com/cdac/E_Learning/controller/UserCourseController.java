package com.cdac.E_Learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.E_Learning.dto.APIResponse;
import com.cdac.E_Learning.dto.AssignCourseRequest;
import com.cdac.E_Learning.dto.FilterByCategoryRequest;
import com.cdac.E_Learning.pojo.Course;
import com.cdac.E_Learning.service.IUserCourseService;


@RestController
@RequestMapping("/api/userCourse")
@CrossOrigin("*")
public class UserCourseController {

	private final IUserCourseService userCourse;
	
	@Autowired
    public UserCourseController(IUserCourseService userCourse) {
        this.userCourse = userCourse;
    }
	
	
	
//  -----userCourse methods-----
//  1.admin assign course to user 
  @PostMapping("/assignCourse")
  public ResponseEntity<APIResponse<String>> assignCourseToUser(
      @RequestBody AssignCourseRequest request
  ) {
	  userCourse.assignCourseToUser(request);
      return ResponseEntity.ok(new APIResponse<>("success", "Course assigned to user successfully", "no error"));
  }
  
//  2.user fetches all courses assigned to him 
  @GetMapping("/courses/{userId}")
  public ResponseEntity<APIResponse<List<Course>>> getAssignedCourses(@PathVariable("userId") Long userId) {
      List<Course> courses = userCourse.getAssignedCourses(userId);
      return ResponseEntity.ok(new APIResponse<>("success", courses, "no error"));
  }
  
//  3.user fetches courses by category id 
  @PostMapping("/courses/filter/{userId}")
  public ResponseEntity<APIResponse<List<Course>>> filterUserCoursesByCategory(
          @PathVariable Long userId,
          @RequestBody FilterByCategoryRequest request) {
      List<Course> filteredCourses = userCourse.filterUserCoursesByCategory(userId, request.getCategoryId());
      return ResponseEntity.ok(new APIResponse<>("success", filteredCourses, "no error"));
  }
}
