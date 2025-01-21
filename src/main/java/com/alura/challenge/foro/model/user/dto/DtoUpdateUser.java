package com.alura.challenge.foro.model.user.dto;

public record DtoUpdateUser(
        String username,
        String email,
        String password
) {
}
