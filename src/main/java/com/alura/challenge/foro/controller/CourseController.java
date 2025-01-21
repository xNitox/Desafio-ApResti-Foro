package com.alura.challenge.foro.controller;

import com.alura.challenge.foro.model.course.Course;
import com.alura.challenge.foro.model.course.dto.DtoUpdateCourse;
import com.alura.challenge.foro.service.CourseService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/courses")
@SecurityRequirement(name = "bearer-key")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<Page<Course>> findAllCourses(@PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok(courseService.showAllCourses(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findCourseById(@PathVariable Long id){
        var optionalCourse = courseService.showCourseById(id);
        if (optionalCourse.isPresent()){
            var course = optionalCourse.get();
            return ResponseEntity.ok(course);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        courseService.createCourse(course);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(course.getId())
                .toUri();
        return ResponseEntity.created(uri).body(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCourse(@PathVariable Long id, @RequestBody DtoUpdateCourse data){
        var optionalCourse = courseService.showCourseById(id);
        if(optionalCourse.isPresent()){
            var course = optionalCourse.get();
            courseService.updateCourse(course, data);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id){
        var optionalCourse = courseService.showCourseById(id);
        if(optionalCourse.isPresent()){
            var course = optionalCourse.get();
            courseService.deleteCourse(course.getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
