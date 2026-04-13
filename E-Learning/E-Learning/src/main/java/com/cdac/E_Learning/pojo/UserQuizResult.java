package com.cdac.E_Learning.pojo;

import java.util.Date;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserQuizResult extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    private int score; // User's score in the quiz

    @Column(nullable = false)
    private int totalQuestions; // Total number of questions in the quiz

    @Column(nullable = false)
    private int correctAnswers; // Total number of correct answers by the user

    @Column(nullable = false)
    private boolean isCompleted;
    
    private Date submittedAt;

}
