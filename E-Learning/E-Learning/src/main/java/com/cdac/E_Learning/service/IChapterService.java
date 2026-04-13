package com.cdac.E_Learning.service;

import java.util.List;

import com.cdac.E_Learning.dto.ChapterRequestDTO;
import com.cdac.E_Learning.pojo.Chapter;



public interface IChapterService {
	
	public List<Chapter> getAllChapter();
	
	public Chapter addChapter(ChapterRequestDTO chapterRequest);
	public Chapter updateChapter(Long id, Chapter updatedChapter);
	public void deleteChapter(Long id);
	public List<Chapter> getChaptersByCourseId(Long courseId);

	

	List<Chapter> getChaptersByCourseId(Long courseId, Long userId);

	public List<Chapter> getChaptersByCourseAndUser(Long courseId, Long userId);
}
