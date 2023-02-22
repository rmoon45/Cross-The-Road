package com.example.s0;

import org.junit.Test;

import static org.junit.Assert.*;

import frogger.ConfigScreen;
import frogger.Game;
import frogger.GameScreen;
import frogger.Player;

public class UnitTests {
    Game game = new Game();
    Player user = new Player();
    GameScreen gameScreen = new GameScreen();


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
