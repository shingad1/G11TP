package com.sps.game.Controller;

import com.sps.game.Sprites.BasicEnemy;
import com.sps.game.Sprites.Player;

public class CombatSystem {

    private Player player;

    private BasicEnemy enemy;

    private Boolean playerTurn;

    private CombatController controller;

    public CombatSystem(Player p, BasicEnemy e, CombatController controller){
        this.player = p;
        this.enemy = e;
        this.controller = controller;

    }

    public void handleCombat(){
        if (playerTurn){
            controller.activate();
        }
    }

    /* Implementing a 'move list'
    public void applyMove(Fighter user, Fighter usee, String move){
        switch(move){
            // uses on usee
        }
    }
    */

}
