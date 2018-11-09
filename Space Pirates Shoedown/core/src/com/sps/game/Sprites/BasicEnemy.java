package com.sps.game.Sprites;

public class BasicEnemy extends AbstractEnemy{
    /**
     * Holds the value of what world it is.
     */
    public static String WORLD = "Earth";

    public BasicEnemy(int x, int y){
        this.x = x;
        this.y = y;
        health = 100;
        changeInString();
    }

    /**
     * Creates a character according to the value of the world.
     */
    private void changeInString() {
        if(WORLD == "Test Battle Screen")
        {

        }
    }

    /**
     * Gets the enemies X coordinate.
     * @return Returns <code>int</code> X coordinate.
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * Gets the enemies Y coordinate.
     * @return Returns <code>int</code> Y coordinate.
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * Gets the enemies health level.
     * @return Returns <code>int</code> health level.
     */
    @Override
    public int getHealth() {
        return health;
    }

    /**
     * Decreases the enemies health by an inputted value.
     * @param <code>int</code> decrease, the amount to decrease the health by.
     */
    public void decreaseHealth(int decrease){
        health -= decrease;
    }
}
