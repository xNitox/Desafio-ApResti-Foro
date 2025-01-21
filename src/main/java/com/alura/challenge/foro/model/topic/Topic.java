package com.alura.challenge.foro.model.topic;

import com.alura.challenge.foro.model.answer.Answer;
import com.alura.challenge.foro.model.course.Course;
import com.alura.challenge.foro.model.course.dto.DtoCreateTopicCourse;
import com.alura.challenge.foro.model.user.User;
import com.alura.challenge.foro.model.user.dto.DtoCreateTopicAuthor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "topics")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String title;
    @NotBlank
    @Column(unique = true)
    private String message;
    private LocalDateTime creationDate = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.OPEN;
    @ManyToOne
    @JoinColumn(name = "author_id")
    @NotNull
    private User author;
    @ManyToOne
    @JoinColumn(name = "course_id")
    @NotNull
    private Course course;
    @OneToMany(mappedBy = "topic", fetch = FetchType.EAGER)
    private List<Answer> answers = List.of();

    public Topic(){}

    public Topic(String title, String message, DtoCreateTopicAuthor author, DtoCreateTopicCourse course) {
        this.title = title;
        this.message = message;
        this.author = new User(author.id(), author.username());
        this.course = new Course(course.id(), course.name());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
