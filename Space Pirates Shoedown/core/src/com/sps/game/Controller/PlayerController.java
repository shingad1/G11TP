    package com.sps.game.Controller;

    import com.badlogic.gdx.Gdx;
    import com.badlogic.gdx.Input;
    import com.badlogic.gdx.InputAdapter;
    import com.badlogic.gdx.graphics.OrthographicCamera;
    import com.badlogic.gdx.graphics.g2d.SpriteBatch;
    import com.badlogic.gdx.maps.tiled.TiledMapTile;
    import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
    import com.badlogic.gdx.math.Vector2;
    import com.sps.game.Inventory2.Inventory;
    import com.sps.game.Scenes.InventoryHud;
    import com.sps.game.Screens.PlayScreen;
    import com.sps.game.Sprites.InteractiveNPC;
    import com.sps.game.Sprites.InteractiveNPCMoving;
    import com.sps.game.Sprites.Location;
    import com.sps.game.Sprites.Player;
    import com.sps.game.dialogue.Dialogue;
    import com.sps.game.profile.ProfileManager;

    import javax.swing.*;
    import java.io.IOException;
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

    private boolean candy;

    private boolean dialogue;

    private boolean fight;
    /**
    * Holds the stack of the old positions of the player before he enters the room.
    */
    private Stack<Vector2> positions;

    private boolean resolve;

    private ArrayList<Location> allLocations;

    private boolean newWorldRight; //move to world right i.e. 1,1 to 1,2

    private boolean newWorldLeft; //move to world left i.e. 1,2 to 1,1

    private boolean newWorldUp; //move to world up i.e. 2,1 to 1,1

    private boolean newWorldDown; //move to world down i.e. 1,1 to 2,1

    private Inventory inventory;

    public Boolean dialogueBoolean;

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
    positions = new Stack<Vector2>();

    this.allLocations = allLocations;
    dialogue = false;
    candy = false;

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
            fight = isPlayerNearProperty("basicEnemy",tiledWidth, tiledHeight);
            break;
        case Input.Keys.I:
            System.out.println("Inventory Loaded");
            break;
        case Input.Keys.O:
            System.out.println("Inventory exit");
            break;
        case Input.Keys.B:
            System.out.println("dialogue exit");
            dialogueBoolean = false;
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

    public boolean getCandy(){return candy;}

    public boolean getNewWorldRight(){ return newWorldRight;}

    public boolean getNewWorldLeft(){ return newWorldLeft;}

    public boolean getNewWorldUp(){ return newWorldUp;}

    public boolean getNewWorldDown(){ return newWorldDown;}

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

    public void newWorldReset(){
    newWorldRight = false;
    newWorldLeft = false;
    newWorldUp = false;
    newWorldDown = false;
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
        if(player.getY() == 0){
            newWorldDown = true;
        } else {
            leave = collisionLayer.getCell((int) (player.getX() / tiledWidth), (int) ((player.getY() - 1) / tiledHeight)).getTile().getProperties().containsKey("leave");
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
            entered = collisionLayer.getCell((int) (player.getX() / tiledWidth), (int) ((player.getY() + 32) / tiledHeight)).getTile().getProperties().containsKey("enter");
            if (entered) {
                positions.push(new Vector2(player.getX(), player.getY()));
            }
            candy = collisionLayer.getCell((int) (player.getX() / tiledWidth), (int) ((player.getY() + 32) / tiledHeight)).getTile().getProperties().containsKey("candyLand");
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
        //newWorld = collisionLayer.getCell((int) ((player.getX() + 32) / tiledWidth), (int) (player.getY() / tiledHeight)).getTile().getProperties().containsKey("newWorld");
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

    public boolean npcInProximity(InteractiveNPCMoving npc){
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

    public boolean npcInProximity1(InteractiveNPC npc){
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

    public void npcInteraction(){
        //if (Gdx.input.isKeyPressed(Input.Keys.B)) {
        PlayScreen.dialogController.render();
        //PlayScreen.dialogBoolean = false;
        //}
    }

        public void npcInteraction(ArrayList<InteractiveNPC> npcList, String npcName) {
            if (!npcList.isEmpty()) {
                for (int i = 0; i < npcList.size(); i++) {
                    InteractiveNPC tempNPC = npcList.get(i);
                    String temp = tempNPC.getName();
                    if (npcInProximity1(tempNPC) && dialogue == false && tempNPC.getType().equals("InteractiveNPC")) {
                        if (Gdx.input.isKeyPressed(Input.Keys.B)) {
                            if (temp.equals(npcName)) {
                                Dialogue dialog2 = new Dialogue(npcName);
                                dialogue = true;
                                if (temp.equals(npcName)) {
                                    player.getVelocity().x = -4;
                                    try
                                    {
                                        Thread.sleep(100);
                                    }catch (InterruptedException e)
                                    {
                                        e.printStackTrace();
                                    }
                                    player.changeState("left");
                                    isKeyDown = true;
                                    dialog2.pack();
                                    dialog2.setVisible(true);
                                }
                            }
                            boolean switchOff = true;

                            if (dialogue == true) {
                                for (InteractiveNPC npc2 : npcList) {
                                    switchOff = false;
                                }

                            }

                            if (switchOff) {
                                dialogue = false;
                            }
                        }

                    }


                    boolean switchOff = true;
                    if (dialogue == true) {
                        for (InteractiveNPC npc : npcList) {
                            switchOff = false;

                        }
                    }
                    if (switchOff) {
                        dialogue = false;
                    }
                }
            }
        }


        public void npcmoving (ArrayList < InteractiveNPCMoving > npcList, String npcName) {

            for (int i = 0; i < npcList.size(); i++) {
                InteractiveNPCMoving tempNPC = npcList.get(i);
                String temp = tempNPC.getName();

                if (npcInProximity(tempNPC) && dialogue == false && (tempNPC.getType().equals("InteractiveNPCMoving"))) {
                    Dialogue dialog = new Dialogue(npcName);
                    //dialogue i= true;

                    if (temp.equals(npcName)) {
                        dialog.pack();
                        dialog.setVisible(true);
                    }
                }
            }

            boolean switchOff = true;
            if (dialogue == true) {
                for (InteractiveNPCMoving npc : npcList) {
                    if (npcInProximity(npc)) {
                        switchOff = false;
                    }

                }
            }

            if (switchOff) {
                dialogue = false;
            }
        }
}
