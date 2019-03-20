package com.sps.game.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.animation.NpcAnimation;
import com.sps.game.maps.MapFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * This class creates a Merchant
 * @author Devin Shingadia
 * @version
 */
public class MerchantNPC extends AbstractNPC {
    /**
     * Keep record of the Merchants X coordinate.
     */
    private int x;
    /**
     * Keep record of the Merchants Y coordinate.
     */
    private int y;
    /**
     *
     */
    private int tick = 0; //May not need
    /**
     * Holds a Random number generator.
     */
    private Random random; //May not need;
    /**
     * Holds the current world the Merchant is in.
     */
    private MapFactory.MapType world;
    /**
     * Holds the Merchants velocity.
     */
    private Vector2 velocity;
    /**
     * Holds all the NpcAnimations of the Merchant.
     */
    private HashMap<String, NpcAnimation> animation;
    /**
     * Holds the current state of the Merchant.
     */
    private String state;
    /**
     * Holds the name of the merchant.
     */
    private String name;
    /**
     * Creates and Holds all the locations of all the Merchants
     */
    public static ArrayList<Location>allMerchantNPCLocations = new ArrayList<Location>();

    public MerchantNPC(int x, int y, MapFactory.MapType world, SpriteBatch sb, String name) {
        this.x = x;
        this.y = y;
        this.world = world;
        this.name = name;
        velocity = new Vector2();
        velocity.x = 0;
        velocity.y = 0;
        animation = new HashMap<String, NpcAnimation>();
        location = new Location(x, y);

        allMerchantNPCLocations.add(location);

        animation.put("idle",new NpcAnimation(sb,this, "npcMerchant" +"Idle.atlas",1/15f));

    }
    /**
     * Returns the NPC x Axis
     */
    @Override
    public int getX() {
        return x;
    }
    /**
     * Returns the NPC Y Axis
     */
    @Override
    public int getY() {
        return y;
    }
    /**
     * Returns the velocity of the NPC.
     */
    @Override
    public Vector2 getVelocity() {
        return velocity;
    }
    /**
     * Sets the value of the Y coordinate.
     * @param float newY
     */
    @Override
    public void setY(float newY) {
        y += newY;
    }
    /**
     * Sets the value of the X coordinate.
     * @param float newX
     */
    @Override
    public void setX(float newX) {
        x += newX;
    }
    /**
     * Gets the world the NPC is in
     * @return MapFactory.MapType world
     */
    @Override
    public MapFactory.MapType getWorld() {
        return world;
    }
    /**
     * Gets the current animation of the NPC.
     * @return NpcAnimation
     */
    @Override
    public NpcAnimation getAnimation() {
        return animation.get("idle");
    }
    /**
     * Changes the state of the NPC.
     * @param String newState
     */
    @Override
    public void changeState(String newState) {
        state = newState;
    }
    /**
     * Get the name of the NPC.
     * @return String name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the Location of the Merchant.
     * @return
     */
    public Location getLocation() {
        return location;
    }
}
