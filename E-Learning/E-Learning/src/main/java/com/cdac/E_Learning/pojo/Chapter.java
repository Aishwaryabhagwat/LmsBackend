package com.cdac.E_Learning.pojo;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.cdac.E_Learning.dto.QuizDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chapter extends BaseEntity {

    private String title;
    private String description;
    
    @Column(nullable = false)
    private int isActive;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
    
    //new
    @JsonManagedReference 
    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quiz> quizzes;
    
    @Override
    public int hashCode() {
        return Objects.hash(getId()); // Hash based on unique ID
    }

    
 


}
