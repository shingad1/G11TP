package com.sps.game.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.controller.NPCController;
import com.sps.game.controller.PlayerController;
import com.sps.game.SpacePiratesShoedown;
import com.sps.game.maps.*;
import com.sps.game.sprites.AbstractEnemy;
import com.sps.game.sprites.AbstractNPC;
import com.sps.game.sprites.InteractiveNPC;
import com.sps.game.sprites.Location;

import java.util.ArrayList;

/**
 * This class holds all the different maps for each house interior, and contains its own unique functionality,
 * specific to the rooms.
 * @author Miraj Shah
 * @version 1.0
 */
public class HouseInteriorScreen extends PlayScreen {

    /**
     * 2D array, that contains all the different house interiors
     */
    private Map[][] interiors = {{new HomeInteriorMap(), new HomeInteriorMap2(), new TropicalInteriorMap()},
                                 {new CandyInteriorMap(), new CandyMansionMap(), new TropicalInteriorMap2()}};
    /**
     * Chooses the map to load from the array
     */
    private Vector2 interiorSelector;
    /**
     * An array containing the bounds of the x axis
     */
    private int[] xbound = {0,1600};
    /**
     * An array containing the bounds of the y axis
     */
    private int[] ybound = {0,1600};

    public HouseInteriorScreen(SpacePiratesShoedown game, Vector2 chooseMap) {
        super(game);
        interiorSelector= chooseMap;
        Map selected = interiors[Math.round(interiorSelector.y)][Math.round(interiorSelector.x)];
        currentMap = selected.getCurrentMap();
        renderer = new OrthogonalTiledMapRenderer(currentMap);
        currentCollisionLayer = selected.getCollisionLayer();
        currentMapState = selected.getCurrentMapType();
        npc = new ArrayList<AbstractNPC>();
        npcController = new ArrayList<NPCController>();
        //npc.add(new InteractiveNPC(862, 480, MapFactory.MapType.HomeInterior, batch, ""));
        //enemies = new ArrayList<AbstractEnemy>();
        allLocations = new ArrayList<Location>();
        changeNpcLocations(selected);
        if(currentMapState.equals(MapFactory.MapType.HomeInterior) ||currentMapState.equals(MapFactory.MapType.HomeInterior2 )){
            p.setX(800);
            p.setY(384);
        }
        else if(currentMapState.equals(MapFactory.MapType.CandyInterior) || currentMapState.equals(MapFactory.MapType.CandyMansion)){
            p.setX(832);
            p.setY(384);
        }
        else if(currentMapState.equals(MapFactory.MapType.TropicalInterior1) || currentMapState.equals(MapFactory.MapType.TropicalInterior2)){
            p.setX(768);
            p.setY(352);
        }

       // if(currentMapState.equals(MapFactory.MapType.HomeInterior)){
            //npc.add(new InteractiveNPC(800, 640, MapFactory.MapType.HomeInterior, batch, "Enemyreg1"));
            //enemies.add()
        //}

        p.setBatch(batch);
        controller = new PlayerController(p, currentCollisionLayer, xbound, ybound, allLocations);
        gamecam.position.set(p.getX(), p.getY(), 0);
        music = Gdx.audio.newMusic(Gdx.files.internal("core/assets/Music/tense.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
    }
    /**
     * Returns a cell according to the Map and the location
     * @param location
     * @param map
     * @return
     */
    public TiledMapTileLayer.Cell getCell(Location location, MapFactory.MapType map){
        return getMap(getWorldMapByWorld(map)).getCollisionLayer().getCell((int) location.getX() / 32, (int) location.getY()/32);
    }
    /**
     * Checks if the location is occupied by an npc or a blocked tile
     * @param location
     * @return
     */
    @Override
    public boolean checkPosition(Location location, MapFactory.MapType world) {
        for (AbstractNPC nonPlayingCharacter : getMapNPC(world)) {
            if (location.equals(nonPlayingCharacter.getLocation())) {
                return false;
            }
        }
        if(getCell(location, world) == null || getCell(location,world).getTile().getProperties().containsKey("blocked")){
            return false;
        }
        return true;
    }
    /**
     * Changes the map that is rendered once the player is on a certain location or going of the screen
     */
    @Override
    public void changeMaps() {
        if(p.getLocation().equals(new Location(800,352 ))) {//will change
            dispose();
            if (oldState.equals(MapFactory.MapType.HomeWorldMap1)) {
                game.setScreen(new HomeWorldScreen(game, new Vector2(0, 0), 864, 608));
            } else if (oldState.equals(MapFactory.MapType.HomeWorldMap2)) {
                game.setScreen(new HomeWorldScreen(game, new Vector2(1, 0), 288, 640));
            }
        }
        if(p.getLocation().equals(new Location(832, 352))){
            dispose();
            if(oldState.equals(MapFactory.MapType.CandyWorld1)){
                game.setScreen(new CandyLandScreen(game, new Vector2(0,0), 416, 928));
            } else if(oldState.equals(MapFactory.MapType.CandyWorld2)){
                game.setScreen(new CandyLandScreen(game, new Vector2(1,0), 1152, 1152));
            }
        }
        if(p.getLocation().equals(new Location(768, 320))){
            dispose();
            if(oldState.equals(MapFactory.MapType.TropicalWorld1)){
                if(currentMapState.equals(MapFactory.MapType.TropicalInterior1)) {
                    game.setScreen(new TropicalWorldScreen(game, 1504, 1344));
                }
                else if(currentMapState.equals(MapFactory.MapType.TropicalInterior2)){
                    game.setScreen(new TropicalWorldScreen(game, 992, 928));
                }
            }
        }

    }
    /**
     * Returns a map from the array according to the vector2 value passed in as a parameter
     * @param selector
     * @return
     */
    @Override
    public Map getMap(Vector2 selector) {
        return interiors[Math.round(selector.x)][Math.round(selector.y)];
    }
    /**
     * Returns a Vector2 value to get a Map according to the map type specified in the parameter
     * @param map
     * @return
     */
    @Override
    public Vector2 getWorldMapByWorld(MapFactory.MapType map) {
        for(int i = 0; i < interiors.length; i++){
            for (int j = 0; j < interiors[i].length; j++){
                if (map.equals(interiors[i][j].getCurrentMapType())){
                    return new Vector2(i,j);
                }
            }
        }
        return null;
    }
}
