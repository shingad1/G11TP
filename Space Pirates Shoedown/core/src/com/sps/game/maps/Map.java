package com.sps.game.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.sps.game.sprites.Location;
import com.sps.game.sprites.Player;
import com.sps.game.Utility;

/**
 * This class contains the common methods and fields for each Map.
 * @author Miraj Shah and Miguel Abaquin.
 * @version 1.0
 */
public abstract class Map{
    /**
     * Holds the name of the class
     */
    private static final String TAG = Map.class.getSimpleName();

    /**
     * Constant field to direct where the file is located.
     */
    public static final String ASSETS_PATH = "tiledassets/";

    protected Json json; //may need to be removed
    /**
     * Holds the current position of the player.
     */
    protected Vector2 playerPosition;
    /**
     * Holds an instance of the player.
     */
    protected Player p;
    /**
     * Holds the current tiled map to be displayed.
     */
    protected TiledMap currentMap;
    /**
     * Holds an array contains all the locations of the npc's.
     */
    protected Array<Location> npcPositions;
    /**
     * Holds the collision layer of the current map.
     */
    protected TiledMapTileLayer collisionLayer;
    /**
     * Holds the type of the current map.
     */
    protected MapFactory.MapType currentMapType;

    Map(MapFactory.MapType mapType, String fullMapPath){
        json = new Json();
        p = Player.getPlayer();
        playerPosition = p.getVelocity();
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

    /**
     * Returns the type of current type of the map.
     * @return MapFactory.MapType
     */
    public MapFactory.MapType getCurrentMapType(){
        return currentMapType;
    }

    /**
     * Sets the type of the map.
     * @param type
     */
    public void setMapType(MapFactory.MapType type){
        currentMapType = type;
    }

    /**
     * Returns the position of the player.
     * @return Vector2 playerPosition
     */
    public Vector2 getPlayerPosition(){
        Location loc = p.getLocation();
        playerPosition = new Vector2(loc.getX(), loc.getY());
        return playerPosition;
    }

    /**
     * Sets the position of the player.
     * @param position
     */
    public void setPlayerPosition(Vector2 position){
        System.out.println(position.x + " " + position.y);
        p.setPosition((int) position.x, (int) position.y);
    }

    /**
     * Returns the collision layer of the map.
     * @return TiledMapTileLayer collisionLayer
     */
    public TiledMapTileLayer getCollisionLayer() {
        return collisionLayer;
    }

    /**
     * Returns the current tiled map.
     * @return TiledMap currentMap
     */
    public TiledMap getCurrentMap(){
        return currentMap;
    }

    protected void dispose(){

    }
}
