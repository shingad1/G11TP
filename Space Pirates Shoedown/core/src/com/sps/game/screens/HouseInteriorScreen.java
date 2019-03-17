package com.sps.game.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.controller.NPCController;
import com.sps.game.controller.PlayerController;
import com.sps.game.SpacePiratesShoedown;
import com.sps.game.maps.*;
import com.sps.game.sprites.*;

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
    private Map[][] interiors = {{new HomeInteriorMap(), new HomeInteriorMap2(), new TropicalInteriorMap(), new CandyInteriorMap2()},
                                 {new CandyInteriorMap(), new CandyMansionMap(), new TropicalInteriorMap2(), new TropicalInteriorMap3()}};
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
        enemies = new ArrayList<AbstractEnemy>();
        if(currentMapState.equals(MapFactory.MapType.HomeInterior) ||currentMapState.equals(MapFactory.MapType.HomeInterior2 )){
            p.setX(800);
            p.setY(384);
        }
        else if(currentMapState.equals(MapFactory.MapType.CandyInterior) || currentMapState.equals(MapFactory.MapType.CandyMansion) || currentMapState.equals(MapFactory.MapType.CandyInterior2)){
            p.setX(832);
            p.setY(384);
        }
        else if(currentMapState.equals(MapFactory.MapType.TropicalInterior1) || currentMapState.equals(MapFactory.MapType.TropicalInterior2) || currentMapState.equals(MapFactory.MapType.TropicalInterior3)){
            p.setX(768);
            p.setY(352);
        }

        if(currentMapState.equals(MapFactory.MapType.HomeInterior)){
            enemies.add(new BasicEnemy(800, 640, MapFactory.MapType.HomeInterior, batch, "Enemyreg1"));
        } else if(currentMapState.equals(MapFactory.MapType.HomeInterior2)){
            enemies.add(new BasicEnemy(672,640, MapFactory.MapType.HomeInterior2,batch,"EnemyTwo"));
            enemies.add(new BasicEnemy(1120, 544,MapFactory.MapType.HomeInterior2,batch,"EnemyThree"));
        } else if(currentMapState.equals(MapFactory.MapType.CandyInterior)){
            enemies.add(new BasicEnemy(800, 832,MapFactory.MapType.CandyInterior,batch,"EnemyCandyOne"));
            enemies.add(new BasicEnemy(576,480, MapFactory.MapType.CandyInterior,batch,"EnemyCandyTwo"));
        } else if(currentMapState.equals(MapFactory.MapType.CandyInterior2)){
            enemies.add(new BasicEnemy(768,800,MapFactory.MapType.CandyInterior2,batch,"EnemyCandyThree"));
            enemies.add(new BasicEnemy(1120,928,MapFactory.MapType.CandyInterior2,batch,"EnemyCandyFour"));
        } else if(currentMapState.equals(MapFactory.MapType.CandyMansion)){
            enemies.add(new BasicEnemy(832,512, MapFactory.MapType.CandyMansion,batch,"Enemyreg1"));
            enemies.add(new BasicEnemy(1216,448, MapFactory.MapType.CandyMansion,batch,"EnemyCandyFive"));
            enemies.add(new BasicEnemy(320,288,MapFactory.MapType.CandyMansion,batch,"EnemyCandySix"));
            enemies.add(new BasicEnemy(384,672,MapFactory.MapType.CandyMansion,batch,"EnemyCandySeven"));
            enemies.add(new BasicEnemy(1248,800, MapFactory.MapType.CandyMansion,batch,"EnemyCandyEight"));
            enemies.add(new HeadEnemy(832, 928,MapFactory.MapType.CandyMansion,batch,"HeadEnemyCandy"));
        } else if(currentMapState.equals(MapFactory.MapType.TropicalInterior1)){
            enemies.add(new BasicEnemy(928,768,MapFactory.MapType.TropicalInterior1,batch,"EnemyTropicalTwo"));
            enemies.add(new BasicEnemy(448,832,MapFactory.MapType.TropicalInterior1,batch,"EnemyTropicalOne"));
            enemies.add(new BasicEnemy(448,480, MapFactory.MapType.TropicalInterior1,batch,"EnemyFour"));
        } else if(currentMapState.equals(MapFactory.MapType.TropicalInterior2)){
            enemies.add(new BasicEnemy(1056,800,MapFactory.MapType.TropicalInterior2,batch,"EnemySix"));
            enemies.add(new BasicEnemy(352,512,MapFactory.MapType.TropicalInterior2,batch,"EnemySeven"));
        }
        allLocations = new ArrayList<Location>();
        addEnemiesLocations(selected);
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
                if(currentMapState.equals(MapFactory.MapType.CandyMansion)) {
                    game.setScreen(new CandyLandScreen(game, new Vector2(1, 0), 1152, 1152));
                } else if(currentMapState.equals(MapFactory.MapType.CandyInterior2)){
                    game.setScreen(new CandyLandScreen(game, new Vector2(1,0),352,416));
                }
            }
        }
        if(p.getLocation().equals(new Location(768, 320))){
            dispose();
            if(oldState.equals(MapFactory.MapType.TropicalWorld1)){
                if(currentMapState.equals(MapFactory.MapType.TropicalInterior1)) {
                    game.setScreen(new TropicalWorldScreen(game,new Vector2(0,0), 1504, 1344));
                }
                if(currentMapState.equals(MapFactory.MapType.TropicalInterior2)){
                    game.setScreen(new TropicalWorldScreen(game,new Vector2(0,0), 992, 928));
                }
            } else if(oldState.equals(MapFactory.MapType.TropicalWorld2)){
                if(currentMapState.equals(MapFactory.MapType.TropicalInterior3)){
                    game.setScreen(new TropicalWorldScreen(game,new Vector2(1,0), 1216, 672));
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

    /**
     * Returns an array list with all the enemies on the map.
     * @param map
     * @return ArrayList<AbstractEnemy> enemies
     */
    public ArrayList<AbstractEnemy> getMapEnemy(MapFactory.MapType map){
        return enemies;
    }
}
