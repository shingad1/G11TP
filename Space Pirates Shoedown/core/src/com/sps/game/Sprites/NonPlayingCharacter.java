package com.sps.game.Sprites;

import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class NonPlayingCharacter {
    private int x; //keep record of the NPC's x co-ordinate
    private int y; //keep record of the NPC's y co-ordinate

   //private int time = 0; //keep record of the time
    private int tick = 0; //Uses Tick to break down movement into a number of iterations so that it doesnt move too fast
    private Random random;
    private String world;
    private Vector2 velocity;


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

    public int NPCGetX()
    {
        return x;
    } //returns the x-axis

    public int NPCGetY()
    {
        return y;
    } //returns the y-axis

    public String getWorld(){ return world;} //returns the world

    private void reset(){
        velocity.x = 0;
        velocity.y = 0;
        tick = 0;
    }
}
