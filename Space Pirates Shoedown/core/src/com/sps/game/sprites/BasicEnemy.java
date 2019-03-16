package com.sps.game.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.animation.EnemyAnimation;
import com.sps.game.maps.MapFactory;

import java.util.HashMap;
import java.util.Random;

/**
 * This class creates a Basic Enemy.
 * @author Miraj Shah, Miguel Abaquin
 * @version 1.0
 */
public class BasicEnemy extends AbstractEnemy{
    /**
     * Holds the world the basic enemy will be rendered in.
     */
    private MapFactory.MapType world;
    /**
     * Holds the X value of the basic enemy.
     */
    private int x;
    /**
     * Holds the Y value of the basic enemy.
     */
    private int y;
    /**
     * Holds the basic enemies vector 2.
     */
    private Vector2 velocity;
    /**
     * Holds the name of the basic enemy.
     */
    private String name;

    private String state;

    public BasicEnemy(int x, int y, MapFactory.MapType world, SpriteBatch sb, String name){
        this.x = x;
        this.y = y;
        this.name = name;
        velocity = new Vector2(0,0);
        location = new Location(x,y);
        health = 100;
        attack = 20;
        defence = 0;

        fightAnimation = new HashMap<String, EnemyAnimation>();
        fightAnimation.put("Idle",new EnemyAnimation(sb, this, "enemyIdle.atlas", 1/15f));
        fightAnimation.put("Right",new EnemyAnimation(sb, this, "enemyMoveRight.atlas", 1/15f));
        fightAnimation.put("Left",new EnemyAnimation(sb, this, "enemyMoveLeft.atlas", 1/15f));
        fightAnimation.put("basicAttack",new EnemyAnimation(sb, this, "enemyBasicAttack.atlas", 1/3f));
        fightAnimation.put("block",new EnemyAnimation(sb, this, "enemyBasicBlock.atlas", 1/3f));

        animation = new HashMap<String, EnemyAnimation>();
        animation.put("idle", new EnemyAnimation(sb, this, "regenemyIdle.atlas", 1/2f));
    }

    /**
     * Returns a HashMap containing all the fight animations of the enemy.
     * The key is the name of the enemy.
     * The value is an Enemy Animation.
     * @return HashMap<String,EnemyAnimation> fightAnimation
     */
    public HashMap<String, EnemyAnimation> getFightAnimation(){return fightAnimation;}

    /**
     * Returns the world the enemy is in.
     * @return MapFactory.MapType world.
     */
    public MapFactory.MapType getWorld(){
        return world;
    }

    /**
     * Returns the name of the enemy.
     * @return String name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Changes the state of the enemy.
     * @param newState
     */
    @Override
    public void changeState(String newState) {
        state = newState;
    }

    /**
     * Gets the enemies X coordinate.
     * @return Returns <code>int</code> X coordinate.
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Gets the enemies Y coordinate.
     * @return Returns <code>int</code> Y coordinate.
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * Gets the velocity of the enemy.
     * @return Vector2 velocity
     */
    @Override
    public Vector2 getVelocity() {
        return velocity;
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
     * Gets the enemies health level.
     * @return Returns <code>int</code> health level.
     */
    @Override
    public int getHealth() {
        return health;
    }

    /**
     *
     */
    @Override
    public void battleMove() {

    }

    /**
     *
     * @param diff
     */
    @Override
    public void changeDefence(int diff){defence += diff;}

    /**
     *
     * @param diff
     */
    @Override
    public void changeAttack(int diff){attack += diff;}

    /**
     * Decreases the enemies health by an inputted value.
     * @param <code>int</code>decrease, the amount to decrease the health by.
     */
    public void decreaseHealth(int decrease){
        health -= decrease;
    }

    /*
    @Override
    public void battleMove()
    {
        Random rand = new Random();

        if(getHealth() > 40)
        {
            int temp = 1;
            switch (temp) {
                case 1:
                    system.doMove("basicAttack");
                    break;
            }
        }
        else
        {
            system.doMove("block");
        }
    }
    */
}
