package com.example.s0;
import org.junit.Test;

import static org.junit.Assert.*;

import frogger.ConfigScreen;
import frogger.Game;
import frogger.GameScreen;
import frogger.Player;
import android.view.View;
public class NikkiTests {
    Game game = new Game();
    Player user = new Player();
    GameScreen gameScreen = new GameScreen();
    ConfigScreen configScreen=new ConfigScreen();
    //Nikki: Tests Invalid Name
    @Test
    public void testSpaceAsName() {
        user.setName(" ");
        assertTrue(configScreen.getInvalidName().getVisibility()==View.VISIBLE );

    }
    public void testnullAsName() {
        user.setName(null);
        assertTrue(configScreen.getInvalidName().getVisibility()==View.VISIBLE );
    }

}
