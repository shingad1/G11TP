package com.sps.game.Screens;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.Controller.NPCController;
import com.sps.game.Controller.PlayerController;
import com.sps.game.SpacePiratesShoedown;
import com.sps.game.Sprites.*;
import com.sps.game.maps.HomeWorldMap;
import com.sps.game.maps.HomeWorldMap2;
import com.sps.game.maps.Map;
import com.sps.game.maps.MapFactory;

import java.util.ArrayList;
import java.util.Random;

public class HomeWorldScreen extends PlayScreen {

    private Map[][] worldMaps = {{new Map(MapFactory.MapType.HomeWorldMap1, ASSETS_PATH + "HomeWorld/HomeWorldMap1.tmx"), new Map(MapFactory.MapType.HomeWorldMap2, ASSETS_PATH + "HomeWorld/HomeWorldMap2.tmx")},
                                 {null, null}};

    private Vector2 mapSelector; //selects map from worldMaps

    private int[] xbounds  = {0,1600};

    private int[] ybounds  = {0,1600};


    public HomeWorldScreen(SpacePiratesShoedown game) {
        super(game);
        //overworldMap = "HomeWorld/HomeWorldMap1.tmx";
        mapSelector = new Vector2(0,0); //change when moving worlds
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

        allLocations = new ArrayList<Location>();
        changeNpcLocations(selectedMap);

        p.setX(736); //change when moving worlds
        p.setY(1280); //""
        p.setBatch(batch);
        controller = new PlayerController(p, currentCollisionLayer,xbounds,ybounds,allLocations);
        gamecam.position.set(p.getX(), p.getY(), 0); //change when moving worlds

    }

    @Override
    public ArrayList<InteractiveNPC> getInteractiveNPC() {
        ArrayList<InteractiveNPC> interactiveNPCs = new ArrayList<InteractiveNPC>();
        for(AbstractNPC nonPlayingCharacter : npc){
            if(nonPlayingCharacter.getClass() == InteractiveNPC.class){
                interactiveNPCs.add((InteractiveNPC) nonPlayingCharacter);
            }
        }
        return interactiveNPCs;
    }

    @Override
    public ArrayList<NonInteractiveNPC> getNonInteractiveNPC() {
        ArrayList<NonInteractiveNPC> nonInteractiveNPCs = new ArrayList<NonInteractiveNPC>();
        for(AbstractNPC nonPlayingCharacter : npc){
            if(nonPlayingCharacter.getClass() == NonInteractiveNPC.class){
                nonInteractiveNPCs.add((NonInteractiveNPC) nonPlayingCharacter);
            }
        }
        return nonInteractiveNPCs;
    }

    @Override
    public ArrayList<InteractiveNPCMoving> getInteractiveNPCMoving() {
        ArrayList<InteractiveNPCMoving> InteractiveNPCsMoving = new ArrayList<InteractiveNPCMoving>();
        for(AbstractNPC InteractiveNPC : npc){
            if(InteractiveNPC.getClass() == InteractiveNPCMoving.class){
                InteractiveNPCsMoving.add((InteractiveNPCMoving) InteractiveNPC);
            }
        }
        return InteractiveNPCsMoving;
    }

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

    public ArrayList<AbstractNPC> getMapNPC(MapFactory.MapType map){
        ArrayList<AbstractNPC> result = new ArrayList<AbstractNPC>();
        for (int i = 0; i < npc.size(); i++){
            if (npc.get(i).getWorld().equals(map)){
                result.add(npc.get(i));
            }
        }
        return result;
    }

    public Map getMap(Vector2 selector){
        return worldMaps[Math.round(selector.x)][Math.round(selector.y)];
    }

    /**
     * Checks if the location is occupied by an npc or a blocked tile
     * @param location
     * @return
     */
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

    public TiledMapTileLayer.Cell getCell(Location location, MapFactory.MapType map){
        return getMap(getWorldMapByWorld(map)).getCollisionLayer().getCell((int) (location.getX() / 32), (int) ((location.getY())/32));
    }

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

        if(camX != 0 || camY != 0) {
            Map selectedMap = worldMaps[Math.round(mapSelector.y)][Math.round(mapSelector.x)]; //change when moving worlds
            currentMap = selectedMap.getCurrentMap();//change when moving worlds
            renderer = new OrthogonalTiledMapRenderer(currentMap); //change when moving worlds
            currentCollisionLayer = (TiledMapTileLayer) currentMap.getLayers().get(1); //change when moving worlds
            currentMapState = selectedMap.getCurrentMapType(); //change when moving worlds

            gamecam.position.set(p.getX()+(240 * camX), p.getY() + (240 * camY), 0); //change when moving worlds
            changeNpcLocations(selectedMap);
            controller.changeNpcLocations(allLocations);
            //controller = new PlayerController(p, currentCollisionLayer,xbounds,ybounds,allLocations);
            controller.changeCollisionLayer(currentCollisionLayer, xbounds, ybounds);
            controller.newWorldReset();
        }
    }

    private void changeNpcLocations(Map selectedMap) {
        for (AbstractNPC nonPlayingCharacter : npc) {
            if (nonPlayingCharacter.getWorld().equals(selectedMap.getCurrentMapType()))
                allLocations.add(nonPlayingCharacter.getLocation());
        }
    }
}