package com.sps.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sps.game.Scenes.HudScene;
import com.sps.game.SpacePiratesShoedown;

/**
 * This class creates the combat screen, which is shown when the player fights with an enemy.
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia
 * @version 1.0
 */

public class CombatScreen implements Screen {

    private Game game;

    /**
     * Holds a version of the game
     */
    private SpacePiratesShoedown game;
    /**
     * Renders the texture resources
     */
    private SpriteBatch batch;

    /**
     * Constant field to direct where the file is located.
     */
    private static final String ASSETS_PATH = "core/assets/tiledAssets";

    /**
     *Displays what the user will see
     */
    private Viewport gameport;
    /**
     * Holds instance of the HudScene class, which displays vital information to the user
     */
    private HudScene hud;

    /**
     * Holds the texture showing the player
     */
    private Texture Player;



    public CombatScreen(Game game){
        this.game = game;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
    batch.begin();

    batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
