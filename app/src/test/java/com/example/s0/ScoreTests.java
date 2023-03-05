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

import frogger.ConfigScreen;
import frogger.Game;
import frogger.GameScreen;
import frogger.Player;
import frogger.ScoreManager;

public class ScoreTests {

    private ScoreManager scoreManager;

    @Before
    public void setup() {
        scoreManager = mock(ScoreManager.class);
    }

    // score increases by 3 after crossing a river
    @Test
    public void testScoreIncreasesBy3AfterCrossingRiver() {
        assertEquals(scoreManager.getScoreAfterMove(0, "river"), 3);
    }

    // score increases by 2 after crossing a road
    @Test
    public void testScoreIncreasesBy2AfterCrossingRoad() {
        assertEquals(scoreManager.getScoreAfterMove(0, "road"), 2);
    }

    // score increases by 1 after crossing a safe tile
    @Test
    public void testScoreIncreasesBy1AfterCrossingRoad() {
        assertEquals(scoreManager.getScoreAfterMove(0, "safe"), 1);
    }
}
