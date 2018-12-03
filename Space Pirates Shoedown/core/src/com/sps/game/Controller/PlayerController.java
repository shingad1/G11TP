package com.sps.game.Controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.Sprites.AbstractNPC;
import com.sps.game.Sprites.InteractiveNPC;
import com.sps.game.Sprites.Location;
import com.sps.game.Sprites.Player;
import com.sps.game.dialogue.Dialogue;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Stack;

/**
 * This class creates a controller for the player which checks for any collisions and allows the player to move
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia
 * @version 1.0
 */

public class PlayerController extends InputAdapter {

    //JSON json;

    private int[] xbounds;
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
     * Used to regulare actions.
     * @see #keyDown #action
     */
    private boolean isKeyDown;
    /**
     * Holds which layer of the TiledMap contains objects that the user can not pass through.
     * @see #keyDown
     */
    private TiledMapTileLayer collisionLayer;

    /**
     * Holds a boolean value to see if the player can enter the building or not.
     */
    private boolean entered;
    /**
     * Holds a boolean value to see if the player can leave the building or not.
     */
    private boolean leave;

    private boolean fight;
    /**
     * Holds the stack of the old positions of the player before he enters the room.
     */
    private Stack<Vector2> positions;

    private boolean resolve;

    private ArrayList<Location> allLocations;

    public PlayerController(Player p, TiledMapTileLayer collisionLayer, int[] xbound, int[] ybound, ArrayList<Location> allLocations){
        this.player = p;
        this.collisionLayer = collisionLayer;
        xbounds = new int[2];
        ybounds = new int[2];
        for (int i=0; i < 2; i++) {
            xbounds[i] = xbound[i];
            ybounds[i] = ybound[i];
        }
        positions = new Stack<Vector2>();

        this.allLocations = allLocations;

        reset();
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
                    fight = isPlayerNearProperty("basicEnemy",tiledWidth, tiledHeight);
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
        The camera will also follow the player except for when the player is near the edge of the map
         */
    }

    public boolean getFight(){
        return fight;
    }

    /**
     * Gets the value of entered.
     * @return <code>boolean</code> entered. True if the player can enter, false if the player cannot enter.
     */
    public boolean getEntered(){
        return entered;
    }

    /**
     * Gets the value of leave.
     * @return <code>boolean</code> leave. True if the player can leave, false if the player cannot leave.
     */
    public boolean getLeave(){
        return leave;
    }

    /**
     * Returns the last position of the player, before they entered the house.
     * @return <code>Vector2</code> last position.
     */
    public Vector2 popPosition(){
        return positions.pop();
    }

    /**
     *
     * @param newLayer
     * @param xbound
     * @param ybound
     */
    public void changeCollisionLayer(TiledMapTileLayer newLayer, int[] xbound, int[] ybound){
        collisionLayer = newLayer;
        reset();
        for (int i=0; i < 2; i++){
            xbounds[i] = xbound[i];
            ybounds[i] = ybound[i];
        }
    }

    public void reset(){
        isKeyDown = false;
        resolve = false;
        player.getVelocity().x = 0;
        player.getVelocity().y = 0;
        entered = false;
        leave = false;
        player.changeState("idle");
        keyPressed = -1;
    }

    public boolean isPlayerNearProperty(String property, float tiledWidth, float tiledHeight) {
        return (getTileNearPlayerWithProperty(property, tiledWidth, tiledHeight) != null);
    }

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

    public void setFight(boolean bool){
        fight = bool;
        if(fight == false){
            collisionLayer.setVisible(false);
        }
    }

    public void collisionCheck(int keycode, boolean collisionY, boolean collisionX, float tiledWidth, float tiledHeight){
        switch(keycode) {

            case Input.Keys.DOWN:
                leave = collisionLayer.getCell((int) (player.getX() / tiledWidth), (int) ((player.getY() - 1) / tiledHeight)).getTile().getProperties().containsKey("leave");
                collisionY = collisionLayer.getCell((int) (player.getX() / tiledWidth), (int) ((player.getY() - 1) / tiledHeight)).getTile().getProperties().containsKey("blocked");
                if (collisionY || npcCollision(new Location(player.getX(),player.getY() - 32))) {
                    player.getVelocity().y = 0;
                } else {
                    player.getVelocity().y = -4;
                    player.changeState("down");
                    isKeyDown = true;
                }
                break;
            case Input.Keys.UP:
                entered = collisionLayer.getCell((int) (player.getX() / tiledWidth), (int) ((player.getY() + 32) / tiledHeight)).getTile().getProperties().containsKey("enter");
                if (entered) {
                    positions.push(new Vector2(player.getX(), player.getY()));
                }
                collisionY = collisionLayer.getCell((int) (player.getX() / tiledWidth), (int) ((player.getY() + 32) / tiledHeight)).getTile().getProperties().containsKey("blocked");
                if (collisionY || npcCollision(new Location(player.getX(),player.getY() + 32))) {
                    player.getVelocity().y = 0;
                } else {
                    player.getVelocity().y = 4;
                    player.changeState("down");
                    isKeyDown = true;
                }
                break;
            case Input.Keys.LEFT:
                collisionX = collisionLayer.getCell((int) ((player.getX() - 1) / tiledWidth), (int) (player.getY() / tiledHeight)).getTile().getProperties().containsKey("blocked");
                if (collisionX || npcCollision(new Location(player.getX() - 32,player.getY()))) {
                    player.getVelocity().x = 0;
                } else {
                    player.getVelocity().x = -4;
                    player.changeState("left");
                    isKeyDown = true;
                }
                break;
            case Input.Keys.RIGHT:
                collisionX = collisionLayer.getCell((int) ((player.getX() + 32) / tiledWidth), (int) (player.getY() / tiledHeight)).getTile().getProperties().containsKey("blocked");
                if (collisionX || npcCollision(new Location(player.getX() + 32,player.getY()))) {
                    player.getVelocity().x = 0;
                } else {
                    player.getVelocity().x = 4;
                    player.changeState("right");
                    isKeyDown = true;
                }
                break;
        }
    }

    public boolean npcCollision(Location location){
        for (Location npcLocation : allLocations){
            if(npcLocation.equals(location)){
                return true;
            }
        }
        return false;
    }

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

    public void test(InteractiveNPC npc)
    {
        if(!(npcInProximity(npc)))
        {
            //System.out.println("test");
        }
        else
        {
            Dialogue dialog = new Dialogue();
            dialog.pack();
            dialog.setVisible(true);
            System.exit(0);
        }
    }

}
