package com.example.s0;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedConstruction;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.os.Looper;

import androidx.appcompat.app.AppCompatDelegate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import frogger.ConfigScreen;
import frogger.Game;
import frogger.GameScreen;
import frogger.Player;
import frogger.Player1;
import frogger.ScoreManager;

public class ScoreTests {

    private ScoreManager scoreManager;
    private GameScreen gamescreen;
    private Looper looper = null;
    private Player1 user = null;
    private int squareSize;
    private int screenWidth;
    private int screenHeight;

    @Before
    public void setup() {
        scoreManager = mock(ScoreManager.class);
        gamescreen = mock(GameScreen.class);
        looper = mock(Looper.class);
        Context context = mock(Context.class);
        when(context.getMainLooper()).thenReturn(looper);
        user = new Player1();
        squareSize = 20;
        screenWidth = 100;
        screenHeight = 200;

    }

    // Nicole: score increases by 3 after crossing a river
    @Test
    public void testScoreIncreasesBy3AfterCrossingRiver() {
        assertEquals(scoreManager.getScoreAfterMove(0, "river", true), 3);
    }

    // Nicole: score increases by 2 after crossing a road
    @Test
    public void testScoreIncreasesBy2AfterCrossingRoad() {
        assertEquals(scoreManager.getScoreAfterMove(0, "road", true), 2);
    }

    // Madison: score increases by 1 after crossing a safe tile
    @Test
    public void testScoreIncreasesBy1AfterCrossingRoad() {
        assertEquals(scoreManager.getScoreAfterMove(0, "safe", true), 1);
    }

    // Nikki: score does not increase when player goes up to already travelled to lane
    @Test
    public void testScoreNotIncreasedWhenAlreadyTravelled() {
        gamescreen.setCurrPos(10);
        gamescreen.setGreatestPos(15);
        assertEquals(scoreManager.getScoreAfterMove(0, "safe",false), 0);
    }

    // Nikki: score does not increase when player moves but does not change lanes
    @Test
    public void testScoreNotIncreasedWhenGoingSide() {
        boolean scoreChangeFromRight = gamescreen.getScoreChange("moveRight");
        assertEquals(scoreManager.getScoreAfterMove(0, "safe",scoreChangeFromRight), 0);
        boolean scoreChangeFromLeft = gamescreen.getScoreChange("moveLeft");
        assertEquals(scoreManager.getScoreAfterMove(0, "safe",scoreChangeFromLeft), 0);
    }

    // Nikki: score does not increase when player goes down the map
    @Test
    public void testScoreNotIncreasedWhenGoingDown() {
        boolean scoreChangeFromDown = gamescreen.getScoreChange("moveDown");
        assertEquals(scoreManager.getScoreAfterMove(0, "safe",scoreChangeFromDown), 0);

    }

    // Madison: get the correct tile the player is on
    @Test
    public void testCorrectTilesRetrievedUsingPlayerPosition() {
        List<String> map = new ArrayList<String>(Arrays.asList(
                "safe",
                "river",
                "road",
                "safe",
                "safe"
        ));

        assertEquals("safe", scoreManager.getTileCorrespondingToPosition(0, map));
        assertEquals("safe", scoreManager.getTileCorrespondingToPosition(1, map));
        assertEquals("road", scoreManager.getTileCorrespondingToPosition(2, map));
        assertEquals("river", scoreManager.getTileCorrespondingToPosition(3, map));
        assertEquals("safe", scoreManager.getTileCorrespondingToPosition(4, map));
    }
}
