package com.alura.challenge.foro.controller;

import com.alura.challenge.foro.model.user.User;
import com.alura.challenge.foro.model.user.dto.DtoUserAuthentication;
import com.alura.challenge.foro.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticacionController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticacionController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<String> authenticateUser(@RequestBody @Valid DtoUserAuthentication dtoUserAuthentication){
        Authentication authToken = new UsernamePasswordAuthenticationToken(dtoUserAuthentication.username(),
                dtoUserAuthentication.password());
        var userAuth = authenticationManager.authenticate(authToken);
        var JWTToken = tokenService.generateToken((User) userAuth.getPrincipal());
        return ResponseEntity.ok(JWTToken);
    }
}
