package com.sps.game.Sprites;

public class BasicEnemy extends AbstractEnemy{

    public BasicEnemy(int x, int y){
        this.x = x;
        this.y = y;
        health = 100;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getHealth() {
        return health;
    }
}
