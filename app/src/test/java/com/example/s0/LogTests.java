package com.example.s0;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import frogger.Log;
import frogger.Player;

public class LogTests {

    private Log log = mock(Log.class, CALLS_REAL_METHODS);
    private Player player = mock(Player.class);

    @Before
    public void setup() {
        setField(log, "SLOWSPEED", 10);
        setField(log, "FASTSPEED", 20);
        setField(log, "screenWidth", 50);
    }

    @Test
    public void testLogCanMoveAtMultipleMovementSpeeds() {
        doReturn(0.0f).when(log).getX();

        setField(log, "isFast", false);
        log.updateLogPositionAndMovePlayerIfNeeded(player);

        verify(log, times(1)).setX(10);

        setField(log, "isFast", true);
        log.updateLogPositionAndMovePlayerIfNeeded(player);

        verify(log, times(1)).setX(20);
    }

    @Test
    public void testPlayerOnLogMovesAtSameSpeedAsLog() {
        doReturn(true).when(log).isColliding(anyInt(), anyInt());
        doReturn(5.0f).when(player).getX();

        setField(log, "isFast", false);
        log.updateLogPositionAndMovePlayerIfNeeded(player);
        verify(player, times(1)).setX(5.0f + 10);

        setField(log, "isFast", true);
        log.updateLogPositionAndMovePlayerIfNeeded(player);
        verify(player, times(1)).setX(5.0f + 20);
    }

    @Test
    public void testLogDoesNotMovePlayerIfPlayerNotOnLog() {
        setField(log, "isFast", false);
        doReturn(false).when(log).isColliding(anyInt(), anyInt());
        doReturn(5.0f).when(player).getX();

        log.updateLogPositionAndMovePlayerIfNeeded(player);
        verify(player, times(0)).setX(anyFloat());
    }

    @Test
    public void testLogCorrectDetectsIfSomethingIsOnLog() {
        setField(log, "leftGridX", 3);
        setField(log, "rightGridX", 5);
        setField(log, "gridY", 7);

        assertTrue(log.isColliding(4, 7));
    }

    @Test
    public void testLogCorrectDetectsIfNothingIsOnLog() {
        setField(log, "leftGridX", 3);
        setField(log, "rightGridX", 5);
        setField(log, "gridY", 7);

        assertFalse(log.isColliding(10, 8));
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
