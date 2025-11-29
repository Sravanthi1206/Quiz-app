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

   
    @CircuitBreaker(name = "questionServiceCB", fallbackMethod = "fallbackGetQuestions")
    public List<QuestionDto> getQuestions(String quizId) {

        String url = "http://question-service/questions/by-quiz/" + quizId;

        QuestionDto[] result = restTemplate.getForObject(url, QuestionDto[].class);

        if (result == null) {
            return Collections.emptyList();
        }
        return Arrays.asList(result);
    }

    // Fallback for getQuestions
    public List<QuestionDto> fallbackGetQuestions(String quizId, Throwable t) {
        return Collections.emptyList();
    }


   
    @CircuitBreaker(name = "myCB", fallbackMethod = "fallbackCallDownstream")
    public String callDownstream() {
        return restTemplate.getForObject("http://question-service/api", String.class);
    }

    // Fallback for callDownstream
    public String fallbackCallDownstream(Throwable t) {
        return "fallback response";
    }
}
