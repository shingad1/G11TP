package com.sps.game.Sprites;

public abstract class AbstractEnemy {

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

}
