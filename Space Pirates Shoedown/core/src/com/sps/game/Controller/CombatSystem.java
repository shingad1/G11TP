package com.sps.game.Controller;

import com.sps.game.Sprites.BasicEnemy;
import com.sps.game.Sprites.Player;

public class CombatSystem {

    private Player player;

    private BasicEnemy enemy;

    private boolean playerTurn;

    private int tick;

    //private CombatController controller;

    public CombatSystem(Player p, BasicEnemy e){//maybe have controller as a parameter
        this.player = p;
        this.enemy = e;
      //this.controller = controller;
        playerTurn = true;
        tick = 0;
    }

    public void basicPlayerAttack(){
        enemy.decreaseHealth(20);
        playerTurn = false;
        tick = 1;
    }

    public void basicEnemyAttack(){
        if (playerTurn = false) {
            player.decreaseHealth(20);
        }
    }

    public void basicPlayerBlock(){
        if(!(player.getHP()==100)) {
            player.increaseHealth(10);
            playerTurn = false;
        }
        tick = 1;
    }

    public boolean getPlayerTurn(){
        return playerTurn;
    }

    public void update(){
        if (playerTurn != true){
            if (tick == 17) {
                basicEnemyAttack();
                tick++;
            } else {
                if (tick != 33) {
                    tick++;
                } else {
                    tick = 0;
                    playerTurn = true;
                }
            }
        }
    }

    /* Implementing a 'move list'
    public void applyMove(Fighter user, Fighter usee, String move){
        switch(move){
            case "basicAttack":
                usee.decreaseHealth(20);
                user.endTurn();
        }
    }
    */


}
