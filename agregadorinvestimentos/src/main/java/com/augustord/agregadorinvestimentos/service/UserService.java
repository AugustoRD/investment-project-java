package com.augustord.agregadorinvestimentos.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.augustord.agregadorinvestimentos.controller.CreateUserDto;
import com.augustord.agregadorinvestimentos.controller.UpdateUserDto;
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

    public Optional<User> getUserById(String userId) {

      return userRepository.findById(UUID.fromString(userId));
    }


    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void updateUser(String userId, UpdateUserDto updateUserDto) {

        var id = UUID.fromString(userId);

        var userEntity = userRepository.findById(id);

        if(userEntity.isPresent()){
            var user = userEntity.get();

            if(updateUserDto.username() != null){
                user.setUsername(updateUserDto.username());
            }

            if(updateUserDto.password() != null){
                user.setPassword(updateUserDto.password());
            }

            userRepository.save(user);
        }

    }
   

    public void deleteById(String userId) {

        var userExists = userRepository.existsById(UUID.fromString(userId));

        if(userExists){
            userRepository.deleteById(UUID.fromString(userId));
        }
        
    }
    
}
