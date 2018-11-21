package com.sps.game.Sprites;

import com.badlogic.gdx.math.Vector2;



public abstract class AbstractNPC {
    /**
     * Stores the NPC character's x co-ordinate
     * @see #update()
     */
    private int x;
    /**
     * Stores the NPC character's y co-ordinate
     * @see #update()
     */
    private int y;

    /**
     *changes the speed of the NPC
     * @see #update() #reset
     */
    private Vector2 velocity;


    /**
     * Returns the NPc x Axis
     */
    public abstract int getX();
    /**
     * Returns the y Axis
     */
    public abstract int getY();
    /**
     *
     */
    public abstract Vector2 getVelocity();

    public abstract void setY(float newY);
    public abstract void setX(float newX);


}
