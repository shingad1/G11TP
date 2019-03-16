package com.sps.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.controller.NPCController;
import com.sps.game.controller.PlayerController;
import com.sps.game.SpacePiratesShoedown;
import com.sps.game.sprites.*;
import com.sps.game.maps.CandyWorldMap;
import com.sps.game.maps.CandyWorldMap2;
import com.sps.game.maps.Map;
import com.sps.game.maps.MapFactory;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class holds all the different maps of Candy Land, and contains its own unique functionality,
 * specific to this world.
 * @author Miraj Shah
 * @version 1.0
 */
public class CandyLandScreen extends PlayScreen {

    /**
     * 2D array, that contains all the maps for candy land
     */
    private Map[][] worldMaps = {{new CandyWorldMap(), new CandyWorldMap2()},
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
    private int[] ybounds = {0, 1600};

    public CandyLandScreen(SpacePiratesShoedown game, Vector2 chooseMap, int px, int py) {
        super(game);
        mapSelector = chooseMap;
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
                world = MapFactory.MapType.CandyWorld1;
            } else{
                world = MapFactory.MapType.CandyWorld2;
            }
            if(checkPosition(location, world)){
                npc.add(new NonInteractiveNPC(Math.round(location.getX()), Math.round(location.getY()), world, batch, "Gingerbread"));//role to change
                npcController.add(new NPCController(npc.get(i), getMap(getWorldMapByWorld(world)).getCollisionLayer()));
                i++;
            }
            int x1 = random.nextInt(49);
            int y1 = random.nextInt(49);
            Location loc = new Location(x1 * 32, y1 * 32);
            if(random.nextBoolean()){
                world = MapFactory.MapType.CandyWorld1;
            } else{
                world = MapFactory.MapType.CandyWorld2;
            }
            if(checkPosition(location, world)){
                npc.add(new NonInteractiveNPC(Math.round(location.getX()), Math.round(location.getY()), world, batch, "Peppermint"));//role to change
                npcController.add(new NPCController(npc.get(i), getMap(getWorldMapByWorld(world)).getCollisionLayer()));
                i++;
            }
        }

        if(currentMapState.equals(MapFactory.MapType.CandyWorld1)){
            npc.add(new InteractiveNPC(480, 1184, MapFactory.MapType.CandyWorld1, batch, "MuffinWelcome"));
            npc.add(new InteractiveNPC(1152, 672, MapFactory.MapType.CandyWorld1, batch, "MuffinSeventhNPC"));
            npc.add(new InteractiveNPC(1504, 864, MapFactory.MapType.CandyWorld1, batch, "MuffinRoleModel"));
            npc.add(new InteractiveNPC(384, 160, MapFactory.MapType.CandyWorld1, batch, "MuffinStranger"));
        }
        npc.add(new InteractiveNPC(832, 832, MapFactory.MapType.CandyWorld2, batch, "MuffinEigthNP"));
        npc.add(new InteractiveNPC(1408, 448, MapFactory.MapType.CandyWorld2, batch, "MuffinScared"));
        npc.add(new InteractiveNPC(512, 352, MapFactory.MapType.CandyWorld2, batch, "MuffinWimp"));
        npc.add(new InteractiveNPC(1088, 1088, MapFactory.MapType.CandyWorld2, batch, "MuffinNinthNPC"));

        allLocations = new ArrayList<Location>();
        changeNpcLocations(selectedMap);
        p.setX(px);
        p.setY(py);
        p.setBatch(batch);
        controller = new PlayerController(p, currentCollisionLayer, xbounds, ybounds, allLocations);
        gamecam.position.set(p.getX(),p.getY(),0);
        music = Gdx.audio.newMusic(Gdx.files.internal("core/assets/Music/candy.mp3"));
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
        for(AbstractNPC nonPlayingCharacter : getMapNPC(world)){
            if(location.equals(nonPlayingCharacter.getLocation())){
                return false;
            }
        }
        if(getCell(location, world) == null || getCell(location, world).getTile().getProperties().containsKey("blocked")){
            return false;
        }
        return true;
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
        }
        if (currentMapState.equals(MapFactory.MapType.CandyWorld1)){
            if((p.getLocation().equals(new Location(384, 1280)) || p.getLocation().equals(new Location(416,1280))) && controller.getEnterShip()){
                dispose();
                game.setScreen(new TropicalWorldScreen(game, 320, 256));
            }
            if(p.getLocation().equals(new Location(416, 992))){
                oldState = MapFactory.MapType.CandyWorld1;
                dispose();
                game.setScreen(new HouseInteriorScreen(game, new Vector2(0,1)));
            }
        }
        if(currentMapState.equals(MapFactory.MapType.CandyWorld2)){
            if(p.getLocation().equals(new Location(1152, 1184))){
                oldState = MapFactory.MapType.CandyWorld2;
                dispose();
                game.setScreen(new HouseInteriorScreen(game, new Vector2(1,1)));
            } else if(p.getLocation().equals(new Location(352, 480))){
                oldState = MapFactory.MapType.CandyWorld2;
                dispose();
                game.setScreen(new HouseInteriorScreen(game, new Vector2(3,0)));
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

    @Override
    public ArrayList<AbstractEnemy> getMapEnemy(MapFactory.MapType map) {
        return null;
    }


}
