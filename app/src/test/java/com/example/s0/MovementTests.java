package com.example.s0;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.Context;
import android.os.Looper;

import frogger.Game;
import frogger.GameScreen;
import frogger.Player;

public class MovementTests {
    private Looper looper = null;
    private Player user = null;
    private int squareSize;
    private int screenWidth;
    private int screenHeight;

    @Before
    public void setup() {
        looper = mock(Looper.class);
        Context context = mock(Context.class);
        when(context.getMainLooper()).thenReturn(looper);
        user = new Player();
        squareSize = 20;
        screenWidth = 100;
        screenHeight = 200;
    }

    // Ashwini: testing that you move up
    @Test
    public void testMoveUp(){
        user.setPosY(10);
        user.movePlayerTest("moveUp", squareSize, screenWidth, screenHeight);
        assertTrue(user.isMoveUp());
    }

    // Ashwini: testing that you move left
    @Test
    public void testMoveLeft(){
        user.setPosX(0);
        user.movePlayerTest("moveLeft", squareSize, screenWidth, screenHeight);
        assertTrue(user.isMoveLeft());
    }

    // Madison: testing that you move right
    @Test
    public void testMoveRight(){
        user.setPosX((100 - (100 / 2) - (20) - 1));
        user.movePlayerTest("moveRight", squareSize, screenWidth, screenHeight);
        assertTrue(user.isMoveRight());
    }

    // Madison: testing that you move down
    @Test
    public void testMoveDown(){
        user.setPosY(screenHeight - 2 * squareSize - 1);
        user.movePlayerTest("moveDown", squareSize, screenWidth, screenHeight);
        assertTrue(user.isMoveDown());
    }

    // Nikki: testing if you can't move upwards offscreen
    @Test
    public void testMoveUpOff(){
        user.setPosY(-10);
        user.movePlayerTest("moveUp", squareSize, screenWidth, screenHeight);
        assertEquals(false, user.isMoveUp());
    }

    // Nikki: testing if you can't move offscreen to the left
    @Test
    public void testMoveLeftOff(){
        user.setPosX(-(squareSize / 2) - 1);
        user.movePlayerTest("moveLeft", squareSize, screenWidth, screenHeight);
        assertEquals(false, user.isMoveLeft());
    }
}
