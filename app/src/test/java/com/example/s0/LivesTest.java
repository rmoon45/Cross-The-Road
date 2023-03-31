package com.example.s0;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.Context;
import android.os.Looper;

import androidx.annotation.NonNull;

import frogger.GameScreen;
import frogger.Player1;
import frogger.ScoreManager;
import frogger.Vehicle;
import frogger.Game;

public class LivesTest {
    private Looper looper = null;
    private Player1 player = null;

    private Vehicle car = null;
    private GameScreen gamescreen = null;
    private Game game = null;
    private ScoreManager scoreManager = null;

    @Before
    public void setup() {
        int squareSize = 112;
        int numVerticalSquares = 18;
        int numHorizontalSquares = 11;
        looper = mock(Looper.class);
        player = new Player1();
        car = new Vehicle("car1", numVerticalSquares, squareSize);
        gamescreen = mock(GameScreen.class);
        game = new Game();
        scoreManager = new ScoreManager();

        //vehicle2 = new Vehicle("car2", numVerticalSquares, squareSize);
        //vehicle3 = new Vehicle("car3", numVerticalSquares, squareSize);
        //vehicle4 = new Vehicle("car4", numVerticalSquares, squareSize);


        Context context = mock(Context.class);
        when(context.getMainLooper()).thenReturn(looper);
    }

    @Test
    public void testCarCollision() {
        player.setPosX(10);
        player.setPosY(10);
        assertTrue(player.isColliding(10.0F, 10.0F, 10.0F, 10.0F));
        }

    @Test
    public void testCollisionLivesDecrease() {
        player.setPosX(10);
        player.setPosY(10);
        player.setLives(3);
        if(player.isColliding(10.0F, 10.0F, 10.0F, 10.0F)) {
            assertTrue(player.getLives() == 2);
        }
    }

    @Test
    public void testCollisionScoreReset() {
        player.setPosX(10);
        player.setPosY(10);
        int score = 9;
        player.setScore(score);
        if(player.isColliding(10.0F, 10.0F, 10.0F, 10.0F)) {
            assertTrue(player.getScore() == 0);
        }
    }

    @Test
    public void testRiverScoreReset() {
        when(gamescreen.scoreResetTest(2)).thenCallRealMethod();
        assertEquals(true, gamescreen.scoreResetTest(2));
        //assertEquals(scoreManager.getScoreAfterMove(0, "river", true), 0);
    }

    @Test
    public void testRiverLivesDecrease() {
        if(player.isColliding(50.0F, 1472, 50.0F, 50.0F)) {
            assertTrue(player.getLives() == 2);
        }
    }
    @Test
    public void testRespawnHittingWaterAtStarting() {

        if(player.isColliding(50.0F, 1472, 50.0F, 50.0F)) {
            assertTrue(player.getRespawned());
        }
    }
    @Test
    public void testRespawnHittingCarAtStarting() {

        if(player.isColliding(10.0F, 10.F, 10.F, 10.0F)) {
            assertTrue(player.getRespawned());
        }
    }


    @Test
    public void testScoreDisplay() {

    }

}
