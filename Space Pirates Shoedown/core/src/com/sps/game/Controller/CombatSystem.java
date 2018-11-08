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
        tick = 1;
    }

    public void basicEnemyAttack(){
        player.decreaseHealth(20);
        tick = 1;
    }

    public void basicPlayerBlock(){
        if(!(player.getHP()==100)) {
            player.increaseHealth(10);
        }
        tick = 1;
    }

    public boolean getPlayerTurn(){
        return (playerTurn && (tick == 0));
    }

    public void update(){
        if(!(playerTurn) && (tick == 0)){
            basicEnemyAttack();
        }
        if (tick > 0){
            tick++;
            if (tick >= 65){
                playerTurn = !(playerTurn);
                tick = 0;
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
