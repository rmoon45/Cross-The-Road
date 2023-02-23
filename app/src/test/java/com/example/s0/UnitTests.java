package com.example.s0;

import org.junit.Test;

import static org.junit.Assert.*;

import android.graphics.Color;
import android.view.View;

import frogger.ConfigScreen;
import frogger.Game;
import frogger.GameScreen;
import frogger.Player;

public class UnitTests {
    Game game = new Game();
    Player user = new Player();
    GameScreen gameScreen = new GameScreen();
    ConfigScreen configScreen=new ConfigScreen();
    //Nikki: Tests Invalid Name
    @Test
    public void testSpaceAsName() {
        user.setName(" ");
        assertTrue(configScreen.getInvalidName().getVisibility()== View.VISIBLE );

    }
    public void testnullAsName() {
        user.setName(null);
        assertTrue(configScreen.getInvalidName().getVisibility()==View.VISIBLE );
    }
    //Nikki: checking button background changing depending on difficulty
    public void checkEasyBackground(){
        configScreen.getEasy().performClick();
        assertEquals(configScreen.getEasy().getBackground(), Color.BLUE);
        assertEquals(configScreen.getMedium().getBackground(), -7829368);
        assertEquals(configScreen.getHard().getBackground(), -7829368);
    }
    public void checkMediumBackground(){
        configScreen.getMedium().performClick();
        assertEquals(configScreen.getMedium().getBackground(), Color.BLUE);
        assertEquals(configScreen.getEasy().getBackground(), -7829368);
        assertEquals(configScreen.getHard().getBackground(), -7829368);
    }
    public void checkHardBackground(){
        configScreen.getHard().performClick();
        assertEquals(configScreen.getHard().getBackground(), Color.BLUE);
        assertEquals(configScreen.getEasy().getBackground(), -7829368);
        assertEquals(configScreen.getMedium().getBackground(), -7829368);
    }

    //Ashwini: game screen displays valid name input
    @Test
    public void testValidName() {
        user.setName("valid name");
        assertEquals(gameScreen.getNameView().getText().toString(), "valid name");
    }

    //Ashwini: game screen displays chosen difficulty
    @Test
    public void testDifficultyShown() {
        game.setDifficulty("medium");
        assertEquals(gameScreen.getDifficultyView().getText().toString(), "medium");
    }

    //Esther: Selecting a certain difficulty changes the number of lives the player has
    @Test
    public void testDifficultyLives() {
        game.setDifficulty("easy");
        assertEquals(game.getLives(), 7);
        game.setDifficulty("medium");
        assertEquals(game.getLives(), 3);
        game.setDifficulty("hard");
        assertEquals(game.getLives(), 1);
    }

    // Game screen displays chosen character sprite
    @Test
    public void testCharacterSpriteDisplay() {

    }

}
