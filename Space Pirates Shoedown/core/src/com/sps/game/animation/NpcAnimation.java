package com.sps.game.animation;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sps.game.Sprites.AbstractNPC;

public class NpcAnimation extends ApplicationAdapter {
    /**
     * Holds all of the sprites that will be displayed on the screen
     */
    private SpriteBatch batch;
    /**
     * SpriteSheet created by the texturePacker
     */
    private TextureAtlas npcAtlas;
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
    private static final String ASSETS_PATH = "core/assets/textureAtlas/npcAtlas/";

    private AbstractNPC npc;

    public NpcAnimation(SpriteBatch sb, AbstractNPC npc, String animationName, float duration) {
        this.npc = npc;
        batch = sb;
        npcAtlas = new TextureAtlas(ASSETS_PATH + animationName);
        animation = new Animation <TextureRegion> (duration, npcAtlas.getRegions());
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
