package com.cdac.E_Learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.E_Learning.dto.APIResponse;
import com.cdac.E_Learning.dto.ChapterRequestDTO;
import com.cdac.E_Learning.pojo.Chapter;
import com.cdac.E_Learning.service.IChapterService;

@RestController
@RequestMapping("/api/chapters")
public class ChapterController {

    @Autowired
    private IChapterService chapterService;
    
    @GetMapping("/getAllChapter")
    public APIResponse<List<Chapter>> getAllChapter() {
    	List<Chapter> chapter = chapterService.getAllChapter();
        if (!chapter.isEmpty()) {
            return new APIResponse<>("success", chapter, "no error");
        } else {
            return new APIResponse<>("error", null, "No course found");
        }
    }
    
	    @GetMapping("/getChapterById/{courseId}")
	    public APIResponse<List<Chapter>> getChaptersByCourseId(@PathVariable Long courseId) {
	        List<Chapter> chapters = chapterService.getChaptersByCourseId(courseId);
	        if (!chapters.isEmpty()) {
	            return new APIResponse<>("success", chapters, "No error");
	        } else {
	            return new APIResponse<>("error", null, "No chapters found for the given course");
	        }
	    }

    @GetMapping("/getChaptersWithQuizzes/{courseId}/{userId}")
    public APIResponse<List<Chapter>> getChaptersWithQuizzes(
        @PathVariable Long courseId,
        @PathVariable Long userId) {
        List<Chapter> chapters = chapterService.getChaptersByCourseAndUser(courseId, userId);
        return new APIResponse<>("success", chapters, null);
    }



    @PostMapping("/add")
    public ResponseEntity<APIResponse<String>> addChapter(@RequestBody ChapterRequestDTO chapterRequest) {
        Chapter chapter = chapterService.addChapter(chapterRequest);
        if (chapter != null) {
        	String message = "chapter '" + chapter.getTitle() + "' created successfully with ID: " + chapter.getId();
            return ResponseEntity.ok(new APIResponse<>("success", message, "No error"));
        } else {
            return ResponseEntity.ok(new APIResponse<>("error", null, "Failed to add chapter"));
        }
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<APIResponse<String>> updateChapter(@PathVariable Long id, @RequestBody Chapter updatedChapter) {
    	Chapter chapter = chapterService.updateChapter(id, updatedChapter);
        String message = "Chapter with ID: " + chapter.getId() + " updated successfully to '" + chapter.getTitle() + "'";
        return ResponseEntity.ok(new APIResponse<>("success", message, "Chapter updated successfully"));
    }
    
    @PutMapping("/delete/{id}")
    public ResponseEntity<APIResponse<String>> deleteChapter(@PathVariable Long id) {
    	chapterService.deleteChapter(id);
    return ResponseEntity.ok(new APIResponse<>("success", "Chapter deleted successfully", "no error"));
    }
}