package com.sps.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sps.game.Scenes.HudScene;
import com.sps.game.SpacePiratesShoedown;

public class PlayScreen implements Screen {

    private static final String ASSETS_PATH = "core/assets/tiled assests/";
    private SpacePiratesShoedown game;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera gamecam; //what the view port displays
    private Viewport gameport;
    private HudScene hud;
    private GameScreensManager gsm;

    public PlayScreen(SpacePiratesShoedown game, GameScreensManager gsm){
        this.game = game;
        this.gsm = gsm;
        gamecam = new OrthographicCamera();
        gameport = new ScreenViewport(gamecam);
        hud = new HudScene(game.batch);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(ASSETS_PATH + "HackMap.tmx"); //tmx file of map itself
        renderer = new OrthogonalTiledMapRenderer(map); //renders the tmx file provided
        gamecam.position.set(gameport.getWorldWidth() / 2, gameport.getWorldHeight() / 2, 0); //positions gamecam
    }

    @Override
    public void show() {
    }

    public void handleInput(float dt){
        if(Gdx.input.isTouched()){
            gamecam.position.x += 100 * dt; //temp, changes position of game cam
        }
    }

    //checks to see if any inputs are happening
    public void update(float dt){
        handleInput(dt);

        gamecam.update(); //update every iteration of the game cycle
        renderer.setView(gamecam); //renders only what the gamecam can see
    }

    @Override
    public void render(float delta) {
        update(delta); //render method keeps getting called
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
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
