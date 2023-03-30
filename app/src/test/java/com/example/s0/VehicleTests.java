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
import frogger.Vehicle;

public class VehicleTests {

    private Looper looper = null;
    private int squareSize;
    private int screenWidth;
    private int screenHeight;
    private GameScreen gamescreen;
    private Vehicle vehicleInfo;
    private int numVerticalSquares;
    private int numHorizontalSquares;

    private Vehicle vehicle1;
    private Vehicle vehicle2;
    private Vehicle vehicle3;
    private Vehicle vehicle4;

    @Before
    public void setup() {
        looper = mock(Looper.class);
        Context context = mock(Context.class);
        when(context.getMainLooper()).thenReturn(looper);
        gamescreen = mock(GameScreen.class);
        vehicleInfo = mock(Vehicle.class);
        vehicle1 = new Vehicle("car1", numVerticalSquares, squareSize);
        vehicle2 = new Vehicle("car2", numVerticalSquares, squareSize);
        vehicle3 = new Vehicle("car3", numVerticalSquares, squareSize);
        vehicle4 = new Vehicle("car4", numVerticalSquares, squareSize);

        squareSize = 112;
        numVerticalSquares = 18;
        numHorizontalSquares = 11;

    }

    // Ashwini: each lane should generate each 1 type of vehicle only
    @Test
    public void testOneLaneForOneVehicleType() {
        float lane1 = gamescreen.getSquareSize() * (gamescreen.getNumVerticalSquares() - 2) - gamescreen.getSquareSize();
        float lane2 = gamescreen.getSquareSize() * (gamescreen.getNumVerticalSquares() - 2) - (2 * gamescreen.getSquareSize());
        assertEquals(gamescreen.getCar1Y(), lane1, 0);
        assertEquals(gamescreen.getCar2Y(), lane2, 0);
    }

    //Ashwini: vehicles should not collide with each other
    @Test
    public void testCollidingVehicles() {
//        float car1Position = gamescreen.getCar1Y();
//        float car2Position = gamescreen.getCar2Y();
//        gamescreen.layoutSetup();
//        gamescreen.playerAndVehiclesOnGame();
//        assertNotEquals(car1Position, car2Position);
        vehicle1.setYPosition("car1", squareSize, numVerticalSquares);
        vehicle2.setYPosition("car2", squareSize, numVerticalSquares);
        assertNotEquals(vehicle1.getyPosition(), vehicle2.getyPosition());



    }

    // Esther: different vehicles should have different speeds
    @Test
    public void testDifferentVehiclesDifferentSpeeds() {
        vehicle1.setVehicleSpeed("car1", squareSize);
        vehicle3.setVehicleSpeed("car3", squareSize);
        assertNotEquals(vehicle1.getVehicleSpeed(), vehicle3.getVehicleSpeed());
    }


    //Esther: different vehicles should have different sizes
    @Test
    public void testDifferentVehiclesDifferentSizes() {
        vehicle1.setVehicleWidth("car1", squareSize);
        vehicle2.setVehicleWidth("car2", squareSize);
        vehicle3.setVehicleWidth("car3", squareSize);
        vehicle4.setVehicleWidth("car4", squareSize);
        assertNotEquals(vehicle1.getVehicleWidth(), vehicle2.getVehicleWidth());
        assertNotEquals(vehicle2.getVehicleWidth(), vehicle3.getVehicleWidth());
        assertNotEquals(vehicle3.getVehicleWidth(), vehicle4.getVehicleWidth());
        assertNotEquals(vehicle1.getVehicleWidth(), vehicle4.getVehicleWidth());


    }



}
