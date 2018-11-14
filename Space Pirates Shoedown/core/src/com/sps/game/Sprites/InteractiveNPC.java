package com.sps.game.Sprites;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class InteractiveNPC extends AbstractNPC{
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
     * Uses Tick to break down movement into a number of iterations so that it doesnt move too fast
     * @see #update()
     */
    private int tick = 0;
    /**
     * Stores the random variable so it can be used for the movement
     * @see #update()
     */
    private Random random;
    /**
     *changes the speed of the NPC
     * @see #update() #reset
     */
    private Vector2 velocity;

    public InteractiveNPC(){

    }

    @Override
    public int NPCGetX() {
        return x;
    }

    @Override
    public int NPCGetY() {
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

}
