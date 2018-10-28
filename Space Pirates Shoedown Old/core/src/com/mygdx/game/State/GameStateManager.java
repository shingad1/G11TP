package com.mygdx.game.State;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;

    public GameStateManager() {
        states = new Stack<State>();
    }

        public void push (State state) {
            states.push(state);
        }

        public void pop() {
            states.pop();
        }

        public void set(State state) { //pops and then immediatly pushes a state into stack
             states.pop();
             states.push(state);
        }

        public void update(float dt) {
            states.peek().update(dt);
        }

        public void render(SpriteBatch sb) { //sb container and renders everything to screen
            states.peek().render(sb);
        }
    }



