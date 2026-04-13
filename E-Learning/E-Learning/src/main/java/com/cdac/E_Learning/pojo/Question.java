package com.cdac.E_Learning.pojo;

import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
public class Question extends BaseEntity {

    private String content;

    @Column(nullable = false)
    private int isActive; // 0: Inactive, 1: Active

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    
    private List<QuizOption> options;

    @Column(nullable = false)
    private int correctOptionIndex;

}

