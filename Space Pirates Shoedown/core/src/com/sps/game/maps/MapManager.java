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
import org.json.simple.parser.ParseException;
import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
                String currentMap = profileManager.getProperty("currentMapType");
                //, String.class
                MapFactory.MapType mapType;
                if(currentMap == null || currentMap.isEmpty()){
                    mapType = MapFactory.MapType.HomeWorldMap1;
                } else{
                    mapType = MapFactory.MapType.valueOf(currentMap);
                }
                loadMap(mapType);

                //MapFactory.getMap(MapFactory.MapType.HomeWorldMap1).setPlayerPosition(getPlayerX());
               /* Vector2 homeWorldMap1StartPosition = new Vector2(getPlayerX());
                if(homeWorldMap1StartPosition != null){
                    MapFactory.getMap(MapFactory.MapType.HomeWorldMap1).setPlayerPosition(homeWorldMap1StartPosition);
                }
                Vector2 homeWorldMap2StartPosition = new Vector2(getPlayerX());
                if(homeWorldMap2StartPosition != null){
                    MapFactory.getMap(MapFactory.MapType.HomeWorldMap2).setPlayerPosition(homeWorldMap2StartPosition);
                }*/

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

    public void getPlayerX() {
        int x = 0;
        int y = 0;

        ProfileManager profileManager = new ProfileManager();
        if (profileManager.doesProfileExist("default")) {
            FileReader file = null;
            JSONParser parser = new JSONParser();
            try {
                file = new FileReader("default.json");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Object obj = null;
            try {
                obj = parser.parse(file);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            JSONObject jsonObject = (JSONObject) obj;
            Iterator<String> iterator = jsonObject.keySet().iterator();

            for(Iterator iterator1 = jsonObject.keySet().iterator(); iterator.hasNext();) {
                String key = (String) iterator1.next();
                char[] array = key.toCharArray();
                System.out.print(array);
                System.out.println(jsonObject.get(key) + "\n");
            }
            /*Vector2 vector2 = new Vector2(x, y);
            return vector2;*/
        }
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
