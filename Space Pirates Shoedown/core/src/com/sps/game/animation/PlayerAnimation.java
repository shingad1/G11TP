package com.sps.game.animation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sps.game.sprites.Location;
import com.sps.game.sprites.Player;

public class PlayerAnimation extends ApplicationAdapter {
    /**
     * Holds all the sprites that will be displayed on the screen
     * @see #PlayerAnimation
     */
    private SpriteBatch batch;
    /**
     * SpriteSheet created by the TexturePacker
     * @see #PlayerAnimation
     */
    private TextureAtlas playerAtlas;
    /**
     * animation object stores a list of objects representing an animated sequence.
     * @see #PlayerAnimation
     */
    private Animation <TextureRegion> animation;
    /**
     * Delta time
     *@see #PlayerAnimation
     */
    private float timePassed = 0;
    /**
     * The path for textures
     */
   private static final String ASSETS_PATH = "core/assets/textureAtlas/playerAtlas/";

   private Player player;

    public PlayerAnimation(SpriteBatch sb, Player player, String animationName, float duration) {
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
    public void render(Location location,float frame) {
        //Gdx.gl.glClearColor(0, 1, 0, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        timePassed += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(frame, true), location.getX(), location.getY(),32,32);
        batch.end();
    }
    public void render(Location location) {
        //Gdx.gl.glClearColor(0, 1, 0, 1);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        timePassed += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(timePassed, true), location.getX(), location.getY(),32,32);
        batch.end();
    }
}

