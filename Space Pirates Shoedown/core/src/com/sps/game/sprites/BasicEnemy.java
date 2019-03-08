package com.sps.game.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sps.game.animation.enemyAnimation;

import java.util.HashMap;
import java.util.Random;

public class BasicEnemy extends AbstractEnemy{
    /**
     * Holds the value of what world it is.
     */
    public static String WORLD = "Earth";

    private HashMap<String, enemyAnimation> fightAnimation;

    public BasicEnemy(int x, int y, SpriteBatch sb){
        this.x = x;
        this.y = y;
        health = 100;
        attack = 20;
        defence = 0;
        changeInString();
        fightAnimation = new HashMap<String, enemyAnimation>();
        fightAnimation.put("Idle",new enemyAnimation(sb, this, "enemyIdle.atlas", 1/15f));
        fightAnimation.put("Right",new enemyAnimation(sb, this, "enemyMoveRight.atlas", 1/15f));
        fightAnimation.put("Left",new enemyAnimation(sb, this, "enemyMoveLeft.atlas", 1/15f));
        fightAnimation.put("basicAttack",new enemyAnimation(sb, this, "enemyBasicAttack.atlas", 1/3f));
        fightAnimation.put("block",new enemyAnimation(sb, this, "enemyBasicBlock.atlas", 1/3f));
    }

    /**
     * Creates a character according to the value of the world.
     */
    private void changeInString() {
        if(WORLD == "Test Battle Screen")
        {

        }
    }

    public HashMap<String,enemyAnimation> getFightAnimation(){return fightAnimation;}

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
}
