package com.sps.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sps.game.maps.MapFactory;
import com.sps.game.sprites.BasicEnemy;
import com.sps.game.sprites.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

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
        sb = new SpriteBatch();
        //p = new Player();
        cs = new CombatSystem(p,e,sb);
        e = new BasicEnemy(9,9,world,sb, "Pirate");
        combatController = new CombatController(p, e, cs);
    }

    @Test
    public void keyDown()
    {
        keyPressed = 81;
        int keycode = keyPressed;
        assertTrue(keyPressed==keycode);
    }
}