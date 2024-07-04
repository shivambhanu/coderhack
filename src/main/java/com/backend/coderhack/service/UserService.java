package com.backend.coderhack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.coderhack.dto.PostUserRequestDto;
import com.backend.coderhack.model.User;
import com.backend.coderhack.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(String userId){
        Optional<User> user = userRepository.findById(userId);
        return user.get();
    }

    
    public User createUser(PostUserRequestDto postUserRequestDto){
        User newUser = new User(postUserRequestDto.getUserName(), postUserRequestDto.getUserId());
        User savedUser = userRepository.save(newUser);
        return savedUser;
    }


    public boolean userAlreadyExists(String userId){
        return userRepository.existsById(userId);
    }
}
