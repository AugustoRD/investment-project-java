package com.augustord.agregadorinvestimentos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
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
        @DisplayName("Should create user successfully")
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

        @Test
        @DisplayName("Should throw exception when error occurs")
        void shouldThrowExceptionWhenErrorOccurs(){
            //arrange 
            doThrow(new RuntimeException()).when(userRepository).save(any());

            var inputUser = new CreateUserDto("username", "email@example.com", "123");

            //act and assert
            assertThrows(RuntimeException.class, () -> userService.createUser(inputUser));
            
        }
    }

    @Nested
    class getUserById{

        @Test
        @DisplayName("Should get user by id successfully when user exists")
        void shouldGetUserByIdSuccessfully(){
            //arrange
            var user = new User(UUID.randomUUID(), "username", "email@example.com", "password", Instant.now(), null);
            doReturn(Optional.of(user)).when(userRepository).findById(uuidArgumentCaptor.capture());

            //act
            var outputUser = userService.getUserById(user.getId().toString());

            //assert
            assertTrue(outputUser.isPresent());
            assertEquals(user.getId(), uuidArgumentCaptor.getValue());
        } 

        @Test
        @DisplayName("Should return empty when user does not exist")
        void shouldReturnEmptyWhenUserDoesNotExist(){
            var userid = UUID.randomUUID();
            doReturn(Optional.empty()).when(userRepository).findById(uuidArgumentCaptor.capture());

            //act
            var outputUser = userService.getUserById(userid.toString());

            //assert
            assertFalse(outputUser.isPresent());
            assertEquals(userid, uuidArgumentCaptor.getValue());

    }
}

    @Nested
    class listUsers{

        @Test
        @DisplayName("Should list users successfully")
        void shouldListUsersSuccessfully(){
            //arrange
            var user = new User(UUID.randomUUID(), "username", "email@example.com", "password", Instant.now(), null);

            var userList = List.of(user);
            doReturn(userList)
                    .when(userRepository)
                    .findAll();

            //act
            var outputUsers = userService.listUsers();

            //assert
            assertNotNull(outputUsers);
            assertEquals(userList.size(), outputUsers.size());
            assertEquals(user.getId(), outputUsers.get(0).getId());

        }
    }
}
