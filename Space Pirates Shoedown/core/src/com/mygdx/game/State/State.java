package com.mygdx.game.State;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public abstract class State {

    protected OrthographicCamera cam;
    protected Vector2 mouse;
    protected GameStateManager gsm; //sets the state of the game.

    protected State(GameStateManager gsm) {
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector2();
    }

    protected abstract void handleInput(); //Handles the user input
    public abstract void update(float dt);  //Handles the movement
    public abstract void render(SpriteBatch sb); //Draws on screen

    }



