package com.sps.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sps.game.sprites.AbstractEnemy;
import com.sps.game.sprites.Location;
import com.sps.game.sprites.Player;

import java.util.HashMap;
import java.util.Stack;

/**
 * This class has all the methods used in the battle scene, in order to increase/decrease the player's/ enemies lives
 * @author Miguel Abaquin
 * @version 1.0
 */

public class CombatSystem
{
    enum Inputs {Q,W,E,R,A,S,D}

    enum BattleOutcome {Win, Draw, Lose}

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

    private MoveList.Move playerChosenMove;
    private String playerChosenMoveKey;

    private MoveList.Move enemyChosenMove;
    private String enemyChosenMoveKey;

    private boolean resolveOnce;
    private boolean playerWin;

    public CombatSystem(Player p, AbstractEnemy e, SpriteBatch sb){
        this.player = p;
        this.enemy = e;
        enemy.setCombatSystem(this);
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

        resolveOnce = false;
        playerWin = true;
    }
    /**
     * Changes the boolean when it is the players turn.
     */
    public boolean getPlayerTurn(){
        return (playerTurn && (tick == 0));
    }

    public void render(){
        animationHandler.render(playerWin);
    }

    /**
     * This updates the battle.
     * The battle relies on a Rock, Paper, Scissors system. The player may end up not getting hit or they may never hit the enemy.
     * There are 3 different move types: attack, defend, sneak
     * And this is how the rock, paper, scissors is represented (--> means strong against)
     *      Attack --> Sneak --> Defend --> Attack
     */
    public void update() {
        if (!(finished)){
            if(playerControl && !(animationHandler.isInAnimation())){
                if(playerChosenMove != null && !(playerChosenMoveKey.equals(""))){
                    playerControl = false;
                }
            } else if(!animationHandler.isInAnimation()){
                enemy.battleMove(enemyMoveList);
            }
            if(playerChosenMove != null && enemyChosenMove != null){
                BattleOutcome outcome = resolveCombat();
                if (outcome == BattleOutcome.Win && !resolveOnce){
                    playerWin = true;
                    animationHandler.setupPlayerAnimation(playerChosenMoveKey);
                    resolveOnce = true;
                } else if (outcome == BattleOutcome.Lose && !resolveOnce){
                    playerWin = false;
                    animationHandler.setupEnemyAnimation(enemyChosenMoveKey);
                    resolveOnce = true;
                } else if (outcome == BattleOutcome.Draw && !resolveOnce){
                    player.combatUpdate();
                    enemy.combatUpdate();
                    resetInput(2);
                }

                if(animationHandler.isAnimationEnd()){
                    switch(outcome){
                        case Win:
                            applyMove(playerMoveList,playerChosenMoveKey);
                            break;
                        case Lose:
                            applyMove(enemyMoveList,enemyChosenMoveKey);
                            break;
                    }
                    player.combatUpdate();
                    enemy.combatUpdate();
                    resetInput(2);
                }
            }
            if ((player.getHealth() <= 0) || (enemy.getHealth() <= 0)) {
                finished = true;
                if(finished){
                    //Dialogue d = new Dialogue("Enemy");
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

    /**
     * This method sets up a string array of options for the player during combat
     *
     * @return String[] options
     */
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

        return options;
    }

    public Object[] getMoveSelectionStackKeySet(){
         return moveSelectionStack.peek().keySet().toArray();
    }

    /**
     * This translates the input from player and enemy and updates the combat system
     *
     * @param input
     * @param inputByPlayer
     */
    public void doInput(Inputs input, boolean inputByPlayer){
        if((inputByPlayer && playerControl) || !(inputByPlayer && playerControl)) {
            switch (input) {
                case Q:
                    if (getOptions()[0] != "" && !(moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[0]) instanceof MoveList.Move)) {
                        moveSelectionStack.push((HashMap) moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[0]));
                        resetInput(0);
                    } else if (moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[0]) instanceof MoveList.Move) {
                        assignMove(getOptions()[0], (MoveList.Move) moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[0]), inputByPlayer);
                    }
                    break;
                case W:
                    if (getOptions()[1] != "" && !(moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[1]) instanceof MoveList.Move)) {
                        moveSelectionStack.push((HashMap) moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[1]));
                        resetInput(0);
                    } else if (moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[1]) instanceof MoveList.Move) {
                        assignMove(getOptions()[1], (MoveList.Move) moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[1]), inputByPlayer);
                    }
                    break;
                case E:
                    if (getOptions()[2] != "" && !(moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[2]) instanceof MoveList.Move)) {
                        moveSelectionStack.push((HashMap) moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[2]));
                        resetInput(0);
                    } else if (moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[2]) instanceof MoveList.Move) {
                        assignMove(getOptions()[2], (MoveList.Move) moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[2]), inputByPlayer);
                    }
                    break;
                case R:
                    if (getOptions()[3] != "" && !(moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[3]) instanceof MoveList.Move)) {
                        moveSelectionStack.push((HashMap) moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[3]));
                        resetInput(0);
                    } else if (moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[3]) instanceof MoveList.Move) {
                        assignMove(getOptions()[3], (MoveList.Move) moveSelectionStack.peek().get(getMoveSelectionStackKeySet()[3]), inputByPlayer);
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
        }
    }

    /**
     * This method assigns a MoveList.Move to a variable (either to a player or an enemy)
     *
     * @param moveKey (Name of the move)
     * @param move (MoveList.Move)
     * @param inputByPlayer (Is the input made by the player or not)
     */
    public void assignMove(String moveKey, MoveList.Move move, boolean inputByPlayer){
        if((inputByPlayer && playerControl) || !(inputByPlayer && playerControl)) {
            if (playerControl && inputByPlayer) {
                playerChosenMoveKey = moveKey;
                playerChosenMove = move;
            } else if (!(inputByPlayer)) {
                enemyChosenMoveKey = moveKey;
                enemyChosenMove = move;
            }
            resetInput(1);
        }
    }

    /**
     * evaluates the rock, paper, scissors part of the combat.
     * Returns BattleOutcome.Win if the player's move type beats the enemy's move type etc.
     *
     * @return BattleOutcome (Win, Draw, Lose)
     */
    private BattleOutcome resolveCombat(){
        if(playerChosenMove.getType() == getStrongOrWeakType(enemyChosenMove, true)) return BattleOutcome.Win;
        if(playerChosenMove.getType() == getStrongOrWeakType(enemyChosenMove, false)) return BattleOutcome.Lose;
        return BattleOutcome.Draw;
    }

    private MoveList.MoveType getStrongOrWeakType(MoveList.Move move, boolean weakAgainst){
        MoveList.MoveType type = null;
        switch(move.getType()){
            case attack:
                type = weakAgainst ? MoveList.MoveType.defend : MoveList.MoveType.sneak;
                break;
            case defend:
                type = weakAgainst ? MoveList.MoveType.sneak : MoveList.MoveType.attack;
                break;
            case sneak:
                type = weakAgainst ? MoveList.MoveType.attack : MoveList.MoveType.defend;
                break;
        }
        return type;
    }

    private void resetInput(int resetLevel){
        moveSelector = 0;
        if(resetLevel >= 1){ //resets options
            while(moveSelectionStack.size() > 1){
                moveSelectionStack.pop();
            }
            if(resetLevel >= 2){ //resets the combat system
                playerChosenMoveKey = "";
                playerChosenMove = null;
                playerControl = true;
                enemyChosenMoveKey = "";
                enemyChosenMove = null;
                resolveOnce = false;
            }
        }
    }

    //Implementing a 'move list'
    public void applyMove(MoveList userMoveList, String move){
        userMoveList.use(move);
    }


}
