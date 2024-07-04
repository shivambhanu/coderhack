package com.backend.coderhack.controller;

import org.springframework.web.bind.annotation.RestController;

import com.backend.coderhack.dto.PostUserRequestDto;
import com.backend.coderhack.dto.UserScoreDto;
import com.backend.coderhack.model.User;
import com.backend.coderhack.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class CoderhackController {
    
    @Autowired
    private UserService userService;
    

    // GET /users - Retrieve a list of all registered users
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }


    // GET /users/{userId} - Retrieve the details of a specific user
    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        if(user != null)
            return ResponseEntity.ok().body(user);
        else
            return ResponseEntity.notFound().build();
    }
    
    
    // POST /users - Register a new user to the contest
    @PostMapping("/users")
    public ResponseEntity<String> postUser(@RequestBody PostUserRequestDto postUserRequestDto) {
        if(userService.userAlreadyExists(postUserRequestDto.getUserId())){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }else{
            userService.createUser(postUserRequestDto);
            return ResponseEntity.ok("User registered successfully!");
        }
    }


    // PUT /users/{userId} - Update the score of a specific user
    @PutMapping("users/{userId}")
    public ResponseEntity<String> putUserScore(@PathVariable String userId, @RequestBody UserScoreDto userScoreDto) {
        
        boolean isUpdated = userService.updateUserScore(userId, userScoreDto.getScore());
        if(isUpdated)
            return ResponseEntity.ok("User score updated successfully!");
        else
            return ResponseEntity.notFound().build();
    }



    // DELETE /users/{userId} - Deregister a specific user from the contest
    @DeleteMapping("users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId){
        boolean isDeleted = userService.deleteUser(userId);

        if(isDeleted){
            return ResponseEntity.ok("User deleted successfully!");
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    
}
