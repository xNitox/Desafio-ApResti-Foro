package com.alura.challenge.foro.service;

import com.alura.challenge.foro.model.user.User;
import com.alura.challenge.foro.model.user.dto.DtoUpdateProfile;
import com.alura.challenge.foro.model.user.dto.DtoUpdateUser;
import com.alura.challenge.foro.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> findAllUsers(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public void updateUser(User user, DtoUpdateUser data){
        if(data.username() != null) user.setUsername(data.username());
        if(data.email() != null) user.setEmail(data.email());
        if(data.password() != null) user.setPassword(data.password());
        userRepository.save(user);
    }

    public void addProfile(User user, DtoUpdateProfile data){
        if(data.profile() != null) user.setProfiles(data.profile());
        userRepository.save(user);
    }

    public void revokeProfile(User user, DtoUpdateProfile data){
        if(data.profile() != null) user.revokeProfile(data.profile());
        userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
