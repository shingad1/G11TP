package com.sps.game.sprites;

import com.badlogic.gdx.math.Vector2;
import com.sps.game.animation.NpcAnimation;
import com.sps.game.maps.MapFactory;


public abstract class AbstractNPC {
    /**
     * Stores the NPC character's x co-ordinate
     * @see
     */
    private int x;
    /**
     * Stores the NPC character's y co-ordinate
     * @see
     */
    private int y;

    /**
     *changes the speed of the NPC
     * @see
     */
    private Vector2 velocity;

    protected Location location;

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

    public abstract MapFactory.MapType getWorld();

    public abstract NpcAnimation getAnimation();

    public Location getLocation(){return location;}

    public abstract void changeState(String newState);

    public abstract String getName();
}
