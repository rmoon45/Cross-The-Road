package com.example.s0;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;

import frogger.Log;
import frogger.Player;

public class LogTests {

    private Log log = mock(Log.class, CALLS_REAL_METHODS);
    private Player player = mock(Player.class);

    @Before
    public void setup() {
        setField(log, "screenWidth", 50);
    }

    @Test
    /*
    Note: this test pulls ten random speeds and asserts that there are at least two different speeds.
    This test could fail by random chance. Try running it again if it fails.
     */
    public void testLogCanMoveAtMultipleMovementSpeeds() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HashSet<Integer> speeds = new HashSet<Integer>();

        Method indexOfMethod = Log.class.getDeclaredMethod("generateSpeed");
        indexOfMethod.setAccessible(true);

        for (int i = 0; i < 10; i++) {
            indexOfMethod.invoke(log);
            speeds.add((Integer) getField(log, "speed"));
        }

        assertTrue(speeds.size() >= 2);
    }

    @Test
    public void testPlayerOnLogMovesWithTheLog() {
        doReturn(0).when(log).collisionLocationAbsoluteCoords(anyInt(), anyInt());
        doReturn(true).when(log).isColliding(anyInt(), anyInt());
        doReturn(0).when(log).calculateClosestGridX();

        doReturn(5.0f).when(log).getX();
        log.updateLogPositionAndMovePlayerIfNeeded(player);
        verify(player, times(1)).setX(5.0f);

        doReturn(10.0f).when(log).getX();
        log.updateLogPositionAndMovePlayerIfNeeded(player);
        verify(player, times(1)).setX(10.0f);
    }

    @Test
    public void testLogDoesNotMovePlayerIfPlayerNotOnLog() {
        doReturn(-1).when(log).collisionLocationAbsoluteCoords(anyInt(), anyInt());
        doReturn(false).when(log).isColliding(anyInt(), anyInt());
        doReturn(0).when(log).calculateClosestGridX();

        doReturn(5.0f).when(log).getX();
        log.updateLogPositionAndMovePlayerIfNeeded(player);
        verify(player, times(0)).setX(anyFloat());
    }

    @Test
    public void testLogCorrectDetectsIfSomethingIsOnLog() {
        doReturn(3.0f * 10.0f).when(log).getX();
        setField(log, "leftGridX", 3);
        setField(log, "gridY", 7);
        setField(log, "squareSize", 10);

        assertTrue(log.isColliding(4 * 10, 7));
    }

    @Test
    public void testLogCorrectDetectsIfNothingIsOnLog() {
        doReturn(3.0f * 10.0f).when(log).getX();
        setField(log, "leftGridX", 3);
        setField(log, "gridY", 7);
        setField(log, "squareSize", 10);

        assertFalse(log.isColliding(9 * 10, 8));
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
