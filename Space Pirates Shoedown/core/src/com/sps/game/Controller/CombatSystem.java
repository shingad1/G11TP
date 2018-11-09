package com.sps.game.Controller;

import com.sps.game.Sprites.BasicEnemy;
import com.sps.game.Sprites.Player;

/**
 * This class has all the methods used in the battle scene, in order to increase/decrease the player's/ enemies lives
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia
 * @version 1.0
 */

public class CombatSystem
{
    private Player player;
    /**
     * creates an instance of the player
     * @see #CombatSystem
     */
    private BasicEnemy enemy;
    /**
     * creates an instance of the enemy
     * @see #CombatSystem
     */
    private boolean playerTurn;
    /**
     * holds a boolean value, which changes if it is the players turn and only then the player can attack the enemy
     * @see #getPlayerTurn
     */
    private int tick;
    /**
     * keeps track of the time so the player and enemy has equal chance to fight
     * @see #getPlayerTurn
     */
    private boolean finished;
    /**
     * keeps track whether the battle is finished or not
     * @see #getPlayerTurn
     */

    public CombatSystem(Player p, BasicEnemy e){
        this.player = p;
        this.enemy = e;
        playerTurn = true;
        tick = 0;
        finished = false;
    }
    /**
     * Changes the enemies health if it has been attacked and sets the tick to 1
     */
    public void basicPlayerAttack(){
        enemy.decreaseHealth(20);
        tick = 1;
    }
    /**
     * Changes the players health if it has been attacked and sets the tick to 1
     */
    public void basicEnemyAttack(){
        player.decreaseHealth(20);
        tick = 1;
    }
    /**
     * Changes the players health, increases it and sets the tick to 1
     */
    public void basicPlayerBlock() {
        if (!(player.getHP() == 100)) {
            player.increaseHealth(10);
        }
        tick = 1;
    }
    /**
     * changes the boolean when it is the players turn
     */
    public boolean getPlayerTurn(){
        return (playerTurn && (tick == 0));
    }
    /**
     *this updates the battle scene so the enemy and the player has equal chances
     */
    public void update() {
        if (!(finished)){
            if (!(playerTurn) && (tick == 0)) {
                basicEnemyAttack();
            }
            if (tick > 0) {
                tick++;
                if (tick >= 65) {
                    playerTurn = !(playerTurn);
                    tick = 0;
                }
            }
            if ((player.getHP() == 0) || (enemy.getHealth() == 0)) {
                finished = true;
            }
        }
    }
    /**
     * keeps track of whether the game is finished or nto
     * @return <code>Boolean</code>
     */
    public boolean getFinished(){
        return finished;
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
