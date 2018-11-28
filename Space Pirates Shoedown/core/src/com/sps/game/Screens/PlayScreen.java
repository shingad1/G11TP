package com.sps.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sps.game.Controller.NPCController;
import com.sps.game.Controller.PlayerController;
import com.sps.game.Scenes.HudScene;
import com.sps.game.SpacePiratesShoedown;
import com.sps.game.Sprites.*;

import java.util.ArrayList;

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
     * Creates instance of the player, which holds the logic of the player.
     * @see #render
     */
    private Player p;
    /**
     * Holds the texture showing the npcTexture.
     * @see #render
     */
    private Texture npcTexture;
    /**
     * Holds a list of NonInteractiveNPC objects.
     * @see #render
     */
    private ArrayList<NonInteractiveNPC> npc;
    /**
     * Handles the users input, and updates the players properties accordingly.
     * @see #show #handleInput #combatExit
     */
    private PlayerController controller;
    /**
     * Holds the layer of objects which the player cannot go through.
     */
    private TiledMapTileLayer collisionLayer;

    private Texture cryingNPCTexture;

    private String mapState;

    private NPCController npcController;

    private ArrayList<Location> allLocations;

    private InteractiveNPC cryingNPC;

    public PlayScreen(SpacePiratesShoedown game){
        this.game = game;
        gamecam = new OrthographicCamera(480,480);
        gameport = new FitViewport(1600, 1600, gamecam);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(ASSETS_PATH + "testMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(800, 800, 0);
        npcTexture = new Texture(ASSETS_PATH + "../npcIdle.png");
        cryingNPCTexture = new Texture(ASSETS_PATH + "../Graphics and Sprites/Home World NPCs/Crying NPC/CryingNPC2.png");
        cryingNPC = new InteractiveNPC();
        batch = new SpriteBatch();
        p = new Player(800,800,batch);
        npc = new ArrayList<NonInteractiveNPC>();
        npc.add(new NonInteractiveNPC(960,960,"Overworld", batch));
        allLocations = new ArrayList<Location>();
        for (AbstractNPC nonPlayingCharacter : npc){
            allLocations.add(nonPlayingCharacter.getLocation());
        }
        int[] xbounds = {0, 1600};
        int[] ybounds = {0,1600};
        collisionLayer = (TiledMapTileLayer) map.getLayers().get(1);
        controller = new PlayerController(p, collisionLayer,xbounds,ybounds,allLocations);
        hud = new HudScene(game.batch,p);
        mapState = "Overworld";
        npcController = new NPCController(npc.get(0), collisionLayer);
    }

    /**
     * Specifies which controller will be used to check the input.
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(controller);
    }

    /**
     * Checks the users input, changing the screen accordingly.
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
            //BasicEnemy.WORLD = "Test Battle Screen";
            renderer = new OrthogonalTiledMapRenderer(map);
            int[] xbounds = {32,320};
            int[] ybounds = {32,320};
            controller.changeCollisionLayer((TiledMapTileLayer) map.getLayers().get(1),xbounds,ybounds);
            mapState = "House";
        }
        if(controller.getLeave()){
            dispose();
            Vector2 oldPosition = controller.popPosition();
            gamecam.position.x = (int) oldPosition.x;
            gamecam.position.y = (int) oldPosition.y;
            p.setPosition((int) oldPosition.x, (int) oldPosition.y);
            map = mapLoader.load(ASSETS_PATH + "testMap.tmx");
            renderer = new OrthogonalTiledMapRenderer(map);
            int[] xbounds = {0, 1600};
            int[] ybounds = {0,1600};
            controller.changeCollisionLayer((TiledMapTileLayer) map.getLayers().get(1),xbounds,ybounds);
            mapState = "Overworld";
        }
        if(controller.getFight()){
            game.setScreen(new CombatScreen(game, p, new BasicEnemy(160, 250),this));
            mapState = "HouseFight";
        }
    }

    /**
     * Checks to see if any inputs are happening, and sets the gamecam.
     * @param <code>float</code> dt.
     */
    public void update(float dt){
        handleInput(dt);
        for (int i = 0; i < npc.size(); i++){
            if (npc.get(i).getWorld().equals(mapState)) {
                npcController.move(p);
            }
        }
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
        batch.draw(cryingNPCTexture,800, 612, 32, 32);
        batch.end();
        for (int i = 0; i < npc.size(); i++) {
            if (npc.get(i).getWorld().equals(mapState)) {
                npc.get(i).getAnimation().render();
            }
        }
        p.getAnimation().render();
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

    /**
     * Changes the screen once combat is finished.
     */
    public void combatExit(){
        controller.setFight(false);
        TiledMapTile enemyTile = controller.getTileNearPlayerWithProperty("basicEnemy",32,32);
        enemyTile.getProperties().remove("basicEnemy");
        enemyTile.getProperties().remove("blocked");
        enemyTile.getProperties().put("invisible","true");
        mapState = "House";
        game.setScreen(this);
    }

    public SpriteBatch getSpriteBatch()
    {
        return batch;
    }
}
