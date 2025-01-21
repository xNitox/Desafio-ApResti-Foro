package com.alura.challenge.foro.controller;

import com.alura.challenge.foro.model.answer.Answer;
import com.alura.challenge.foro.model.answer.dto.DtoShowAnswer;
import com.alura.challenge.foro.model.answer.dto.DtoUpdateAnswer;
import com.alura.challenge.foro.service.AnswerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/answers")
@SecurityRequirement(name = "bearer-key")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping
    public ResponseEntity<Page<DtoShowAnswer>> findAllAnswers(@PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok(answerService.findAllAnswers(pageable).map(DtoShowAnswer::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoShowAnswer> findAnswerById(@PathVariable Long id){
        var optionalAnswer = answerService.getAnswer(id);
        if(optionalAnswer.isPresent()){
            var answer = optionalAnswer.get();
            answerService.getAnswer(answer.getId());
            return ResponseEntity.ok(new DtoShowAnswer(answer));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<DtoShowAnswer> createAnswer(@RequestBody Answer answer){
        answerService.saveAnswer(answer);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(answer.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new DtoShowAnswer(answer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Answer> updateAnswer(@PathVariable Long id, @RequestBody DtoUpdateAnswer data){
        var optionalAnswer = answerService.getAnswer(id);
        if(optionalAnswer.isPresent()){
            var answer = optionalAnswer.get();
            answerService.updateAnswer(answer, data);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long id){
        var optionalAnswer = answerService.getAnswer(id);
        if(optionalAnswer.isPresent()){
            var answer = optionalAnswer.get();
            answerService.deleteAnswer(answer.getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
