package com.backend.coderhack.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.assertj.AssertableReactiveWebApplicationContext;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import com.backend.coderhack.repository.UserRepository;
import com.backend.coderhack.dto.PostUserRequestDto;
import com.backend.coderhack.model.User;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    
    @Test
    @DisplayName("Testing to get all users")
    public void testGetAllUsers(){
        User userOne = new User("Aman", "1");
        User userTwo = new User("Rakesh", "2");
        User userThree = new User("Robert", "3");
        when(userRepository.findAll()).thenReturn(new ArrayList<>(Arrays.asList(userOne, userTwo, userThree)));

        List<User> userList = userService.getAllUsers();

        Assertions.assertEquals(3, userList.size());
    }


    @Test
    @DisplayName("Testing to get a specified user")
    public void testGetUserById(){
        User testUser = new User("Abhay", "2");
        when(userRepository.findById("2")).thenReturn(Optional.ofNullable(testUser));

        User actualUser = userService.getUserById("2");

        Assertions.assertEquals(testUser, actualUser);
    }




    @Test
    @DisplayName("Testing to create user")
    public void testCreateUser(){
        PostUserRequestDto postUserRequestDto = new PostUserRequestDto("1", "Mohan");
        userService.createUser(postUserRequestDto);

        verify(userRepository, times(1)).save(any(User.class));
    }


    @Test
    @DisplayName("Testing to update user score")
    public void testUpdateUserScore(){
        User userOne = new User("Aman", "1");
        User userTwo = new User("Rakesh", "2");
        User userThree = new User("Robert", "3");

        userService.updateUserScore(userOne.getUserId(), 20);
        userService.updateUserScore(userTwo.getUserId(), 40);
        userService.updateUserScore(userThree.getUserId(), 70);

        verify(userRepository, times(1)).save(any(User.class));
    }


    
}
