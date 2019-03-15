package com.sps.game.Controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sps.game.Screens.Fighter;
import com.sps.game.Sprites.AbstractEnemy;
import com.sps.game.Sprites.BasicEnemy;
import com.sps.game.Sprites.Location;
import com.sps.game.Sprites.Player;
import com.sps.game.dialogue.Dialogue;

import java.util.HashMap;
import java.util.Stack;

/**
 * This class has all the methods used in the battle scene, in order to increase/decrease the player's/ enemies lives
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia
 * @version 1.0
 */

public class CombatSystem
{
    enum Inputs {Q,W,E,R,A,S,D}

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
    private Stack<HashMap> moveSelectionStack;

    private int moveSelector;

    private MoveList playerMoveList;
    private MoveList enemyMoveList;

    private BattleAnimationHandler animationHandler;

    private boolean playerControl;

    private MoveList.Move chosenMove;

    public CombatSystem(Player p, AbstractEnemy e, SpriteBatch sb){
        this.player = p;
        this.enemy = e;
        playerMoveList = new MoveList(this.player,this.enemy);
        enemyMoveList = new MoveList(this.enemy,this.player);
        playerTurn = true;
        tick = 0;
        finished = false;
        animationHandler = new BattleAnimationHandler(p,e,sb,new Location(64,150),new Location(256,150));

        HashMap<MoveList.MoveType, HashMap<String, MoveList.Move>> moveSelectionHashMap = new HashMap<MoveList.MoveType, HashMap<String, MoveList.Move>>();
        for (MoveList.MoveType moveType : playerMoveList.getMoveTypes()){
            moveSelectionHashMap.put(moveType,playerMoveList.getMovesHashMapByType(moveType));
        }
        moveSelectionStack = new Stack<HashMap>();
        moveSelectionStack.push(moveSelectionHashMap);
        moveSelector = 0;
        playerControl = true;
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
            if(playerControl){

            }
            /**
            if (!(playerTurn) && !(animationHandler.isInAnimation()) && !(animationHandler.isAnimationEnd())) {
                enemy.battleMove();
                if(!(chosenMove.equals(""))) {
                    animationHandler.setupEnemyAnimation("");
                }
            } else if ((playerTurn) && !(animationHandler.isInAnimation()) && !(animationHandler.isAnimationEnd())){
                if(chosenMove != null) {
                    animationHandler.setupPlayerAnimation("");
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
             */
            if ((player.getHealth() == 0) || (enemy.getHealth() == 0)) {
                finished = true;
                Dialogue d = new Dialogue("Enemy");
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

    public String[] getOptions(){
        String[] options = new String[7];
        for (String option: options) {
            option = "";
        }
        Object[] moveSelectionStackKeySet = getMoveSelectionStackKeySet();
        int moveSelectorEnd = moveSelector + 4;
        if(moveSelector + 4 > moveSelectionStack.peek().keySet().size())
            moveSelectorEnd = moveSelectionStack.peek().keySet().size();
        for(int i = (4 * moveSelector); i < moveSelectorEnd; i++){
            options[i - moveSelector] = moveSelectionStackKeySet[i - moveSelector].toString();
        }
        if(moveSelector * 4 < moveSelectionStackKeySet.length)
            options[4] = "Next Page";
        if(moveSelector > 0)
            options[5] = "Previous Page";
        if(moveSelectionStack.size() > 1)
            options[6] = "Back";
    }

    public Object[] getMoveSelectionStackKeySet(){
         return moveSelectionStack.peek().keySet().toArray();
    }

    public void doInput(Inputs input){
        switch (input){
            case Q:
                if(getOptions()[0] != "" && !(moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[0]) instanceof MoveList.Move)) {
                    moveSelectionStack.push((HashMap) moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[0]));
                    resetInput(0);
                } else if(moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[0]) instanceof MoveList.Move){
                    chosenMove = (MoveList.Move) moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[0]);
                    playerControl = false;
                    resetInput(1);
                }
                break;
            case W:
                if(getOptions()[1] != "" && !(moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[1]) instanceof MoveList.Move)) {
                    moveSelectionStack.push((HashMap) moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[1]));
                    resetInput(0);
                } else if(moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[1]) instanceof MoveList.Move){
                    chosenMove = (MoveList.Move) moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[1]);
                    playerControl = false;
                    resetInput(1);
                }
                break;
            case E:
                if(getOptions()[2] != "" && !(moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[2]) instanceof MoveList.Move)) {
                    moveSelectionStack.push((HashMap) moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[2]));
                    resetInput(0);
                } else if(moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[2]) instanceof MoveList.Move){
                    chosenMove = (MoveList.Move) moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[2]);
                    playerControl = false;
                    resetInput(1);
                }
                break;
            case R:
                if(getOptions()[3] != "" && !(moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[3]) instanceof MoveList.Move)) {
                    moveSelectionStack.push((HashMap) moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[3]));
                    resetInput(0);
                } else if(moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[3]) instanceof MoveList.Move){
                    chosenMove = (MoveList.Move) moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[3]);
                    playerControl = false;
                    resetInput(1);
                }
                break;
            case A:
                if (moveSelector * 4 < getMoveSelectionStackKeySet().length)
                    moveSelector++;
                break;
            case S:
                if (moveSelector > 0)
                    moveSelector--;
                break;
            case D:
                if (moveSelectionStack.size() > 1)
                    moveSelectionStack.pop();
                    resetInput(0);
                break;
        }
        /**
         * The player chooses an action
         * If the action is not a move then continue giving the player control
         * If it is a move, then, select the move and the player loses control
         * The enemy chooses their move and the battle occurs
         *
         * Choosing the action
         * 0-3 (pushes on the moveSelectionStack
         * 4, 5 and 6 are special (add checks)
         *  4: scrolls to next page
         *  5: scrolls back to previous page
         *  6: goes back
         */
    }

    private void resetInput(int resetLevel){
        moveSelector = 0;
        if(resetLevel >= 1){ //resets options
            while(moveSelectionStack.size() > 1){
                moveSelectionStack.pop();
            }
            if(resetLevel >= 2){ //resets the combat system
                chosenMove = null;
                playerControl = true;
            }
        }
    }

    //Implementing a 'move list'
    public void applyMove(MoveList userMoveList){
        userMoveList.use("");
    }


}
