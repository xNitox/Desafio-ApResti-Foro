package com.alura.challenge.foro.model.course.dto;

import com.alura.challenge.foro.model.course.CategoryEnum;

public record DtoUpdateCourse(
        String name,
        CategoryEnum category
) {
}
