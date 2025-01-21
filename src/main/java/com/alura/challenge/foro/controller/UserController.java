package com.alura.challenge.foro.controller;

import com.alura.challenge.foro.model.user.User;
import com.alura.challenge.foro.model.user.dto.DtoUpdateProfile;
import com.alura.challenge.foro.model.user.dto.DtoUpdateUser;
import com.alura.challenge.foro.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private  final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Page<User>> findAllUsers(@PageableDefault(size = 10) Pageable pageable){
        return ResponseEntity.ok(userService.findAllUsers(pageable));
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<User> findById(@PathVariable Long id){
        var optionalUser = userService.findUserById(id);
        if(optionalUser.isPresent()){
            var user = optionalUser.get();
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(uri).body(userService.createUser(user));
    }

    @PutMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody DtoUpdateUser data){
        var optionalUser = userService.findUserById(id);
        if(optionalUser.isPresent()){
            var user = optionalUser.get();
            userService.updateUser(user, data);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/profile/add/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Void> addProfile(@PathVariable Long id, @RequestBody DtoUpdateProfile data){
        var optionalUser = userService.findUserById(id);
        if(optionalUser.isPresent()){
            var user = optionalUser.get();
            userService.addProfile(user, data);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/profile/revoke/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Void> revokeProfile(@PathVariable Long id, @RequestBody DtoUpdateProfile data){
        var optionalUser = userService.findUserById(id);
        if(optionalUser.isPresent()){
            var user = optionalUser.get();
            userService.revokeProfile(user, data);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        var optionalUser = userService.findUserById(id);
        if(optionalUser.isPresent()){
            var user = optionalUser.get();
            userService.deleteUser(user.getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
