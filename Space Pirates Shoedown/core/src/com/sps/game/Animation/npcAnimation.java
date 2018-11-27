package com.sps.game.Animation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sps.game.Sprites.NonInteractiveNPC;

public class npcAnimation extends ApplicationAdapter {
    /**
     * Holds all of the sprites that will be displayed on the screen
     */
    private SpriteBatch batch;
    /**
     * SpriteSheet created by the texturePacker
     */
    private TextureAtlas npcAtlas;
    /**
     * Animation object stores a list of objects representing an animated sequence.
     */
    private Animation<TextureRegion> animation;
    /**
     * Delta time.
     */
    private float timePassed = 0;
    /**
     * The path for textures
     */
    private static final String ASSETS_PATH = "core/assets/textureAtlas/npcAtlas/";

    private NonInteractiveNPC npc;

    public npcAnimation(SpriteBatch sb, NonInteractiveNPC npc, String animationName) {
        this.npc = npc;
        batch = sb;
        npcAtlas = new TextureAtlas(ASSETS_PATH + animationName);
        animation = new Animation <TextureRegion> (1/15f, npcAtlas.getRegions());
    }

    public void dispose() {
        batch.dispose();
        npcAtlas.dispose();
    }

    public void render() {
        batch.begin();
        timePassed += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(timePassed, true), npc.getX(), npc.getY(), 32, 32);
        batch.end();
    }
}