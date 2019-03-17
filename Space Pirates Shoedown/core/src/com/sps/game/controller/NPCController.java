package com.sps.game.Controller;

import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.sps.game.Screens.PlayScreen;
import com.sps.game.Sprites.AbstractNPC;
import com.sps.game.sprites.InteractiveNPC;
import com.sps.game.Sprites.Location;
import com.sps.game.Sprites.Player;
import com.sps.game.maps.MapFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * This class creates a controller for NPCs which checks for any collisions and allows the NPC to move
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia
 * @version 1.0
 */
public class NPCController {
    /**
     * Stores all npc controllers to use for interaction between controllers.
     */
    private static HashMap<MapFactory.MapType, ArrayList<NPCController>> npcControllers = new HashMap<MapFactory.MapType, ArrayList<NPCController>>();
    /**
     * Used to give each controller a unique ID.
     */
    private static int controllerCurrentID = 0;
    /**
     * ID of the controller
     */
    private int controllerID;
    /**
     * Holds which layer of the TiledMap contains objects that the user can not pass through.
     * @see #collisionDetection
     */
    private TiledMapTileLayer collisionLayer;
    /**
     * Holds a type of NPC
     */
    private AbstractNPC npc;

    private FixtureDef npcBody;
    /**
     * Holds a float value of the width of each tile.
     */
    private float tiledWidth = 32;
    /**
     * Holds a float value of the height of each tile.
     */
    private float tiledHeight = 32;

    /**
     * Uses Tick to break down movement into a number of iterations so that it doesnt move too fast
     * @see #move
     */
    private int tick = 0;
    /**
     * Stores the random variable so it can be used for the movement
     * @see #move
     */
    private Random random;

    /**
     * Establishing a common set of directions.
     */
    private enum Direction {LEFT,RIGHT,DOWN,UP}

    public NPCController(AbstractNPC npc, TiledMapTileLayer collisionLayer){
        this.collisionLayer = collisionLayer;
        this.npc = npc;
        random = new Random();
        npcBody = new FixtureDef();

        this.controllerID = controllerCurrentID;
        controllerCurrentID++;

        if(!npcControllers.containsKey(npc.getWorld()))
            npcControllers.put(npc.getWorld(),new ArrayList<NPCController>());
        npcControllers.get(npc.getWorld()).add(this);
    }

    /**
     * This method checks if the NPC is able to move a location or not. True if they can move otherwise false.
     * @return boolean
     */
    public boolean collisionDetection(){
        boolean collisionX = true;
        boolean collisionY = true;
        Location tempLocation;

        if(npc.getVelocity().y > 0){
            tempLocation = new Location(Math.round(npc.getLocation().getX()),Math.round(npc.getLocation().getY() + 32));
            if(getCellInDirection(Direction.UP) != null)
                collisionY = getTileInDirection(Direction.UP).getProperties().containsKey("blocked") || getTileInDirection(Direction.UP).getProperties().containsKey("nonpc");
            return (collisionY || playerInLocation(tempLocation) || npcInLocation(tempLocation) || interactiveNPCInLocation(tempLocation));
        }
        if(npc.getVelocity().y < 0){
            tempLocation = new Location(Math.round(npc.getLocation().getX()),Math.round(npc.getLocation().getY() - 32));
            if(getCellInDirection(Direction.DOWN) != null)
                collisionY = getTileInDirection(Direction.DOWN).getProperties().containsKey("blocked") || getTileInDirection(Direction.DOWN).getProperties().containsKey("nonpc");
            return (collisionY || playerInLocation(tempLocation) || npcInLocation(tempLocation ) || interactiveNPCInLocation(tempLocation));
        }
        if(npc.getVelocity().x > 0){
            tempLocation = new Location(Math.round(npc.getLocation().getX() + 32),Math.round(npc.getLocation().getY()));
            if(getCellInDirection(Direction.RIGHT) != null)
                collisionX = getTileInDirection(Direction.RIGHT).getProperties().containsKey("blocked") || getTileInDirection(Direction.RIGHT).getProperties().containsKey("nonpc");
            return (collisionX || playerInLocation(tempLocation) || npcInLocation(tempLocation) || interactiveNPCInLocation(tempLocation));
        }
        if(npc.getVelocity().x < 0){
            tempLocation = new Location(Math.round(npc.getLocation().getX() - 32),Math.round(npc.getLocation().getY()));
            if(getCellInDirection(Direction.LEFT) != null)
                collisionX = getTileInDirection(Direction.LEFT).getProperties().containsKey("blocked") || getTileInDirection(Direction.LEFT).getProperties().containsKey("nonpc");
            return (collisionX || playerInLocation(tempLocation) || npcInLocation(tempLocation) || interactiveNPCInLocation(tempLocation));
        }

        return false;
    }

    /** Returns a Tile from a Tiled Map depending on the direction (enum)
     *  Directions : UP, DOWN, LEFT, RIGHT
     *
     * @param direction
     * @return TiledMapTile
     */
    private TiledMapTile getTileInDirection(Direction direction){
        switch (direction){
            case UP : return collisionLayer.getCell((int) (npc.getX() / tiledWidth), (int) ((npc.getY() + 32)/tiledHeight)).getTile();
            case DOWN : return collisionLayer.getCell((int) (npc.getX() / tiledWidth), (int) ((npc.getY() - 32)/tiledHeight)).getTile();
            case RIGHT : return collisionLayer.getCell((int) ((npc.getX() + 32) / tiledWidth), (int) (npc.getY()/tiledHeight)).getTile();
            case LEFT : return collisionLayer.getCell((int) ((npc.getX() - 32) / tiledWidth), (int) (npc.getY()/tiledHeight)).getTile();
        }
        return null;
    }

    /**
     * Gets the cell the NPC is about to move to according to the direction of their move
     * @param direction
     * @return TiledMapTileLayer.Cell
     */
    private TiledMapTileLayer.Cell getCellInDirection(Direction direction){
        switch (direction){
            case UP : return collisionLayer.getCell((int) (npc.getX() / tiledWidth), (int) ((npc.getY() + 32)/tiledHeight));
            case DOWN : return collisionLayer.getCell((int) (npc.getX() / tiledWidth), (int) ((npc.getY() - 32)/tiledHeight));
            case RIGHT : return collisionLayer.getCell((int) ((npc.getX() + 32) / tiledWidth), (int) (npc.getY()/tiledHeight));
            case LEFT : return collisionLayer.getCell((int) ((npc.getX() - 32) / tiledWidth), (int) (npc.getY()/tiledHeight));
        }
        return null;
    }

    /**
     * This method updates the movement for the NPc
     */
    public void move() {
        float oldX = npc.getX(), oldY = npc.getY();
        if (tick == 0){
            switch (random.nextInt(6) + 1){
                case 2:
                    npc.getVelocity().y = 1;
                    if(collisionDetection()) {
                        npc.getVelocity().y = 0;
                    } else {
                        npc.changeState("up");
                        npc.getLocation().setY(npc.getY() + 32);
                    }
                    break;
                case 3:
                    npc.getVelocity().y = -1;
                    if(collisionDetection()) {
                        npc.getVelocity().y = 0;
                    } else {
                        npc.changeState("down");
                        npc.getLocation().setY(npc.getY() - 32);
                    }
                    break;
                case 4:
                    npc.getVelocity().x = 1;
                    if(collisionDetection()) {
                        npc.getVelocity().x = 0;
                    } else {
                        npc.changeState("right");
                        npc.getLocation().setX(npc.getX() + 32);
                    }
                    break;
                case 5:
                    npc.getVelocity().x = -1;
                   if(collisionDetection()) {
                       npc.getVelocity().x = 0;
                   } else {
                       npc.changeState("left");
                       npc.getLocation().setX(npc.getX() - 32);
                   }
                    break;
            }
            tick = 1;
        } else {
            npc.setY(npc.getVelocity().y);
            npc.setX(npc.getVelocity().x);
            tick++;
            if(tick == 33){
                reset();
            }
        }
    }

    /**
     * Returns the ControllerID.
     * @return int controllerID
     */
    public int getControllerID(){ return controllerID;}

    /**
     * Returns the instance of the NPC
     * @return AbstractNPC npc
     */
    public AbstractNPC getNPC(){ return npc;}

    /**
     * Resets the velocity and the time
     */
    public void reset(){
        npc.getVelocity().x = 0;
        npc.getVelocity().y = 0;
        tick = 0;
        npc.changeState("idle");
    }

    /**
     * Returns a boolean to see if the player is in the location they are about to move to.
     * True if the player is in the location they are about to move to, otherwise false.
     * @param location
     * @return boolean
     */
    private boolean playerInLocation(Location location){
        return(location.equals(Player.getPlayer().getLocation()));
    }

    /**
     * Returns a boolean to see if there is an NPC is in the location they are about to move to.
     * True if there is an NPC in that location, otherwise false.
     * @param location
     * @return
     */
    private boolean npcInLocation(Location location){
        ArrayList<NPCController> worldNPCs = npcControllers.get(npc.getWorld());
        for(NPCController controller : worldNPCs){
            if(this.controllerID != controller.getControllerID() && location.equals(controller.getNPC().getLocation()))
                return true;
        }
        return false;
    }

    private boolean interactiveNPCInLocation(Location location){
        ArrayList<Location> interactiveNPCs = InteractiveNPC.allInteractiveNPCLocations;
        for(Location loc : interactiveNPCs){
            if(location.equals(loc)){
                return true;
            }
        }
        return false;
    }
}
