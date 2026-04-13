package com.cdac.E_Learning.service;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cdac.E_Learning.pojo.Chapter;
import com.cdac.E_Learning.pojo.Video;
import com.cdac.E_Learning.repo.IChapterRepo;
import com.cdac.E_Learning.repo.IVideoRepo;
import com.cdac.E_Learning.util.FileValidator;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityNotFoundException;

@Service
public class VideoServiceImpl implements IVideoService {

    @Autowired
    private IVideoRepo videoRepo;

    @Autowired
    private IChapterRepo chapterRepo;
    
    @Value("${video.upload.directory}")
    private String uploadDir;

    
    private static final Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);
    
    
    @Override
    public Video uploadVideo(String title, MultipartFile file, int duration, Long chapterId) {
//        Chapter chapter = chapterRepo.findById(chapterId).orElseThrow(() -> new EntityNotFoundException("Chapter not found"));

        logger.info("Video upload started for title: {}", title);
        
        Chapter chapter = chapterRepo.findById(chapterId)
                .orElseThrow(() -> {
                    logger.error("Chapter not found with ID: {}", chapterId);
                    return new EntityNotFoundException("Chapter not found");
                });
        
        // ✅ Validate file
        FileValidator.validate(file);
        logger.debug("File validation passed for: {}", file.getOriginalFilename());
        
        
        // Save the file to a directory (you can configure this path)
//        String filePath = uploadDir + "/" + file.getOriginalFilename();
       
        

        // ✅ Generate safe filename
        String safeFileName = FileValidator.generateSafeFileName(file.getOriginalFilename());
        logger.debug("Generated safe filename: {}", safeFileName);
        
        String filePath = uploadDir + "/" + safeFileName;
//        try {
//            File dest = new File(filePath);
//            file.transferTo(dest);
//            
//            scanFile(dest);
//            
//
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to store file", e);
//        }
        
        
        try {
            file.transferTo(new File(filePath));
            logger.info("Video file stored successfully at: {}", filePath);
        } catch (IOException e) {
            logger.error("Error while saving video file: {}", safeFileName, e);
            throw new RuntimeException("Failed to store file", e);
        }

        Video video = new Video();
        video.setTitle(title);
        video.setUrl(filePath);
        video.setDuration(duration);
        video.setChapter(chapter);

        video.setIsActive(1);
        

        logger.info("Video upload completed for title: {}", title);
        return videoRepo.save(video);
    }
    
    public Video getVideoById(Long videoId) {
    	 logger.info("Fetching video with ID: {}", videoId);

        return videoRepo.findById(videoId).orElseThrow(() -> new RuntimeException("Video not found"));
    }

    public void saveVideo(Video video) {
        videoRepo.save(video);
    }
    
   
    
    @Override
    public List<Video> getActiveVideosByChapterId(Long chapterId) {
        return videoRepo.findActiveVideosByChapterId(chapterId);
    }
    
    @Override
    public void softDeleteVideoById(Long videoId) {
        videoRepo.softDeleteById(videoId);
    }
    

    
    

}
