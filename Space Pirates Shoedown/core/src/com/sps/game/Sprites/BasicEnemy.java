package com.sps.game.Sprites;

public class BasicEnemy extends AbstractEnemy{

    public static String WORLD = "Earth";

    public BasicEnemy(int x, int y){
        this.x = x;
        this.y = y;
        health = 100;
        ChangeInString();
    }

    private void ChangeInString()
    {
        if(WORLD == "Test Battle Screen")
        {

        }
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

    public void decreaseHealth(int decrease){
        health -= decrease;
    }
}
