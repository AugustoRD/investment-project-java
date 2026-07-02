package com.augustord.agregadorinvestimentos.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.augustord.agregadorinvestimentos.controller.CreateUserDto;
import com.augustord.agregadorinvestimentos.model.User;
import com.augustord.agregadorinvestimentos.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto) {
        var user = new User(createUserDto.username(), createUserDto.email(), createUserDto.password());

        var userSaved = userRepository.save(user);

        return userSaved.getId();

    }
    
}
