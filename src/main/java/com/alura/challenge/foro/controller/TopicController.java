package com.alura.challenge.foro.controller;

import com.alura.challenge.foro.model.topic.Topic;
import com.alura.challenge.foro.model.topic.dto.DtoCreateTopic;
import com.alura.challenge.foro.model.topic.dto.DtoShowTopic;
import com.alura.challenge.foro.model.topic.dto.DtoUpdateTopic;
import com.alura.challenge.foro.service.TopicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearer-key")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<DtoShowTopic>> showAllTopics(@PageableDefault(value = 10,
            sort = "creationDate", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(topicService.showAllTopics(pageable).map(DtoShowTopic::new));
    }

    @GetMapping
    public ResponseEntity<Page<DtoShowTopic>> showOpenTopics(@PageableDefault(value = 10,
            sort = "creationDate", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.ok(topicService.showOpenTopics(pageable).map(DtoShowTopic::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoShowTopic> showTopicById(@PathVariable Long id){
        var optionalTopic = topicService.showTopicById(id);
        if(optionalTopic.isPresent()){
            var topic = optionalTopic.get();
            return ResponseEntity.ok(new DtoShowTopic(topic));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<DtoShowTopic> createTopic(@RequestBody @Valid DtoCreateTopic data){
        var topic = topicService.saveTopic(new Topic(data.title(), data.message(), data.author(), data.course()));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(topic.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new DtoShowTopic(topic));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTopic(@PathVariable Long id, @RequestBody @Valid DtoUpdateTopic data){
        var optionalTopic = topicService.showTopicById(id);
        if(optionalTopic.isPresent()){
            var topic = optionalTopic.get();
            topicService.updateTopic(topic, data);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/close")
    public ResponseEntity<Void> closeTopic(@PathVariable Long id){
        var optionalTopic = topicService.showTopicById(id);
        if(optionalTopic.isPresent()){
            var topic = optionalTopic.get();
            topicService.closeTopic(topic.getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id){
        var optionalTopic = topicService.showTopicById(id);
        if(optionalTopic.isPresent()){
            var topic = optionalTopic.get();
            topicService.deleteTopic(topic.getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
