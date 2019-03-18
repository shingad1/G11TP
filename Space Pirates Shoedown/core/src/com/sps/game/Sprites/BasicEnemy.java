package com.sps.game.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sps.game.animation.EnemyAnimation;
import com.sps.game.controller.CombatSystem;
import com.sps.game.controller.MoveList;
import com.sps.game.screens.Fighter;

import java.util.HashMap;
import java.util.Random;

public class BasicEnemy extends AbstractEnemy{
    /**
     * Holds the value of what world it is.
     */
    public static String WORLD = "Earth";

    private HashMap<String, EnemyAnimation> fightAnimation;

    public BasicEnemy(int x, int y, SpriteBatch sb){
        this.x = x;
        this.y = y;
        health = 100;
        attack = 20;
        defence = 0;
        changeInString();
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
     * Creates a character according to the value of the world.
     */
    private void changeInString() {
        if(WORLD == "Test Battle Screen")
        {

        }
    }

    public HashMap<String,EnemyAnimation> getFightAnimation(){return fightAnimation;}

    @Override
    public EnemyAnimation getAnimation() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Location getLocation() {
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
            int temp = 1;
            switch (temp) {
                case 1:
                    system.assignMove("Attack", moveList.getMovelist().get("Attack"), false);
                    break;
            }
        }
        else
        {
            system.assignMove("Block", moveList.getMovelist().get("Block"), false);
        }
    }
}
