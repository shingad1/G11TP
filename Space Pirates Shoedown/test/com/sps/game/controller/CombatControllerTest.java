package com.sps.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sps.game.maps.MapFactory;
import com.sps.game.sprites.BasicEnemy;
import com.sps.game.sprites.Player;
import com.sps.test.GdxTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(GdxTestRunner.class)
public class CombatControllerTest {

    private SpriteBatch sb;
    private MapFactory.MapType world;
    private CombatController combatController;
    private Player p;
    private BasicEnemy e;
    private CombatSystem cs;
    private int keyPressed;


    @Before
    public void setUp(){
        //world = MapFactory.Maptype.HomeWorldScreen;
        sb = new SpriteBatch();
        //cs = new CombatSystem(p,e,sb);
        e = new BasicEnemy(9,9,world,sb, "Pirate");
        combatController = new CombatController(p, e, cs);
    }

    @Test
    public void keyDown()
    {
        keyPressed = 72;
        int keycode = keyPressed;
        assertTrue(keyPressed == keycode);
    }
}