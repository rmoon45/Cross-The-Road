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
import frogger.Player1;

public class UserTests {
    private Player1 user;

    private Looper looper = null;
    private Context context;

    @Before
    public void setup() {
        looper = mock(Looper.class);
        context = mock(Context.class);
        when(context.getMainLooper()).thenReturn(looper);

        user = mock(Player1.class);
    }

    // Nicole: valid name is valid
    @Test
    public void testValidName() {
        user.setName("valid name");
        when(user.checkName("valid name")).thenReturn(true);
    }

    // Esther: test space as invalid name
    @Test
    public void testSpaceAsName() {
        user.setName(" ");
        when(user.checkName(" ")).thenReturn(false);
    }
    // Esther: test null as invalid name
    @Test
    public void testNullAsName() {
        user.setName(null);
        when(user.checkName(null)).thenReturn(false);
    }
}
