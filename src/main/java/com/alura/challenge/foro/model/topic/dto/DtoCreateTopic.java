package com.alura.challenge.foro.model.topic.dto;

import com.alura.challenge.foro.model.course.dto.DtoCreateTopicCourse;
import com.alura.challenge.foro.model.topic.Topic;
import com.alura.challenge.foro.model.user.dto.DtoCreateTopicAuthor;

public record DtoCreateTopic(
        String title,
        String message,
        DtoCreateTopicAuthor author,
        DtoCreateTopicCourse course
) {
    public DtoCreateTopic(Topic topic) {
        this(topic.getTitle(), topic.getMessage(), new DtoCreateTopicAuthor(topic.getAuthor()), new DtoCreateTopicCourse(topic.getCourse()));
    }
}
