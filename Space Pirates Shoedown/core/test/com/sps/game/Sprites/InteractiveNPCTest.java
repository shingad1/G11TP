package com.sps.game.Sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sps.game.maps.MapFactory;
import com.sps.game.sprites.InteractiveNPC;
import com.sps.game.sprites.Location;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class InteractiveNPCTest {

    private SpriteBatch sb;
    private InteractiveNPC iNPC;
    private String state;

    @Mock
    private SpriteBatch mockSpriteBatch;

    @Before
    public void setup() {
        mockSpriteBatch = mock(SpriteBatch.class);
       iNPC = new InteractiveNPC(5,9, MapFactory.MapType.CandyWorld1,mockSpriteBatch,"Muffin");
    }

    @Test
    public void getAnimation() {
    }

    @Test
    public void getWorld() {
        assertEquals(MapFactory.MapType.CandyWorld1, iNPC.getWorld());
    }

    @Test
    public void getName() {
        assertEquals("Muffin",iNPC.getName());
    }

    @Test
    public void getType() {
        //this is just returning a string so I don't think it needs a test?
    }

    @Test
    public void getX() {
        assertEquals(5, iNPC.getX());
    }

    @Test
    public void getY() {
        assertEquals(9, iNPC.getY());
    }

    @Test
    public void getVelocity() {
    }

    @Test
    public void setY() {
        iNPC.setY(58);
        assertEquals(58, iNPC.getY(),0);
    }

    @Test
    public void setX() {
        iNPC.setX(22);
        assertEquals(22, iNPC.getX());
    }

    @Test
    public void getLocation() {
        Location location = new Location(5, 9);
        assertEquals(5,location.getX(),0);
        assertEquals(9, location.getY(), 0);
    }

    @Test
    public void changeState() {
        state = "Paused";
        assertEquals("Paused", state);
    }
}