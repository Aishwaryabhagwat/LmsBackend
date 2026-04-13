package com.cdac.E_Learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.cdac.E_Learning.dto.NoteUrlResponse;
import com.cdac.E_Learning.dto.APIResponse;
import com.cdac.E_Learning.dto.VideoUrlResponse;
import com.cdac.E_Learning.pojo.Note;
import com.cdac.E_Learning.repo.INoteRepo;
import com.cdac.E_Learning.service.INoteService;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private INoteService noteService;
    
    @Autowired
    private INoteRepo noteRepo;


    @PostMapping("/upload")
    public ResponseEntity<APIResponse<Note>> uploadNote(
            @RequestParam("title") String title,
            @RequestParam("file") MultipartFile file,
            @RequestParam("chapterId") Long chapterId) {

        Note note = noteService.uploadNote(title, file, chapterId);
        return ResponseEntity.ok(new APIResponse<>("success", note, "Note uploaded successfully"));
    }

//    @GetMapping("/{noteId}")
//    public ResponseEntity<NoteUrlResponse> getNoteById(@PathVariable Long noteId) {
//    	 // Retrieve Note entity from database based on noteId
//        Optional<Note> noteOptional = noteRepo.findById(noteId);
//        if (noteOptional.isPresent()) {
//        	 String filePath = noteOptional.get().getUrl();
//        	 
//        	 String url = "https://paramrudra.iuac.res.in/lms-backend/notes/" + new File(filePath).getName();
//        	 NoteUrlResponse response = new NoteUrlResponse(url);
//            return ResponseEntity.ok(response);
//        }else {
//            return ResponseEntity.notFound().build();
//        }
//
//        }
    
    @GetMapping("/{noteId}")
    public ResponseEntity<NoteUrlResponse> getNoteById(@PathVariable Long noteId) {

        Optional<Note> noteOptional = noteRepo.findById(noteId);
        if (!noteOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        String fileName = new File(noteOptional.get().getUrl()).getName();
        String url = "https://paramrudra.cdacdelhi.in/notes/" + fileName;

        return ResponseEntity.ok(new NoteUrlResponse(url));
    }



    @DeleteMapping("/delete/{noteId}")
    public ResponseEntity<APIResponse<String>> deleteNoteById(@PathVariable Long noteId) {
        noteService.deleteNoteById(noteId);
        return ResponseEntity.ok(new APIResponse<>("success", "Note deleted successfully", "no error"));
    }

    @PutMapping("/update")
    public ResponseEntity<APIResponse<Note>> updateNote(
            @RequestParam("noteId") Long noteId,
            @RequestParam("title") String title,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        noteService.updateNote(noteId, title, file);
        Note updatedNote = noteService.getNoteById(noteId);
        if (updatedNote != null) {
            return ResponseEntity.ok(new APIResponse<>("success", updatedNote, "Note updated successfully"));
        } else {
            return ResponseEntity.status(404).body(new APIResponse<>("error", null, "Note not found or inactive"));
        }
    }

    @GetMapping("/activeNotes/{chapterId}")
    public ResponseEntity<APIResponse<List<Note>>> getNotesByChapterId(@PathVariable Long chapterId) {
        List<Note> notes = noteService.getNotesByChapterId(chapterId);
        return ResponseEntity.ok(new APIResponse<>("success", notes, "Notes retrieved successfully"));
    }
}
