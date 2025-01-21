package com.alura.challenge.foro.model.course.dto;

import com.alura.challenge.foro.model.course.Course;

public record DtoCreateTopicCourse(
        Long id,
        String name
) {
    public DtoCreateTopicCourse(Course course) {
        this(course.getId(), course.getName());
    }
}
