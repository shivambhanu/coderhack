package com.backend.coderhack.model;

import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
public class User implements Comparable<User> {
    
    @Id
    private String userId;

    private String userName;
    private Integer score;
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

    public void updateBadge(){
        badgeSet.clear();

        if(score >= 1 && score < 30)
            badgeSet.add("Code Ninja");
        if(score >= 30 && score < 60){
            badgeSet.add("Code Ninja");
            badgeSet.add("Code Champ");
        }
        if(score >= 60 && score <= 100){
            badgeSet.add("Code Ninja");
            badgeSet.add("Code Champ");
            badgeSet.add("Code Master");
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
