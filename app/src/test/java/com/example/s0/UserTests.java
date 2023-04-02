package com.example.s0;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;

import frogger.Player;

public class UserTests {
    private Player player = mock(Player.class, CALLS_REAL_METHODS);

    // Nicole: valid name is valid
    @Test
    public void testValidName() {
        assertTrue(player.checkName("valid name"));
    }

    // Esther: test space as invalid name
    @Test
    public void testSpaceAsName() {
        assertFalse(player.checkName("   "));
    }

    // Esther: test null as invalid name
    @Test
    public void testNullAsName() {
        assertFalse(player.checkName(null));
    }
}
