package com.example.s0;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import frogger.GameOverScreen;
import frogger.GameScreen;
import frogger.Player;

public class LivesTest {
    private Player player = mock(Player.class, CALLS_REAL_METHODS);
    private GameScreen gamescreen = mock(GameScreen.class, CALLS_REAL_METHODS);

    @Before
    public void setup() {
        initializePlayerFields(player);
        initializeGameScreenFields(gamescreen, player);
    }

    // Madison: when colliding with a car, Player properly idenfifies that it is colliding with a car
    @Test
    public void testCarCollision() {
        player.setX(10.0f);
        player.setY(10.0f);
        assertTrue(player.isColliding(10.0F, 10.0F, 10.0F, 10.0F));
    }

    // Madison: upon collision, GameScreen decreases lives
    @Test
    public void testCollisionLivesDecrease() {
        player.setX(10.0f);
        player.setY(10.0f);
        when(player.move((ArrayList<String>) any(), anyInt())).thenReturn(2);
        setField(gamescreen, "lives", 3);

        try {
            gamescreen.onKeyUp(KeyEvent.KEYCODE_W, mock(KeyEvent.class));
        } catch (Exception e) {
            System.out.println("harmless expected error");
        }

        verify(gamescreen, times(1)).setLives(2);
    }

    // Nikki: upon collision, GameScreen sets score to 0
    @Test
    public void testCollisionScoreReset() {
        gamescreen = mock(GameScreen.class);

        when(gamescreen.onKeyUp(anyInt(), (KeyEvent) any())).thenCallRealMethod();
        doNothing().when(gamescreen).setLives(anyInt());

        initializeGameScreenFields(gamescreen, player);
        player.setX(10.0f);
        player.setY(10.0f);
        when(player.move((ArrayList<String>) any(), anyInt())).thenReturn(2);
        setField(gamescreen, "lives", 3);
        setField(gamescreen, "score", 9);

        try {
            gamescreen.onKeyUp(KeyEvent.KEYCODE_W, mock(KeyEvent.class));
        } catch (Exception e) {
            System.out.println("harmless expected error");
        }

        assertEquals(0, gamescreen.getScore());
    }

    // Nikki: correctly identify when score should be reset / when it should not be reset
    @Test
    public void testRiverScoreReset() {
        when(gamescreen.scoreResetTest(2)).thenCallRealMethod();
        assertEquals(true, gamescreen.scoreResetTest(2));
    }

    // Ashwini: when Player moves into a river, decrease lives
    @Test
    public void testRiverLivesDecrease() {
        setField(player, "gridX", 1);
        setField(player, "gridY", 1);
        setField(gamescreen, "map", new ArrayList<String>(Arrays.asList("river", "river", "river", "river")));

        try {
            gamescreen.onKeyUp(KeyEvent.KEYCODE_W, mock(KeyEvent.class));
        } catch (Exception e) {
            System.out.println("harmless expected error");
        }

        verify(gamescreen, times(1)).setLives(2);
    }

    // Ashwini: when Player collides with water, Player respawns to the beginning
    @Test
    public void testRespawnHittingWaterAtStarting() {
        if (player.isColliding(50.0F, 1472, 50.0F, 50.0F)) {
            verify(player, times(1)).respawn();
        }
    }

    // Esther: when Player collides with car, Player respawns to the beginning
    @Test
    public void testRespawnHittingCarAtStarting() {
        if (player.isColliding(10.0F, 10.0F, 10.0F, 10.0F)) {
            verify(player, times(1)).respawn();
        }
    }

    // Esther: when Player does not collide with a river, do not decrease lives
    @Test
    public void testNotRiverNoLivesChange() {
        setField(player, "gridX", 1);
        setField(player, "gridY", 1);
        setField(gamescreen, "map", new ArrayList<String>(Arrays.asList("safe", "safe", "safe", "safe")));

        try {
            gamescreen.onKeyUp(KeyEvent.KEYCODE_W, mock(KeyEvent.class));
        } catch (Exception e) {
            System.out.println("harmless expected error");
        }

        verify(gamescreen, times(0)).setLives(anyInt());
    }

    // Nicole: when Player does not collide with a river, do not decrease score
    @Test
    public void testNotRiverNoScoreReset() {
        setField(player, "gridX", 1);
        setField(player, "gridY", 1);
        setField(gamescreen, "map", new ArrayList<String>(Arrays.asList("safe", "safe", "safe", "safe")));
        setField(gamescreen, "score", 9);

        try {
            gamescreen.onKeyUp(KeyEvent.KEYCODE_W, mock(KeyEvent.class));
        } catch (Exception e) {
            System.out.println("harmless expected error");
        }

        assertTrue(gamescreen.getScore() >= 9);
    }

    // Nicole: game over screen has a way to go to a new screen
    @Test
    public void testGameOverScreenCanLaunchNewScreen() {
        GameOverScreen gameoverscreen = mock(GameOverScreen.class, CALLS_REAL_METHODS);
        gameoverscreen.onRestartGame(mock(View.class));
        verify(gameoverscreen, times(1)).startActivity((Intent) any());
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

    private void initializeGameScreenFields(GameScreen gamescreen, Player player) {
        setField(gamescreen, "player", player);
        setField(gamescreen, "map", new ArrayList<String>(Arrays.asList("goal", "safe", "river", "river", "river", "river", "river", "river", "river", "river", "river", "safe", "road", "road", "road", "road", "safe", "safe")));
        setField(gamescreen, "tileValues", new HashMap(Map.of("road", 2, "safe", 1, "river", 3)));
        setField(gamescreen, "squareSize", 123);
        setField(gamescreen, "numHorizontalSquares", 13);
        setField(gamescreen, "numVerticalSquares", 18);
        setField(gamescreen, "horizontalOffset", -79);
        setField(gamescreen, "score", 0);
        setField(gamescreen, "lives", 3);
        setField(gamescreen, "screenWidth", 1440);
        setField(gamescreen, "currPos", 0);
        setField(gamescreen, "greatestPos", 0);
    }

    private void initializePlayerFields(Player player) {
        setField(player, "squareSize", 123);
        setField(player, "numHorizontalSquares", 13);
        setField(player, "numVerticalSquares", 18);
        setField(player, "horizontalOffset", -79);
        setField(player, "gridX", 6);
        setField(player, "gridY", 16);
        setField(player, "spawnX", 6);
        setField(player, "spawnY", 16);
        setField(player, "furthestReached", 16);
        setField(player, "movingEnabled", true);
    }
}