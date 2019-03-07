package com.sps.game.Screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.Controller.NPCController;
import com.sps.game.Controller.PlayerController;
import com.sps.game.SpacePiratesShoedown;
import com.sps.game.Sprites.AbstractNPC;
import com.sps.game.Sprites.InteractiveNPC;
import com.sps.game.Sprites.Location;
import com.sps.game.maps.HomeInteriorMap;
import com.sps.game.maps.HomeInteriorMap2;
import com.sps.game.maps.Map;
import com.sps.game.maps.MapFactory;

import java.util.ArrayList;

public class HouseInteriorScreen extends PlayScreen {

    private Map[][] interiors = {{new HomeInteriorMap(), new HomeInteriorMap2()},
                                 {null, null}};

    private static Vector2 interiorSelector;

    private int[] xbound = {0,1600};

    private int[] ybound = {0,1600};

    public HouseInteriorScreen(SpacePiratesShoedown game) {
        super(game);
        interiorSelector= new Vector2(0,0);
        Map selected = interiors[Math.round(interiorSelector.y)][Math.round(interiorSelector.x)];
        currentMap = selected.getCurrentMap();
        renderer = new OrthogonalTiledMapRenderer(currentMap);
        currentCollisionLayer = selected.getCollisionLayer();
        currentMapState = selected.getCurrentMapType();
        npc = new ArrayList<AbstractNPC>();
        npcController = new ArrayList<NPCController>();
        npc.add(new InteractiveNPC(1088, 736, MapFactory.MapType.HomeInterior, batch, ""));
        //npcController.add(new NPCController(npc.get(0), getMap(getWorldMapByWorld(currentMapState)).getCollisionLayer()));
        allLocations = new ArrayList<Location>();
        changeNpcLocations(selected);
        p.setX(800);
        p.setY(448);
        p.setBatch(batch);
        controller = new PlayerController(p, currentCollisionLayer, xbound, ybound, allLocations);
        gamecam.position.set(p.getX(), p.getY(), 0);
        music = Gdx.audio.newMusic(Gdx.files.internal("core/assets/Music/tense.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
    }

    public TiledMapTileLayer.Cell getCell(Location location, MapFactory.MapType map){
        return getMap(getWorldMapByWorld(map)).getCollisionLayer().getCell((int) location.getX() / 32, (int) location.getY()/32);
    }

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

    @Override
    public void changeMaps() {
        if(p.getLocation().equals(new Location(800,384 )) || p.getLocation().equals(new Location(832, 384))){//will change
            dispose();
            if(oldState.equals(MapFactory.MapType.HomeWorldMap1)) {
                game.setScreen(new HomeWorldScreen(game, new Vector2(0,0),864, 608));
            } else{
                game.setScreen(new HomeWorldScreen(game, new Vector2(1,0),288, 640));
            }
        }

    }

    @Override
    public Map getMap(Vector2 selector) {
        return interiors[Math.round(selector.x)][Math.round(selector.y)];
    }

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

    public static void setMap(int x){
        interiorSelector = new Vector2(0,x);
    }
}
