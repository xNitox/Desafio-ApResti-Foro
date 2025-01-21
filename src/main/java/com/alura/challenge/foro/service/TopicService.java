package com.alura.challenge.foro.service;

import com.alura.challenge.foro.model.topic.StatusEnum;
import com.alura.challenge.foro.model.topic.Topic;
import com.alura.challenge.foro.model.topic.dto.DtoUpdateTopic;
import com.alura.challenge.foro.repository.TopicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Page<Topic> showAllTopics(Pageable pageable){
        return topicRepository.findAll(pageable);
    }

    public Page<Topic> showOpenTopics(Pageable pageable){
        return topicRepository.findOpenTopics(pageable);
    }

    public Optional<Topic> showTopicById(Long id){
        return topicRepository.findById(id);
    }

    public Topic saveTopic(Topic topic){
        return topicRepository.save(topic);
    }

    public void updateTopic(Topic topic, DtoUpdateTopic data){
        if(data.title() != null) topic.setTitle(data.title());
        if(data.message() != null) topic.setMessage(data.message());
        if(data.course() != null) topic.setCourse(data.course());
        topicRepository.save(topic);
    }

    public void deleteTopic(Long id){
        topicRepository.deleteById(id);
    }

    public void closeTopic(Long id){
        var topic = topicRepository.getReferenceById(id);
        topic.setStatus(StatusEnum.CLOSE);
        topicRepository.save(topic);
    }
}
