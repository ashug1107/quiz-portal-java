package com.quiz.controller;

import com.quiz.model.*;
import com.quiz.service.QuizService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> creds) {
        Optional<User> user = quizService.login(creds.get("username"), creds.get("password"));
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
    }

    @GetMapping("/quizzes")
    public List<Quiz> getQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/quiz/{id}")
    public ResponseEntity<?> getQuiz(@PathVariable Long id) {
        return quizService.getQuizById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/quiz/{id}/submit")
    public ResponseEntity<?> submitQuiz(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        String studentName = (String) payload.get("studentName");
        Map<String, String> rawAnswers = (Map<String, String>) payload.get("answers");

        Map<Long, String> answers = new HashMap<>();
        if (rawAnswers != null) {
            rawAnswers.forEach((k, v) -> answers.put(Long.parseLong(k), v));
        }

        return ResponseEntity.ok(quizService.evaluateQuiz(id, studentName, answers));
    }

    @PostMapping("/admin/quiz")
    public Quiz createQuiz(@RequestBody Quiz quiz) {
        return quizService.createQuiz(quiz);
    }

    @GetMapping("/admin/results")
    public List<Result> getResults() {
        return quizService.getAllResults();
    }
}