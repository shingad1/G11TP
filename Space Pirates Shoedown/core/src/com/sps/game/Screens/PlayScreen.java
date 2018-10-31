package com.sps.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sps.game.Controller.PlayerController;
import com.sps.game.Scenes.HudScene;
import com.sps.game.SpacePiratesShoedown;
import com.sps.game.Sprites.Player;

public class PlayScreen implements Screen {

    private static final String ASSETS_PATH = "core/assets/tiledassets/";
    private SpacePiratesShoedown game;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera gamecam; //what the view port displays
    private Viewport gameport;
    private HudScene hud;

    //Box2D Variables
    private World world;
    private Box2DDebugRenderer b2dr;

    //Adding a sprite
    private SpriteBatch batch;
    private Texture player;
    private Player p;
    private PlayerController controller;

    public PlayScreen(SpacePiratesShoedown game){
        this.game = game;
        gamecam = new OrthographicCamera(480,480);
        gameport = new FitViewport(1600, 1600, gamecam);
        hud = new HudScene(game.batch);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(ASSETS_PATH + "testMap.tmx"); //tmx file of map itself;    HackMap.tmx
        renderer = new OrthogonalTiledMapRenderer(map); //renders the tmx file provided
        gamecam.position.set(256, 1600-256, 0); //positions gamecam, subject to change
        player = new Texture(ASSETS_PATH + "temp32Character.png");
        batch = new SpriteBatch();
        p = new Player(256,1600-256); //subject to change
        controller = new PlayerController(p, (TiledMapTileLayer) map.getLayers().get(1));
    }

    @Override
    public void show() {
        //specify which controller to use
        Gdx.input.setInputProcessor(controller);
    }

    public void handleInput(float dt){
        controller.action(gamecam); //This resolves any actions the user inputs, does nothing if nothing inputed
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

        batch.setProjectionMatrix(hud.stage.getCamera().combined); //setting the display what the hud should see
        hud.stage.draw(); //actually drawing the graphics
        batch.setProjectionMatrix(gamecam.combined);
        batch.begin();
        batch.draw(player, p.getX(),p.getY(), 32, 32); //may want to create a settings class
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
