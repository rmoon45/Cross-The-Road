package com.example.s0;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;

import frogger.ConfigScreen;
import frogger.Game;
import frogger.GameScreen;
import frogger.Player;

//@RunWith(MockitoJUnitRunner::class)
public class UnitTests {
    private Game game ;
    private Player user;
    private GameScreen gameScreen;
    private ConfigScreen configScreen;

    private Looper looper = null;
    private Context context;

    @Before
    public void setup() {
        looper = mock(Looper.class);
        context = mock(Context.class);
        when(context.getMainLooper()).thenReturn(looper);

        game = mock(Game.class);
        user = mock(Player.class);
        gameScreen = mock(GameScreen.class);
        configScreen = mock(ConfigScreen.class);
    }

    //Ashwini: game screen displays valid name input
    @Test
    public void testValidName() {
        user.setName("valid name");
        //gameScreen.create();
        when(gameScreen.getNameView().getText().toString()).thenCallRealMethod();
        assertEquals("valid name", gameScreen.getNameView().getText().toString());
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
