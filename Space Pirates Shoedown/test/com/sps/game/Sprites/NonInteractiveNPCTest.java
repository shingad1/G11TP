package com.sps.game.Sprites;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import org.mockito.Mock;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sps.game.maps.MapFactory;
import com.sps.game.screens.PlayScreen;
import com.sps.game.sprites.Player;
import com.sps.test.GdxTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.sps.game.sprites.Location;
import com.sps.game.sprites.NonInteractiveNPC;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class NonInteractiveNPCTest {

    //private SpriteBatch sb;
    private MapFactory.MapType world;
    private NonInteractiveNPC nonInteractiveNPC;
    private String state;
    private Location location;
    private OrthographicCamera gamecam;
    private TextureAtlas atlas;


    @Mock
    private SpriteBatch mockSpriteBatch;

    @Before
    public void setup() {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("core\\assets\\textureAtlas\\npcAtlas\\npcPeppermintDown.atlas"));
       //core\assets\textureAtlas\npcAtlas\npcPirateDown.atlas
        mockSpriteBatch = mock(SpriteBatch.class);
        gamecam = new OrthographicCamera(480,480);
        //sb = new SpriteBatch();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mockSpriteBatch.setProjectionMatrix(gamecam.combined);
        world = MapFactory.MapType.HomeWorldMap1;
        nonInteractiveNPC = new NonInteractiveNPC(12, 9, world, mockSpriteBatch,"Pirate");
        location = new Location(12,9);
        state = "";
    }

    @Test
    public void getX() {
       assertEquals(12, nonInteractiveNPC.getX(),0);
    }

    @Test
    public void getY() {
        assertEquals(9, nonInteractiveNPC.getY(),1);
    }

    @Test
    public void getWorld() {
        assertEquals(MapFactory.MapType.HomeWorldMap1, nonInteractiveNPC.getWorld());
    }

    @Test
    public void setY() {
        nonInteractiveNPC.setY(58);
        assertEquals(58, nonInteractiveNPC.getY(),1);
    }

    @Test
    public void setX() {
        nonInteractiveNPC.setX(22);
        assertEquals(22, nonInteractiveNPC.getLocation().getX(),1);
    }

    @Test
    public void getLocation() {
        assertEquals(12, location.getX(),1);
        assertEquals(9, location.getY(),1);
    }

    @After
    public void after() {
        nonInteractiveNPC.getLocation().setX(12);
        nonInteractiveNPC.getLocation().setY(9);
    }
}