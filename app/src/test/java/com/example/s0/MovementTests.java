package com.example.s0;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.Context;
import android.os.Looper;

import java.lang.reflect.Field;

import frogger.Player;

public class MovementTests {
    private Player player = mock(Player.class, CALLS_REAL_METHODS);
    private int squareSize;
    private int screenWidth;
    private int screenHeight;

    @Before
    public void setup() {
        squareSize = 20;
        setField(player, "squareSize", squareSize);
        
        screenWidth = 100;
        screenHeight = 200;
    }

    // Ashwini: testing that you move up
    @Test
    public void testMoveUp(){
        when(player.getY()).thenReturn(10.0f);
        player.movePlayerTest("moveUp", squareSize, screenWidth, screenHeight);
        assertTrue((boolean) getField(player, "isMoveUp"));
    }

    // Ashwini: testing that you move left
    @Test
    public void testMoveLeft(){
        when(player.getX()).thenReturn(0.0f);
        player.movePlayerTest("moveLeft", squareSize, screenWidth, screenHeight);
        assertTrue((boolean) getField(player, "isMoveLeft"));
    }

    // Madison: testing that you move right
    @Test
    public void testMoveRight(){
        when(player.getX()).thenReturn((float) (100 - (100 / 2) - (20) - 1));
        player.movePlayerTest("moveRight", squareSize, screenWidth, screenHeight);
        assertTrue((boolean) getField(player, "isMoveRight"));
    }

    // Madison: testing that you move down
    @Test
    public void testMoveDown(){
        when(player.getY()).thenReturn((float) screenHeight - 2 * squareSize - 1);
        player.movePlayerTest("moveDown", squareSize, screenWidth, screenHeight);
        assertTrue((boolean) getField(player, "isMoveDown"));
    }

    // Nikki: testing if you can't move upwards offscreen
    @Test
    public void testMoveUpOff(){
        when(player.getY()).thenReturn(-10.0f);
        player.movePlayerTest("moveUp", squareSize, screenWidth, screenHeight);
        assertFalse((boolean) getField(player, "isMoveUp"));
    }

    // Nikki: testing if you can't move offscreen to the left
    @Test
    public void testMoveLeftOff(){
        when(player.getX()).thenReturn((float) -(squareSize / 2) - 1);
        player.movePlayerTest("moveLeft", squareSize, screenWidth, screenHeight);
        assertFalse((boolean) getField(player, "isMoveLeft"));
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
}
