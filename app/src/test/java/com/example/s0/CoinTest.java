package com.example.s0;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

import android.view.KeyEvent;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import frogger.GameScreen;
import frogger.Log;
import frogger.Player;
import frogger.WinScreen;
import frogger.Coin;


public class CoinTest {

    private Player player = mock(Player.class, CALLS_REAL_METHODS);
    private GameScreen gamescreen = mock(GameScreen.class, CALLS_REAL_METHODS);

    private WinScreen winscreen = mock(WinScreen.class, CALLS_REAL_METHODS);
    private Coin coin = mock(Coin.class, CALLS_REAL_METHODS);

    @Before
    public void setup() {
        initializePlayerFields(player);
        initializeGameScreenFields(gamescreen, player);
        setField(coin, "screenWidth", 50);
    }

    //Nikki: grabbing coin increases lives
    @Test
    public void testLivesIncreaseFromCoin() {
        player.setX(10.0f);
        player.setY(10.0f);
        coin.setX(10.0f);
        coin.setY(10.0f);
        when(player.move((ArrayList<String>) any(), anyInt())).thenReturn(3);
        setField(gamescreen, "lives", 2);

        try {
            gamescreen.onKeyUp(KeyEvent.KEYCODE_W, mock(KeyEvent.class));
        } catch (Exception e) {
            System.out.println("harmless expected error");
        }

        assertEquals(3, coin.livesChange(2));
    }

    //Nikki: coin appear in different locations
    @Test
    public void testCoinLocations() {
        coin.setX(15.0f);
        coin.setY(30.0f);
        try {
            gamescreen.onKeyUp(KeyEvent.KEYCODE_W, mock(KeyEvent.class));
        } catch (Exception e) {
            System.out.println("harmless expected error");
        }
        assertNotEquals(coin.getX(), 20.0);
        assertNotEquals(coin.getY(), 10.0);

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
