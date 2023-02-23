package com.example.s0;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import android.os.Looper;
import android.view.View;

import frogger.ConfigScreen;
import frogger.Game;
import frogger.GameScreen;
import frogger.Player;

public class UnitTests {
    private Game game = null;
    private Player user = null;
    private GameScreen gameScreen = null;
    private ConfigScreen configScreen = null;

    private Looper looper;

    @Before
    public void setup() {
        game = new Game();
        user = new Player();
        gameScreen = new GameScreen();
        configScreen = new ConfigScreen();
    }

    //Nikki: Tests Invalid Name
    @Test
    public void testSpaceAsName() {
        user.setName(" ");
        assertTrue(configScreen.getInvalidName().getVisibility() == View.VISIBLE );
    }
    @Test
    public void testNullAsName() {
        user.setName(null);
        assertTrue(configScreen.getInvalidName().getVisibility() ==View.VISIBLE );
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

    // Game screen displays chosen character sprite
    @Test
    public void testCharacterSpriteDisplay() {

    }

}
