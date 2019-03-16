package com.sps.game.Controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sps.game.Animation.enemyAnimation;
import com.sps.game.Animation.playerAnimation;
import com.sps.game.Sprites.AbstractEnemy;
import com.sps.game.Sprites.Location;
import com.sps.game.Sprites.Player;

import java.util.HashMap;
import java.util.Stack;

public class BattleAnimationHandler {
    private Player player;
    private AbstractEnemy enemy;
    private Stack<String> enemyAnimationStack;
    private Stack<String> playerAnimationStack;
    private SpriteBatch sb;
    private MoveList moveList;
    private Location oldLocation;
    private float frame;
    private boolean inAnimation;
    private boolean animationEnd;
    private Location playerLocation;
    private Location enemyLocation;

    public BattleAnimationHandler(Player player,
                                  AbstractEnemy enemy,
                                  SpriteBatch sb,
                                  Location playerLocation,
                                  Location enemyLocation){
        this.player = player;
        this.enemy = enemy;
        enemyAnimationStack = new Stack<String>();
        playerAnimationStack = new Stack<String>();
        enemyAnimationStack.push("Idle");
        playerAnimationStack.push("Idle");
        this.sb = sb;
        this.moveList = new MoveList(player,enemy);
        inAnimation = false;
        frame = 0;
        this.playerLocation = playerLocation;
        this.enemyLocation = enemyLocation;
    }

    public void render(boolean playerTurn){
        String playerAction = playerAnimationStack.peek();
        String enemyAction = enemyAnimationStack.peek();
        controlAnimation(playerTurn);
        if(playerAction.equals("Right") || playerAction.equals("Left") || playerAction.equals("Idle")) {
            player.getFightAnimation().get(playerAnimationStack.peek()).render(playerLocation);
        } else {
            player.getFightAnimation().get(playerAnimationStack.peek()).render(playerLocation,frame);
        }
        if(enemyAction.equals("Right") || enemyAction.equals("Left") || enemyAction.equals("Idle")) {
            enemy.getFightAnimation().get(enemyAnimationStack.peek()).render(enemyLocation);
        } else {
            enemy.getFightAnimation().get(enemyAnimationStack.peek()).render(enemyLocation,frame);
        }
    }

    private void controlAnimation(boolean playerTurn){
        if(playerTurn){
            if(playerAnimationStack.peek().equals("Right")){
                if(playerLocation.equals(new Location(Math.round(enemyLocation.getX() - 32),Math.round(enemyLocation.getY())))){
                    playerAnimationStack.pop();
                } else {
                    playerLocation.setX(Math.round(playerLocation.getX() + 4));
                }
            } else if(playerAnimationStack.peek().equals("Left")){
                if(playerLocation.equals(oldLocation)){
                    playerAnimationStack.pop();
                } else {
                    playerLocation.setX(Math.round(playerLocation.getX() - 4));
                }
            } else if(playerAnimationStack.peek().equals("Idle")){
                //do nothing
            } else {
                if (frame >= 1.0f){
                    frame = 0.0f;
                    playerAnimationStack.pop();
                } else {
                    frame += 0.3f;
                }
            }
        } else {
            if(enemyAnimationStack.peek().equals("Left")){
                if(enemyLocation.equals(new Location(Math.round(playerLocation.getX() + 32),Math.round(playerLocation.getY())))){
                    enemyAnimationStack.pop();
                } else {
                    enemyLocation.setX(Math.round(enemyLocation.getX() - 4));
                }
            } else if(enemyAnimationStack.peek().equals("Right")){
                if(enemyLocation.equals(oldLocation)){
                    enemyAnimationStack.pop();
                } else {
                    enemyLocation.setX(Math.round(enemyLocation.getX() + 4));
                }
            } else if(enemyAnimationStack.peek().equals("Idle")){
                //do nothing
            } else {
                if (frame >= 1.0f){
                    frame = 0.0f;
                    enemyAnimationStack.pop();
                } else {
                    frame += 0.3f;
                }
            }
        }
        if(animationEnd){
            animationEnd = false;
        }
        if(isInAnimation() && playerAnimationStack.peek().equals("Idle") && enemyAnimationStack.peek().equals("Idle")){
            inAnimation = false;
            animationEnd = true;
        }
    }

    public void setupPlayerAnimation(String chosenMove){
        oldLocation = new Location(Math.round(playerLocation.getX()),Math.round(playerLocation.getY()));
        if(moveList.getMoveRange(chosenMove) == MoveList.MoveRange.near){
            playerAnimationStack.push("Left");
            playerAnimationStack.push("basicAttack");
            playerAnimationStack.push("Right");
        } else {
            playerAnimationStack.push("block");
        }

        inAnimation = true;
    }
    public void setupEnemyAnimation(String chosenMove){
        oldLocation = new Location(Math.round(enemyLocation.getX()),Math.round(enemyLocation.getY()));
        if(moveList.getMoveRange(chosenMove) == MoveList.MoveRange.near){
            enemyAnimationStack.push("Right");
            enemyAnimationStack.push("basicAttack");
            enemyAnimationStack.push("Left");
        } else {
            enemyAnimationStack.push("block");
        }

        inAnimation = true;
    }

    public String getAnimationStage(boolean playerTurn){
        if(playerTurn){
            return playerAnimationStack.peek();
        } else {
            return enemyAnimationStack.peek();
        }
    }

    public boolean isInAnimation(){return inAnimation;}

    public boolean isAnimationEnd(){return animationEnd;}

}
