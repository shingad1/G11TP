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
     * Holds the texture showing the player.
     */
    private HashMap<String, PlayerAnimation> animation;

    private HashMap<String, PlayerAnimation> fightAnimation;

    private String state;

    private Location location;

    private int attack;

    private int defence;

    private static Player player = new Player();

    private Player(){ //int x, int y, SpriteBatch sb
        //this.x = x;
        //this.y = y;
        velocity = new Vector2();
        velocity.x = 0; velocity.y = 0;
        gold = 50;
        HP = 100;
        location = new Location(0, 0);
        attack = 20;
        defence = 10;

        //setAnimations(sb);
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

    public HashMap<String, PlayerAnimation> getFightAnimation(){return fightAnimation;}

    public void setX(int x){
        this.x = x;
        location.setX(x);
    }

    public void setY(int y){
        this.y = y;
        location.setY(y);
    }

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

    @Override
    public void changeHP(int diff) {
        increaseHealth(diff); //if diff is negative, implicitly takes away health points
    }

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

    @Override
    public int getDefence() {
        return defence;
    }

    @Override
    public void changeDefence(int diff){defence += diff;}

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

    public PlayerAnimation getAnimation(){
        return animation.get(state);
    }

    public void changeState(String newState){state = newState;}

    public Location getLocation(){return location;}

    public void changeSpriteBatch(SpriteBatch sb){
        setAnimations(sb);
    }

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

    public static Player getPlayer(){
        return player;
    }
}
