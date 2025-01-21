package com.alura.challenge.foro.model.answer.dto;

import com.alura.challenge.foro.model.answer.Answer;

public record DtoShowAnswer(
        Long id,
        String message,
        String author,
        String topic
) {
    public DtoShowAnswer(Answer answer){
            this(answer.getId(), answer.getMessage(),
                answer.getAuthor().getUsername(),
                answer.getTopic().getTitle());
    }
}
