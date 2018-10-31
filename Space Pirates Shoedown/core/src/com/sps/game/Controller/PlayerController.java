package com.sps.game.Controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.sps.game.Screens.PlayScreen;
import com.sps.game.Sprites.Player;
import java.lang.Math;
import java.util.Iterator;

public class PlayerController extends InputAdapter {

    private Player player;
    private int keyPressed; //used to track button presses
    private int tickCount; //used to regulate actions
    private TiledMapTileLayer collisionLayer;

    public PlayerController(Player p, TiledMapTileLayer collisionLayer){
        this.player = p;
        this.collisionLayer = collisionLayer;
    }

    @Override
    public boolean keyDown(int keycode) {
        boolean collisionY = false;
        boolean collisionX = false;


        float oldX = player.getX(), oldY = player.getY();
        float tiledWidth = collisionLayer.getTileWidth(), tiledHeight = collisionLayer.getTileHeight();
        if(tickCount == 0) { //starts the 8 tick count for the movement (in other words, movement is separated into 8 ticks)
            keyPressed = keycode;
            switch(keycode){

                case Input.Keys.DOWN:
                    //checks the bottom middle
                    //if (player.getVelocity().y < 0) {
                    collisionY = collisionLayer.getCell((int)(player.getX()/tiledWidth),(int) ((player.getY() - 32)/tiledHeight)).getTile().getProperties().containsKey("blocked");
                       if(collisionY) {
                           player.getVelocity().y = 0;
                       }
                      else{
                           //maybe make y = oldY
                           player.getVelocity().y = -4;
                     }
                    //}
                    break;
                case Input.Keys.UP:
                    collisionY = collisionLayer.getCell((int)(player.getX()/tiledWidth),(int)((player.getY() + 32)/tiledHeight)).getTile().getProperties().containsKey("blocked");
                    if(collisionY){
                        player.getVelocity().y = 0;
                    }else {
                        player.getVelocity().y = 4;
                    }
                    break;
                case Input.Keys.LEFT:
                    collisionX = collisionLayer.getCell((int)((player.getX() - 32)/tiledWidth),(int)(player.getY()/tiledHeight)).getTile().getProperties().containsKey("blocked");
                    if(collisionX){
                        player.getVelocity().y = 0;
                    }
                    else {
                        player.getVelocity().x = -4;
                    }
                    break;
                case Input.Keys.RIGHT:
                    collisionX = collisionLayer.getCell((int)((player.getX() + 32)/tiledWidth),(int)(player.getY()/tiledHeight)).getTile().getProperties().containsKey("blocked");
                    if(collisionX){
                        player.getVelocity().x = 0;
                    }
                    else {
                        player.getVelocity().x = 4;
                    }
                    break;
            }
            tickCount = 1;
        }

        return false; //if input event was absorbed
    }

    public void action(OrthographicCamera camera) {
        if (tickCount <= 8 && tickCount != 0) { //regulates the amount the player moves so the player moves 1 tile at a time but isn't too fast
            tickCount++;
            player.move(0, Math.round(player.getVelocity().y));
            player.move(Math.round(player.getVelocity().x), 0);
            //if ((player.getX() + player.getVelocity().x > 257) && (player.getX() + player.getVelocity().x < (1600 - 257))){
                camera.position.x += Math.round(player.getVelocity().x);
            //}
            //if ((player.getY() + player.getVelocity().y > 257) && (player.getY() + player.getVelocity().y < (1600 - 257))) {
                camera.position.y += Math.round(player.getVelocity().y);
            //}
        } else {
            tickCount = 0;
            player.getVelocity().x = 0;
            player.getVelocity().y = 0;
        }
    }
}
