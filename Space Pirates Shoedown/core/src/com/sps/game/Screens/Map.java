package com.sps.game.Screens;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Map {

    private TiledMap map;

    private TiledMapTileLayer collisionLayer;

    private String mapName;

    public Map(TiledMap map, String mapName){
        this.map = map;
        this.collisionLayer = (TiledMapTileLayer) map.getLayers().get(1);
        this.mapName = mapName;
    }

    public TiledMap getMap(){
        return map;
    }

    public TiledMapTileLayer getCollisionLayer(){
        return collisionLayer;
    }

    public String getMapName(){
        return mapName;
    }
}
