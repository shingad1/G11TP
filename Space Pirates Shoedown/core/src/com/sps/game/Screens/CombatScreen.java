package com.sps.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sps.game.Scenes.HudScene;
import com.sps.game.SpacePiratesShoedown;

/**
 * This class creates the combat screen, which is shown when the player fights with an enemy.
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia
 * @version 1.0
 */

public class CombatScreen implements Screen {

    /**
     * Holds a version of the game
     */
    private Game game;
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
     * Holds the texture showing the player.
     */
    private Texture player;
    /**
     * Holds the texture showing the enemy.
     */
    private Texture enemy;
    /**
     * Holds the tmx file.
     */
    private TmxMapLoader mapLoader;
    /**
     * Displays the tmx file.
     */
    private TiledMap map;
    /**
     * Sets the view and displays it to the screen.
     * @see
     */
    private OrthogonalTiledMapRenderer renderer;
    /**
     * Holds what the view port will display.
     * @see
     */
    private OrthographicCamera gamecam;


    public CombatScreen(Game game) {
        this.game = game;
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(ASSETS_PATH + "emptyBattleMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam = new OrthographicCamera(160, 160);
        gameport = new FitViewport(1600, 1600, gamecam);
        player = new Texture(ASSETS_PATH + "singleCharacter.png");
        enemy = new Texture(ASSETS_PATH + "singleEnemy.png");

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        renderer.render();
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
