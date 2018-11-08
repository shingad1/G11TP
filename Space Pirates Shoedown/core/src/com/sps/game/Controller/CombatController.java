package com.sps.game.Controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.sps.game.Sprites.BasicEnemy;
import com.sps.game.Sprites.Player;
//allow the player to select and option
public class CombatController extends InputAdapter {

    private Player player;

    private BasicEnemy enemy;

    private CombatSystem combatSystem;

    private int keyPressed;

    public CombatController(Player p, BasicEnemy e, CombatSystem cs){
        this.player = p;
        this.enemy = e;
        combatSystem = cs;
    }

    @Override
    public boolean keyDown(int keycode){
        if(combatSystem.getPlayerTurn()){//was activated
            keyPressed = keycode;
            switch(keycode) {
                case Input.Keys.Q:
                    combatSystem.basicPlayerAttack();
                    System.out.println(player.getHP());
                    break;
                case Input.Keys.W:
                    combatSystem.basicPlayerBlock();
                    break;
            }
        }
        //activated = false;
        return false;
    }

}
