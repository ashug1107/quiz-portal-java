package com.quiz.repository;

import com.quiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    // Custom query methods can be added here if needed (e.g., finding questions by quiz ID)
}