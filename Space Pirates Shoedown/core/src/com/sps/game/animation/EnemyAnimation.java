package com.sps.game.animation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sps.game.sprites.AbstractEnemy;
import com.sps.game.sprites.Location;
/**
 * This class creates the animation for the enemy, according the state of the enemy.
 * @author Miraj Shah
 * @version 1.0
 */
public class EnemyAnimation extends ApplicationAdapter {
    /**

     * Holds all of the sprites that will be displayed on the screen
     */
    private SpriteBatch batch;
    /**
     * SpriteSheet created by the texturePacker
     */
    private TextureAtlas enemyAtlas;
    /**
     * animation object stores a list of objects representing an animated sequence.
     */
    private Animation<TextureRegion> animation;
    /**
     * Delta time.
     */
    private float timePassed = 0;
    /**
     * The path for textures
     */
    private static final String ASSETS_PATH = "core/assets/textureAtlas/enemyAtlas/";

    private AbstractEnemy enemy;

    public EnemyAnimation(SpriteBatch sb, AbstractEnemy enemy, String animationName, float duration) {
        this.enemy = enemy;
        batch = sb;
        enemyAtlas = new TextureAtlas(ASSETS_PATH + animationName);
        animation = new Animation <TextureRegion> (duration, enemyAtlas.getRegions());
    }

    /**
     * Disposes the batch and atlas, so less memory is used.
     */
    public void dispose() {
        batch.dispose();
        enemyAtlas.dispose();
    }

    /**
     * Renders the enemy according to its state
     */
    public void render() {
        batch.begin();
        timePassed += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(timePassed, true), enemy.getX(), enemy.getY(), 32, 32);
        batch.end();
    }

    /**
     * Renders the enemy according to its state
     * @param Location location
     * @param Float frame
     */
    public void render(Location location, float frame) {
        batch.begin();
        timePassed += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(frame, true), location.getX(), location.getY(), 32, 32);
        batch.end();
    }

    /**
     * Renders the enemy according to its state
     * @param Location location
     */
    public void render(Location location) {
        batch.begin();
        timePassed += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(timePassed, true), location.getX(), location.getY(), 32, 32);
        batch.end();
    }
}
