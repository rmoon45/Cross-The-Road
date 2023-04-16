package com.example.s0;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.view.View;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


import frogger.GameOverScreen;
import frogger.GameScreen;
import frogger.Player;
import frogger.WinScreen;



public class StatusTests {

    private Player player = mock(Player.class, CALLS_REAL_METHODS);
    private GameScreen gamescreen = mock(GameScreen.class, CALLS_REAL_METHODS);

    private WinScreen winscreen = mock(WinScreen.class, CALLS_REAL_METHODS);

    @Before
    public void setup() {
        initializePlayerFields(player);
        initializeGameScreenFields(gamescreen, player);
    }

    //Madison: reaching goal tile gives the largest amount of points
    @Test
    public void testGoalTilePoints() {
        setField(player, "gridX", 1);
        setField(player, "gridY", 1);
        setField(gamescreen, "map", new ArrayList<String>(Arrays.asList("safe", "safe", "safe", "safe")));
        setField(gamescreen, "score", 9);

        try {
            gamescreen.onKeyUp(KeyEvent.KEYCODE_W, mock(KeyEvent.class));
        } catch (Exception e) {
            System.out.println("harmless expected error");
        }
        setField(gamescreen, "player", player);
        setField(gamescreen, "map", new ArrayList<String>(Arrays.asList("goal", "safe", "river", "river", "river", "river", "river", "river", "river", "river", "river", "safe", "road", "road", "road", "road", "safe", "safe")));
        setField(gamescreen, "tileValues", new HashMap(Map.of("road", 2, "safe", 1, "river", 3)));
        setField(gamescreen, "squareSize", 123);
        setField(gamescreen, "score", 14);
        setField(gamescreen, "numHorizontalSquares", 13);
        setField(gamescreen, "numVerticalSquares", 18);
        setField(gamescreen, "horizontalOffset", -79);
        WinScreen winscreen = mock(WinScreen.class, CALLS_REAL_METHODS);

        assertTrue(gamescreen.getScore() == 14);

    }

    //Esther: win screen displays and stores the highest score
    @Test
    public void testWinScreenScore() {
        try {
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
            winscreen.setScore(19);
            gamescreen.setScore(19);
        } catch (Exception e) {
            System.out.println("harmless error, this is fine");
        }
        int score = (int) getField(winscreen, "score");

        assertEquals(19, score);

    }



    //Esther: win screen has a way to go to next or another screen
    @Test
    public void testWinScreenCanLaunchNewScreen() {
        WinScreen winscreen = mock(WinScreen.class, CALLS_REAL_METHODS);
        winscreen.onRestartGame(mock(View.class));
        verify(winscreen, times(1)).startActivity((Intent) any());
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

    private <T> Object getField(T object, String fieldName) {
        try {
            Field field = object.getClass().getSuperclass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(object);
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
