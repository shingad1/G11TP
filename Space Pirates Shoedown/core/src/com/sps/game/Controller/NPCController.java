package com.sps.game.Controller;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.sps.game.Sprites.AbstractNPC;
import com.sps.game.Sprites.Location;
import com.sps.game.Sprites.NonInteractiveNPC;
import com.sps.game.Sprites.Player;

import javax.swing.text.Position;
import java.util.Random;

public class NPCController
{

    /**
     * Holds which layer of the TiledMap contains objects that the user can not pass through.
     * @see #collisionDetection
     */
    private TiledMapTileLayer collisionLayer;

    private NonInteractiveNPC npc;

    private FixtureDef npcBody;

    /**
     * Uses Tick to break down movement into a number of iterations so that it doesnt move too fast
     * @see #move
     */
    private int tick = 0;
    /**
     * Stores the random variable so it can be used for the movement
     * @see #move
     */
    private Random random;

    public NPCController(NonInteractiveNPC npc, TiledMapTileLayer collisionLayer){
        this.collisionLayer = collisionLayer;
        this.npc = npc;
        random = new Random();
        npcBody = new FixtureDef();

    }

    public boolean collisionDetection(Player player){
        boolean collisionX;
        boolean collisionY;

        float tiledWidth = 32;
        float tiledHeight = 32;

        if(npc.getVelocity().y > 0){
            collisionY = collisionLayer.getCell((int) (npc.getX() / tiledWidth), (int) ((npc.getY() + 32)/tiledHeight)).getTile().getProperties().containsKey("blocked");
            //npcBody.getPosition.y = new Position;
            return (collisionY || playerInLocation(player,new Location(Math.round(npc.getLocation().getX()),Math.round(npc.getLocation().getY() + 32))));
        }
        if(npc.getVelocity().y < 0){
            collisionY = collisionLayer.getCell((int) (npc.getX() / tiledWidth), (int) ((npc.getY() - 32)/tiledHeight)).getTile().getProperties().containsKey("blocked");
            return (collisionY || playerInLocation(player,new Location(Math.round(npc.getLocation().getX()),Math.round(npc.getLocation().getY() - 32))));
        }
        if(npc.getVelocity().x > 0){
            collisionX = collisionLayer.getCell((int) ((npc.getX() + 32) / tiledWidth), (int) (npc.getY()/tiledHeight)).getTile().getProperties().containsKey("blocked");
            return (collisionX || playerInLocation(player,new Location(Math.round(npc.getLocation().getX() + 32),Math.round(npc.getLocation().getY()))));
        }
        if(npc.getVelocity().x < 0){
            collisionX = collisionLayer.getCell((int) ((npc.getX() - 32) / tiledWidth), (int) (npc.getY()/tiledHeight)).getTile().getProperties().containsKey("blocked");
            return (collisionX || playerInLocation(player,new Location(Math.round(npc.getLocation().getX() - 32),Math.round(npc.getLocation().getY()))));
        }

        return false;
    }

    /**
     * This method updates the movement for the NPc
     */
    public void move(Player player) {
        float oldX = npc.getX(), oldY = npc.getY();
        if (tick == 0){
            switch (random.nextInt(6) + 1){
                case 2:
                    npc.getVelocity().y = 1;
                    if(collisionDetection(player)) {
                        npc.getVelocity().y = 0;
                    } else {
                        npc.changeState("up");
                        npc.getLocation().setY(npc.getY() + 32);
                    }
                    break;
                case 3:
                    npc.getVelocity().y = -1;
                    if(collisionDetection(player)) {
                        npc.getVelocity().y = 0;
                    } else {
                        npc.changeState("down");
                        npc.getLocation().setY(npc.getY() - 32);
                    }
                    break;
                case 4:
                    npc.getVelocity().x = 1;
                    if(collisionDetection(player)) {
                        npc.getVelocity().x = 0;
                    } else {
                        npc.changeState("right");
                        npc.getLocation().setX(npc.getX() + 32);
                    }
                    break;
                case 5:
                    npc.getVelocity().x = -1;
                   if(collisionDetection(player)) {
                       npc.getVelocity().x = 0;
                   } else {
                       npc.changeState("left");
                       npc.getLocation().setX(npc.getX() - 32);
                   }
                    break;
            }
            tick = 1;
        } else {
            npc.setY(npc.getVelocity().y);
            npc.setX(npc.getVelocity().x);
            tick++;
            if(tick == 33){
                reset();
            }
        }
    }

    /**
     * resets the velocity and the time
     */
    public void reset(){
        npc.getVelocity().x = 0;
        npc.getVelocity().y = 0;
        tick = 0;
        npc.changeState("idle");
    }

    public boolean playerInLocation(Player player, Location location){
        return(location.equals(player.getLocation()));
    }

}
