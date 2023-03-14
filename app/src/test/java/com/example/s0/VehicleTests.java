package com.example.s0;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotEquals;
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
    private GameScreen gamescreen;

    @Before
    public void setup() {
        looper = mock(Looper.class);
        Context context = mock(Context.class);
        when(context.getMainLooper()).thenReturn(looper);
        gamescreen = mock(GameScreen.class);
    }

    // each lane should generate each 1 type of vehicle only
    @Test
    public void testOneLaneForOneVehicleType() {
        float lane1 = squareSize * (gamescreen.getNumVerticalSquares() - 2) - squareSize;
        float lane2 = squareSize * (gamescreen.getNumVerticalSquares() - 2) - (2 * squareSize);
        assertEquals(gamescreen.getCar1Y(), lane1, 0);
        assertEquals(gamescreen.getCar2Y(), lane2, 0);


    }

    //vehicles should not collide with each other
    @Test
    public void testCollidingVehicles() {
        float car1Position = gamescreen.getCar1Y();
        float car2Position = gamescreen.getCar2Y();
        gamescreen.layoutSetup();
        gamescreen.playerAndVehiclesOnGame();
        assertNotEquals(car1Position, car2Position);

    }

    // different vehicles should have different speeds
    @Test
    public void testDifferentVehiclesDifferentSpeeds() {
        gamescreen.layoutSetup();
        gamescreen.playerAndVehiclesOnGame();
        assertNotEquals(gamescreen.getCar1Speed(), gamescreen.getCar3Speed());
    }


    //different vehicles should have different sizes
    @Test
    public void testDifferentVehiclesDifferentSizes() {
        gamescreen.layoutSetup();
        gamescreen.playerAndVehiclesOnGame();
        assertNotEquals(gamescreen.getCar1Width(), gamescreen.getCar2Width());
        assertNotEquals(gamescreen.getCar2Width(), gamescreen.getCar3Width());
        assertNotEquals(gamescreen.getCar3Width(), gamescreen.getCar4Width());
        assertNotEquals(gamescreen.getCar1Width(), gamescreen.getCar4Width());

    }



}
