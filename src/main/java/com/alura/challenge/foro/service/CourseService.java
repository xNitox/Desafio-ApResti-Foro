package com.alura.challenge.foro.service;

import com.alura.challenge.foro.model.course.Course;
import com.alura.challenge.foro.model.course.dto.DtoUpdateCourse;
import com.alura.challenge.foro.repository.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Page<Course> showAllCourses(Pageable pageable){
        return courseRepository.findAll(pageable);
    }

    public Optional<Course> showCourseById(Long id){
        return courseRepository.findById(id);
    }

    public Course createCourse(Course course){
        return courseRepository.save(course);
    }

    public void updateCourse(Course course,DtoUpdateCourse data){
        if(course.getName() != null) course.setName(data.name());
        if(course.getCategory() != null) course.setCategory(data.category());
        courseRepository.save(course);
    }

    public void deleteCourse(Long id){
        courseRepository.deleteById(id);
    }
}