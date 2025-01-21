package com.alura.challenge.foro.repository;

import com.alura.challenge.foro.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
