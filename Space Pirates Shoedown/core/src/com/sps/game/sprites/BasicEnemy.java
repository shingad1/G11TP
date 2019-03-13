package com.sps.game.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.animation.EnemyAnimation;
import com.sps.game.maps.MapFactory;

import java.util.HashMap;
import java.util.Random;

public class BasicEnemy extends AbstractEnemy{

    private MapFactory.MapType world;

    private int x;

    private int y;

    private Vector2 velocity;

    private String state;

    private String name;

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


    public HashMap<String, EnemyAnimation> getFightAnimation(){return fightAnimation;}

    @Override
    public String getName() {
        return name;
    }


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

    @Override
    public Vector2 getVelocity() {
        return velocity;
    }

    /**
     * Returns the world the enemy is in.
     * @return MapFactory.MapType world.
     */
    public MapFactory.MapType getWorld(){
        return world;
    }

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

    @Override
    public void battleMove() {

    }

    @Override
    public void changeDefence(int diff){defence += diff;}

    @Override
    public void changeAttack(int diff){attack += diff;}

    /**
     * Decreases the enemies health by an inputted value.
     * @param <code>int</code> decrease, the amount to decrease the health by.
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
