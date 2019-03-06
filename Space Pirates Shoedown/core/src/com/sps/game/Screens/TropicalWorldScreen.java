package com.sps.game.Screens;

import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.Controller.NPCController;
import com.sps.game.SpacePiratesShoedown;
import com.sps.game.Sprites.AbstractNPC;
import com.sps.game.Sprites.Location;
import com.sps.game.Sprites.NonInteractiveNPC;
import com.sps.game.maps.Map;
import com.sps.game.maps.MapFactory;
import com.sps.game.maps.TropicalWorld;

import java.util.ArrayList;
import java.util.Random;

public class TropicalWorldScreen extends PlayScreen {

    private Map[][] worldMaps = {{new TropicalWorld(), null},
                                 {null, null}};

    private Vector2  mapSelector;

    private int[] xbounds = {0,1600};

    private int[] ybounds = {0, 1600};

    public TropicalWorldScreen(SpacePiratesShoedown game) {
        super(game);
        mapSelector = new Vector2(0,0);
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
                npc.add(new NonInteractiveNPC(Math.round(location.getX()), Math.round(location.getY()), world, batch, ""));//need to change role
                npcController.add(new NPCController(npc.get(i), getMap(getWorldMapByWorld(world)).getCollisionLayer()));
                i++;
            }
        }

        allLocations = new ArrayList<Location>();
        changeNpcLocations(selectedMap);
        
    }

    @Override
    public boolean checkPosition(Location location, MapFactory.MapType world) {
        return false;
    }

    @Override
    public void changeMaps() {

    }

    @Override
    public Map getMap(Vector2 selector) {
        return null;
    }

    @Override
    public Vector2 getWorldMapByWorld(MapFactory.MapType map) {
        return null;
    }
}
