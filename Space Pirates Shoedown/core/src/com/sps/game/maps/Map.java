package com.sps.game.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.sps.game.Utility;

public abstract class Map{//was abstract

    private static final String TAG = Map.class.getSimpleName();

    /**
     * Constant field to direct where the file is located.
     */
    public static final String ASSETS_PATH = "core/assets/tiledassets/";

    //public static final float UNIT_SCALE = 1/16f;

    protected Json json; //may need to be removed

    protected Vector2 playerPosition;
    //protected Vector2 convertedUnits;

    protected TiledMap currentMap;

    protected Array<Vector2> npcPositions;

    protected TiledMapTileLayer collisionLayer;

    protected MapFactory.MapType currentMapType;

    //protected Array<AbstractNPC> npcs;

    Map(MapFactory.MapType mapType, String fullMapPath){
        json = new Json();
        playerPosition = new Vector2(0,0);
        currentMapType = mapType;
        if(fullMapPath == null || fullMapPath.isEmpty()){
            Gdx.app.debug(TAG, "Map is invalid: " + fullMapPath);
            return;
        }

        Utility.loadMapAsset(fullMapPath);
        if(Utility.isAssetLoaded(fullMapPath)){
            currentMap = Utility.getMapAsset(fullMapPath);
        } else{
            Gdx.app.debug(TAG, "Map not loaded");
            return;
        }

        collisionLayer = (TiledMapTileLayer) currentMap.getLayers().get(1);
        if(collisionLayer == null){
            Gdx.app.debug(TAG, "No collision layer");
        }

        //npcPositions = getNPCStartPositions();
    }

    public MapFactory.MapType getCurrentMapType(){
        return currentMapType;
    }

    public Vector2 getPlayerPosition(){
        return playerPosition;
    }

    public void setPlayerPosition(Vector2 position){
        playerPosition = position;
    }

    public TiledMapTileLayer getCollisionLayer() {
        return collisionLayer;
    }

    public TiledMap getCurrentMap(){
        return currentMap;
    }

    protected void dispose(){

    }
}
