package com.sps.game.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.animation.PlayerAnimation;
import com.sps.game.screens.Fighter;

import java.util.HashMap;

/**
 * This class creates the player and sets the starting x and y coordinates.
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia
 * @version 1.0
 */
public class Player implements Fighter {
    /**
     * Keeps track of the player's gold
     */
    private int gold;
    /**
     * Keeps track of the player's HP
     */
    private int HP;
    /**
     * Holds the players X coordinates.
     * @see #move #getX
     */
    private int x;
    /**
     * Holds the players Y coordinates.
     * @see #move #getY
     */
    private int y;
    /**
     * Holds the players velocity.
     * #getVelocity
     */
    private Vector2 velocity;
    /**
     * Holds the animation showing the player.
     */
    private HashMap<String, PlayerAnimation> animation;
    /**
     * Holds all the animations for the player during the combat
     */
    private HashMap<String, PlayerAnimation> fightAnimation;
    /**
     * Holds the current state of the Player.
     */
    private String state;
    /**
     * Holds the current location of the player.
     */
    private Location location;
    /**
     * Holds the attack damage of the player.
     */
    private int attack;
    /**
     * Holds the defence of the player.
     */
    private int defence;
    /**
     * Holds the instance of the player.
     */
    private static Player player = new Player();

    private Player(){
        velocity = new Vector2();
        velocity.x = 0; velocity.y = 0;
        gold = 100;
        HP = 100;
        location = new Location(0, 0);
        attack = 20;
        defence = 10;
    }

    /**
     * Updates the players x and y coordinates according to the players movement.
     * @param <code>int</code>dx. Holds the change in the x axis.
     * @param <code>int</code>dy. Holds the change in the y axis
     */
    public void move(int dx, int dy){
        x += dx;
        y += dy;
        if(Math.round(x) % 32 == 0 && Math.round(y) % 32 == 0){
            location = new Location(x,y);
        }
    }

    /**
     * Gets the HashMap containing the fight animations of the Player
     * @return HashMap<String,PlayerAnimation> fightAnimation
     */
    public HashMap<String, PlayerAnimation> getFightAnimation(){return fightAnimation;}

    /**
     * Sets the X value of the player.
     * @param int x
     */
    public void setX(int x){
        this.x = x;
        location.setX(x);
    }

    /**
     * Sets the Y value of the player.
     * @param int y
     */
    public void setY(int y){
        this.y = y;
        location.setY(y);
    }

    /**
     * Sets the sprite batch of the player
     * @param SpriteBatch sb
     */
    public void setBatch(SpriteBatch sb){
        setAnimations(sb);
    }

    /**
     * Gets the players X coordinate.
     * @return Returns a <code>int</code> X coordinate value.
     */
    public int getX(){
        return x;
    }

    /**
     * Gets the players Y coordinate.
     * @return Returns a <code>int</code> Y coordinate value.
     */
    public int getY(){
        return y;
    }

    /**
     * Gets the players velocity.
     * @return Returns a <code>Vector2</code> Velocity value.
     */
    public Vector2 getVelocity(){
        return velocity;
    }

    /**
     * Gets the amount of gold the player has
     * @return Returns a <code>int</code> gold value.
     */
    public int getGold(){
        return gold;
    }

    /**
     * Increases the value of the players gold
     * @param int goldValue
     */
    public void increaseGold(int goldValue) { gold += goldValue; }
    /**
     * Decreases the value of the players gold
     * @param int goldValue
     */
    public void decreaseGold(int goldValue) { gold -= goldValue; }

    /**
     * Changes the value of the players health
     * @param int diff
     */
    @Override
    public void changeHP(int diff) {
        increaseHealth(diff); //if diff is negative, implicitly takes away health points
    }

    /**
     * Gets the attack value of the player.
     * @return
     */
    @Override
    public int getAttack() {
        return attack;
    }

    /**
     * Gets the health level of the player.
     * @return Returns a <code>int</code> health points value.
     */
    public int getHP(){
        return HP;
    }

    /**
     * Gets the defence value of the player.
     * @return
     */
    @Override
    public int getDefence() {
        return defence;
    }

    /**
     * Changes the value of the defence.
     * @param int diff
     */
    @Override
    public void changeDefence(int diff){defence += diff;}

    /**
     * Changes the value of the attack
     * @param int diff
     */
    @Override
    public void changeAttack(int diff){attack += diff;}

    /**
     * Sets the position of the player on the screen
     * @param <code>int<code> dx, change in the x axis.
     * @param <code>int</code> dy, change in the y axis.
     */
    public void setPosition(int dx, int dy){
        x = dx;
        y = dy;
        location = new Location(dx,dy);
    }

    /**
     * Decreases the players health by an inputted amount.
     * @param <code>int</code> decrease, the amount to decrease the health level by.
     */
    public void decreaseHealth(int decrease){
        HP-=decrease;
    }

    /**
     * Increases the players health by an inputted amount.
     * @param <code>int</code> increase, the amount to increase the health by.
     */
    public void increaseHealth(int increase){
       /* if(getHP() == 100){
            HP = 100;
        }
        else {*/
            HP += increase;
        //}
    }

    /**
     * Gets the animation of the player according to its state.
     * @return PlayerAnimation
     */
    public PlayerAnimation getAnimation(){
        return animation.get(state);
    }

    /**
     * Changes the state of the player.
     * @param String newState
     */
    public void changeState(String newState){state = newState;}

    /**
     * Gets the current location of the player.
     * @return Location location
     */
    public Location getLocation(){return location;}

    /**
     * Changes the sprite batch of the player
     * @param SpriteBatch sb
     */
    public void changeSpriteBatch(SpriteBatch sb){
        setAnimations(sb);
    }

    /**
     * Sers the animations of the player.
     * @param SpriteBatch sb
     */
    private void setAnimations(SpriteBatch sb){
        animation = new HashMap<String, PlayerAnimation>();
        animation.put("down",new PlayerAnimation(sb,this, "playerDown.atlas",1/15f));
        animation.put("up",new PlayerAnimation(sb,this, "playerUp.atlas",1/15f));
        animation.put("left",new PlayerAnimation(sb,this, "playerLeft.atlas",1/15f));
        animation.put("right",new PlayerAnimation(sb,this, "playerRight.atlas",1/15f));
        animation.put("idle",new PlayerAnimation(sb,this, "playerIdle.pack",1/15f));
        fightAnimation = new HashMap<String, PlayerAnimation>();
        fightAnimation.put("Idle",new PlayerAnimation(sb, this, "combatPlayerIdle.atlas",1/15f));
        fightAnimation.put("Right",new PlayerAnimation(sb, this, "playerRight.atlas",1/15f));
        fightAnimation.put("Left",new PlayerAnimation(sb, this, "playerLeft.atlas",1/15f));
        fightAnimation.put("basicAttack",new PlayerAnimation(sb, this, "playerBasicAttack.atlas",1/3f));
        fightAnimation.put("block",new PlayerAnimation(sb, this, "playerBasicBlock.atlas",1/3f));

    }

    /**
     * Gets the instance of the player.
     * @return
     */
    public static Player getPlayer(){
        return player;
    }
}
