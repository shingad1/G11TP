package com.sps.game.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.animation.EnemyAnimation;
import com.sps.game.controller.MoveList;
import com.sps.game.maps.MapFactory;

import java.util.HashMap;

public class HeadEnemy extends AbstractEnemy {

    /**
     * Holds the world the head enemy will be rendered in.
     */
    private MapFactory.MapType world;
    /**
     * Holds the X value of the head enemy.
     */
    private int x;
    /**
     * Holds the Y value of the head enemy.
     */
    private int y;
    /**
     * Holds the name of the head enemy.
     */
    private String name;

    private String state;

    public HeadEnemy(int x, int y, MapFactory.MapType world, SpriteBatch sb, String name){
        this.x = x;
        this.y = y;
        location = new Location(x,y);
        this.world = world;
        this.name = name;
        health = 100;
        attack = 50;//to change
        defence = 0;//to change

        fightAnimation = new HashMap<String, EnemyAnimation>();

        animation = new HashMap<String, EnemyAnimation>();
        animation.put("idle", new EnemyAnimation(sb, this, "headenemyIdle.atlas",1/2));
    }

    /**
     * Abstract method that will return the enemies X coordinate.
     * @return <code>int</code> X coordinate
     */
    @Override
    public int getX() {
        return x;
    }
    /**
     * Abstract method that will return the enemies Y coordinate.
     * @return <code>int</code> Y coordinate
     */
    @Override
    public int getY() {
        return y;
    }

    @Override
    public void battleMove(MoveList moveList) {

    }
    /**
     * Returns the world the enemy is in.
     * @return MapFactory.MapType world
     */
    @Override
    public MapFactory.MapType getWorld() {
        return world;
    }
    /**
     * Gets the current animation of the enemy, outside of the battle.
     * @return EnemyAnimation
     */
    @Override
    public EnemyAnimation getAnimation() {
        return animation.get("idle");
    }

    /**
     * Returns the health of the enemy.
     * @return int health
     */
    @Override
    public int getHealth() {
        return health;
    }

    /**
     * Returns a HashMap contains all the Fighting animations of the enemy.
     * @return HashMap<String,EnemyAnimation> fightAnimation
     */
    @Override
    public HashMap<String, EnemyAnimation> getFightAnimation() {
        return fightAnimation;
    }
    /**
     * Returns the name of the enemy.
     * @return String name
     */
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Location getLocation() {
        return null;
    }

    /**
     *
     * @param diff
     */
    @Override
    public void changeAttack(int diff) {

    }

    /**
     *
     * @param diff
     */
    @Override
    public void changeDefence(int diff) {

    }
}
