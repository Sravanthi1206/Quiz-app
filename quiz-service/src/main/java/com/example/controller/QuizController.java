package com.example.controller;

import com.example.dto.QuestionDto;
import com.example.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @GetMapping("/start/{quizId}")
    public List<QuestionDto> start(@PathVariable String quizId) {
        return quizService.getQuestions(quizId);
    }
}
