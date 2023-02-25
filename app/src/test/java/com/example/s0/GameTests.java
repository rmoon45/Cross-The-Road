package com.example.s0;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.Context;
import android.os.Looper;

import frogger.Game;

public class GameTests {
    private Game game = null;
    private Looper looper = null;

    @Before
    public void setup() {
        looper = mock(Looper.class);
        Context context = mock(Context.class);
        when(context.getMainLooper()).thenReturn(looper);
        game = new Game();
    }

    //Esther: Selecting a certain difficulty changes the number of lives the player has
    @Test
    public void testDifficultyLives() {
        game.setDifficulty("easy");
        int easyLives = game.getLives();

        game.setDifficulty("medium");
        int mediumLives = game.getLives();

        game.setDifficulty("hard");
        int hardLives = game.getLives();

        assertFalse(easyLives == mediumLives || mediumLives == hardLives || easyLives == hardLives);
    }
}
