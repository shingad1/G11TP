package com.sps.game.Sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sps.game.maps.MapFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class NonInteractiveNPCTest {

    private SpriteBatch sb;
    private MapFactory.MapType world;
    private com.sps.game.sprites.NonInteractiveNPC nonnteractiveNPC;
    private String state;
    //private Vector2 velocity;


    @Before
    public void setup() {
        sb = new SpriteBatch();
        //velocity = new Vector2();
        nonnteractiveNPC = new com.sps.game.sprites.NonInteractiveNPC(12, 9, world,sb, "Pirate");
    }

    @Test
    public void getX() {
        assertEquals(12, nonnteractiveNPC.getX());
    }

    @org.junit.Test
    public void getY() {
        assertEquals(9, nonnteractiveNPC.getY());
    }

    @org.junit.Test
    public void getWorld() {
        assertEquals("Candy Land", nonnteractiveNPC.getWorld());
    }

    //@Test
    //public void getVelocity() {

    //}

    @Test
    public void setY() {
        nonnteractiveNPC.setY(58);
        assertEquals(58, nonnteractiveNPC.getY(),0);
    }

    @Test
    public void setX() {
        nonnteractiveNPC.setX(22);
        assertEquals(22, nonnteractiveNPC.getX());
    }

    @Test
    public void getAnimation() {
    }

    @org.junit.Test
    public void changeState() {
        state = "Paused";
        assertEquals("Paused", state);
    }

    @org.junit.Test
    public void getLocation() {
        com.sps.game.sprites.Location location = new com.sps.game.sprites.Location(5, 9);
        assertEquals(5,location.getX(),0);
        assertEquals(9, location.getY(), 0);
    }
}