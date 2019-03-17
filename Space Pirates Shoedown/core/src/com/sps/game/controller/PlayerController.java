package com.sps.game.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.sps.game.screens.PlayScreen;
import com.sps.game.sprites.*;
import java.lang.Math;
import java.util.ArrayList;

/**
 * This class creates a controller for the player which checks for any collisions and allows the player to move
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia
 * @version 1.0
 */
public class PlayerController extends InputAdapter {

    /**
     * Holds the xbounds of the Map in the Map in an array.
     */
    private int[] xbounds;
    /**
     * Holds the ybounds of the Map in the Map in an array.
     */
    private int[] ybounds;
    /**
     * Creates instance of the player, which holds the logic of the player to control it.
     * @see #keyDown #action
     */
    private Player player;
    /**
     * Tracks the button pressed.
     * @see #keyDown
     */
    private int keyPressed;
    /**
     * Used to regulate actions.
     * @see #keyDown #action
     */
    private boolean isKeyDown;
    /**
     * Holds which layer of the TiledMap contains objects that the user can not pass through.
     * @see #keyDown
     */
    private TiledMapTileLayer collisionLayer;

    private boolean resolve;
    /**
     * Holds all the locations of all the NPCs on the map.
     */
    private ArrayList<Location> allLocations;

    private boolean newWorldRight; //move to world right i.e. 1,1 to 1,2

    private boolean newWorldLeft; //move to world left i.e. 1,2 to 1,1

    private boolean newWorldUp; //move to world up i.e. 2,1 to 1,1

    private boolean newWorldDown; //move to world down i.e. 1,1 to 2,1

    private boolean enterShip;

    public PlayerController(Player p, TiledMapTileLayer collisionLayer, int[] xbound, int[] ybound, ArrayList<Location> allLocations){
        //dialogue = false;
        this.player = p;
        this.collisionLayer = collisionLayer;
        xbounds = new int[2];
        ybounds = new int[2];
        for (int i=0; i < 2; i++) {
            xbounds[i] = xbound[i];
            ybounds[i] = ybound[i];
        }

        this.allLocations = allLocations;

        reset();
        newWorldReset();
    }

    /**
     * Checks to see what the user inputs, then performs a check to see if it can move there, then moves if the user can
     * otherwise it remains where it was.
     * @param <code>int</code>keycode
     * @return <code>Boolean</code>
     */
    @Override
    public boolean keyDown(int keycode) {
        boolean collisionY = false;
        boolean collisionX = false;

        //float oldX = player.getX(), oldY = player.getY();
        float tiledWidth = collisionLayer.getTileWidth(), tiledHeight = collisionLayer.getTileHeight();
        if(!(isKeyDown)) { //starts the 8 tick count for the movement (in other words, movement is separated into 8 ticks)
            keyPressed = keycode;
            switch(keycode){
                case Input.Keys.A:

                    break;
                case Input.Keys.E:
                    //WILL HAVE IF STATEMENT AROUND IT
                    enterShip = true;
                    break;
                case Input.Keys.X:
                   // ProfileManager.getInstance().saveProfile();
                    PlayScreen.setGameState(PlayScreen.GameState.Saving);
                    System.out.println("Game Saved");
                    break;
                case Input.Keys.I:
                    System.out.println("Inventory Loaded");
                    break;
                case Input.Keys.O:
                    System.out.println("Inventory exit");
                    break;
                default:
                    collisionCheck(keycode,collisionY,collisionX,tiledWidth,tiledHeight);
            }
        }

        return false; //if input event was absorbed
    }

    public boolean keyUp(int keyCode){
        resolve = true;
        return true;
    }

    /**
     *
     * @param <code>OrthographicCamera<code>camera
     */
    public void action(OrthographicCamera camera) {
        if (isKeyDown) { //regulates the amount the player moves so the player moves 1 tile at a time but isn't too fast
            collisionCheck(keyPressed,false,false,32,32);
            player.move(0, Math.round(player.getVelocity().y));
            player.move(Math.round(player.getVelocity().x), 0);
            if ((player.getX() + player.getVelocity().x >= xbounds[0] + 256) && (player.getX() + player.getVelocity().x <= xbounds[1] - 256)){
                camera.position.x = player.getX();
            }
            if ((player.getY() + player.getVelocity().y >= ybounds[0] + 256) && (player.getY() + player.getVelocity().y <= ybounds[1] - 256)) {
                camera.position.y = player.getY();
            }
        }
        if(resolve == true){
            if(player.getX() % 32 == 0 && player.getY() % 32 == 0) {
                reset();
            }
        }
        /* The code above breaks down the movement into 8 ticks so that the character doesn't move too fast
        tickCount will be responsible for doing keeping track of the 8 ticks
        The movement will then be confined to moving 32px per key press
        The camera will also follow the player except for when the player is near the edge of the currentMap
         */
    }

    /**
     * Gets the boolean value of newWorldRight.
     * @return boolean newWorldRight
     */
    public boolean getNewWorldRight(){ return newWorldRight;}
    /**
     * Gets the boolean value of newWorldLeft.
     * @return boolean newWorldLeft
     */
    public boolean getNewWorldLeft(){ return newWorldLeft;}
    /**
     * Gets the boolean value of newWorldUp.
     * @return boolean newWorldUp
     */
    public boolean getNewWorldUp(){ return newWorldUp;}
    /**
     * Gets the boolean value of newWorldDown.
     * @return boolean newWorldDown
     */
    public boolean getNewWorldDown(){ return newWorldDown;}

    /**
     * Gets the boolean value of enterShip.
     * @return boolean enterShip
     */
    public boolean getEnterShip(){
        return enterShip;
    }

    /**
     * Changes the current collision layer, to the collision layer of the current map.
     * @param TiledMapLayer newLayer
     * @param int[] xbound
     * @param int[] ybound
     */
    public void changeCollisionLayer(TiledMapTileLayer newLayer, int[] xbound, int[] ybound){
        collisionLayer = newLayer;
        reset();
        for (int i=0; i < 2; i++){
            xbounds[i] = xbound[i];
            ybounds[i] = ybound[i];
        }
    }

    /**
     * Resets the all the values.
     */
    public void reset(){
        isKeyDown = false;
        resolve = false;
        player.getVelocity().x = 0;
        player.getVelocity().y = 0;
        player.changeState("idle");
        keyPressed = -1;
    }

    /**
     * Resest the values of the newWorld variables
     */
    public void newWorldReset(){
        newWorldRight = false;
        newWorldLeft = false;
        newWorldUp = false;
        newWorldDown = false;
    }

    /**
     * Checks to see if the player is near a tile with a particular property.
     * @param String property
     * @param float tiledWidth
     * @param float tiledHeight
     * @return boolean
     */
    public boolean isPlayerNearProperty(String property, float tiledWidth, float tiledHeight) {
        return (getTileNearPlayerWithProperty(property, tiledWidth, tiledHeight) != null);
    }

    /**
     * Get a tile with a particular property
     * @param String property
     * @param float tiledWidth
     * @param float tiledHeight
     * @return TiledMapTile
     */
    public TiledMapTile getTileNearPlayerWithProperty(String property, float tiledWidth, float tiledHeight){
        if (collisionLayer.getCell((int)((player.getX() + 32)/tiledWidth),(int)(player.getY()/tiledHeight)).getTile().getProperties().containsKey(property)){
            return collisionLayer.getCell((int)((player.getX() + 32)/tiledWidth),(int)(player.getY()/tiledHeight)).getTile();
        } else if (collisionLayer.getCell((int)((player.getX() - 32)/tiledWidth),(int)(player.getY()/tiledHeight)).getTile().getProperties().containsKey(property)){
            return collisionLayer.getCell((int)((player.getX() - 32)/tiledWidth),(int)(player.getY()/tiledHeight)).getTile();
        } else if (collisionLayer.getCell((int)((player.getX())/tiledWidth),(int)((player.getY() + 32)/tiledHeight)).getTile().getProperties().containsKey(property)){
            return collisionLayer.getCell((int)((player.getX())/tiledWidth),(int)((player.getY() + 32)/tiledHeight)).getTile();
        } else if (collisionLayer.getCell((int)((player.getX())/tiledWidth),(int)((player.getY() - 32)/tiledHeight)).getTile().getProperties().containsKey(property)){
            return collisionLayer.getCell((int)((player.getX())/tiledWidth),(int)((player.getY() - 32)/tiledHeight)).getTile();
        }
        return null;
    }

    /**
     * Checks to see if the player can move in a particular direction and moves them if they can.
     * @param int keycode
     * @param boolean collisionY
     * @param boolean collisionX
     * @param float tiledWidth
     * @param float tiledHeight
     */
    public void collisionCheck(int keycode, boolean collisionY, boolean collisionX, float tiledWidth, float tiledHeight){
        switch(keycode) {
            case Input.Keys.DOWN:
                if(player.getY() == 0){
                    newWorldDown = true;
                } else {
                    collisionY = collisionLayer.getCell((int) (player.getX() / tiledWidth), (int) ((player.getY() - 1) / tiledHeight)).getTile().getProperties().containsKey("blocked");
                    if (collisionY || npcCollision(new Location(player.getX(), player.getY() - 32))) {
                        player.getVelocity().y = 0;
                    } else {
                        player.getVelocity().y = -4;
                        player.changeState("down");
                        isKeyDown = true;
                    }
                    break;
                }
            case Input.Keys.UP:
                if (player.getY() == 49 * 32){
                    newWorldUp = true;
                } else {
                    collisionY = collisionLayer.getCell((int) (player.getX() / tiledWidth), (int) ((player.getY() + 32) / tiledHeight)).getTile().getProperties().containsKey("blocked");
                    if (collisionY || npcCollision(new Location(player.getX(), player.getY() + 32))) {
                        player.getVelocity().y = 0;
                    } else {
                        player.getVelocity().y = 4;
                        player.changeState("down");
                        isKeyDown = true;
                    }
                    break;
                }
            case Input.Keys.LEFT:
                if (player.getX() == 0){
                    newWorldLeft = true;
                } else {
                    collisionX = collisionLayer.getCell((int) ((player.getX() - 1) / tiledWidth), (int) (player.getY() / tiledHeight)).getTile().getProperties().containsKey("blocked");
                    if (collisionX || npcCollision(new Location(player.getX() - 32, player.getY()))) {
                        player.getVelocity().x = 0;
                    } else {
                        player.getVelocity().x = -4;
                        player.changeState("left");
                        isKeyDown = true;
                    }
                    break;
                }
            case Input.Keys.RIGHT:
                if(player.getX() == 49 * 32) { //If the player reaches the end of the map and presses right move to the map to the right
                  newWorldRight = true;
                } else {
                    collisionX = collisionLayer.getCell((int) ((player.getX() + 32) / tiledWidth), (int) (player.getY() / tiledHeight)).getTile().getProperties().containsKey("blocked");
                    if (collisionX || npcCollision(new Location(player.getX() + 32, player.getY()))) {
                        player.getVelocity().x = 0;
                    } else {
                        player.getVelocity().x = 4;
                        player.changeState("right");
                        isKeyDown = true;
                    }
                    break;
                }
        }
    }

    /**
     * Returns a boolean if there is a NPC in the location the player wants to move to.
     * @param Location location
     * @return boolean
     */
    public boolean npcCollision(Location location){
        if(allLocations != null) {
            for (Location npcLocation : allLocations) {
                if (npcLocation.equals(location)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks to see if there is an NPC near the player.
     * @param AbstractNPC npc
     * @return boolean
     */
    public boolean npcInProximity(AbstractNPC npc){
        if((new Location(Math.round(player.getLocation().getX()),Math.round(player.getLocation().getY()) + 32)).equals(npc.getLocation())){
            return true;
        }
        if((new Location(Math.round(player.getLocation().getX()),Math.round(player.getLocation().getY()) - 32)).equals(npc.getLocation())){
            return true;
        }
        if((new Location(Math.round(player.getLocation().getX()) + 32,Math.round(player.getLocation().getY()))).equals(npc.getLocation())){
            return true;
        }
        if((new Location(Math.round(player.getLocation().getX()) - 32,Math.round(player.getLocation().getY()))).equals(npc.getLocation())){
            return true;
        }
        return false;
    }

    /**
     * Checks to see if there is an enemy near the player.
     * @param AbstractEnemy enemy
     * @return boolean
     */
    public boolean enemyInProximity(AbstractEnemy enemy){
        if((new Location(Math.round(player.getLocation().getX()),Math.round(player.getLocation().getY()) + 32)).equals(enemy.getLocation())){
            return true;
        }
        if((new Location(Math.round(player.getLocation().getX()),Math.round(player.getLocation().getY()) - 32)).equals(enemy.getLocation())){
            return true;
        }
        if((new Location(Math.round(player.getLocation().getX()) + 32,Math.round(player.getLocation().getY()))).equals(enemy.getLocation())){
            return true;
        }
        if((new Location(Math.round(player.getLocation().getX()) - 32,Math.round(player.getLocation().getY()))).equals(enemy.getLocation())){
            return true;
        }
        return false;
    }

    /**
     * Adds all the NPCs to the allLocations ArrayList
     * @param npcLocations
     */
    public void changeNpcLocations(ArrayList<Location> npcLocations){
        allLocations = npcLocations;
    }
}
