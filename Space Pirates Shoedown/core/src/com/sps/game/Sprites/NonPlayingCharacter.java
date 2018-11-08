package com.sps.game.Sprites;

import java.util.Random;

public class NonPlayingCharacter {
    private int x; //keep record of the NPC's x co-ordinate
    private int y; //keep record of the NPC's y co-ordinate

    private int time = 0; //keep record of the time
    private Random random;

    public NonPlayingCharacter(int x, int y)
    {
        this.x = x;
        this.y = y;
        random = new Random();
    }

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

    public int NPCGetX()
    {
        return x;
    } //returns the x-axis

    public int NPCGetY()
    {
        return y;
    } //returns the y-axis
}
