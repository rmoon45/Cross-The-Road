package com.example.s0;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.Context;
import android.os.Looper;

import frogger.Game;
import frogger.Player;

public class MovementTests {
    private Game game = null;
    private Looper looper = null;
    private Player user = null;

    @Before
    public void setup() {
        looper = mock(Looper.class);
        Context context = mock(Context.class);
        when(context.getMainLooper()).thenReturn(looper);
        user = new Player();
        game = new Game();
    }

    // Ashwini: testing that you move up
    @Test
    public void testMoveUp(){
        user.setPosY(10);
        user.movePlayerTest("moveUp", game);
        assertTrue(user.isMoveUp());
    }

    // Ashwini: testing that you move left
    @Test
    public void testMoveLeft(){
        user.setPosX(0);
        user.movePlayerTest("moveLeft", game);
        assertTrue(user.isMoveLeft());
    }

    // Madison: testing that you move right
    @Test
    public void testMoveRight(){
        user.setPosX((game.getScreenWidth() - (game.getSquareSize() / 2) - (game.getSquareSize()) - 1));
        user.movePlayerTest("moveRight", game);
        assertTrue(user.isMoveRight());
    }

    // Madison: testing that you move down
    @Test
    public void testMoveDown(){
        user.setPosY(game.getScreenHeight() - 2 * game.getSquareSize() - 1);
        user.movePlayerTest("moveDown", game);
        assertTrue(user.isMoveDown());
    }

    // Nikki: testing if you can't move upwards offscreen
    @Test
    public void testMoveUpOff(){
        user.setPosY(-10);
        user.movePlayerTest("moveUp", game);
        assertEquals(false, user.isMoveUp());
    }

    // Nikki: testing if you can't move offscreen to the left
    @Test
    public void testMoveLeftOff(){
        user.setPosX(-(game.getSquareSize() / 2) - 1);
        user.movePlayerTest("moveLeft", game);
        assertEquals(false, user.isMoveLeft());
    }


}
