package com.cdac.E_Learning.dto;

import java.util.List;

import com.cdac.E_Learning.pojo.User;
import com.cdac.E_Learning.pojo.UserAnswer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizSubmissionRequest {

	
    private Long quizId;
    private User user;
    private Long userId; 
    private List<UserAnswer> userAnswers;
}
