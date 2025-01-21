package com.alura.challenge.foro.model.answer.dto;

import com.alura.challenge.foro.model.answer.Answer;

public record DtoUpdateAnswer(
        String message
) {
    public DtoUpdateAnswer(Answer answer){
        this(answer.getMessage());
    }
}
