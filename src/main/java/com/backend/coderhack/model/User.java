package com.backend.coderhack.model;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String userName;
    private Integer score;
    private String userId;
    private Set<String> badgeSet;
    
    public User(String userName, String userId){
        this.userName = userName;
        this.userId = userId;
        
        //Default values to initialize with
        score = 0;
        badgeSet = new HashSet<>();
    }


    //getters
    public String getUsername(){
        return userName;
    }
    public Integer getScore(){
        return score;
    }
    public String getUserId(){
        return userId;
    }
    public Set<String> getBadgeSet(){
        return badgeSet;
    }


    //setters
    public void addScore(Integer score){
        if(score > 0 && this.score + score <= 100){
            this.score += score;
        }
    }

    public void deductScore(Integer score){
        if(score > 0 && this.score - score >= 0){
            this.score -= score;
        }
    }
}
