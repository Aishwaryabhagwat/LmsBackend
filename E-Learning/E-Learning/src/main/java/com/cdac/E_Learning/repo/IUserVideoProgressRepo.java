package com.cdac.E_Learning.repo;

import com.cdac.E_Learning.pojo.UserVideoProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUserVideoProgressRepo extends JpaRepository<UserVideoProgress, Long> {
    Optional<UserVideoProgress> findByUserIdAndVideoId(Long userId, Long videoId);
    List<UserVideoProgress> findByUserIdAndVideoChapterCourseId(Long userId, Long courseId);
    
    @Query("SELECT uvp FROM UserVideoProgress uvp WHERE uvp.user.id = :userId AND uvp.video.chapter.course.id = :courseId")
    List<UserVideoProgress> findByUserIdAndCourseId(Long userId, Long courseId);
    @Query("SELECT (SUM(uvp.completedDuration) * 100.0 / SUM(v.duration)) " +
 	       "FROM UserVideoProgress uvp " +
 	       "JOIN uvp.video v " +
 	       "JOIN v.chapter c " +
 	       "JOIN c.course co " +
 	       "WHERE uvp.user.id = :userId AND co.id = :courseId")
 	Double getCourseProgress(@Param("userId") Long userId, @Param("courseId") Long courseId);
}
