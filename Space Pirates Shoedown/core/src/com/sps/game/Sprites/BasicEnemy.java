package com.sps.game.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.animation.EnemyAnimation;
import com.sps.game.controller.CombatSystem;
import com.sps.game.controller.MoveList;
import com.sps.game.maps.MapFactory;
import com.sps.game.screens.Fighter;

import java.util.HashMap;
import java.util.Random;

public class BasicEnemy extends AbstractEnemy{

    /**
     * Holds the world the basic enemy will be rendered in.
     */
    private MapFactory.MapType world;
    /**
     * Holds the name of the basic enemy.
     */
    private String name;

    private String state;

    private HashMap<String, EnemyAnimation> fightAnimation;

    public BasicEnemy(int x, int y, MapFactory.MapType world, SpriteBatch sb, String name){
        this.x = x;
        this.y = y;
        location = new Location(x,y);
        this.name = name;
        this.world = world;
        health = 100;
        attack = 20;
        defence = 0;
        speed = 10;

        fightAnimation = new HashMap<String, EnemyAnimation>();
        fightAnimation.put("Idle",new EnemyAnimation(sb, this, "enemyIdle.atlas", 1/15f));
        fightAnimation.put("Right",new EnemyAnimation(sb, this, "enemyMoveRight.atlas", 1/15f));
        fightAnimation.put("Left",new EnemyAnimation(sb, this, "enemyMoveLeft.atlas", 1/15f));
        fightAnimation.put("basicAttack",new EnemyAnimation(sb, this, "enemyBasicAttack.atlas", 1/3f));
        fightAnimation.put("block",new EnemyAnimation(sb, this, "enemyBasicBlock.atlas", 1/3f));

        animation = new HashMap<String, EnemyAnimation>();
        animation.put("idle", new EnemyAnimation(sb, this, "regenemyIdle.atlas", 1/2f));
    }

    public HashMap<String,EnemyAnimation> getFightAnimation(){return fightAnimation;}

    @Override
    public EnemyAnimation getAnimation() {
        return animation.get("idle");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public MapFactory.MapType getWorld() {
        return null;
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
     * Gets the enemies health level.
     * @return Returns <code>int</code> health level.
     */
    @Override
    public int getHealth() {
        return health;
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

    @Override
    public void battleMove(MoveList moveList)
    {
        Random rand = new Random();

        if(getHealth() > 40)
        {
            int temp = rand.nextInt(4)+1; //rand.nextInt(size of list) + 1
            switch (temp) {
                /*case 1:
                    system.assignMove("Slow", moveList.getMoveByKey("Slow"),false);
                    break;
                case 2:
                    system.assignMove("Quick Attack", moveList.getMoveByKey("Quick Attack"), false);
                    break;
                case 3:
                    system.assignMove("Poison", moveList.getMoveByKey("Poison"), false);
                    break;
                case 4:
                    system.assignMove("Frighten", moveList.getMoveByKey("Frighten"),false);
                    break;*/
                default:
                    system.assignMove("Block", moveList.getMoveByKey("Block"), false);
                    break;
            }
        }
        else
        {
            int temp = rand.nextInt(2) + 1;
            switch (temp){
                case 1:
                    system.assignMove("Block", moveList.getMovelist().get("Block"), false);
                    break;
                case 2:
                    system.assignMove("Weaken", moveList.getMovelist().get("Weaken"),false);
                    break;
                default:
                    system.assignMove("Patch Up", moveList.getMovelist().get("Patch Up"), false);
                    break;
            }
        }
    }
}
