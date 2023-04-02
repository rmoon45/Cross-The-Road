package com.example.s0;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.view.View;

import java.lang.reflect.Field;

import frogger.ConfigScreen;

public class GameTests {
    private ConfigScreen configScreen = mock(ConfigScreen.class, CALLS_REAL_METHODS);

    @Before
    public void setup() {
        doCallRealMethod().when(configScreen).onEasySelected((View) any());
        doCallRealMethod().when(configScreen).onMediumSelected((View) any());
        doCallRealMethod().when(configScreen).onHardSelected((View) any());
    }

    // Nicole: Selecting a certain difficulty changes the number of lives the player has
    @Test
    public void testDifficultyLives() {
        try {
            configScreen.onEasySelected(mock(View.class));
        } catch (Exception e) {
            System.out.println("harmless error, this is fine");
        }
        int easyLives = (int) getField(configScreen, "lives");

        try {
            configScreen.onMediumSelected(mock(View.class));
        } catch (Exception e) {
            System.out.println("harmless error, this is fine");
        }
        int mediumLives = (int) getField(configScreen, "lives");

        try {
            configScreen.onHardSelected(mock(View.class));
        } catch (Exception e) {
            System.out.println("harmless error, this is fine");
        }
        int hardLives = (int) getField(configScreen, "lives");

        assertFalse(easyLives == mediumLives || mediumLives == hardLives || easyLives == hardLives);
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
