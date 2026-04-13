package com.cdac.E_Learning.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cdac.E_Learning.pojo.Question;
import com.cdac.E_Learning.pojo.Quiz;
import com.cdac.E_Learning.pojo.QuizOption;
import com.cdac.E_Learning.pojo.UserQuizResult;

@Repository
public interface IQuizRepository  extends JpaRepository<Quiz, Long> {
	 List<Quiz> findByCourseId(Long courseId);
	   List<Quiz> findByCourseIdAndIsActive(Long courseId, int isActive);
//	   List<Quiz> findByChapterId(Long chapterId);
	   List<Quiz> findByChapterIdAndIsActive(Long chapterId, int isActive);
	   
//	   for fetching quiz attempted status 
	   
	   
	   @Query("SELECT DISTINCT new com.cdac.E_Learning.pojo.Quiz(q.id, q.title, q.isActive, " +
		       "CASE WHEN qr.id IS NOT NULL THEN true ELSE false END, qr.score) " +
		       "FROM Quiz q " +
		       "LEFT JOIN UserQuizResult qr ON q.id = qr.quiz.id AND qr.user.id = :userId " +
		       "WHERE q.chapter.id = :chapterId")
		List<Quiz> findQuizzesWithUserStatus(@Param("chapterId") Long chapterId, @Param("userId") Long userId);


	   


	   
	  

	
	 
	
}
