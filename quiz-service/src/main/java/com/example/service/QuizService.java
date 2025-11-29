package com.example.service;

import com.example.dto.QuestionDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final RestTemplate restTemplate;

    @CircuitBreaker(name = "questionServiceCB", fallbackMethod = "fallback")
    public List<QuestionDto> getQuestions(String quizId) {

        String url = "http://question-service/questions/by-quiz/" + quizId;

        QuestionDto[] result = restTemplate.getForObject(url, QuestionDto[].class);

        if (result == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(result);
    }

    public List<QuestionDto> fallback(String quizId, Throwable t) {
        return Collections.emptyList();
    }
}
