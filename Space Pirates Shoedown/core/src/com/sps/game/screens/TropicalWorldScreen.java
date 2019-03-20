package com.sps.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.controller.NPCController;
import com.sps.game.controller.PlayerController;
import com.sps.game.SpacePiratesShoedown;
import com.sps.game.sprites.*;
import com.sps.game.maps.Map;
import com.sps.game.maps.MapFactory;
import com.sps.game.maps.TropicalWorldMap;
import com.sps.game.maps.TropicalWorldMap2;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class holds all the different maps of the Tropical World, and contains its own unique functionality,
 * specific to this world.
 * @author Miraj Shah
 * @version 1.0
 */
public class TropicalWorldScreen extends PlayScreen {

    /**
     * 2D array, that contains all the maps for the tropical world
     */
    private Map[][] worldMaps = {{new TropicalWorldMap(), new TropicalWorldMap2()},
                                 {null, null}};
    /**
     * Chooses the map to load from the array
     */
    private Vector2  mapSelector;
    /**
     * An array containing the bounds of the x axis
     */
    private int[] xbounds = {0,1600};
    /**
     * An array containing the bounds of the y axis
     */
    private int[] ybounds = {0,1600};

    public TropicalWorldScreen(SpacePiratesShoedown game, Vector2 selector,int px, int py) {
        super(game);
        mapSelector = selector;
        Map selectedMap = worldMaps[Math.round(mapSelector.y)][Math.round(mapSelector.x)];
        currentMap = selectedMap.getCurrentMap();
        renderer = new OrthogonalTiledMapRenderer(currentMap);
        currentCollisionLayer = selectedMap.getCollisionLayer();
        currentMapState = selectedMap.getCurrentMapType();
        random = new Random();
        int numNonInteractive = random.nextInt(15) + 10;
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
                world = MapFactory.MapType.TropicalWorld1;
            } else {
                world = MapFactory.MapType.TropicalWorld2;
            }
            if(checkPosition(location, world)){
                npc.add(new NonInteractiveNPC(Math.round(location.getX()), Math.round(location.getY()), world, batch, "Hula"));//need to change role
                npcController.add(new NPCController(npc.get(i), getMap(getWorldMapByWorld(world)).getCollisionLayer()));
                i++;
            }
            int x1 = random.nextInt(49);
            int y1 = random.nextInt(49);
            Location loc = new Location(x1 * 32, y1 * 32);
            if(random.nextBoolean()){
                world = MapFactory.MapType.TropicalWorld1;
            } else{
                world = MapFactory.MapType.TropicalWorld2;
            }
            if(checkPosition(location, world)){
                npc.add(new NonInteractiveNPC(Math.round(location.getX()), Math.round(location.getY()), world, batch, "Blondehula"));//role to change
                npcController.add(new NPCController(npc.get(i), getMap(getWorldMapByWorld(world)).getCollisionLayer()));
                i++;
            }
        }
        if(currentMapState.equals(MapFactory.MapType.TropicalWorld1)){
            npc.add(new InteractiveNPC(384,576,MapFactory.MapType.TropicalWorld1, batch, "TropicalWelcome"));
            npc.add(new InteractiveNPC(288,800,MapFactory.MapType.TropicalWorld1, batch, "TropicalPeterNPC"));
            npc.add(new InteractiveNPC(800,1472,MapFactory.MapType.TropicalWorld1, batch, "TropicalNPC17"));
            npc.add(new InteractiveNPC(1184,1472, MapFactory.MapType.TropicalWorld1, batch, "TropicalNPC4"));

        }
            npc.add(new InteractiveNPC(448,896, MapFactory.MapType.TropicalWorld2, batch, "TropicalNPC8"));
            npc.add(new InteractiveNPC(1376,352,MapFactory.MapType.TropicalWorld2, batch, "TropicalNPC12"));
            npc.add(new InteractiveNPC(608,960,MapFactory.MapType.TropicalWorld2, batch,"TropicalNPC7"));
            npc.add(new InteractiveNPC(1184,1024,MapFactory.MapType.TropicalWorld2, batch, "TropicalNPC2"));
            npc.add(new MerchantNPC(96,1440, MapFactory.MapType.TropicalWorld2,batch,"Merchant"));
        allLocations = new ArrayList<Location>();
        changeNpcLocations(selectedMap);
        p.setX(px);
        p.setY(py);
        p.setBatch(batch);
        controller = new PlayerController(p, currentCollisionLayer, xbounds, ybounds, allLocations);
        gamecam.position.set(p.getX(), p.getY(), 0);
        music = Gdx.audio.newMusic(Gdx.files.internal("Music/tropical.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
    }
    /**
     * Checks if the location is occupied by an npc or a blocked tile
     * @param Location location
     * @param MapFactory.MapType world
     * @return boolean, True if there is no NPC or blocked tile or nonpc tile otherwise false.
     */
    @Override
    public boolean checkPosition(Location location, MapFactory.MapType world) {
        for (AbstractNPC nonPlayingCharacter : getMapNPC(world)) {
            if (location.equals(nonPlayingCharacter.getLocation())) {
                return false;
            }
        }
        if(getCell(location, world) == null || getCell(location,world).getTile().getProperties().containsKey("blocked")|| getCell(location,world).getTile().getProperties().containsKey("nonpc")){
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
        int camX = 0;
        int camY = 0;
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
        if(currentMapState.equals(MapFactory.MapType.TropicalWorld1)){
            if(p.getLocation().equals(new Location(1504, 1376))){
                dispose();
                oldState = MapFactory.MapType.TropicalWorld1;
                game.setScreen(new HouseInteriorScreen(game, new Vector2(2,0)));
            }else if(p.getLocation().equals(new Location(992, 960))){
                dispose();
                oldState = MapFactory.MapType.TropicalWorld1;
                game.setScreen(new HouseInteriorScreen(game, new Vector2(2,1)));
            }
        }
        if(currentMapState.equals(MapFactory.MapType.TropicalWorld2)){
            if(p.getLocation().equals(new Location(1280, 1376)) && flags[2] == true){
                dispose();
                game.setScreen(new GraveyardScreen(game, new Vector2(1,1), 832, 160));
            } else if(p.getLocation().equals(new Location(1216,736))){
                dispose();
                oldState = MapFactory.MapType.TropicalWorld2;
                game.setScreen(new HouseInteriorScreen(game, new Vector2(3,1)));
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
     * @return map
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
