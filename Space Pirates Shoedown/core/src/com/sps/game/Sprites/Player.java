package com.sps.game.Sprites;

import com.badlogic.gdx.math.Vector2;

/**
 * This class creates the player and sets the starting x and y coordinates.
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia
 * @version 1.0
 */
public class Player{
    /**
     * Keeps track of the player's gold
     */
    private int gold;
    /**
     * Keeps track of the player's HP
     */
    private int HP;
    /**
     * Holds the players X coordinates.
     * @see #move #getX
     */
    private int x;
    /**
     * Holds the players Y coordinates.
     * @see #move #getY
     */
    private int y;
    /**
     * Holds the players velocity.
     * #getVelocity
     */
    private Vector2 velocity;

    public Player(int x, int y){
        this.x = x;
        this.y = y;
        velocity = new Vector2();
        velocity.x = 0; velocity.y = 0;
        gold = 50;
        HP = 100;
    }

    /**
     * Updates the players x and y coordinates according to the players movement.
     * @param <code>int</code>dx. Holds the change in the x axis.
     * @param <code>int</code>dy. Holds the change in the y axis
     */
    public void move(int dx, int dy){
        x += dx;
        y += dy;
    }

    /**
     * Gets the players X coordinate.
     * @return Returns a <code>int</code> X coordinate value.
     */
    public int getX(){
        return x;
    }

    /**
     * Gets the players Y coordinate.
     * @return Returns a <code>int</code> Y coordinate value.
     */
    public int getY(){
        return y;
    }

    /**
     * Gets the players velocity.
     * @return Returns a <code>Vector2</code> Velocity value.
     */
    public Vector2 getVelocity(){
        return velocity;
    }

    /**
     * Gets the amount of gold the player has
     * @return Returns a <code>int</code> gold value.
     */
    public int getGold(){
        return gold;
    }

    /**
     * Gets the health level of the player.
     * @return Returns a <code>int</code> health points value.
     */
    public int getHP(){
        return HP;
    }

    /**
     * Sets the position of the player on the screen
     * @param <code>int<code> dx, change in the x axis.
     * @param <code>int</code> dy, change in the y axis.
     */
    public void setPosition(int dx, int dy){
        x = dx;
        y = dy;
    }

    /**
     * Decreases the players health by an inputted amount.
     * @param <code>int</code> decrease, the amount to decrease the health level by.
     */
    public void decreaseHealth(int decrease){
        HP-=decrease;
    }

    /**
     * Increases the players health by an inputted amount.
     * @param <code>int</code> increase, the amount to increase the health by.
     */
    public void increaseHealth(int increase){
        HP+=increase;
    }
}
