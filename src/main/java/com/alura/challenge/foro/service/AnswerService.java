package com.alura.challenge.foro.service;

import com.alura.challenge.foro.model.answer.Answer;
import com.alura.challenge.foro.model.answer.dto.DtoUpdateAnswer;
import com.alura.challenge.foro.repository.AnswerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public Page<Answer> findAllAnswers(Pageable pageable) {
        return answerRepository.findAll(pageable);
    }

    public Optional<Answer> getAnswer(Long id){
        return answerRepository.findById(id);
    }

    public void updateAnswer(Answer answer, DtoUpdateAnswer data){
        if(data.message() != null) answer.setMessage(data.message());
        answerRepository.save(answer);
    }

    public void saveAnswer(Answer answer){
        answerRepository.save(answer);
    }

    public void deleteAnswer(Long id){
        answerRepository.deleteById(id);
    }
}
