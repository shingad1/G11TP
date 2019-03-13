package com.sps.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.controller.NPCController;
import com.sps.game.controller.PlayerController;
import com.sps.game.SpacePiratesShoedown;
import com.sps.game.sprites.*;
import com.sps.game.maps.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class holds all the different maps of the Home World, and contains its own unique functionality,
 * specific to this world.
 * @author Miraj Shah, Miguel Abaquin
 * @version 1.0
 */
public class HomeWorldScreen extends PlayScreen {
    /**
     * 2D array, that contains all the maps for the home world
     */
    private Map[][] worldMaps = {{new HomeWorldMap(), new HomeWorldMap2()},
                                 {null, null}};
    /**
     * Chooses the map to load from the array
     */
    private Vector2 mapSelector; //selects map from worldMaps
    /**
     * An array containing the bounds of the x axis
     */
    private int[] xbounds  = {0,1600};
    /**
     * An array containing the bounds of the y axis
     */
    private int[] ybounds  = {0,1600};


    public HomeWorldScreen(SpacePiratesShoedown game, Vector2 chosenMap, int px, int py) {
        super(game);
        mapSelector = chosenMap; //change when moving worlds
        Map selectedMap = worldMaps[Math.round(mapSelector.y)][Math.round(mapSelector.x)]; //change when moving worlds
        currentMap = selectedMap.getCurrentMap();//change when moving worlds
        renderer = new OrthogonalTiledMapRenderer(currentMap); //change when moving worlds
        currentCollisionLayer = worldMaps[Math.round(mapSelector.y)][Math.round(mapSelector.x)].getCollisionLayer(); //change when moving worlds
        currentMapState = selectedMap.getCurrentMapType(); //change when moving worlds

        random = new Random();
        int numNonInteractive = random.nextInt(20) + 10;
        npc = new ArrayList<AbstractNPC>();
        npcController = new ArrayList<NPCController>();
        int baseNPCSize = npc.size();
        int i = 0;
        while(npc.size() < numNonInteractive + baseNPCSize){
            int x = random.nextInt(49);
            int y = random.nextInt(49);
            Location location = new Location(x * 32, y * 32);
            MapFactory.MapType world;
            if(random.nextBoolean()){
                world = MapFactory.MapType.HomeWorldMap1;
            } else {
                world = MapFactory.MapType.HomeWorldMap2;
            }
            if(checkPosition(location,world)) {
                npc.add(new NonInteractiveNPC(Math.round(location.getX()), Math.round(location.getY()), world, batch, ""));
                npcController.add(new NPCController(npc.get(i), getMap(getWorldMapByWorld(world)).getCollisionLayer()));
                i++;
            }
        }
        if(currentMapState.equals(MapFactory.MapType.HomeWorldMap1)) {
            npc.add(new NonInteractiveNPC(576, 704, MapFactory.MapType.HomeWorldMap1, batch, "Merchant"));
            npcController.add(new NPCController(npc.get(npc.size() - 1), getMap(getWorldMapByWorld(MapFactory.MapType.HomeWorldMap1)).getCollisionLayer()));
            npc.add(new InteractiveNPC(768, 1216, MapFactory.MapType.HomeWorldMap1, batch, "Bob"));
            npc.add(new InteractiveNPC(832, 576, MapFactory.MapType.HomeWorldMap1, batch, "ThirdNPC"));//need to make them first talk then they can move away
            npc.add(new InteractiveNPC(768, 896, MapFactory.MapType.HomeWorldMap1, batch, "BusinessNPC"));
            npc.add(new InteractiveNPC(224, 192, MapFactory.MapType.HomeWorldMap1, batch, "FirstNPC"));
            npc.add(new InteractiveNPC(736, 608, MapFactory.MapType.HomeWorldMap1, batch, "AnotherNPC"));
            //need to add marketman- but dialogue should only work after they defeat the enemy
        }
        npc.add(new InteractiveNPC(128, 864, MapFactory.MapType.HomeWorldMap2, batch, "FourthNPC"));
        npc.add(new InteractiveNPC(896, 512, MapFactory.MapType.HomeWorldMap2, batch, "FifthNPC"));
        npc.add(new InteractiveNPC(1056, 1088, MapFactory.MapType.HomeWorldMap2, batch, "SixthNPC"));


        allLocations = new ArrayList<Location>();
        changeNpcLocations(selectedMap);
        p.setX(px); //change when moving worlds
        p.setY(py); //""
        p.setBatch(batch);
        controller = new PlayerController(p, currentCollisionLayer,xbounds,ybounds,allLocations);
        gamecam.position.set(p.getX(), p.getY(), 0); //change when moving worlds
        music = Gdx.audio.newMusic(Gdx.files.internal("core/assets/Music/firstWorld.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();

    }

    /**
     * Returns a Vector2 value to get a Map according to the map type specified in the parameter
     * @param map
     * @return
     */
    public Vector2 getWorldMapByWorld(MapFactory.MapType map){
        for(int i = 0; i < worldMaps.length; i++){
            for (int j = 0; j < worldMaps[i].length; j++){
                if (map.equals(worldMaps[i][j].getCurrentMapType())){
                    return new Vector2(i,j);
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<AbstractEnemy> getMapEnemy(MapFactory.MapType map) {
        return null;
    }

    /**
     * Returns a map from the array according to the vector2 value passed in as a parameter
     * @param selector
     * @return
     */
    public Map getMap(Vector2 selector){
        return worldMaps[Math.round(selector.x)][Math.round(selector.y)];
    }

    /**
     * Checks if the location is occupied by an npc or a blocked tile
     * @param location
     * @return
     */
    @Override
    public boolean checkPosition(Location location, MapFactory.MapType map){
        for (AbstractNPC nonPlayingCharacter : getMapNPC(map)) {
            if (location.equals(nonPlayingCharacter.getLocation())) {
                return false;
            }
        }
        if(getCell(location, map) == null || getCell(location,map).getTile().getProperties().containsKey("blocked")){
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
    public void changeMaps(){
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
        if(currentMapState.equals(MapFactory.MapType.HomeWorldMap2)){
            if(p.getLocation().equals(new Location(1056, 256))){
                dispose();
                game.setScreen(new CandyLandScreen(game, new Vector2(0,0), 384, 1280));
            } else if(p.getLocation().equals(new Location(288, 704))){
                oldState = MapFactory.MapType.HomeWorldMap2;
                dispose();
                game.setScreen(new HouseInteriorScreen(game, new Vector2(1,0)));
            }
        }
        if(p.getLocation().equals(new Location(864, 640)) && currentMapState.equals(MapFactory.MapType.HomeWorldMap1)){
            oldState = MapFactory.MapType.HomeWorldMap1;
            dispose();
            game.setScreen(new HouseInteriorScreen(game, new Vector2(0,0)));
        }
        if(camX != 0 || camY != 0) {
            Map selectedMap = worldMaps[Math.round(mapSelector.y)][Math.round(mapSelector.x)]; //change when moving worlds
            currentMap = selectedMap.getCurrentMap();//change when moving worlds
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
}