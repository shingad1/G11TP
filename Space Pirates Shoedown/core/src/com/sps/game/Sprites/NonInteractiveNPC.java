package com.sps.game.Sprites;

import java.util.HashMap;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.sps.game.Animation.npcAnimation;
import com.sps.game.Animation.playerAnimation;

public class NonInteractiveNPC extends AbstractNPC {

    private int x; //keep record of the NPC's x co-ordinate
        /**
         * Stores the NPC character's x co-ordinate
         *
         * @see #update()
         */
        //private int x;
        /**
         * Stores the NPC character's y co-ordinate
         *
         * @see #update()
         */
        private int y;
        /**
         * Uses Tick to break down movement into a number of iterations so that it doesnt move too fast
         *
         * @see #update()
         */
        private int tick = 0;
        /**
         * Stores the random variable so it can be used for the movement
         *
         * @see #update()
         */
        private Random random;
        /**
         * Stores the world as a string so we can use it for collisions detection
         *
         * @see #getWorld()
         */
        private String world;
        /**
         * changes the speed of the NPC
         *
         * @see #update() #reset
         */
        private Vector2 velocity;

        /**
         * Holds the animated textures of the NPC.
         * Key = A string which specifies the movement
         * Value = The textureAtlas to display.
         */
        private HashMap<String, npcAnimation> animation;

        private String state;

        public NonInteractiveNPC(int x, int y, String world, SpriteBatch sb) {
            this.x = x;
            this.y = y;
            this.world = world;
            velocity = new Vector2();
            velocity.x = 0;
            velocity.y = 0;

            animation = new HashMap<String, npcAnimation>();

            animation.put("down",new npcAnimation(sb,this, "npcDown.atlas"));
            animation.put("up",new npcAnimation(sb,this, "npcUp.atlas"));
            animation.put("left",new npcAnimation(sb,this, "npcLeft.atlas"));
            animation.put("right",new npcAnimation(sb,this, "npcRight.atlas"));
            animation.put("idle",new npcAnimation(sb,this, "npcIdle.atlas"));


        }

        /**
         * Returns the NPc x Axis
         */
        public int getX() {
            return x;
        }

        /**
         * Returns the y Axis
         */
        public int getY() {
            return y;
        }

        /**
         * Returns the world
         */
        public String getWorld() {
            return world;
        }


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

        public npcAnimation getAnimation() { return animation.get(state);}

        public void changeState(String newState) { state = newState; }
    }
