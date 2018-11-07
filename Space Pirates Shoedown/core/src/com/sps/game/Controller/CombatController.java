package com.sps.game.Controller;

import com.badlogic.gdx.Input;
import com.sps.game.Sprites.Player;
//allow the player to select and option
public class CombatController {

    private Player player;

    private CombatSystem combatSystem;

    public CombatController(Player p){
        this.player = p;
    }

    public void keyDown(int keycode){
        switch(keycode){
            case Input.Keys.Q:
                break;
            case Input.Keys.W:
                break;
        }
    }
}
