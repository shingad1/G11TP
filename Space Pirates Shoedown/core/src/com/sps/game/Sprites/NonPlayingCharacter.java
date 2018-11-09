package com.sps.game.Sprites;

import java.util.Random;
import com.badlogic.gdx.math.Vector2;

public class NonPlayingCharacter
{
    private int x; //keep record of the NPC's x co-ordinate
    /**
     * Stores the NPC character's x co-ordinate
     * @see #update()
     */
    private int y; //keep record of the NPC's y co-ordinate
    /**
     * Stores the NPC character's y co-ordinate
     * @see #update()
     */
    private int tick = 0;
    /**
     * Uses Tick to break down movement into a number of iterations so that it doesnt move too fast
     * @see #update()
     */
    private Random random;
    /**
     * Stores the random variable so it can be used for the movement
     * @see #update()
     */
    private String world;
    /**
     * Stores the world as a string so we can use it for collisions detection
     * @see #getWorld()
     */
    private Vector2 velocity;
    /**
     *changes the speed of the NPC
     * @see #update() #reset
     */
    public NonPlayingCharacter(int x, int y, String world)
    {
        this.x = x;
        this.y = y;
        random = new Random();
        this.world = world;
        velocity = new Vector2();
        velocity.x = 0;
        velocity.y = 0;
    }
    /**
     * This method updates the movement for the NPc
     */
    public void update() {
        if (tick == 0){
            switch (random.nextInt(6) + 1){
                case 2:
                    velocity.y = 2;
                    break;
                case 3:
                    velocity.y = -2;
                    break;
                case 4:
                    velocity.x = 2;
                    break;
                case 5:
                    velocity.x = -2;
                    break;
            }
            tick = 1;
        } else {
            y += velocity.y;
            x += velocity.x;
            tick++;
            if(tick == 17){
                reset();
            }
        }
    }
    /**
     * Returns the NPc x Axis
     */
    public int NPCGetX()
    {
        return x;
    }
    /**
     * Returns the y Axis
     */
    public int NPCGetY()
    {
        return y;
    }
    /**
     * Returns the world
     */
    public String getWorld(){ return world;}
    /**
     * resets the velocity and the time
     */
    private void reset(){
        velocity.x = 0;
        velocity.y = 0;
        tick = 0;
    }
}
