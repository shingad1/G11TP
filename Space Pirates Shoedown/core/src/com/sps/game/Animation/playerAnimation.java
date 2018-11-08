package com.sps.game.Animation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class playerAnimation extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture playerDown;
    private static final String ASSETS_PATH = "core/assets/";

    public void create() {
        batch =  new SpriteBatch();
        playerDown = new Texture(ASSETS_PATH + "playerSpriteSheet.png");


    }

    public playerAnimation() {

    }

    public void dispose() {

    }

    public void render() {
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();
    
    batch.end();
    }
}

