package com.sps.game.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.animation.NpcAnimation;
import com.sps.game.maps.MapFactory;

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

    private NpcAnimation lindaAnimation, muffinAnimation, tropicalAnimation, enemyAnimation, otherAnimation;

    private MapFactory.MapType world;
    /**
     * Holds the name of the InteractiveNPC
     */
    private String name;

    public InteractiveNPC(int x, int y,MapFactory.MapType world, SpriteBatch sb, String name){
        this.x = x;
        this.y = y;
        location = new Location(x,y);
        lindaAnimation = new NpcAnimation(sb,this,"cryingNPC.atlas",1/2f);
        muffinAnimation = new NpcAnimation(sb,this,"interactiveCandy.atlas",1/2f);
        tropicalAnimation = new NpcAnimation(sb, this, "interactiveTropical.atlas", 1/2f);
        enemyAnimation = new NpcAnimation(sb, this, "interactiveEnemy.atlas", 1/2f);
        otherAnimation = new NpcAnimation(sb, this, "interactiveHome.atlas", 1/2f);
        this.world = world;
        this.name = name;
    }

    public NpcAnimation getAnimation() {
        if (getWorld().equals(MapFactory.MapType.HomeWorldMap1) || getWorld().equals(MapFactory.MapType.HomeWorldMap2)) {
            if (name.equals("Linda")) {
                otherAnimation = lindaAnimation;
            }
        }
        else if(getWorld().equals(MapFactory.MapType.CandyWorld1) || getWorld().equals(MapFactory.MapType.CandyWorld2)){
            if(name.contains("Muffin")) {
                otherAnimation = muffinAnimation;
            }
        }
        else if (getWorld().equals(MapFactory.MapType.TropicalWorld1) || getWorld().equals(MapFactory.MapType.TropicalWorld2)){
            if(name.contains("Tropical")){
                otherAnimation = tropicalAnimation;
            }
        }
        else if (getWorld().equals(MapFactory.MapType.GraveyardWorld1) || getWorld().equals(MapFactory.MapType.GraveyardWest)){
            if(name.contains("Grave")){
                otherAnimation = enemyAnimation;
            }
        }

        return otherAnimation;
    }

        public MapFactory.MapType getWorld () {
            return world;
        }

        @Override
        public int getX () {
            return x;
        }

        @Override
        public int getY () {
            return y;
        }

        @Override
        public Vector2 getVelocity () {
            return null;
        }

        @Override
        public void setY ( float newY){

        }

        @Override
        public void setX ( float newX){

        }

        public Location getLocation () {
            return location;
        }

        @Override
        public void changeState (String newState){

        }

        public String getName () {
            return name;
        }

        public String getType () {
            return "InteractiveNPC";
        }
}
