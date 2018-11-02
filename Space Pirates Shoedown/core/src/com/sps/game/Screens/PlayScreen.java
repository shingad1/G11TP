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

/**
 * This class launches the play screen, from where the play last left off.
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia
 * @version 1.0
 */

public class PlayScreen implements Screen {

    /**
     * Constant field to direct where the file is located.
     */
    private static final String ASSETS_PATH = "core/assets/tiledassets/";
    /**
     * Holds a version of the game.
     * @see #handleInput #render
     */
    private SpacePiratesShoedown game;
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
     * @see #update #render
     */
    private OrthogonalTiledMapRenderer renderer;
    /**
     * Holds what the view port will display.
     * @see #handleInput #update #render
     */
    private OrthographicCamera gamecam;
    /**
     * Displays what the user will see.
     */
    private Viewport gameport;
    /**
     * Holds instance of the HudScene class, which displays vital information to the user.
     * @see #render
     */
    private HudScene hud;

    /**
     * Holds all the sprites that will be displayed on the sreen.
     * @see #render
     */
    private SpriteBatch batch;
    /**
     * Holds the texture showing the player.
     * @see #render
     */
    private Texture player;
    /**
     * Creates instance of the player, which holds the logic of the player.
     * @see #render
     */
    private Player p;
    /**
     * Resolves any actions the user inputs, does nothing if nothing inputed.
     * @see #handleInput
     */
    private PlayerController controller;

    public PlayScreen(SpacePiratesShoedown game){
        this.game = game;
        gamecam = new OrthographicCamera(480,480);
        gameport = new FitViewport(1600, 1600, gamecam);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(ASSETS_PATH + "testMap.tmx"); //tmx file of map itself;    HackMap.tmx
        renderer = new OrthogonalTiledMapRenderer(map); //renders the tmx file provided
        gamecam.position.set(800, 800, 0); //positions gamecam, subject to change
        player = new Texture(ASSETS_PATH + "singlecharacter.png");
        batch = new SpriteBatch();
        p = new Player(800,800); //subject to change
        int[] xbounds = {0, 1600};
        int[] ybounds = {0,1600};
        controller = new PlayerController(p, (TiledMapTileLayer) map.getLayers().get(1),xbounds,ybounds);
        hud = new HudScene(game.batch,p);
    }

    /**
     * Spcifies which controller will be used to check the input.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(controller);
    }

    /**
     *
     * @param <code>float</code>dt
     */
    public void handleInput(float dt){
        controller.action(gamecam);
        if(controller.getEntered()){
            dispose();
            gamecam.position.x = 160;
            gamecam.position.y = 160;
            p.setPosition(160,64);
            map = mapLoader.load(ASSETS_PATH + "TestBattleScene.tmx");
            renderer = new OrthogonalTiledMapRenderer(map);
            int[] xbounds = {32,320};
            int[] ybounds = {32,320};
            controller.changeCollisionLayer((TiledMapTileLayer) map.getLayers().get(1),xbounds,ybounds);
        }
        if(controller.getLeave()){
            dispose();
            gamecam.position.x = 694;
            gamecam.position.y = 1200;
            p.setPosition(726, 1248);
            map = mapLoader.load(ASSETS_PATH + "testMap.tmx");
            renderer = new OrthogonalTiledMapRenderer(map);
            int[] xbounds = {0, 1600};
            int[] ybounds = {0,1600};
            controller.changeCollisionLayer((TiledMapTileLayer) map.getLayers().get(1),xbounds,ybounds);
        }
    }

    /**
     * Checks to see if any inputs are happening, and sets the gamecam.
     * @param <code>float</code> dt.
     */
    public void update(float dt){
        handleInput(dt);
        hud.update();
        gamecam.update();
        renderer.setView(gamecam);
    }

    /**
     * Clears the screen and draws the necessary textures.
     * @param <code>float</code>delta
     */
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

    /**
     * Disposes the images so less memory is used.
     */
    @Override
    public void dispose() {
       map.dispose();
       //player.dispose();
    }
}
