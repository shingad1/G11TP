package com.sps.game.Controller;

import com.sps.game.Sprites.BasicEnemy;
import com.sps.game.Sprites.Player;

public class CombatSystem {

    private Player player;

    private BasicEnemy enemy;

    private boolean playerTurn;

    //private CombatController controller;

    public CombatSystem(Player p, BasicEnemy e){//maybe have controller as a parameter
        this.player = p;
        this.enemy = e;
      //this.controller = controller;
        playerTurn = true;
    }

    public void basicPlayerAttack(){
        player.decreaseHealth(20);
        playerTurn = false;
    }

    public void basicEnemyAttack(){
        enemy.decreaseHealth(20);
        playerTurn = true;
    }

    public void basicPlayerBlock(){
        if(!(player.getHP()==100)) {
            player.increaseHealth(10);
            playerTurn = false;
        }
    }

    public boolean getPlayerTurn(){
        return playerTurn;
    }

    /* Implementing a 'move list'
    public void applyMove(Fighter user, Fighter usee, String move){
        switch(move){
            // uses on usee
        }
    }
    */

}
