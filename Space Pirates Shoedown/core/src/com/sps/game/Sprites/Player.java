package com.sps.game.Sprites;

import com.badlogic.gdx.math.Vector2;

/**
 * This class creates the player and sets the starting x and y coordinates.
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia
 * @version 1.0
 */
public class Player{
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


}
