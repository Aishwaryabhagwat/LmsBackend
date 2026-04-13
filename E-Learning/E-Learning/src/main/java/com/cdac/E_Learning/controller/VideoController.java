package com.cdac.E_Learning.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.cdac.E_Learning.dto.APIResponse;
import com.cdac.E_Learning.dto.VideoUrlResponse;
import com.cdac.E_Learning.pojo.Video;
import com.cdac.E_Learning.repo.IVideoRepo;
import com.cdac.E_Learning.service.IVideoService;
import com.cdac.E_Learning.service.NoteServiceImpl;
import com.cdac.E_Learning.util.FileValidator;

import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

	
	private static final Logger logger = LoggerFactory.getLogger(VideoController.class);
	
	@Value("${video.upload.directory}")
    private String uploadDir;
	
    @Autowired
    private IVideoService videoService;
    private final IVideoRepo videoRepository;
    
    @Autowired
    public VideoController(IVideoRepo videoRepository) {
        this.videoRepository = videoRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<APIResponse<Video>> uploadVideo(
            @RequestParam("title") String title,
            @RequestParam("file") MultipartFile file,
            @RequestParam("duration") int duration,
            @RequestParam("chapterId") Long chapterId) {
        Video video = videoService.uploadVideo(title, file, duration, chapterId);
        return ResponseEntity.ok(new APIResponse<>("success", video, "no error"));
    }
    
//    @PutMapping("/update")
//    public ResponseEntity<APIResponse<Video>> updateVideo(
//            @RequestParam("videoId") Long videoId,
//            @RequestParam("title") String title,
//            @RequestParam("duration") int duration,
//            @RequestPart("file") MultipartFile file) throws IOException {
//
//        
//            Video video = videoService.getVideoById(videoId);
//
//            // Update video metadata
//            video.setTitle(title);
//            video.setDuration(duration);
//
//            // Update video file if provided
//            if (file != null && !file.isEmpty()) {
//                // Delete old video file
//                Path oldFilePath = Paths.get(video.getUrl());
//                Files.deleteIfExists(oldFilePath);
//
//                // Save new video file
//                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//                Path newFilePath = Paths.get(uploadDir, fileName);
//                Files.copy(file.getInputStream(), newFilePath);
//
//                // Update video URL
//                video.setUrl(newFilePath.toString());
//            }
//            video.setIsActive(1);
//            videoService.saveVideo(video);
//            
//            return ResponseEntity.ok(new APIResponse<>("success", video, "no error"));
//        
//    }
    
    @PutMapping("/update")
    public ResponseEntity<APIResponse<Video>> updateVideo(
            @RequestParam("videoId") Long videoId,
            @RequestParam("title") String title,
            @RequestParam("duration") int duration,
            @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {

        logger.info("Updating video with ID: {}", videoId);

        Video video = videoService.getVideoById(videoId);

        if (video == null) {
            logger.error("Video not found with ID: {}", videoId);
            return ResponseEntity.badRequest()
                    .body(new APIResponse<>("error", null, "Video not found"));
        }

        // Update metadata
        video.setTitle(title);
        video.setDuration(duration);

        if (file != null && !file.isEmpty()) {

            logger.info("New file received for video ID: {}", videoId);

            // ✅ VALIDATION
            FileValidator.validate(file);

            // Delete old file
            try {
                Path oldFilePath = Paths.get(video.getUrl());
                Files.deleteIfExists(oldFilePath);
                logger.info("Old video deleted: {}", oldFilePath);
            } catch (Exception e) {
                logger.warn("Failed to delete old file: {}", e.getMessage());
            }

            // Save new file
            String fileName = FileValidator.generateSafeFileName(file.getOriginalFilename());
            Path newFilePath = Paths.get(uploadDir, fileName);

            Files.copy(file.getInputStream(), newFilePath);

            video.setUrl(newFilePath.toString());

            logger.info("New video saved at: {}", newFilePath);
        }

        video.setIsActive(1);
        videoService.saveVideo(video);

        logger.info("Video updated successfully: {}", videoId);

        return ResponseEntity.ok(new APIResponse<>("success", video, "no error"));
    }
    
    
   
    
    @GetMapping("/activeVideos/{chapterId}")
    public ResponseEntity<APIResponse<List<Video>>> getActiveVideosByChapterId(@PathVariable Long chapterId) {
        List<Video> videos = videoService.getActiveVideosByChapterId(chapterId);
        if (!videos.isEmpty()) {
            return ResponseEntity.ok(new APIResponse<>("success", videos, "Videos fetched successfully"));
        } else {
            return ResponseEntity.ok(new APIResponse<>("error", null, "Error"));
        }
    }
    
    @DeleteMapping("/deleteVideos/{videoId}")
    public ResponseEntity<APIResponse<String>> softDeleteVideo(@PathVariable Long videoId) {
        videoService.softDeleteVideoById(videoId);
        return ResponseEntity.ok(new APIResponse<>("success", "Video soft deleted successfully", "no error"));
    }
    
  
    
//    @GetMapping("/{id}")
//    public ResponseEntity<VideoUrlResponse> getVideoUrl(@PathVariable Long id) {
//        Optional<Video> videoOptional = videoRepository.findById(id);
//        if (videoOptional.isPresent()) {
//            String url = videoOptional.get().getUrl();
//            VideoUrlResponse response = new VideoUrlResponse(url);
//            return ResponseEntity.ok(response);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

    
    @GetMapping("/{id}")
    public ResponseEntity<VideoUrlResponse> getVideoUrl(@PathVariable Long id) {
        Optional<Video> videoOptional = videoRepository.findById(id);
        if (videoOptional.isPresent()) {
            String filePath = videoOptional.get().getUrl();
            // Convert the local file path to a URL
            String url = "https://paramrudra.cdacdelhi.in:8096/videos/" + new File(filePath).getName();
            VideoUrlResponse response = new VideoUrlResponse(url);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
