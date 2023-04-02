package com.example.s0;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.Context;
import android.os.Looper;
import android.view.KeyEvent;

import androidx.annotation.NonNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import frogger.GameScreen;
import frogger.Player;
import frogger.Player1;
import frogger.ScoreManager;
import frogger.Vehicle;
import frogger.Game;

public class LivesTest {
    private Looper looper = null;
    private Player1 player = null;

    private Player newPlayer = mock(Player.class, CALLS_REAL_METHODS);
    private Vehicle car = null;
    private GameScreen gamescreen = mock(GameScreen.class, CALLS_REAL_METHODS);
    private Game game = null;
    private ScoreManager scoreManager = null;

    @Before
    public void setup() {
        int squareSize = 112;

        setField(newPlayer, "squareSize", squareSize);
        setField(gamescreen, "player", newPlayer);

        int numVerticalSquares = 18;
        int numHorizontalSquares = 11;
        looper = mock(Looper.class);
        player = new Player1();
        car = new Vehicle("car1", numVerticalSquares, squareSize);
        game = new Game();
        scoreManager = new ScoreManager();

        //vehicle2 = new Vehicle("car2", numVerticalSquares, squareSize);
        //vehicle3 = new Vehicle("car3", numVerticalSquares, squareSize);
        //vehicle4 = new Vehicle("car4", numVerticalSquares, squareSize);


        Context context = mock(Context.class);
        when(context.getMainLooper()).thenReturn(looper);
    }

    @Test
    public void testCarCollision() {
        when(newPlayer.getX()).thenReturn(10.0f);
        when(newPlayer.getY()).thenReturn(10.0f);
        assertTrue(newPlayer.isColliding(10.0F, 10.0F, 10.0F, 10.0F));
    }

    @Test
    public void testCollisionLivesDecrease() {
        when(newPlayer.getX()).thenReturn(10.0f);
        when(newPlayer.getY()).thenReturn(10.0f);
        when(newPlayer.move((ArrayList<String>) any(), anyInt())).thenReturn(2);
        setField(gamescreen, "lives", 3);

        try {
            gamescreen.onKeyUp(KeyEvent.KEYCODE_W, mock(KeyEvent.class));
        } catch (Exception e) {
            System.out.println("yup that broken");
        }

        verify(gamescreen, times(1)).setLives(2);
    }

    @Test
    public void testCollisionScoreReset() {
        player.setPosX(10);
        player.setPosY(10);
        int score = 9;
        player.setScore(score);
        if(player.isColliding(10.0F, 10.0F, 10.0F, 10.0F)) {
            assertTrue(player.getScore() == 0);
        }
    }

    @Test
    public void testRiverScoreReset() {
        when(gamescreen.scoreResetTest(2)).thenCallRealMethod();
        assertEquals(true, gamescreen.scoreResetTest(2));
        //assertEquals(scoreManager.getScoreAfterMove(0, "river", true), 0);
    }

    @Test
    public void testRiverLivesDecrease() {
        if(player.isColliding(50.0F, 1472, 50.0F, 50.0F)) {
            assertTrue(player.getLives() == 2);
        }
    }
    @Test
    public void testRespawnHittingWaterAtStarting() {
        if (newPlayer.isColliding(50.0F, 1472, 50.0F, 50.0F)) {
            verify(newPlayer, times(1)).respawn();
        }
    }
    @Test
    public void testRespawnHittingCarAtStarting() {
        if (newPlayer.isColliding(10.0F, 10.0F, 10.0F, 10.0F)) {
            verify(newPlayer, times(1)).respawn();
        }
    }


    @Test
    public void testScoreDisplay() {

    }

    private <T> void setField(T object, String fieldName, T value) {
        try {
            Field field = object.getClass().getSuperclass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}