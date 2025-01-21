package com.alura.challenge.foro.repository;

import com.alura.challenge.foro.model.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
