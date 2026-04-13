package com.cdac.E_Learning.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.E_Learning.pojo.Course;
import com.cdac.E_Learning.pojo.UserAssignment;


@Repository
public interface IUserAssignmentRepo extends JpaRepository<UserAssignment, Long>{

	Optional<UserAssignment> findByUserIdAndAssignmentId(Long userId, Long assignmentId);
	 List<UserAssignment> findByUserIdAndAssignmentCourseId(Long userId, Long courseId);
	Optional<UserAssignment> findByAssignmentIdAndUserId(Long assignmentId, Long userId);
	 
	 

}
