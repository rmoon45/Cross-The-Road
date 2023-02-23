package com.example.s0;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Looper;

import frogger.ConfigScreen;

public class ConfigScreenTests {
    private ConfigScreen configScreen = null;
    private Looper looper = null;

    @Before
    public void setup() {
        looper = mock(Looper.class);
        Context context = mock(Context.class);
        when(context.getMainLooper()).thenReturn(looper);
        configScreen = mock(ConfigScreen.class);
    }

    //Nikki: checking button background changing depending on difficulty
    @Test
    public void checkEasyBackground(){
        when(configScreen.getEasy().performClick()).thenCallRealMethod();
        configScreen.getEasy().performClick();

        when(configScreen.getEasy().getBackground()).thenCallRealMethod();
        assertEquals(Color.BLUE, configScreen.getEasy().getBackground());

        when(configScreen.getMedium().getBackground()).thenCallRealMethod();
        assertEquals(-7829368, configScreen.getMedium().getBackground());

        when(configScreen.getHard().getBackground()).thenCallRealMethod();
        assertEquals(-7829368, configScreen.getHard().getBackground());
    }
    @Test
    public void checkMediumBackground(){
        configScreen.getMedium().performClick();
        assertEquals(Color.BLUE, configScreen.getMedium().getBackground());
        assertEquals(-7829368, configScreen.getEasy().getBackground());
        assertEquals(-7829368, configScreen.getHard().getBackground());
    }
    @Test
    public void checkHardBackground(){
        configScreen.getHard().performClick();
        assertEquals(configScreen.getHard().getBackground(), Color.BLUE);
        assertEquals(-7829368, configScreen.getEasy().getBackground());
        assertEquals(-7829368, configScreen.getMedium().getBackground());
    }

}
