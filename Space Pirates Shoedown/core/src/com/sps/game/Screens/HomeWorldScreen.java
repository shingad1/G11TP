package com.sps.game.Screens;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.sps.game.Controller.NPCController;
import com.sps.game.Controller.PlayerController;
import com.sps.game.SpacePiratesShoedown;
import com.sps.game.Sprites.AbstractNPC;
import com.sps.game.Sprites.Location;
import com.sps.game.Sprites.NonInteractiveNPC;

import java.util.ArrayList;
import java.util.Random;

public class HomeWorldScreen extends PlayScreen {

    public HomeWorldScreen(SpacePiratesShoedown game) {
        super(game);
        overworldMap = "testMap.tmx";
        map = mapLoader.load(ASSETS_PATH + "testMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        collisionLayer = (TiledMapTileLayer) map.getLayers().get(1);
        mapState = "Overworld";
        random = new Random();
        int numNonInteractive = random.nextInt(10) + 5;
        npc = new ArrayList<AbstractNPC>();
        npcController = new ArrayList<NPCController>();
        int baseNPCSize = npc.size();
        int i = 0;
        while(npc.size() < numNonInteractive + baseNPCSize){
            int x = random.nextInt(50);
            int y = random.nextInt(50);
            Location location = new Location(x * 32, y * 32);
            if(checkPosition(location)) {
                npc.add(new NonInteractiveNPC(Math.round(location.getX()), Math.round(location.getY()), "Overworld", batch, ""));
                npcController.add(new NPCController(npc.get(i), collisionLayer));
                i++;
            }
        }
        allLocations = new ArrayList<Location>();
        for (AbstractNPC nonPlayingCharacter : npc){
            allLocations.add(nonPlayingCharacter.getLocation());
        }
        int[] xbounds = {0, 1600};
        int[] ybounds = {0,1600};
        p.setX(736);
        p.setY(1280);
        p.setBatch(batch);
        controller = new PlayerController(p, collisionLayer,xbounds,ybounds,allLocations);
        gamecam.position.set(p.getX(), p.getY(), 0);
    }

    /**
     * Checks if the location is occupied by an npc or a blocked tile
     * @param location
     * @return
     */
    public boolean checkPosition(Location location){
        for (AbstractNPC nonPlayingCharacter : npc){
            if(location.equals(nonPlayingCharacter.getLocation())){
                return false;
            }
        }
        if(collisionLayer.getCell((int) (location.getX() / 32), (int) ((location.getY())/32)).getTile().getProperties().containsKey("blocked")){
            return false;
        }
        return true;
    }

}