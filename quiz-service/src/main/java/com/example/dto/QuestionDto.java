package com.example.dto;

import lombok.Data;
import java.util.List;

@Data
public class QuestionDto {
    private String id;
    private String quizId;
    private String text;
    private List<String> options;
}
