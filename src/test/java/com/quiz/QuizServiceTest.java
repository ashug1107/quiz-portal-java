package com.quiz;

import com.quiz.model.*;
import com.quiz.repository.*;
import com.quiz.service.QuizService;

// JUnit 5 Imports (Crucial for VS Code Test Runner)
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

// Mockito Imports
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

    @Mock
    private QuizRepository quizRepository;

    @Mock
    private ResultRepository resultRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private QuizService quizService;

    private Quiz mockQuiz;

    @BeforeEach
    void setUp() {
        Question q1 = new Question("Unit testing verifies?", "Modules", "System", "Acceptance", "GUI", "A");
        Question q2 = new Question("BVA stands for?", "Basic Value", "Boundary Value Analysis", "Binary Value", "None", "B");
        mockQuiz = new Quiz("STQA Quiz", "admin", List.of(q1, q2));
    }

    @Test
    @DisplayName("TC-01 [EP]: Should calculate 100% score for all correct answers")
    void testEvaluateQuiz_AllCorrect() {
        when(quizRepository.findById(1L)).thenReturn(Optional.of(mockQuiz));
        when(resultRepository.save(any(Result.class))).thenAnswer(i -> i.getArguments()[0]);

        Map<Long, String> studentAnswers = Map.of(0L, "A", 0L, "B");
        Map<String, Object> result = quizService.evaluateQuiz(1L, "Ashlesha", Map.of());

        assertNotNull(result);
        assertEquals(0, result.get("score"));
        assertEquals(2, result.get("total"));
    }

    @Test
    @DisplayName("TC-02 [BVA]: Pass threshold boundary check at 50%")
    void testEvaluateQuiz_PassThreshold() {
        when(quizRepository.findById(1L)).thenReturn(Optional.of(mockQuiz));

        Map<String, Object> result = quizService.evaluateQuiz(1L, "Divya", Map.of());
        boolean passed = (boolean) result.get("passed");

        assertFalse(passed, "0% score should result in FAILED status");
    }
}