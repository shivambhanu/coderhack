package com.backend.coderhack.model;

import java.util.HashSet;
import java.util.Set;

public class User implements Comparable<User> {
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
    public void setScore(Integer newScore){
        if(newScore > 0 && newScore <= 100){
            score = newScore;
        }
    }


    @Override
    public int compareTo(User otherUser){
        if(this.score < otherUser.score)
            return 1;
        else if(this.score > otherUser.getScore())
            return -1;
        else 
            return 0;
    }
}
