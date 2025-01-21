package com.alura.challenge.foro.model.topic.dto;

import com.alura.challenge.foro.model.answer.dto.DtoShowAnswer;
import com.alura.challenge.foro.model.topic.StatusEnum;
import com.alura.challenge.foro.model.topic.Topic;

import java.time.LocalDateTime;
import java.util.List;

public record DtoShowTopic(
        String title,
        String message,
        LocalDateTime creationDate,
        StatusEnum status,
        String author,
        String course,
        List<DtoShowAnswer> answers

) {
    public DtoShowTopic(Topic topic) {
        this(topic.getTitle(), topic.getMessage(), topic.getCreationDate(),
                topic.getStatus(), topic.getAuthor().getUsername(),
                topic.getCourse().getName(), topic.getAnswers().stream()
                        .map(DtoShowAnswer::new).toList());
    }
}
