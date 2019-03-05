package com.sps.game.Sprites;

import com.badlogic.gdx.math.Vector2;
import com.sps.game.Animation.enemyAnimation;
import com.sps.game.Animation.npcAnimation;
import com.sps.game.Controller.CombatSystem;
import com.sps.game.Screens.Fighter;
import com.sps.game.maps.MapFactory;

import java.util.HashMap;


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

    public abstract npcAnimation getAnimation();

    public Location getLocation(){return location;}

    public abstract void changeState(String newState);



}
