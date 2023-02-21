package com.example.s0;

import org.junit.Test;

import static org.junit.Assert.*;

import android.view.View;
import android.os.Bundle;

import frogger.ConfigScreen;
import frogger.Game;
import frogger.GameScreen;
import frogger.Player;

public class UnitTests {
    Game game = new Game();
    Player user = new Player();
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
