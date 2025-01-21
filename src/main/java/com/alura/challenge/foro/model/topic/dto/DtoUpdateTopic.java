package com.alura.challenge.foro.model.topic.dto;

import com.alura.challenge.foro.model.course.Course;

public record DtoUpdateTopic(
        String title,
        String message,
        Course course
) {
}
