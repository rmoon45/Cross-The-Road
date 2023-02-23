package com.example.s0;

import org.junit.Test;

import static org.junit.Assert.*;

<<<<<<< HEAD
=======
import android.view.View;
import android.os.Bundle;

import frogger.ConfigScreen;
>>>>>>> main
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
    ConfigScreen configScreen = new ConfigScreen();
    GameScreen gameScreen = new GameScreen();

    @Test
    public void testValidName() {
        String name = "Aniyah";
        user.setName(name);
        String gameName = gameScreen.getNameView().getText().toString();
        assertEquals(gameName, name);
    }

}
