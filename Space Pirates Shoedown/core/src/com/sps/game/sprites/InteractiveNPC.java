package com.sps.game.sprites;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.animation.NpcAnimation;
import com.sps.game.maps.MapFactory;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class creates the Interactive NPC
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia and Mahamuda Akhter
 * @version 1.0
 */
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
     * Changes the speed of the NPC.
     * @see
     */
    private Vector2 velocity;
    /**
     * Holds the different animations of the different types of NPCs.
     */
    private NpcAnimation lindaAnimation, muffinAnimation, tropicalAnimation, enemyAnimation, otherAnimation;
    /**
     * Holds the type of the map.
     */
    private MapFactory.MapType world;
    /**
     * Holds the name of the InteractiveNPC.
     */
    private String name;
    /**
     * Creates and holds all the locations of all the interactive npc.
     */
    public static ArrayList<Location> allInteractiveNPCLocations = new ArrayList<Location>();

    public InteractiveNPC(int x, int y,MapFactory.MapType world, SpriteBatch sb, String name){
        this.x = x;
        this.y = y;
        location = new Location(x,y);
        allInteractiveNPCLocations.add(location);

        lindaAnimation = new NpcAnimation(sb,this,"cryingNPC.atlas",1/2f);
        muffinAnimation = new NpcAnimation(sb,this,"interactiveCandy.atlas",1/2f);
        tropicalAnimation = new NpcAnimation(sb, this, "interactiveTropical.atlas", 1/2f);
        enemyAnimation = new NpcAnimation(sb, this, "interactiveEnemy.atlas", 1/2f);
        otherAnimation = new NpcAnimation(sb, this, "interactiveHome.atlas", 1/2f);
        this.world = world;
        this.name = name;
    }
    /**
     * Gets the current animation of the NPC.
     * @return NpcAnimation
     */
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

    /**
     * Gets the world the NPC is in
     * @return MapFactory.MapType world
     */
    public MapFactory.MapType getWorld () {
        return world;
    }
    /**
     * Get the name of the NPC.
     * @return String name
     */
    public String getName () {
        return name;
    }

    /**
     * Gets the type of the NPC.
     * @return String
     */
    public String getType () {
        return "InteractiveNPC";
    }
    /**
     * Returns the NPC x Axis
     */
    @Override
    public int getX() {
        return x;
    }
    /**
     * Returns the NPC Y Axis
     */
    @Override
    public int getY() {
        return y;
    }
    /**
     * Returns the velocity of the NPC.
     */
    @Override
    public Vector2 getVelocity() {
        return velocity;
    }
    /**
     * Sets the value of the Y coordinate.
     * @param float newY
     */
    @Override
    public void setY(float newY) {

    }
    /**
     * Sets the value of the X coordinate.
     * @param float newX
     */
    @Override
    public void setX(float newX) {

    }

    /**
     * Gets the current Location of the NPC
     * @return
     */
    public Location getLocation() {
        return location;
    }
    /**
     * Changes the state of the NPC.
     * @param String newState
     */
    @Override
    public void changeState(String newState) {

    }
}
