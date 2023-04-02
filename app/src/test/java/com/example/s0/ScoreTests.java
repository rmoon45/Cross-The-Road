package com.example.s0;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import frogger.GameScreen;

public class ScoreTests {
    private GameScreen gamescreen = mock(GameScreen.class, CALLS_REAL_METHODS);

    @Before
    public void setup() {
        doCallRealMethod().when(gamescreen).getScoreAfterMove(anyInt(), anyString(), anyBoolean());
    }

    // Nicole: score increases by 3 after crossing a river
    @Test
    public void testScoreIncreasesBy3AfterCrossingRiver() {
        assertEquals(gamescreen.getScoreAfterMove(0, "river", true), 3);
    }

    // Nicole: score increases by 2 after crossing a road
    @Test
    public void testScoreIncreasesBy2AfterCrossingRoad() {
        assertEquals(gamescreen.getScoreAfterMove(0, "road", true), 2);
    }

    // Madison: score increases by 1 after crossing a safe tile
    @Test
    public void testScoreIncreasesBy1AfterCrossingRoad() {
        assertEquals(gamescreen.getScoreAfterMove(0, "safe", true), 1);
    }

    // Nikki: score does not increase when player goes up to already travelled to lane
    @Test
    public void testScoreNotIncreasedWhenAlreadyTravelled() {
        gamescreen.setCurrPos(10);
        gamescreen.setGreatestPos(15);
        assertEquals(gamescreen.getScoreAfterMove(0, "safe",false), 0);
    }

    // Nikki: score does not increase when player moves but does not change lanes
    @Test
    public void testScoreNotIncreasedWhenGoingSide() {
        boolean scoreChangeFromRight = gamescreen.getScoreChange("moveRight");
        assertEquals(gamescreen.getScoreAfterMove(0, "safe",scoreChangeFromRight), 0);
        boolean scoreChangeFromLeft = gamescreen.getScoreChange("moveLeft");
        assertEquals(gamescreen.getScoreAfterMove(0, "safe",scoreChangeFromLeft), 0);
    }

    // Nikki: score does not increase when player goes down the map
    @Test
    public void testScoreNotIncreasedWhenGoingDown() {
        boolean scoreChangeFromDown = gamescreen.getScoreChange("moveDown");
        assertEquals(gamescreen.getScoreAfterMove(0, "safe",scoreChangeFromDown), 0);

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

        assertEquals("safe", gamescreen.getTileCorrespondingToPosition(0, map));
        assertEquals("safe", gamescreen.getTileCorrespondingToPosition(1, map));
        assertEquals("road", gamescreen.getTileCorrespondingToPosition(2, map));
        assertEquals("river", gamescreen.getTileCorrespondingToPosition(3, map));
        assertEquals("safe", gamescreen.getTileCorrespondingToPosition(4, map));
    }
}
