package com.sps.game.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.sps.game.Screens.HomeWorldScreen;
import com.sps.game.Sprites.AbstractNPC;
import com.sps.game.Sprites.Player;
import com.sps.game.profile.ProfileManager;
import com.sps.game.profile.ProfileObserver;

import javax.swing.text.html.parser.Entity;

public class MapManager implements ProfileObserver {

    private static final String TAG = MapManager.class.getSimpleName();

    private Camera camera;

    private boolean mapChanged = false;

    private Map map;

    private Player player;

    private Array<AbstractNPC> npcs;

    public MapManager(){
    }

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
    public MapLayer getCollisionLayer(){
        return map.getCollisionLayer();
    }

    public MapFactory.MapType getCurrentMapType(){
        return HomeWorldScreen.getCurrentMapType();
    }

    public TiledMap getCurrentTiledMap(){
        if(map == null){
            loadMap(MapFactory.MapType.HomeWorldMap1); //default
        }
        return map.getCurrentMap();
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public Player getPlayer(){ return this.player;}

    public void setCamera(Camera camera){
        this.camera = camera;
    }

    public Camera getCamera(){
        return camera;
    }

    public boolean hasMapChanged(){
        return mapChanged;
    }

    public void setMapChanged(boolean hasMapChanged){
        this.mapChanged = hasMapChanged;
    }
}
