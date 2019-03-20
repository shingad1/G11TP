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
        attack = 50;//to change
        defence = 0;//to change

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
        if(getHealth() <= 30){
            int temp = rand.nextInt(3)+1; //size of list
            switch(temp){
                case 1:
                    system.assignMove("Shield Bash", moveList.getMovelist().get("Shield Bash"), false);
                    break;
                case 2:
                    system.assignMove("Bravery", moveList.getMovelist().get("Bravery"),false);
                    break;
                case 3:
                    system.assignMove("Heal", moveList.getMovelist().get("Heal"),false);
                    break;
                default:
                    system.assignMove("Patch Up", moveList.getMovelist().get("Patch Up"), false);
                    break;
            }
        }else if(getHealth() > 30 && getHealth() <=60) {
            int temp = rand.nextInt(3)+1;
            switch (temp) {
                case 1:
                    system.assignMove("Poison", moveList.getMovelist().get("Poison"), false);
                    break;
                case 2:
                    system.assignMove("Weaken", moveList.getMovelist().get("Weaken"), false);
                    break;
                case 3:
                    system.assignMove("Frighten", moveList.getMovelist().get("Frighten"), false);
                    break;
                default:
                    system.assignMove("Block", moveList.getMovelist().get("Block"), false);
                    break;
            }
        } else {
            int temp = rand.nextInt(5) + 1;
            switch (temp){
                case 1:
                    system.assignMove("Block", moveList.getMovelist().get("Block"), false);
                    break;
                case 2:
                    system.assignMove("Quick Attack", moveList.getMovelist().get("Quick Attack"), false);
                    break;
                default:
                    system.assignMove("Attack", moveList.getMovelist().get("Attack"), false);
                    break;
            }
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
