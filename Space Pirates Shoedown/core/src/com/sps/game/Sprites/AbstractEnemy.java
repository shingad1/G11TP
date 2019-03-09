package com.sps.game.Sprites;

import com.sps.game.Animation.enemyAnimation;
import com.sps.game.Controller.CombatSystem;
import com.sps.game.Screens.Fighter;

import java.util.HashMap;

public abstract class AbstractEnemy extends Fighter {

    /**
     * Holds the enemies X coordinate.
     */
    public int x;
    /**
     * Holds the enemies Y coordinate.
     */
    public int y;

    protected CombatSystem system;

    protected HashMap<String, enemyAnimation> fightAnimation;

    /**
     * Abstract method that will return the enemies X coordinate.
     * @return <code>int</code> X coordinate
     */
    public abstract int getX();

    /**
     * Abstract method that will return the enemies Y coordinate.
     * @return <code>int</code> Y coordinate
     */
    public abstract int getY();

    public abstract void battleMove();


    public void setCombatSystem(CombatSystem system){this.system = system;}

    public abstract HashMap<String,enemyAnimation> getFightAnimation();

}
