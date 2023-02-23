package com.example.s0;
import org.junit.Test;

import static org.junit.Assert.*;

import frogger.ConfigScreen;
import frogger.Game;
import frogger.GameScreen;
import frogger.Player;

import android.graphics.Color;
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


}
