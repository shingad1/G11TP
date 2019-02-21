package com.sps.game.Controller;

import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.sps.game.Sprites.AbstractNPC;
import com.sps.game.Sprites.Location;
import com.sps.game.Sprites.NonInteractiveNPC;
import com.sps.game.Sprites.Player;
import com.sps.game.maps.MapFactory;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class NPCController
{

    /**
     * Holds which layer of the TiledMap contains objects that the user can not pass through.
     * @see #collisionDetection
     */
    private static HashMap<MapFactory.MapType, ArrayList<NPCController>> npcControllers = new HashMap<MapFactory.MapType, ArrayList<NPCController>>(); //Stores all npc controllers to use for interaction between controllers

    private static int controllerCurrentID = 0; //Used to give each controller a unique ID

    private int controllerID; //ID of the controller

    private TiledMapTileLayer collisionLayer;

    private AbstractNPC npc;

    private FixtureDef npcBody;

    private float tiledWidth = 32;
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

    private enum Direction {LEFT,RIGHT,DOWN,UP} //Establishing a common set of directions (to be used for a switch statement)

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

    public boolean collisionDetection(){
        boolean collisionX = true;
        boolean collisionY = true;
        Location tempLocation;

        if(npc.getVelocity().y > 0){
            tempLocation = new Location(Math.round(npc.getLocation().getX()),Math.round(npc.getLocation().getY() + 32));
            if(getTileInDirection(Direction.UP) != null)
                collisionY = getTileInDirection(Direction.UP).getProperties().containsKey("blocked");
            //npcBody.getPosition.y = new Position;
            return (collisionY || playerInLocation(tempLocation) || npcInLocation(tempLocation));
        }
        if(npc.getVelocity().y < 0){
            tempLocation = new Location(Math.round(npc.getLocation().getX()),Math.round(npc.getLocation().getY() - 32));
            if(getTileInDirection(Direction.DOWN) != null)
                collisionY = getTileInDirection(Direction.DOWN).getProperties().containsKey("blocked");
            return (collisionY || playerInLocation(tempLocation) || npcInLocation(tempLocation));
        }
        if(npc.getVelocity().x > 0){
            tempLocation = new Location(Math.round(npc.getLocation().getX() + 32),Math.round(npc.getLocation().getY()));
            if(getTileInDirection(Direction.RIGHT) != null)
                collisionX = getTileInDirection(Direction.RIGHT).getProperties().containsKey("blocked");
            return (collisionX || playerInLocation(tempLocation) || npcInLocation(tempLocation));
        }
        if(npc.getVelocity().x < 0){
            tempLocation = new Location(Math.round(npc.getLocation().getX() - 32),Math.round(npc.getLocation().getY()));
            if(getTileInDirection(Direction.LEFT) != null)
                collisionX = getTileInDirection(Direction.LEFT).getProperties().containsKey("blocked");
            return (collisionX || playerInLocation(tempLocation) || npcInLocation(tempLocation));
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

    public int getControllerID(){ return controllerID;}

    public AbstractNPC getNPC(){ return npc;}

    /**
     * resets the velocity and the time
     */
    public void reset(){
        npc.getVelocity().x = 0;
        npc.getVelocity().y = 0;
        tick = 0;
        npc.changeState("idle");
    }

    private boolean playerInLocation(Location location){
        return(location.equals(Player.getPlayer().getLocation()));
    }

    private boolean npcInLocation(Location location){
        ArrayList<NPCController> worldNPCs = npcControllers.get(npc.getWorld());
        for(NPCController controller : worldNPCs){
            if(this.controllerID != controller.getControllerID() && npc.getLocation().equals(controller.getNPC().getLocation()))
                return true;
        }
        return false;
    }

}
