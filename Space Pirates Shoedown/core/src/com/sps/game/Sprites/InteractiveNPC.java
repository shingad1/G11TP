package com.sps.game.Sprites;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.Animation.npcAnimation;

import java.util.Random;

public class InteractiveNPC extends AbstractNPC{
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
     * Uses Tick to break down movement into a number of iterations so that it doesnt move too fast
     * @see
     */
    private int tick = 0;
    /**
     * Stores the random variable so it can be used for the movement
     * @see
     */
    private Random random;
    /**
     *changes the speed of the NPC
     * @see
     */
    private Vector2 velocity;

    private npcAnimation animation;

    private String world;
    /**
     * Holds the name of the InteractiveNPC
     */
    private String name;

    public InteractiveNPC(int x, int y,String world, SpriteBatch sb, String name){
        this.x = x;
        this.y = y;
        location = new Location(x,y);
        animation = new npcAnimation(sb,this,"cryingNPC.atlas",1/2f);
        this.world = world;
        setName(name);
    }

    public npcAnimation getAnimation(){return animation;}

    public String getWorld(){return world;}

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Vector2 getVelocity() {
        return null;
    }

    @Override
    public void setY(float newY) {

    }

    @Override
    public void setX(float newX) {

    }

    public Location getLocation(){return location;}

    @Override
    public void changeState(String newState) {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
