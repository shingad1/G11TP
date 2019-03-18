package com.sps.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sps.game.animation.EnemyAnimation;
import com.sps.game.maps.MapFactory;
import com.sps.game.sprites.BasicEnemy;
import com.sps.test.GdxTestRunner;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.HashMap;

import static org.junit.Assert.assertEquals;

@RunWith(GdxTestRunner.class)
public class BasicEnemyTest {

    private SpriteBatch sb;
    private HashMap fightAnimation;
    private BasicEnemy basicEnemy;
    private String state;
    private int health;
    private int defence;

    @Before
    public void setUp() {
        basicEnemy = new BasicEnemy(12,15, MapFactory.MapType.CandyWorld1,sb,"Boss");
        fightAnimation = new HashMap<String, EnemyAnimation>();
        fightAnimation.put("Idle",new EnemyAnimation(sb, basicEnemy, "enemyIdle.atlas", 1/15f));
        health = 100;
        int defence = 20;
    }

    @Test
    public void getFightAnimation() {
        assertEquals(fightAnimation,basicEnemy.getFightAnimation());
    }

    @Test
    public void getWorld() {
        assertEquals(MapFactory.MapType.CandyWorld1, basicEnemy.getWorld());
    }

    @Test
    public void getName() {
        assertEquals("Boss",basicEnemy.getName());
    }

    @Test
    public void changeState() {
        String newState = "run";
        state = newState;
        assertEquals(newState, state);
    }

    @Test
    public void getX() {
        assertEquals(12,basicEnemy.getX());
    }

    @Test
    public void getY() {
        assertEquals(15,basicEnemy.getY());
    }

    @Test
    public void getVelocity() {
    }

    @Test
    public void getAnimation() {
        //assertEquals("idle");
    }

    @Test
    public void getHealth() {
        assertEquals(100,basicEnemy.getHealth());
    }

    @Test
    public void battleMove() {
        //method currently empty
    }

    @Test
    public void changeDefence() {
        basicEnemy.changeDefence(9);
        assertEquals(29, basicEnemy.getDefence());

    }

    @Test
    public void changeAttack() {
        basicEnemy.changeAttack(9);
        assertEquals(29, basicEnemy.getAttack());
    }

    @Test
    public void decreaseHealth() {
    }
}