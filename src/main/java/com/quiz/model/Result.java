package com.quiz.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "results")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    private String quizTitle;
    private int score;
    private int total;
    private double percentage;
    private LocalDateTime submissionTime;

    public Result() {}

    public Result(String studentName, String quizTitle, int score, int total, double percentage) {
        this.studentName = studentName;
        this.quizTitle = quizTitle;
        this.score = score;
        this.total = total;
        this.percentage = percentage;
        this.submissionTime = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public String getStudentName() { return studentName; }
    public String getQuizTitle() { return quizTitle; }
    public int getScore() { return score; }
    public int getTotal() { return total; }
    public double getPercentage() { return percentage; }
    public LocalDateTime getSubmissionTime() { return submissionTime; }
}