package com.sps.game.Controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.sps.game.Screens.PlayScreen;
import com.sps.game.Sprites.Player;
import java.lang.Math;

public class PlayerController extends InputAdapter {

    private Player player;
    private int keyPressed; //used to track button presses
    private int tickCount; //used to regulate actions

    public PlayerController(Player p){
        this.player = p;
    }

    @Override
    public boolean keyDown(int keycode) {

        if(tickCount == 0) { //starts the 8 tick count for the movement (in other words, movement is separated into 8 ticks)
            keyPressed = keycode;
            switch(keycode){
                case Input.Keys.DOWN:
                    player.getVelocity().y = -4;
                    break;
                case Input.Keys.UP:
                    player.getVelocity().y = 4;
                    break;
                case Input.Keys.LEFT:
                    player.getVelocity().x = -4;
                    break;
                case Input.Keys.RIGHT:
                    player.getVelocity().x = 4;
                    break;
            }
            tickCount = 1;
        }

        return false; //if input event was absorbed
    }

    public void action(OrthographicCamera camera) {
        if (tickCount <= 8 && tickCount != 0) { //regulates the amount the player moves so the player moves 1 tile at a time but isn't too fast
            player.move(0, Math.round(player.getVelocity().y));
            camera.position.y += Math.round(player.getVelocity().y);
            player.move(Math.round(player.getVelocity().x), 0);
            camera.position.x += Math.round(player.getVelocity().x);
            tickCount++;
        } else {
            tickCount = 0;
            player.getVelocity().x = 0;
            player.getVelocity().y = 0;
        }
    }
}
