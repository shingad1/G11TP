package com.sps.game.Controller;

import com.sps.game.Screens.Fighter;
import com.sps.game.Sprites.AbstractEnemy;
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
    private AbstractEnemy enemy;
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

    private String chosenMove;

    private MoveList playerMoveList;
    private MoveList enemyMoveList;

    public CombatSystem(Player p, AbstractEnemy e){
        this.player = p;
        this.enemy = e;
        playerMoveList = new MoveList(this.player,this.enemy);
        enemyMoveList = new MoveList(this.enemy,this.player);
        playerTurn = true;
        tick = 0;
        finished = false;
        chosenMove = "";
    }
    /**
     * Changes the boolean when it is the players turn.
     */
    public boolean getPlayerTurn(){
        return (playerTurn && (tick == 0));
    }
    /**
     * This updates the battle scene so the enemy and the player has equal chances.
     */
    public void update() {
        if (!(finished)){
            if (!(playerTurn) && (tick == 0)) {
                enemy.battleMove();
                if(!(chosenMove.equals(""))) {
                    applyMove(playerMoveList);
                }
            } else if ((playerTurn) && (tick == 0)){
                if(!(chosenMove.equals(""))) {
                    applyMove(enemyMoveList);
                }
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
     * Keeps track of whether the game is finished or not.
     * @return <code>Boolean</code>
     */
    public boolean getFinished(){
        return finished;
    }

    public void doMove(String move){
        chosenMove = move;
    }

    //Implementing a 'move list'
    public void applyMove(MoveList userMoveList){
        userMoveList.use(chosenMove);
        chosenMove = "";
        tick = 1;
    }


}
