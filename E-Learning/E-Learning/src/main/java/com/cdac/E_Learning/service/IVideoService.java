package com.cdac.E_Learning.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cdac.E_Learning.pojo.Video;

public interface IVideoService {
	 Video uploadVideo(String title, MultipartFile file, int duration, Long chapterId);

	void saveVideo(Video video);

	Video getVideoById(Long videoId);
	
	List<Video> getActiveVideosByChapterId(Long chapterId);
	void softDeleteVideoById(Long videoId);
}
