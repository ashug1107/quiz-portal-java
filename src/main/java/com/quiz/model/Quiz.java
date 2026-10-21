package com.quiz.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "quizzes")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String createdBy;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private List<Question> questions;

    public Quiz() {}

    public Quiz(String title, String createdBy, List<Question> questions) {
        this.title = title;
        this.createdBy = createdBy;
        this.questions = questions;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getCreatedBy() { return createdBy; }
    public List<Question> getQuestions() { return questions; }
}