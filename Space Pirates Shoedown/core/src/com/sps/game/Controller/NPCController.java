package com.sps.game.Controller;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.sps.game.Sprites.AbstractNPC;
import java.util.Random;

public class NPCController {

    /**
     * Holds which layer of the TiledMap contains objects that the user can not pass through.
     * @see #collisionDetection
     */
    private TiledMapTileLayer collisionLayer;

    private AbstractNPC npc;

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

    public NPCController(AbstractNPC npc, TiledMapTileLayer collisionLayer){
        this.collisionLayer = collisionLayer;
        this.npc = npc;
        random = new Random();
    }

    public boolean collisionDetection(){
        boolean collisionX;
        boolean collisionY;

        float tiledWidth = 32;
        float tiledHeight = 32;

        if(npc.getVelocity().y > 0){
            collisionY = collisionLayer.getCell((int) (npc.NPCGetX() / tiledWidth), (int) ((npc.NPCGetY() + 32)/tiledHeight)).getTile().getProperties().containsKey("blocked");
            System.out.println("Up");
            return collisionY;
        }
        if(npc.getVelocity().y < 0){
            collisionY = collisionLayer.getCell((int) (npc.NPCGetX() / tiledWidth), (int) ((npc.NPCGetY() - 32)/tiledHeight)).getTile().getProperties().containsKey("blocked");
            System.out.println("down");
            return collisionY;
        }
        if(npc.getVelocity().x > 0){
            collisionX = collisionLayer.getCell((int) ((npc.NPCGetX() + 32) / tiledWidth), (int) (npc.NPCGetY()/tiledHeight)).getTile().getProperties().containsKey("blocked");
            System.out.println("right");
            return collisionX;
        }
        if(npc.getVelocity().x < 0){
            collisionX = collisionLayer.getCell((int) ((npc.NPCGetX() - 32) / tiledWidth), (int) (npc.NPCGetY()/tiledHeight)).getTile().getProperties().containsKey("blocked");
            System.out.println("left");
            return collisionX;
        }

        return false;
    }

    /**
     * This method updates the movement for the NPc
     */
    public void move() {
        if (tick == 0){
            switch (random.nextInt(6) + 1){
                case 2:
                    npc.getVelocity().y = 2;
                    if(collisionDetection()) {
                        npc.getVelocity().y = 0;
                    }
                    break;
                case 3:
                    npc.getVelocity().y = -2;
                    if(collisionDetection()) {
                        npc.getVelocity().y = 0;
                    }
                    break;
                case 4:
                    npc.getVelocity().x = 2;
                    if(collisionDetection()) {
                        npc.getVelocity().x = 0;
                    }
                    break;
                case 5:
                    npc.getVelocity().x = -2;
                   if(collisionDetection()) {
                       npc.getVelocity().x = 0;
                   }
                    break;
            }
            tick = 1;
        } else {
            npc.setY(npc.getVelocity().y);
            npc.setX(npc.getVelocity().x);
            tick++;
            if(tick == 17){
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
    }
}
