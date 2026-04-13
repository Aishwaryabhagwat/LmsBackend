package com.cdac.E_Learning.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cdac.E_Learning.pojo.UserQuizResult;

public interface UserQuizResultRepository extends JpaRepository<UserQuizResult, Long> {
	
	 @Query("SELECT CASE WHEN COUNT(uqr) > 0 THEN true ELSE false END " +
	           "FROM UserQuizResult uqr WHERE uqr.user.id = :userId " +
	           "AND uqr.quiz.id = :quizId AND uqr.isCompleted = true")
	    boolean existsByUserIdAndQuizIdAndIsCompleted(@Param("userId") Long userId, @Param("quizId") Long quizId);
	 
//	 UserQuizResult findUserQuizResultByUserIdAndQuizId(Long userId, Long quizId);
	 
	 Optional<UserQuizResult> findByQuizIdAndUserId(Long quizId, Long userId);
	 
	 @Query("SELECT uqr FROM UserQuizResult uqr WHERE uqr.user.id = :userId AND uqr.quiz.id = :quizId ORDER BY uqr.submittedAt DESC")
	 List<UserQuizResult> findUserQuizResults(@Param("userId") Long userId, @Param("quizId") Long quizId);

	 default UserQuizResult findLatestUserQuizResult(Long userId, Long quizId) {
	     List<UserQuizResult> results = findUserQuizResults(userId, quizId);
	     return results.isEmpty() ? null : results.get(0);
	 }

	 boolean existsByUserIdAndQuizCourseIdAndIsCompleted(Long userId, Long courseId, boolean isCompleted);

	 
}
