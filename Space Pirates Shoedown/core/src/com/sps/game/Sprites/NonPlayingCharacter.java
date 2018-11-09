<<<<<<< HEAD
package com.sps.game.Sprites;

import java.util.Random;

public class NonPlayingCharacter
{
    /**
     * Stores the NPC character's x co-ordinate
     * @see #update()
     */
    private int x; //keep record of the NPC's x co-ordinate
    /**
     * Stores the NPC character's y co-ordinate
     * @see #update()
     */
    private int y; //keep record of the NPC's y co-ordinate
    /**
     * keeps record of the time
     * @see #update()
     */
    private int time = 0;
    /**
     * Stores the random variable so it can be used for the movement
     * @see #update()
     */
    private Random random;

    public NonPlayingCharacter(int x, int y)
    {
        this.x = x;
        this.y = y;
        random = new Random();
    }
    /**
     * this method allows the NPC to move, it also changes direction as well as stops every now and then
     */
    public void update() {
        time++; //increases the time
        if (time % (random.nextInt(50) + 30) == 0) //uses the mod in order to change direction every second (the range is from 30-80)
        {
            x = random.nextInt(3) - 1; //change direction
            y = random.nextInt(3) - 1;

            if (random.nextInt(3) ==0)//stop's the NPC every few seconds randomly
            {
                x = 0;
                y = 0;
            }
        }
        if (x > 0) {
            x+= 1;
        }
        if (x < 0) {
            x-=1;
        }
        if (y > 0) {
            y+=1;
        }
        if (y > 0) {
            y-=1;
        }
    }
    /**
     * this method returns the NPC x co-ordinate
     * @return <code>int</code>
     */
    public int NPCGetX()
    {
        return x;
    } //returns the x-axis
    /**
     * this method returns the NPC y co-ordinate
     * @return <code>int</code>
     */
    public int NPCGetY()
    {
        return y;
    } //returns the y-axis
}
=======
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
>>>>>>> e1f8a307cca8acdf98a0323dea42f1d847076107
