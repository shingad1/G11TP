package com.sps.game.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.sps.game.screens.HomeWorldScreen;
import com.sps.game.sprites.AbstractNPC;
import com.sps.game.sprites.Player;
import com.sps.game.profile.ProfileManager;
import com.sps.game.profile.ProfileObserver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class MapManager implements ProfileObserver {

    private static final String TAG = MapManager.class.getSimpleName();

    private Camera camera;

    private boolean mapChanged = false;

    private Map map;

    private Player player;

    private Array<AbstractNPC> npcs;

    public static final String JSON_FILE="default.json";

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

                //MapFactory.getMap(MapFactory.MapType.HomeWorldMap1).setPlayerPosition(getPlayerX());
                Vector2 homeWorldMap1StartPosition = new Vector2(getPlayerX());
                if(homeWorldMap1StartPosition != null){
                    MapFactory.getMap(MapFactory.MapType.HomeWorldMap1).setPlayerPosition(homeWorldMap1StartPosition);
                }
                Vector2 homeWorldMap2StartPosition = new Vector2(getPlayerX());
                if(homeWorldMap2StartPosition != null){
                    MapFactory.getMap(MapFactory.MapType.HomeWorldMap2).setPlayerPosition(homeWorldMap2StartPosition);
                }

                /*Vector2 homeWorldMap1StartPosition = new Vector2(profileManager.getProperty("HomeWorldMap1StartPosition", Vector2.class).x, profileManager.getProperty("HomeWorldMap1StartPosition", Vector2.class).y);
                if(homeWorldMap1StartPosition != null){
                    MapFactory.getMap(MapFactory.MapType.HomeWorldMap1).setPlayerPosition(homeWorldMap1StartPosition);
                }

                Vector2 homeWorldMap2StartPosition = new Vector2(profileManager.getProperty("HomeWorldMap2StartPosition", Vector2.class).x, profileManager.getProperty("HomeWorldMap2StartPosition", Vector2.class).y);
                if(homeWorldMap2StartPosition != null){
                    MapFactory.getMap(MapFactory.MapType.HomeWorldMap2).setPlayerPosition(homeWorldMap2StartPosition);
                }*/
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

    public Vector2 getPlayerX() {
        int x = 0;
        int y = 0;
        ProfileManager profileManager = new ProfileManager();
        if (profileManager.doesProfileExist(JSON_FILE)) {
            InputStream fis = null;
            try {
                fis = new FileInputStream(JSON_FILE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            JsonReader jsonReader = Json.createReader(fis);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            x = jsonObject.getInt("x");
            y = jsonObject.getInt("y");
        }
        /*else{
            x =0;
            y = 0;
        }*/
        Vector2 vector2 = new Vector2(x, y);
        return vector2;
    }

    public int getPlayerY() {
        int y = 0;
        ProfileManager profileManager = new ProfileManager();
        if (profileManager.doesProfileExist(JSON_FILE)) {
            InputStream fis = null;
            try {
                fis = new FileInputStream(JSON_FILE);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            JsonReader jsonReader = Json.createReader(fis);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            y = jsonObject.getInt("y");
        }
        else {
            y = 0;
        }
        return y;
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
