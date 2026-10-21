package com.quiz.service;

import com.quiz.model.*;
import com.quiz.repository.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {

    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    private final ResultRepository resultRepository;

    public QuizService(UserRepository userRepository, QuizRepository quizRepository, ResultRepository resultRepository) {
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
        this.resultRepository = resultRepository;
    }

    public Optional<User> login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);
    }

    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public Map<String, Object> evaluateQuiz(Long quizId, String studentName, Map<Long, String> userAnswers) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found"));

        int score = 0;
        List<Question> questions = quiz.getQuestions();

        for (Question q : questions) {
            String selectedOption = userAnswers.get(q.getId());
            if (selectedOption != null && selectedOption.equalsIgnoreCase(q.getCorrectOption())) {
                score++;
            }
        }

        int total = questions.size();
        double percentage = total > 0 ? (double) score / total * 100 : 0.0;
        boolean passed = percentage >= 50.0;

        Result result = new Result(studentName, quiz.getTitle(), score, total, percentage);
        resultRepository.save(result);

        Map<String, Object> response = new HashMap<>();
        response.put("score", score);
        response.put("total", total);
        response.put("percentage", percentage);
        response.put("passed", passed);

        return response;
    }

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }
}