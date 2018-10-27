package com.mygdx.game.State;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public abstract class State {

    protected OrthographicCamera cam;
    protected Vector2 mouse;
    protected GameStateManager gsm; //sets the state of the game.
    protected static final String ASSETS_PATH = "core/assets/";
    /*
        ASSETS_PATH: internal path to the assets folder
        So any references made to images should be ASSETS_PATH + "<image name>.<extension>";
        i.e. private Texture playButton = New Texture(ASSETS_PATH + "playbutton.png")
     */

    protected State(GameStateManager gsm) {
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector2();
    }

    protected abstract void handleInput(); //Handles the user input
    public abstract void update(float dt);  //Handles the movement
    public abstract void render(SpriteBatch sb); //Draws on screen
    public abstract void dispose(); //dispose of texture and other media to prevent memory leak
}



