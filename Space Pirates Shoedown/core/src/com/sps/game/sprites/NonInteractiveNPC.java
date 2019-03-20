package com.sps.game.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.animation.NpcAnimation;
import com.sps.game.maps.MapFactory;
import java.util.HashMap;
import java.util.Random;

/**
 * This class creates the Non Interactive NPCs
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia and Mahamuda Akhter
 */

public class NonInteractiveNPC extends AbstractNPC {

    /**
     * Stores the NPC character's x co-ordinate
     *
     * @see
     */
    private int x;
    /**
     * Stores the NPC character's y co-ordinate
     *
     * @see
     */
    private int y;
    /**
     * Uses Tick to break down movement into a number of iterations so that it doesnt move too fast
     *
     * @see
     */
    private int tick = 0;
    /**
     * Stores the random variable so it can be used for the movement
     *
     * @see
     */
    private Random random;
    /**
     * Stores the world as a string so we can use it for collisions detection
     *
     * @see #getWorld()
     */
    private MapFactory.MapType world;
    /**
     * changes the speed of the NPC
     *
     * @see
     */
    private Vector2 velocity;

    /**
     * Holds the animated textures of the NPC.
     * Key = A string which specifies the movement
     * Value = The textureAtlas to display.
     */
    private HashMap<String, NpcAnimation> animation;
    /**
     * Holds the current state of the NPC.
     */
    private String state;

    /**
     * Holds the name of the NonInteractiveNPC
     */
    private String name;

    //"npc<ROLE><Direction>.atlas" camel case i.e. "npcMerchantDown.atlas" do this for ALL AbstractNPC
    public NonInteractiveNPC(int x, int y, MapFactory.MapType world, SpriteBatch sb, String role) {
        this.x = x;
        this.y = y;
        this.world = world;
        velocity = new Vector2();
        velocity.x = 0;
        velocity.y = 0;
        animation = new HashMap<String, NpcAnimation>();

        animation.put("down", new NpcAnimation(sb, this, "npc" + role + "Down.atlas", 1 / 15f));
        animation.put("up", new NpcAnimation(sb, this, "npc" + role + "Up.atlas", 1 / 15f));
        animation.put("left", new NpcAnimation(sb, this, "npc" + role + "Left.atlas", 1 / 15f));
        animation.put("right", new NpcAnimation(sb, this, "npc" + role + "Right.atlas", 1 / 15f));
        animation.put("idle", new NpcAnimation(sb, this, "npc" + role + "Idle.atlas", 1 / 15f));

        location = new Location(x, y);
    }

    /**
     * Returns the NPc x Axis
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y Axis
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the world
     */
    public MapFactory.MapType getWorld() {
        return world;
    }

    /**
     * Changes the speed of the NPC
     * @see
     */
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
     * Gets the current animation of the NPC.
     * @return NpcAnimation
     */
    public NpcAnimation getAnimation() {
        return animation.get(state);
    }
    /**
     * Changes the state of the NPC.
     * @param newState
     */
    public void changeState(String newState) {
        state = newState;
    }
    /**
     * Get the name of the NPC.
     * @return String name
     */
    @Override
    public String getName() {
        return null;
    }

    /**
     * Gets the location of the NPC.
     * @return Location location
     */
    public Location getLocation() {
        return location;
    }

}
