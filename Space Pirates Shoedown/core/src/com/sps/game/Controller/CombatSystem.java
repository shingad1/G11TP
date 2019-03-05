package com.sps.game.Controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sps.game.Screens.Fighter;
import com.sps.game.Sprites.AbstractEnemy;
import com.sps.game.Sprites.BasicEnemy;
import com.sps.game.Sprites.Location;
import com.sps.game.Sprites.Player;
import com.sps.game.dialogue.Dialogue;

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

    private BattleAnimationHandler animationHandler;

    public CombatSystem(Player p, AbstractEnemy e, SpriteBatch sb){
        this.player = p;
        this.enemy = e;
        playerMoveList = new MoveList(this.player,this.enemy);
        enemyMoveList = new MoveList(this.enemy,this.player);
        playerTurn = true;
        tick = 0;
        finished = false;
        chosenMove = "";
        animationHandler = new BattleAnimationHandler(p,e,sb,new Location(64,150),new Location(256,150));
    }
    /**
     * Changes the boolean when it is the players turn.
     */
    public boolean getPlayerTurn(){
        return (playerTurn && (tick == 0));
    }

    public void render(){
        animationHandler.render(playerTurn);
    }

    /**
     * This updates the battle scene so the enemy and the player has equal chances.
     */
    public void update() {
        if (!(finished)){
            if (!(playerTurn) && !(animationHandler.isInAnimation()) && !(animationHandler.isAnimationEnd())) {
                enemy.battleMove();
                if(!(chosenMove.equals(""))) {
                    animationHandler.setupEnemyAnimation(chosenMove);
                }
            } else if ((playerTurn) && !(animationHandler.isInAnimation()) && !(animationHandler.isAnimationEnd())){
                if(!(chosenMove.equals(""))) {
                    animationHandler.setupPlayerAnimation(chosenMove);
                }
            }
            if (animationHandler.isAnimationEnd()) {
                if(!(playerTurn)) {
                    applyMove(enemyMoveList);
                } else {
                    applyMove(playerMoveList);
                }
                playerTurn = !playerTurn;
            }
            if ((player.getHP() == 0) || (enemy.getHealth() == 0)) {
                finished = true;
                if(finished){
                    Dialogue d = new Dialogue("Enemy");
                }
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
    }


}
