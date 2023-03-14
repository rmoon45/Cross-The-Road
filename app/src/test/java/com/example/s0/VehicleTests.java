package com.example.s0;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.os.Looper;

import frogger.ConfigScreen;
import frogger.Game;
import frogger.GameScreen;
import frogger.Player;

public class VehicleTests {

    private Looper looper = null;
    private int squareSize;
    private int screenWidth;
    private int screenHeight;

    @Before
    public void setup() {
        looper = mock(Looper.class);
        Context context = mock(Context.class);
        when(context.getMainLooper()).thenReturn(looper);
        GameScreen gamescreen = mock(GameScreen.class);
    }

    @Test
    public void testOneLaneForOneVehicleType() {

    }

    @Test
    public void testCollidingVehicles() {

    }

    @Test
    public void testDifferentVehiclesDifferentSpeeds() {

    }

    @Test
    public void testDifferentVehiclesDifferentSizes() {

    }



}
