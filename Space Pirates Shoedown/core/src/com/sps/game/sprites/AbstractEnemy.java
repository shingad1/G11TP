package com.sps.game.sprites;

import com.sps.game.animation.EnemyAnimation;
import com.sps.game.controller.CombatSystem;
import com.sps.game.screens.Fighter;

import java.util.HashMap;

public abstract class AbstractEnemy implements Fighter {

    /**
     * Holds the enemies X coordinate.
     */
    public int x;
    /**
     * Holds the enemies Y coordinate.
     */
    public int y;
    /**
     * Holds the health of the enemy.
     */
    public int health;
    protected int attack;
    protected int defence;

    protected CombatSystem system;

    protected HashMap<String, EnemyAnimation> fightAnimation;

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

    /**
     * Abstract method that will return the enemies health.
     * @return <code>int</code> Health, which stores the enemies health
     */
    public abstract int getHealth();

    public abstract void battleMove();

    public int getAttack(){return attack;}

    public int getDefence(){return defence;}

    public int getHP(){return health;}

    public void changeHP(int diff){health += diff;}

    public void setCombatSystem(CombatSystem system){this.system = system;}

    public abstract HashMap<String, EnemyAnimation> getFightAnimation();

}
