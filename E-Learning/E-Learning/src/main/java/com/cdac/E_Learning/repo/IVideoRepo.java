package com.cdac.E_Learning.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cdac.E_Learning.pojo.Video;

public interface IVideoRepo extends JpaRepository<Video, Long>{
	  
	  
	  @Query("SELECT v FROM Video v WHERE v.chapter.id = :chapterId AND v.isActive = 1")
	    List<Video> findActiveVideosByChapterId(@Param("chapterId") Long chapterId);
	  
	    @Modifying
	    @Transactional
	    @Query("UPDATE Video v SET v.isActive = 0 WHERE v.id = :videoId")
	    void softDeleteById(@Param("videoId") Long videoId);
}

