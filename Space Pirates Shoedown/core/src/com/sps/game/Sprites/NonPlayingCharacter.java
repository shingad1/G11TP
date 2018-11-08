package com.sps.game.Sprites;

import java.util.Random;

public class NonPlayingCharacter {
    private int x;
    private int y;

    private int time = 0;
    private Random random;

    public NonPlayingCharacter(int x, int y)
    {
        this.x = x;
        this.y = y;
        random = new Random();
    }

    public void update() {
        time++;
        if (time % (random.nextInt(50) + 30) == 0)
        {
            x = random.nextInt(3) - 1; //change direction
            y = random.nextInt(3) - 1;

            if (random.nextInt(3) ==0)//stop
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
    }

    public int NPCGetY()
    {
        return y;
    }
}
