package com.sps.game.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.animation.NpcAnimation;
import com.sps.game.maps.MapFactory;

import java.util.HashMap;
import java.util.Random;

/**
 * @author Devin Shingadia
 */
public class MerchantNPC extends AbstractNPC {
    private int x; //Keep record of the Merchants X coordiante
    private int y; //Keep record of the Merchants Y coordinate
    private int tick = 0; //May not need
    private Random random; //May not need;
    private MapFactory.MapType world;
    private Vector2 velocity;
    private HashMap<String, NpcAnimation> animation;
    private String state;
    private String name;

    public MerchantNPC(int x, int y, MapFactory.MapType world, SpriteBatch sb, String role) {
        this.x = x;
        this.y = y;
        this.world = world;
        velocity = new Vector2();
        velocity.x = 0;
        velocity.y = 0;
        animation = new HashMap<String, NpcAnimation>();

        //Placeholder for the merchant
        animation.put("idle",new NpcAnimation(sb,this, "npc"+ role +"Idle.atlas",1/15f));

        location = new Location(x, y);
    }

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
        return velocity;
    }

    @Override
    public void setY(float newY) {
        y += newY;
    }

    @Override
    public void setX(float newX) {
        x += newX;
    }

    @Override
    public MapFactory.MapType getWorld() {
        return world;
    }

    @Override
    public NpcAnimation getAnimation() {
        return animation.get(state);
    }

    @Override
    public void changeState(String newState) {
        state = newState;
    }

    @Override
    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }
}
