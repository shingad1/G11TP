package com.sps.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.SpacePiratesShoedown;
import com.sps.game.controller.NPCController;
import com.sps.game.controller.PlayerController;
import com.sps.game.maps.GraveyardMap;
import com.sps.game.maps.Map;
import com.sps.game.maps.MapFactory;
import com.sps.game.sprites.AbstractNPC;
import com.sps.game.sprites.Location;
import com.sps.game.sprites.NonInteractiveNPC;

import java.util.ArrayList;
import java.util.Random;

public class GraveyardScreen extends PlayScreen {

    /**
     * 2D array, that contains all the maps for the graveyard world
     */
    private Map[][] worldMaps = {{new GraveyardMap(), null},
                                 {null, null}};
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

    public GraveyardScreen(SpacePiratesShoedown game) {
        super(game);
        mapSelector = new Vector2(0,0);
        Map selectedMap = worldMaps[Math.round(mapSelector.y)][Math.round(mapSelector.x)];
        currentMap = selectedMap.getCurrentMap();
        renderer = new OrthogonalTiledMapRenderer(currentMap);
        currentCollisionLayer = selectedMap.getCollisionLayer();
        currentMapState = getCurrentMapType();
        random = new Random();
        int numNonInteractive = random.nextInt(10) + 10;
        npc = new ArrayList<AbstractNPC>();
        npcController = new ArrayList<NPCController>();
        int baseNPCSize = npc.size();
        int i = 0;
        while(npc.size() < numNonInteractive + baseNPCSize){
            int x = random.nextInt(49);
            int y = random.nextInt(49);
            Location location = new Location(x * 32,y * 32);
            MapFactory.MapType world;
            if(random.nextBoolean()){
                world = MapFactory.MapType.GraveyardWorld1;
            }
            else {
                world = MapFactory.MapType.GraveyardWorld1; //change
            }
            if(checkPosition(location,world)){
                npc.add(new NonInteractiveNPC(Math.round(location.getX()), Math.round(location.getY()), world, batch, ""));
                npcController.add(new NPCController(npc.get(i), getMap(getWorldMapByWorld(world)).getCollisionLayer()));
                i++;
            }
        }
        allLocations = new ArrayList<Location>();
        changeNpcLocations(selectedMap);
        p.setX(250);//change
        p.setY(250);//change
        p.setBatch(batch);
        controller = new PlayerController(p,currentCollisionLayer,xbounds,ybounds,allLocations);
        gamecam.position.set(p.getX(), p.getY(), 0);
        music = Gdx.audio.newMusic(Gdx.files.internal("core/assets/Music/firstWorld.mp3")); //change
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
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
     * Returns a cell according to the Map and the location
     * @param location
     * @param map
     * @return
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
            //controller = new PlayerController(p, currentCollisionLayer,xbounds,ybounds,allLocations);
            controller.changeCollisionLayer(currentCollisionLayer, xbounds, ybounds);
            controller.newWorldReset();
        }
    }
    /**
     * Returns a map from the array according to the vector2 value passed in as a parameter
     * @param selector
     * @return
     */
    @Override
    public Map getMap(Vector2 selector) {
        return worldMaps[Math.round(selector.x)][Math.round(selector.y)];
    }
    /**
     * Returns a Vector2 value to get a Map according to the map type specified in the parameter
     * @param map
     * @return
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
}
