package com.sps.game.Sprites;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LocationTest {

    private com.sps.game.sprites.Location location = new com.sps.game.sprites.Location(12,15);

    @Test
    public void getX() {
        assertEquals(12, location.getX(),0);
    }

    @Test
    public void getY() {
        assertEquals(15, location.getY(),0);
    }

    @Test
    public void setX() {
        location.setX(23);
        assertEquals(23, location.getX(),0);
    }

    @Test
    public void setY() {
        location.setY(58);
        assertEquals(58, location.getY(),0);
    }

}