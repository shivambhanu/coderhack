package com.backend.coderhack.service;

import java.util.Collections;
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
        List<User> userList = userRepository.findAll();
        Collections.sort(userList);

        return userList;
    }

    public User getUserById(String userId){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent())
            return user.get();
        else
            return null;
    }

    
    public void createUser(PostUserRequestDto postUserRequestDto){
        User newUser = new User(postUserRequestDto.getUserName(), postUserRequestDto.getUserId());
        userRepository.save(newUser);
    }


    public boolean updateUserScore(String userId, Integer newScore){
        User user = getUserById(userId);

        if(user != null){
            user.setScore(newScore);
            user.updateBadge();
            userRepository.save(user);
            return true;
        }
        return false;
    }


    public boolean deleteUser(String userId){
        User user = getUserById(userId);

        if(user != null){
            userRepository.delete(user);
            return true;
        }else{
            return false;
        }
    }


    public boolean userAlreadyExists(String userId){
        return userRepository.existsById(userId);
    }
}
