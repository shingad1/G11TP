package com.sps.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.SpacePiratesShoedown;
import com.sps.game.controller.NPCController;
import com.sps.game.controller.PlayerController;
import com.sps.game.maps.*;
import com.sps.game.sprites.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class holds all the different maps of the Graveyard World, and contains its own unique functionality,
 * specific to this world.
 * @author Miraj Shah
 * @version 1.0
 */
public class GraveyardScreen extends PlayScreen {

    /**
     * 2D array, that contains all the maps for the graveyard world
     */
    private Map[][] worldMaps = {{null, new GraveyardNorthMap(), null},
                                {new GraveyardWestMap(), new GraveyardMap(), new GraveyardEastMap()}};
    /**
     * Chooses the map to load from the array
     */
    private Vector2 mapSelector;
    /**
     * An array containing the bounds of the x axis
     */
    private int[] xbounds = {0,1600};
    /**
     * An array containing the bounds of the y axis
     */
    private int[] ybounds = {0,1600};

    public GraveyardScreen(SpacePiratesShoedown game, Vector2 selector, int px, int py) {
        super(game);
        mapSelector = selector;
        Map selectedMap = worldMaps[Math.round(mapSelector.y)][Math.round(mapSelector.x)];
        currentMap = selectedMap.getCurrentMap();
        renderer = new OrthogonalTiledMapRenderer(currentMap);
        currentCollisionLayer = selectedMap.getCollisionLayer();
        currentMapState = selectedMap.getCurrentMapType();
        npc = new ArrayList<AbstractNPC>();
        npcController = new ArrayList<NPCController>();
        oldState = MapFactory.MapType.GraveyardWorld1;
        npc.add(new InteractiveNPC(864,192,MapFactory.MapType.GraveyardWorld1, batch,"Gravewelcome"));

        allLocations = new ArrayList<Location>();
        enemies = new ArrayList<AbstractEnemy>();
        playerCombatPosition = new Location(768,1376);
        enemyCombatPosition = new Location(896, 1376);
        createEnemies(startNum);
        changeNpcLocations(selectedMap);
        p.setX(px);
        p.setY(py);
        p.setBatch(batch);
        controller = new PlayerController(p,currentCollisionLayer,xbounds,ybounds,allLocations);
        gamecam.position.set(p.getX(), p.getY(), 0);
        music = Gdx.audio.newMusic(Gdx.files.internal("Music/graveyard.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
    }
    /**
     * Checks if the location is occupied by an npc or a blocked tile
     * @param Location location
     * @return boolean, True if there is no NPC or blocked tile or nonpc tile otherwise false.
     */
    @Override
    public boolean checkPosition(Location location, MapFactory.MapType world) {
        for (AbstractNPC nonPlayingCharacter : getMapNPC(world)) {
            if (location.equals(nonPlayingCharacter.getLocation())) {
                return false;
            }
        }
        if(getCell(location, world) == null || getCell(location,world).getTile().getProperties().containsKey("blocked") || getCell(location, world).getTile().getProperties().containsKey("nonpc")){
            return false;
        }
        return true;
    }
    /**
     * Returns a cell according to the Map and the location
     * @param Location location
     * @param MapFactory.MapType map
     * @return TiledMapTileLayer.Cell
     */
    public TiledMapTileLayer.Cell getCell(Location location, MapFactory.MapType map){
        return getMap(getWorldMapByWorld(map)).getCollisionLayer().getCell((int) location.getX() / 32, (int) location.getY()/32);
    }
    /**
     * Changes the map that is rendered once the player is on a certain location or going of the screen
     */
    @Override
    public void changeMaps() {
        int camX = 0; //shows if the camera needs to move left or right
        int camY = 0; //shows if the camera needs to go up or down
        if (controller.getNewWorldRight()){
            mapSelector.x += 1;
            p.setX(0);
            camX = 1;
        } else if (controller.getNewWorldLeft()){
            mapSelector.x -= 1;
            p.setX(49 * 32);
            camX = -1;
        } else if (controller.getNewWorldDown()){
            mapSelector.y += 1;
            p.setY(49 * 32);
            camY = -1;
        } else if (controller.getNewWorldUp()){
            mapSelector.y -= 1;
            p.setY(0);
            camY = 1;
        }
        if(currentMapState.equals(MapFactory.MapType.GraveyardWorld1)){
            if(p.getLocation().equals(new Location(800,1536)) || p.getLocation().equals(new Location(832,1536))){
                dispose();
                game.setScreen(new GraveyardScreen(game, new Vector2(1,0),800, 32));
            }
        }

        if(currentMapState.equals(MapFactory.MapType.GraveyardWest)){
            if(p.getLocation().equals(new Location(736,1408)) || p.getLocation().equals(new Location(768,1408))){
                dispose();
                oldState = MapFactory.MapType.GraveyardWest;
                game.setScreen(new HouseInteriorScreen(game, new Vector2(4,0)));
            }
        } else if(currentMapState.equals(MapFactory.MapType.GraveyardEast)){
            if(p.getLocation().equals(new Location(800,1440)) || p.getLocation().equals(new Location(832,1440))){
                dispose();
                oldState = MapFactory.MapType.GraveyardEast;
                game.setScreen(new HouseInteriorScreen(game, new Vector2(4,1)));
            }
        }
        if(camX != 0 || camY != 0) {
            Map selectedMap = worldMaps[Math.round(mapSelector.y)][Math.round(mapSelector.x)]; //change when moving worlds
            currentMap = selectedMap.getCurrentMap();//change when moving worlds
            //selectedMap.setMapType(selectedMap.getCurrentMapType());
            renderer = new OrthogonalTiledMapRenderer(currentMap); //change when moving worlds
            currentCollisionLayer = (TiledMapTileLayer) currentMap.getLayers().get(1); //change when moving worlds
            currentMapState = selectedMap.getCurrentMapType(); //change when moving worlds

            System.out.println("Entering " + currentMapState);
            gamecam.position.set(p.getX()+(240 * camX), p.getY() + (240 * camY), 0); //change when moving worlds
            changeNpcLocations(selectedMap);
            controller.changeNpcLocations(allLocations);
            controller.changeCollisionLayer(currentCollisionLayer, xbounds, ybounds);
            controller.newWorldReset();
        }
    }
    /**
     * Returns a map from the array according to the vector2 value passed in as a parameter
     * @param Vector2 selector
     * @return Map
     */
    @Override
    public Map getMap(Vector2 selector) {
        return worldMaps[Math.round(selector.x)][Math.round(selector.y)];
    }
    /**
     * Returns a Vector2 value to get a Map according to the map type specified in the parameter
     * @param MapFactory.MapType map
     * @return Vector2
     */
    @Override
    public Vector2 getWorldMapByWorld(MapFactory.MapType map) {
        for(int i = 0; i < worldMaps.length; i++){
            for (int j = 0; j < worldMaps[i].length; j++){
                if (map.equals(worldMaps[i][j].getCurrentMapType())){
                    return new Vector2(i,j);
                }
            }
        }
        return null;
    }
    /**
     * Returns an ArrayList containing all the enemies on the map.
     * @param MapFactory.MapType map
     * @return ArrayList<AbstractEnemy>
     */
    @Override
    public ArrayList<AbstractEnemy> getMapEnemy(MapFactory.MapType map) {
        return null;
    }
}
