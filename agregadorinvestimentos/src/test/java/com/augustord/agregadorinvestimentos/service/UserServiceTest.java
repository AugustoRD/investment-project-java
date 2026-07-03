package com.augustord.agregadorinvestimentos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.augustord.agregadorinvestimentos.controller.CreateUserDto;
import com.augustord.agregadorinvestimentos.model.User;
import com.augustord.agregadorinvestimentos.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Captor
    private ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Nested
    class createUser {
        
        @Test
        void shouldCreateUserSuccessfully() {

            //Arrange
            var user = new User(UUID.randomUUID(), "username", "email@example.com", "password", Instant.now(), null);

            doReturn(user).when(userRepository).save(userArgumentCaptor.capture());

            var inputUser = new CreateUserDto("username", "email@example.com", "password");

            //Act
            var outputUser = userService.createUser(inputUser);

            //Assert

            assertNotNull(outputUser);

            var capturedUser = userArgumentCaptor.getValue();

            assertEquals(inputUser.username(), capturedUser.getUsername());
            assertEquals(inputUser.email(), capturedUser.getEmail());
            assertEquals(inputUser.password(), capturedUser.getPassword());
           
        }

    }
    
}
