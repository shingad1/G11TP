package com.sps.game.sprites;

import com.badlogic.gdx.math.Vector2;
import com.sps.game.animation.EnemyAnimation;
import com.sps.game.controller.CombatSystem;
import com.sps.game.maps.MapFactory;
import com.sps.game.screens.Fighter;

import java.util.HashMap;
/**
 * This class holds the basic implementation of all types of enemies.
 * @author Miraj Shah, Miguel Abaquin
 * @version 1.0
 */
public abstract class AbstractEnemy implements Fighter {

    /**
     * Holds the health of the enemy.
     */
    protected int health;
    /**
     * Holds the location of the enemy.
     */
    protected Location location;
    /**
     * Holds an integer value containing the damage the attack can inflict.
     */
    protected int attack;
    /**
     * Holds an integer value containing the health gained from the difference.
     */
    protected int defence;

    //protected CombatSystem system;
    /**
     * Holds a HashMap containing all the Animations of the Enemy during the battle.
     */
    protected HashMap<String, EnemyAnimation> fightAnimation;
    /**
     * Holds a HashMap containing all the Animations of the Enemy when rendered.
     */
    protected HashMap<String, EnemyAnimation> animation;

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
     * Gets the velocity of the enemy.
     * @return Vector2 velocity
     */
    public abstract Vector2 getVelocity();
    /**
     * Returns the world the enemy is in.
     * @return MapFactory.MapType world.
     */
    public abstract MapFactory.MapType getWorld();
    /**
     * Gets the current animation of the enemy, outside of the battle.
     * @return EnemyAnimation
     */
    public abstract EnemyAnimation getAnimation();

    /**
     * Returns the current location of the Enemy.
     * @return Location location
     */
    public Location getLocation(){
        return location;
    }

    /**
     * Abstract method that will return the enemies health.
     * @return <code>int</code> Health, which stores the enemies health
     */
    public abstract int getHealth();

    /**
     *
     */
    public abstract void battleMove();

    /**
     *
     * @return
     */
    public int getAttack(){return attack;}

    /**
     *
     * @return
     */
    public int getDefence(){return defence;}

    /**
     * Returns the current health of the enemy.
     * @return
     */
    public int getHP(){return health;}

    /**
     *
     * @param diff
     */
    public void changeHP(int diff){health += diff;}

    /**
     * Returns a HashMap contains all the Fighting animations of the enemy.
     * @return HashMap<String,EnemyAnimation> fightAnimation
     */
    public abstract HashMap<String, EnemyAnimation> getFightAnimation();

    /**
     * Returns the name of the enemy.
     * @return String name
     */
    public abstract String getName();

    /**
     * Changes the state of the Enemy.
     * @param newState
     */
    public abstract void changeState(String newState);

    //public void setCombatSystem(CombatSystem system){this.system = system;}

}
