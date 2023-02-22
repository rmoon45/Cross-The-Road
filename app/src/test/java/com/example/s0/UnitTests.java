package com.example.s0;

import org.junit.Test;

import static org.junit.Assert.*;

import frogger.Game;
import frogger.GameScreen;
import frogger.Player;

public class UnitTests {
    Game game = new Game();
    Player user = new Player();
    GameScreen gameScreen = new GameScreen();

    //Ashwini: test that inputting a valid name displays it on the game screen
    @Test
    public void testValidName() {
        user.setName("valid name");
        assertEquals(gameScreen.getNameView().getText().toString(), "valid name");
    }

    //Ashwini:

}
