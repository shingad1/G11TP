package com.sps.game.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.animation.EnemyAnimation;
import com.sps.game.controller.MoveList;
import com.sps.game.maps.MapFactory;

import java.util.HashMap;
import java.util.Random;

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
        attack = 30;//to change
        defence = 15;//to change
        speed = 0;

        fightAnimation = new HashMap<String, EnemyAnimation>();
        fightAnimation.put("Idle",new EnemyAnimation(sb, this, "enemyIdle.atlas", 1/15f));
        fightAnimation.put("Right",new EnemyAnimation(sb, this, "enemyMoveRight.atlas", 1/15f));
        fightAnimation.put("Left",new EnemyAnimation(sb, this, "enemyMoveLeft.atlas", 1/15f));
        fightAnimation.put("basicAttack",new EnemyAnimation(sb, this, "enemyBasicAttack.atlas", 1/3f));
        fightAnimation.put("block",new EnemyAnimation(sb, this, "enemyBasicBlock.atlas", 1/3f));

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
        Random rand = new Random();
        if(health >= 60){
            int temp = rand.nextInt(100); //rand.nextInt(size of list) + 1
            if(temp >= 0 && temp < 50)
                system.assignMove("Attack", moveList.getMoveByKey("Attack"),false);
            if(temp >= 50 && temp < 75)
                system.assignMove("Shield Bash", moveList.getMoveByKey("Shield Bash"),false);
            if(temp >= 75 && temp < 100)
                system.assignMove("Block", moveList.getMoveByKey("Block"),false);
        } else if(health >= 10){
            int temp = rand.nextInt(100); //rand.nextInt(size of list) + 1
            if(temp >= 0 && temp < 25)
                system.assignMove("Shield Bash", moveList.getMoveByKey("Shield Bash"),false);
            if(temp >= 25 && temp < 50)
                system.assignMove("Block", moveList.getMoveByKey("Block"),false);
            if(temp >= 50 && temp < 75)
                system.assignMove("Wind Speed", moveList.getMoveByKey("Wind Speed"),false);
            if(temp >= 75 && temp < 100)
                system.assignMove("Quick Attack", moveList.getMoveByKey("Quick Attack"),false);
        } else {
            int temp = rand.nextInt(100); //rand.nextInt(size of list) + 1
            if(temp >= 0 && temp < 33)
                system.assignMove("Attack", moveList.getMoveByKey("Attack"),false);
            if(temp >= 33 && temp < 66)
                system.assignMove("Shield Bash", moveList.getMoveByKey("Shield Bash"),false);
            if(temp >= 66 && temp < 100)
                system.assignMove("Quick Attack", moveList.getMoveByKey("Quick Attack"),false);
        }
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
        return location;
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
