package com.cdac.E_Learning.pojo;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter

public class Quiz extends BaseEntity {

    private String title;
    private String description;

    // User-specific fields (add dynamically during query)
    @Column(nullable = false)
    @org.hibernate.annotations.ColumnDefault("false")
    private boolean attempted = false ;



    private Integer score = null;
    
    
    
    @Column(nullable = false)
    private int isActive; // 0: Inactive, 1: Active

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    @ManyToMany
    @JoinTable(
        name = "UserQuiz",
        joinColumns = @JoinColumn(name = "quiz_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users; // Users assigned to take this quiz
    
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "chapter_id", nullable = false)
    private Chapter chapter;
    
	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    // Constructor for Hibernate query
    public Quiz(Long id, String title, int isActive, Boolean attempted, Integer score) {
        this.id = id;
        this.title = title;
        this.isActive = isActive;
        this.attempted = attempted;
        this.score = score;
    }

    
   
    
    
    
	public Quiz(String title, String description, int isActive, Course course, List<Question> questions,
			List<User> users, Chapter chapter, Boolean attempted, Integer score) {
		super();
		this.title = title;
		this.description = description;
		this.isActive = isActive;
		this.course = course;
		this.questions = questions;
		this.users = users;
		this.chapter = chapter;
		this.attempted = attempted;
		this.score = score;
	}

	@Override
	public String toString() {
		return "Quiz [title=" + title + ", description=" + description + ", attempted=" + attempted + ", score=" + score
				+ ", isActive=" + isActive + ", course=" + course + ", questions=" + questions + ", users=" + users
				+ ", chapter=" + chapter + "]";
	}
	
    @Override
    public int hashCode() {
        return Objects.hash(getId()); // Hash based on unique ID
    }

    
    

 

  
}
