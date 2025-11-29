package com.example.controller;

import com.example.model.Question;
import com.example.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionRepository repository;

    @GetMapping
    public List<Question> getAll() {
        return repository.findAll();
    }

    @GetMapping("/by-quiz/{quizId}")
    public List<Question> getByQuiz(@PathVariable String quizId) {
        return repository.findByQuizId(quizId);
    }

    @PostMapping
    public Question create(@RequestBody Question question) {
        return repository.save(question);
    }
}
