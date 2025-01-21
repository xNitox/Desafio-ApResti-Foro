package com.alura.challenge.foro.model.user.dto;

import com.alura.challenge.foro.model.user.User;

public record DtoCreateTopicAuthor(
    Long id,
    String username
) {
    public DtoCreateTopicAuthor(User user){
        this(user.getId(), user.getUsername());
    }
}
