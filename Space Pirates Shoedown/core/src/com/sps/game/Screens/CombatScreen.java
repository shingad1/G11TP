package com.sps.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * This class creates the combat screen, which is shown when the player fights with an enemy.
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia
 * @version 1.0
 */

public class CombatScreen implements Screen {

    /**
     * Constant field to direct where the file is located.
     */
    private static final String ASSETS_PATH = "core/assets/tiledassets/";

    /**
     * Holds what the view port will display.
     * @see
     */
    private OrthographicCamera gamecam;

    /**
     * Holds the texture of the enemy sprite.
     */
    private Texture enemy;
    /**
     * Displays what the user will see.
     */
    private Viewport gameport;
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

    private Game game;

    public CombatScreen(Game game){
        this.game = game;
        enemy = new Texture(ASSETS_PATH + "singleEnemy.png");
        gamecam.position.set(160, 160,0);
        gameport = new FitViewport(160, 160, gamecam);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(ASSETS_PATH + "TestBattleScene.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
    }
    @Override
    public void show() {

    }

    public void update(float dt){

    }

    @Override
    public void render(float delta) {
        //update method called here
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        //batch begin/draw/end here
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
