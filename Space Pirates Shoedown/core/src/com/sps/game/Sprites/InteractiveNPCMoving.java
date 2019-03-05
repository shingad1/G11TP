package com.sps.game.Sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.Animation.npcAnimation;
import com.sps.game.maps.MapFactory;

import java.util.HashMap;
import java.util.Random;

public class InteractiveNPCMoving extends AbstractNPC {
    /**
     * Stores the NPC character's x co-ordinate
     *
     * @see
     */
    private int x; //keep record of the NPC's x co-ordinate
    //private int x;
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
    private HashMap<String, npcAnimation> animation;

    private String state;

    /**
     * Holds the name of the InteractiveNPC
     */
    private String name;


    public InteractiveNPCMoving(int x, int y, MapFactory.MapType world, SpriteBatch sb, String role) {
        this.x = x;
        this.y = y;

        this.world = world;
        velocity = new Vector2();
        velocity.x = 0;
        velocity.y = 0;
        animation = new HashMap<String, npcAnimation>();
        setName(name);

        animation.put("down", new npcAnimation(sb, this, "npc" + role + "Down.atlas", 1 / 15f));
        animation.put("up", new npcAnimation(sb, this, "npc" + role + "Up.atlas", 1 / 15f));
        animation.put("left", new npcAnimation(sb, this, "npc" + role + "Left.atlas", 1 / 15f));
        animation.put("right", new npcAnimation(sb, this, "npc" + role + "Right.atlas", 1 / 15f));
        animation.put("idle", new npcAnimation(sb, this, "npc" + role + "Idle.atlas", 1 / 15f));

        location = new Location(x, y);

    }

    public npcAnimation getAnimation(String direction) {return animation.get(direction);}

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


    public Vector2 getVelocity() {
        return velocity;
    }

    @Override
    public void setY(float newY) {
        y += newY;
    }

    @Override
    public void setX(float newX) {
        x += newX;
    }

    public npcAnimation getAnimation() {
        return animation.get(state);
    }

    public void changeState(String newState) {
        state = newState;
    }

    public String getState(){return state;}

    public Location getLocation() {
        return location;
    }

    public void setName(String name) { this.name = name;}

    public String getName() {return name;}

    public String getType() {return "InteractiveNPCMoving";}

}






