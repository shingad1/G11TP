package com.sps.game.sprites;

import com.badlogic.gdx.math.Vector2;

public class Location {
    //Field used to store the location
    private Vector2 location;

    public Location(int x,int y){
        location = new Vector2(x,y);
    }

    public float getX() {return location.x;}

    public float getY() {return location.y;}

    public void setX(int x){location.x = x;}

    public void setY(int y){location.y = y;}

    public boolean equals(Location another){
        return(Math.round(this.getX()) == Math.round(another.getX()) && Math.round(this.getY()) == Math.round(another.getY()));
    }

    public String toString(){return getX() + "," + getY();}

}
