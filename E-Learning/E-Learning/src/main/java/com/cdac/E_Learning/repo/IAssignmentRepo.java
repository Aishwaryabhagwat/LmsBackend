package com.cdac.E_Learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cdac.E_Learning.pojo.Assignment;
import com.cdac.E_Learning.pojo.UserAssignment;

import java.util.List;

public interface IAssignmentRepo extends JpaRepository<Assignment, Long> {
	
    @Query("SELECT a FROM Assignment a WHERE a.course.id = :courseId AND a.isActive = 1")
    List<Assignment> findActiveAssignmentsByCourseId(@Param("courseId") Long courseId);
    
    List<Assignment> findByCourseIdAndIsActive(Long courseId, int isActive);
 
}
