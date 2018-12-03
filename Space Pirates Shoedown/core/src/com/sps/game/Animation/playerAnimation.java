package com.sps.game.Animation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sps.game.Sprites.Player;

public class playerAnimation extends ApplicationAdapter {
    /**
     * Holds all the sprites that will be displayed on the screen
     * @see #playerAnimation
     */
    private SpriteBatch batch;
    /**
     * SpriteSheet created by the TexturePacker
     * @see #playerAnimation
     */
    private TextureAtlas playerAtlas;
    /**
     * Animation object stores a list of objects representing an animated sequence.
     * @see #playerAnimation
     */
    private Animation <TextureRegion> animation;
    /**
     * Delta time
     *@see #playerAnimation
     */
    private float timePassed = 0;
    /**
     * The path for textures
     */
   private static final String ASSETS_PATH = "core/assets/textureAtlas/playerAtlas/";

   private Player player;

    public playerAnimation(SpriteBatch sb,Player player,String animationName, float duration) {
        this.player = player;
        batch = sb;
        playerAtlas = new TextureAtlas(ASSETS_PATH + animationName);
        animation = new Animation <TextureRegion> (duration, playerAtlas.getRegions());
    }


    /**
     * Frees up resources in the memory
     */
    public void dispose() {
        batch.dispose();
        playerAtlas.dispose();
    }

    /**
     * Sets a temporary background colour, and uses the spritebatch to render the animation object, using the timePassed field.
     */
    public void render() {
    //Gdx.gl.glClearColor(0, 1, 0, 1);
    //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    batch.begin();
    timePassed += Gdx.graphics.getDeltaTime();
    batch.draw(animation.getKeyFrame(timePassed, true), player.getX(), player.getY(),32,32);
    batch.end();
    }
}

