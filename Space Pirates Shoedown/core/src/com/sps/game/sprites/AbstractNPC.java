package com.sps.game.sprites;

import com.badlogic.gdx.math.Vector2;
import com.sps.game.animation.NpcAnimation;
import com.sps.game.maps.MapFactory;

import java.util.ArrayList;

/**
 * This class holds the basic implementation of all types of NPCs.
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia
 * @version 1.0
 */
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
    /**
     * Holds the location of the NPC.
     */
    protected Location location;

    /**
     * Returns the NPC x Axis
     */
    public abstract int getX();
    /**
     * Returns the y Axis
     */
    public abstract int getY();
    /**
     * Returns the velocity of the NPC.
     */
    public abstract Vector2 getVelocity();

    /**
     * Sets the value of the Y coordinate.
     * @param float newY
     */
    public abstract void setY(float newY);

    /**
     * Sets the value of the X coordinate.
     * @param newX
     */
    public abstract void setX(float newX);

    /**
     * Gets the world the NPC is in
     * @return MapFactory.MapType world
     */
    public abstract MapFactory.MapType getWorld();

    /**
     * Gets the current animation of the NPC.
     * @return NpcAnimation
     */
    public abstract NpcAnimation getAnimation();

    /**
     * Gets the location of the NPC.
     * @return Location location
     */
    public Location getLocation(){return location;}

    /**
     * Changes the state of the NPC.
     * @param String newState
     */
    public abstract void changeState(String newState);

    /**
     * Get the name of the NPC.
     * @return String name
     */
    public abstract String getName();
}
