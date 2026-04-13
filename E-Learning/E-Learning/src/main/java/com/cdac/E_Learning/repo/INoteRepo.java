package com.cdac.E_Learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cdac.E_Learning.pojo.Note;

import java.util.List;

public interface INoteRepo extends JpaRepository<Note, Long> {
    List<Note> findByChapterIdAndIsActive(Long chapterId, int isActive);

    @Query("SELECT n FROM Note n WHERE n.id = :noteId AND n.isActive = 1")
    Note findActiveNoteById(@Param("noteId") Long noteId);
}
