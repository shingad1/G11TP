package com.sps.game.Sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sps.game.maps.MapFactory;
import com.sps.test.GdxTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.sps.game.sprites.Location;
import com.sps.game.sprites.NonInteractiveNPC;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class NonInteractiveNPCTest {

    private SpriteBatch sb;
    private MapFactory.MapType world;
    private NonInteractiveNPC nonInteractiveNPC;
    private String state;
    private Location location;

    @Before
    public void setup() {
        sb = new SpriteBatch();
        world = MapFactory.MapType.HomeWorldMap1;
        nonInteractiveNPC = new NonInteractiveNPC(12, 9, world,sb, "Pirate");
        location = new Location(12,9);
        state = "";
    }

    @Test
    public void getX() {
       System.out.println( nonInteractiveNPC.getX());
        assertEquals(12, nonInteractiveNPC.getX(),0);
    }

    @Test
    public void getY() {
        assertEquals(9, nonInteractiveNPC.getY(),0);
    }

    @Test
    public void getWorld() {
        assertEquals("Candy Land", nonInteractiveNPC.getWorld());
    }

    @Test
    public void setY() {
        nonInteractiveNPC.setY(58);
        assertEquals(58, nonInteractiveNPC.getY(),0.2);
    }

    @Test
    public void setX() {
        nonInteractiveNPC.setX(22);
        assertEquals(22, nonInteractiveNPC.getLocation().getX(),0);
    }

    @Test
    public void getLocation() {
        assertEquals(12, location.getX(),0);
        assertEquals(9, location.getY(),0);
    }

    @After
    public void after() {
        nonInteractiveNPC.getLocation().setX(12);
        nonInteractiveNPC.getLocation().setY(9);
    }
}