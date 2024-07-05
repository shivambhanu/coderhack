package com.backend.coderhack.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import com.backend.coderhack.exceptions.InvalidScoreException;

public class UserTest {

    private User testUser;

    @BeforeEach
    public void setUp(){
        testUser = new User("Aman", "1");
    }

    
    @Test
    @DisplayName("Testing setScore method for valid and invalid values")
    public void testSetScore(){
        testUser.setScore(61);
        Assertions.assertEquals(61, testUser.getScore());

        Assertions.assertThrows(InvalidScoreException.class, () -> testUser.setScore(-5));
    }



    @Test
    @DisplayName("Testing for score < 30")
    public void testUpdateBadgeForScoreLessThanThirty(){
        testUser.setScore(20);
        testUser.updateBadge();
        List<String> exptectedBadgeListOne = new ArrayList<>(Arrays.asList("Code Ninja"));
        Assertions.assertEquals(exptectedBadgeListOne, new ArrayList<>(testUser.getBadgeSet()));
    }



    @Test
    @DisplayName("Testing for score < 60")
    public void testUpdateBadgeForScoreLessThanSixty(){
        testUser.setScore(40);
        testUser.updateBadge();
        List<String> exptectedBadgeListOne = new ArrayList<>(Arrays.asList("Code Ninja", "Code Champ"));
        Assertions.assertEquals(exptectedBadgeListOne, new ArrayList<>(testUser.getBadgeSet()));
    }

    

    @Test
    @DisplayName("Testing for score <= 100")
    public void testUpdateBadgeForScoreLessThanHundred(){
        testUser.setScore(80);
        testUser.updateBadge();
        List<String> exptectedBadgeListOne = new ArrayList<>(Arrays.asList("Code Ninja", "Code Champ", "Code Master"));
        Assertions.assertEquals(exptectedBadgeListOne, new ArrayList<>(testUser.getBadgeSet()));
    }
}
