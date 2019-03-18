package com.sps.game.sprites;

import com.badlogic.gdx.math.Vector2;

/**
 * This class holds all the location information of the NPC.
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia
 * @version 1.0
 */
public class Location {
    /**
     * Store the location.
     */
    private Vector2 location;

    public Location(int x,int y){
        location = new Vector2(x,y);
    }

    /**
     * Gets the X coordinate.
     * @return float x
     */
    public float getX() {return location.x;}

    /**
     * Gets the Y coordinate
     * @return float y
     */
    public float getY() {return location.y;}

    /**
     * Sets the X coordinate.
     * @param int x
     */
    public void setX(int x){location.x = x;}

    /**
     * Sets the Y coordinate.
     * @param float y
     */
    public void setY(int y){location.y = y;}

    /**
     * Compares the current location with another location. Returns true if they are the same location otherwise false.
     * @param Location another
     * @return boolean
     */
    public boolean equals(Location another){
        return(Math.round(this.getX()) == Math.round(another.getX()) && Math.round(this.getY()) == Math.round(another.getY()));
    }

    /**
     * Returns a string representation of the class.
     * @return String
     */
    public String toString(){return getX() + "," + getY();}

}
