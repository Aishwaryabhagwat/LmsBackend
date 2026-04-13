package com.cdac.E_Learning.service;

import org.springframework.web.multipart.MultipartFile;

import com.cdac.E_Learning.pojo.Note;

import java.util.List;

public interface INoteService {
    Note uploadNote(String title, MultipartFile file, Long chapterId);
    Note getNoteById(Long noteId);
    void deleteNoteById(Long noteId);
    void updateNote(Long noteId, String title, MultipartFile file);
    List<Note> getNotesByChapterId(Long chapterId);
}
