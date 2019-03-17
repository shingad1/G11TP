package com.sps.game.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.sps.game.screens.HomeWorldScreen;
import com.sps.game.sprites.AbstractNPC;
import com.sps.game.sprites.Player;
import com.sps.game.profile.ProfileManager;
import com.sps.game.profile.ProfileObserver;

/**
 * This class stores the current state of the Map.
 * @author Miraj Shah
 * @version 1.0
 */
public class MapManager implements ProfileObserver {
    /**
     * Holds the name of the class.
     */
    private static final String TAG = MapManager.class.getSimpleName();
    /**
     * Holds the camera
     */
    private Camera camera;
    /**
     * Boolean checks if the map has changed. True if it has, otherwise false.
     */
    private boolean mapChanged = false;
    /**
     * Holds the map.
     */
    private Map map;
    /**
     * Holds an instance of the player.
     */
    private Player player;
    /**
     * Holds an array of all types of NPCs.
     */
    private Array<AbstractNPC> npcs;

    public MapManager(){
    }

    /**
     * Observes the current events and takes out the appropriate action according to the event.
     * @param profileManager
     * @param event
     */
    @Override
    public void onNotify(ProfileManager profileManager, ProfileEvent event) {
        switch (event){
            case PROFILE_LOADED:
                String currentMap = profileManager.getProperty("currentMapType", String.class);
                MapFactory.MapType mapType;
                if(currentMap == null || currentMap.isEmpty()){
                    mapType = MapFactory.MapType.HomeWorldMap1;
                } else{
                    mapType = MapFactory.MapType.valueOf(currentMap);
                }
                loadMap(mapType);

                Vector2 homeWorldMap1StartPosition = new Vector2(profileManager.getProperty("HomeWorldMap1StartPosition", Vector2.class).x, profileManager.getProperty("HomeWorldMap1StartPosition", Vector2.class).y);
                if(homeWorldMap1StartPosition != null){
                    MapFactory.getMap(MapFactory.MapType.HomeWorldMap1).setPlayerPosition(homeWorldMap1StartPosition);
                }

                Vector2 homeWorldMap2StartPosition = new Vector2(profileManager.getProperty("HomeWorldMap2StartPosition", Vector2.class).x, profileManager.getProperty("HomeWorldMap2StartPosition", Vector2.class).y);
                if(homeWorldMap2StartPosition != null){
                    MapFactory.getMap(MapFactory.MapType.HomeWorldMap2).setPlayerPosition(homeWorldMap2StartPosition);
                }
                break;

            case SAVING_PROFILE:
                map = MapFactory.getMap(getCurrentMapType());
                if(map != null) {
                    profileManager.setProperty("currentMapType", HomeWorldScreen.getCurrentMapType().toString()); //map.getCurrentMapType().toString()
                    System.out.println("Saving game as " + HomeWorldScreen.getCurrentMapType().toString());
                    //profileManager.setProperty("currentCollisionLayer", map.getCollisionLayer());
                }
                profileManager.setProperty("homeWorldMap1StartPosition", MapFactory.getMap(MapFactory.MapType.HomeWorldMap1).getPlayerPosition());
                profileManager.setProperty("homeWorldMap2StartPosition", MapFactory.getMap(MapFactory.MapType.HomeWorldMap2).getPlayerPosition());
                break;
            case CLEAR_CURRENT_PROFILE:
                map = null;
                profileManager.setProperty("currentMapType", MapFactory.MapType.HomeWorldMap1.toString());
                MapFactory.clearCache();
                profileManager.setProperty("homeWorldMap1StartPosition", MapFactory.getMap(MapFactory.MapType.HomeWorldMap1).getPlayerPosition());
                profileManager.setProperty("homeWorldMap2StartPosition", MapFactory.getMap(MapFactory.MapType.HomeWorldMap2).getPlayerPosition());
                break;
            default:
                break;
        }
    }

    /**
     * Loads the map according to the MapType.
     * @param mapType
     */
    public void loadMap(MapFactory.MapType mapType){
        Map temp = MapFactory.getMap(mapType);
        if(temp == null){
            Gdx.app.debug(TAG, "Map does not exist");
            return;
        }

        //methods to load music
        map = temp;
        mapChanged = true;
        //clearCurrentSelectedMapEntity();
        Gdx.app.debug(TAG, "Player Start: (" + map.getPlayerPosition().x + "," + map.getPlayerPosition().y + ")");
    }

    public void unregisterCurrentMapEntityObservers(){
       //todo for entities
    }
/*
    public void registerCurrentMapEntityObservers(ComponentObserver observer){
        //todo for entities
    }
*/

    /**
     * Returns the collision layer of the current map.
     * @return MapLayer collisionLayer
     */
    public MapLayer getCollisionLayer(){
        return map.getCollisionLayer();
    }

    /**
     * Returns the current type of the map
     * @return MapFactory.MapType currentMapType.
     */
    public MapFactory.MapType getCurrentMapType(){
        return HomeWorldScreen.getCurrentMapType();
    }

    /**
     * Returns the current tiled map. If it is null, it will return a default of HomeWorldMap1.
     * @return TileMap currentMap
     */
    public TiledMap getCurrentTiledMap(){
        if(map == null){
            loadMap(MapFactory.MapType.HomeWorldMap1); //default
        }
        return map.getCurrentMap();
    }

    /**
     * Sets the player with its current instance.
     * @param player
     */
    public void setPlayer(Player player){
        this.player = player;
    }

    /**
     * Returns the instance of the player.
     * @return
     */
    public Player getPlayer(){ return this.player;}

    /**
     * Sets the camera.
     * @param camera
     */
    public void setCamera(Camera camera){
        this.camera = camera;
    }

    /**
     * Gets the current instance of the camera.
     * @return Camera camera
     */
    public Camera getCamera(){
        return camera;
    }

    /**
     * Returns the boolean mapChanged. True if the map has changed; false if it has not.
     * @return
     */
    public boolean hasMapChanged(){
        return mapChanged;
    }

    /**
     * Sets the value of mapChanged.
     * @param hasMapChanged
     */
    public void setMapChanged(boolean hasMapChanged){
        this.mapChanged = hasMapChanged;
    }
}
