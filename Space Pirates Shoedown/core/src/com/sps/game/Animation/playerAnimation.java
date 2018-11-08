package com.sps.game.Animation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class playerAnimation extends ApplicationAdapter {
    private SpriteBatch batch;
    private TextureAtlas playerDownAtlas;
    private Animation <TextureRegion> animation;
    private float timePassed = 0;

   private static final String ASSETS_PATH = "core/assets/";

    public playerAnimation() {
    }

    public void create() {
        batch =  new SpriteBatch();

        playerDownAtlas = new TextureAtlas(Gdx.files.internal("playerDown.atlas"));
        animation = new Animation <TextureRegion> (1/3f, playerDownAtlas.getRegions());

    }

    public void dispose() {
        batch.dispose();
        playerDownAtlas.dispose();
    }

    public void render() {
    Gdx.gl.glClearColor(0, 1, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();
    timePassed += Gdx.graphics.getDeltaTime();
    batch.draw(animation.getKeyFrame(timePassed, true), 100, 500);
    batch.end();
    }
}

