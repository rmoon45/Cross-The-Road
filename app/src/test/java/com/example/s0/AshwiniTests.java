package com.example.s0;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.content.Context;
import android.os.Looper;
import android.view.KeyEvent;

import frogger.Game;
import frogger.GameScreen;
import frogger.Player;

public class AshwiniTests {
    private Game game = null;
    private Looper looper = null;
    private Player user = null;
    private GameScreen gameScreen = null;
    private KeyEvent event = null;

    @Before
    public void setup() {
        looper = mock(Looper.class);
        Context context = mock(Context.class);
        when(context.getMainLooper()).thenReturn(looper);
        user = mock(Player.class);
        gameScreen = mock(GameScreen.class);
        event = mock(KeyEvent.class);
    }

    //test upward movement
    @Test
    public void testMoveUp(){
        user.setPosY(-50);
        float og = user.getPosY();
        when(KeyEvent.getMaxKeyCode()).thenReturn(KeyEvent.getMaxKeyCode());
        event = new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.getMaxKeyCode());
        gameScreen.onKeyUp(KeyEvent.KEYCODE_W, event);
        assertTrue(user.getPosY() > og);
    }

}
