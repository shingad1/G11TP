package com.sps.game.Controller;

import com.badlogic.gdx.Input;
import com.sps.game.Sprites.Player;
//allow the player to select and option
public class CombatController {

    private Player player;

    private CombatSystem combatSystem;

    private boolean activated;

    public CombatController(Player p){
        this.player = p;
    }

    public void keyDown(int keycode){
        if(activated){
            switch(keycode) {
                case Input.Keys.Q:
                    break;
                case Input.Keys.W:
                    break;
            }
        }
        activated = false;
    }

    public void activate(){
        activated = true;
    }
}
