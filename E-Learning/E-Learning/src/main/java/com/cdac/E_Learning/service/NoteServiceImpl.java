package com.cdac.E_Learning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cdac.E_Learning.pojo.Note;
import com.cdac.E_Learning.pojo.Chapter;
import com.cdac.E_Learning.repo.IChapterRepo;
import com.cdac.E_Learning.repo.INoteRepo;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityNotFoundException;

@Service
public class NoteServiceImpl implements INoteService {

    @Autowired
    private INoteRepo noteRepo;

    @Autowired
    private IChapterRepo chapterRepo;
    
    @Value("${notes.upload.directory}")
    private String uploadDir;

    private static final Logger logger = LoggerFactory.getLogger(NoteServiceImpl.class);
    
//    @Override
//    public Note uploadNote(String title, MultipartFile file, Long chapterId) {
//        Chapter chapter = chapterRepo.findById(chapterId).orElseThrow(() -> new EntityNotFoundException("Chapter not found"));
//
//        // Save the file to a directory (you can configure this path)
//        String filePath = uploadDir + "/" + file.getOriginalFilename();
//        try {
//            file.transferTo(new File(filePath));
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to store file", e);
//        }
//
//        Note note = new Note();
//        note.setTitle(title);
//        note.setUrl(filePath);
//        note.setChapter(chapter);
//        note.setIsActive(1);
//        return noteRepo.save(note);
//    }

    @Override
    public Note getNoteById(Long noteId) {
        return noteRepo.findActiveNoteById(noteId);
    }

    @Override
    public void deleteNoteById(Long noteId) {
        Note note = getNoteById(noteId);
        if (note != null) {
            note.setIsActive(0);
            noteRepo.save(note);
        }
    }

    @Override
    public void updateNote(Long noteId, String title, MultipartFile file) {
        Note note = getNoteById(noteId);
        if (note != null) {
            // Update note metadata
            note.setTitle(title);

            // Update note file if provided
            if (file != null && !file.isEmpty()) {

                // ✅ Validate new file
                validateFile(file);

                // Delete old file
                File oldFile = new File(note.getUrl());
                if (oldFile.exists()) {
                    oldFile.delete();
                }

                // ✅ Safe filename
                String safeFileName = java.util.UUID.randomUUID() + "_" + file.getOriginalFilename();
                String filePath = uploadDir + "/" + safeFileName;

                try {
                    File dest = new File(filePath);

                    file.transferTo(dest);

                    // ✅ Virus scan
                    scanFile(dest);

                } catch (IOException e) {
                    throw new RuntimeException("Failed to store file", e);
                }

                note.setUrl(filePath);
            }

            noteRepo.save(note);
        }
    }
    
    @Override
    public Note uploadNote(String title, MultipartFile file, Long chapterId) {
    	
    	

        Chapter chapter = chapterRepo.findById(chapterId)
                .orElseThrow(() -> new EntityNotFoundException("Chapter not found"));

        // ✅ Validate file
        validateFile(file);

        // ✅ Safe filename
        String originalName = file.getOriginalFilename();
        String safeFileName = java.util.UUID.randomUUID() + "_" + originalName;

        String filePath = uploadDir + "/" + safeFileName;

        try {
            File dest = new File(filePath);

            // Save file
            file.transferTo(dest);
            logger.info("file stored successfully at: {}", dest); 

            // ✅ Virus scan (optional but recommended)
            scanFile(dest);

        } catch (IOException e) {
        	logger.error("Error while saving notes file: {}", safeFileName, e);
            throw new RuntimeException("Failed to store file", e);
        }

        Note note = new Note();
        note.setTitle(title);
        note.setUrl(filePath);
        note.setChapter(chapter);
        note.setIsActive(1);

     
        return noteRepo.save(note);
    }

    @Override
    public List<Note> getNotesByChapterId(Long chapterId) {
        return noteRepo.findByChapterIdAndIsActive(chapterId, 1);
    }
    
    private void validateFile(MultipartFile file) {

        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        // ✅ Allow only PDF
        List<String> allowedTypes = Arrays.asList("application/pdf", "image/png");

        if (!allowedTypes.contains(file.getContentType())) {
            throw new RuntimeException("Only PDF files are allowed");
        }

        // ✅ Extension check
        String filename = file.getOriginalFilename();

        if (filename == null || !filename.endsWith(".pdf")) {
            throw new RuntimeException("Invalid file extension");
        }

        // ✅ Size limit (20MB example)
        long maxSize = 20 * 1024 * 1024;

        if (file.getSize() > maxSize) {
            throw new RuntimeException("File size exceeds limit");
        }
    }
    
    private void scanFile(File file) {
        try {
            ProcessBuilder pb = new ProcessBuilder("clamscan", file.getAbsolutePath());
            Process process = pb.start();

            int exitCode = process.waitFor();

            if (exitCode != 0) {
                file.delete();
                throw new RuntimeException("File contains malware!");
            }

        } catch (Exception e) {
            throw new RuntimeException("Virus scan failed", e);
        }
    }
}
