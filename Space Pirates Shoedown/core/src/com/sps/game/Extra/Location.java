package com.sps.game.Extra;

import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

public class Location {

    public HashMap<Vector2, Boolean> locations;

    private Location(){
        locations = new HashMap<Vector2, Boolean>();
    }

    public void addLocation(Vector2 v, Boolean b){
        locations.put(v,b);
    }

    public void removeLocation(Vector2 v){
        locations.remove(v);
    }

    public HashMap<Vector2, Boolean> getLocations() {
        return locations;
    }

    public void clearLocations(){
        locations.clear();
    }
}