package com.sps.game.Sprites;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.Animation.npcAnimation;
import com.sps.game.maps.MapFactory;

import java.util.HashMap;
import java.util.Random;

public class InteractiveNPC extends AbstractNPC{
    /**
     * Stores the NPC character's x co-ordinate
     * @see
     */
    private int x;
    /**
     * Stores the NPC character's y co-ordinate
     * @see
     */
    private int y;
    /**
     * Uses Tick to break down movement into a number of iterations so that it doesnt move too fast
     * @see
     */
    private int tick = 0;
    /**
     * Stores the random variable so it can be used for the movement
     * @see
     */
    private Random random;
    /**
     *changes the speed of the NPC
     * @see
     */
    private Vector2 velocity;

    private npcAnimation animation, animation3;

    private MapFactory.MapType world;
    /**
     * Holds the name of the InteractiveNPC
     */
    private String name;

    public InteractiveNPC(int x, int y,MapFactory.MapType world, SpriteBatch sb, String name){
        this.x = x;
        this.y = y;
        location = new Location(x,y);
        animation = new npcAnimation(sb,this,"cryingNPC.atlas",1/2f);
        animation3 = new npcAnimation(sb, this, "npcIdle.atlas", 1/2f);

        this.world = world;
        setName(name);
    }

    public npcAnimation getAnimation(){
        if (this.name.equals("Linda")){
            return animation;
        }
        else
        {
            return animation3;
        }
    }

    public MapFactory.MapType getWorld(){return world;}

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Vector2 getVelocity() {
        return null;
    }

    @Override
    public void setY(float newY) {

    }

    @Override
    public void setX(float newX) {

    }

    public Location getLocation(){return location;}

    @Override
    public void changeState(String newState) {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getType() {return "InteractiveNPC";}
}
