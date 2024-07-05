package com.backend.coderhack.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    @DisplayName("Testing to update user score and badge between 1 and 30")
    public void testUpdateUserScoreAndBadgeUnderThirty(){
        User userOne = new User("Aman", "1");
        when(userRepository.findById("1")).thenReturn(Optional.ofNullable(userOne));

        Assertions.assertTrue(userService.updateUserScore(userOne.getUserId(), 20));
        Assertions.assertEquals(new ArrayList<>(Arrays.asList("Code Ninja")), new ArrayList<>(userOne.getBadgeSet()));
        
        verify(userRepository, times(1)).findById(any(String.class));
        verify(userRepository, times(1)).save(any(User.class));
    }


    @Test
    @DisplayName("Testing to update user score and badge between 30 and 60")
    public void testUpdateUserScoreAndBadgeUnderSixty(){
        User userOne = new User("Aman", "1");
        when(userRepository.findById("1")).thenReturn(Optional.ofNullable(userOne));

        Assertions.assertTrue(userService.updateUserScore(userOne.getUserId(), 40));
        Assertions.assertEquals(new ArrayList<>(Arrays.asList("Code Ninja", "Code Champ")), new ArrayList<>(userOne.getBadgeSet()));
        
        verify(userRepository, times(1)).findById(any(String.class));
        verify(userRepository, times(1)).save(any(User.class));
    }


    @Test
    @DisplayName("Testing to update user score and badge between 60 and 100")
    public void testUpdateUserScoreAndBadgeUnderHundred(){
        User userOne = new User("Aman", "1");
        when(userRepository.findById("1")).thenReturn(Optional.ofNullable(userOne));

        Assertions.assertTrue(userService.updateUserScore(userOne.getUserId(), 90));
        Assertions.assertEquals(new ArrayList<>(Arrays.asList("Code Ninja", "Code Champ", "Code Master")), new ArrayList<>(userOne.getBadgeSet()));

        verify(userRepository, times(1)).findById(any(String.class));
        verify(userRepository, times(1)).save(any(User.class));
    }


    @Test
    @DisplayName("Testing to delete a specific user")
    public void testDeleteUser(){
        User userOne = new User("Aman", "1");
        when(userRepository.findById("1")).thenReturn(Optional.ofNullable(userOne));

        Assertions.assertTrue(userService.deleteUser(userOne.getUserId()));

        verify(userRepository, times(1)).findById(any(String.class));
        verify(userRepository, times(1)).delete(any(User.class));
    }


    @Test
    @DisplayName("Testing whether user already exists")
    public void testUserAlreadyExistsOrNot(){
        User userOne = new User("Aman", "1");
        when(userRepository.existsById("1")).thenReturn(true);

        Assertions.assertTrue(userService.userAlreadyExists(userOne.getUserId()));

        verify(userRepository, times(1)).existsById(any(String.class));
    }
}
