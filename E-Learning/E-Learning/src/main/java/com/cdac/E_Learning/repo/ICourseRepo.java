package com.cdac.E_Learning.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cdac.E_Learning.pojo.Course;

public interface ICourseRepo extends JpaRepository<Course, Long>{

	List<Course> findByIsActive(int isActive);
	
	 @Query("SELECT c FROM Course c JOIN c.enrolledUsers u WHERE u.id = :userId ")
	    List<Course> findActiveCoursesByUserId(@Param("userId") Long Id);
	 
	 Course save(Course course);

	Course getCourseById(Long courseId);
}
