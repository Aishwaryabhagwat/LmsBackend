package com.cdac.E_Learning.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cdac.E_Learning.pojo.Chapter;


public interface IChapterRepo extends JpaRepository<Chapter, Long>{
	List<Chapter> findByIsActive(int isActive);
	
	@Query("SELECT c FROM Chapter c WHERE c.course.id = :courseId AND c.isActive = 1")
    List<Chapter> findByCourseId(@Param("courseId") Long courseId);
	
	
}
