package com.cdac.E_Learning.dto;

import java.util.List;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDTO {
    private String content;
    private List<String> options; 
    private int correctOptionIndex;

    // Getters and setters
}
