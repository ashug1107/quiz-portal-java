package com.quiz;

import com.quiz.model.*;
import com.quiz.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class QuizApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizApplication.class, args);
    }

    @Bean
    CommandLineRunner initData(UserRepository userRepository, QuizRepository quizRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                userRepository.save(new User("admin", "admin123", "admin"));
                userRepository.save(new User("student1", "student123", "student"));

                Question q1 = new Question("Which level tests individual modules in isolation?", "Integration", "Unit Testing", "System", "Acceptance", "B");
                Question q2 = new Question("What is Boundary Value Analysis (BVA)?", "White-box technique", "Black-box technique for extreme inputs", "Performance test", "UML tool", "B");

                Quiz defaultQuiz = new Quiz("Software Testing & Quality Assurance Mid-Term", "admin", List.of(q1, q2));
                quizRepository.save(defaultQuiz);

                System.out.println("Java Spring Boot Database Seeded Successfully!");
            }
        };
    }
}