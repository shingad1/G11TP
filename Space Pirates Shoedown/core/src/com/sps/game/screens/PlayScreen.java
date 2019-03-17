package com.sps.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.sps.game.Controller.*;
import com.sps.game.Scenes.Dialogue;
import com.sps.game.Scenes.HudScene;
import com.sps.game.SpacePiratesShoedown;
import com.sps.game.Sprites.*;
//import com.sun.tools.internal.ws.processor.model.ModelVisitor;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * This class launches the play screen, from where the play last left off.
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia
 * @version 1.0
 */

public abstract class PlayScreen implements Screen
{

    /**
     * Constant field to direct where the file is located.
     */
    public static final String ASSETS_PATH = "core/assets/tiledassets/";
    /**
     * Holds a version of the game.
     * @see #handleInput #render
     */
    private SpacePiratesShoedown game;
    /**
     * Holds the tmx file.
     */
    protected TmxMapLoader mapLoader;
    /**
     * Displays the tmx file.
     */
    protected TiledMap currentMap;
    /**
     * Sets the view and displays it to the screen.
     * @see #update #render
     */
    protected OrthogonalTiledMapRenderer renderer;
    /**
     * Holds what the view port will display.
     * @see #handleInput #update #render
     */
    protected OrthographicCamera gamecam;
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
    protected SpriteBatch batch;

    /**
     * Creates instance of the player, which holds the logic of the player.
     * @see #render
     */
    protected Player p;
    /**
     * Holds a list of NonInteractiveNPC objects.
     * @see #render
     */
    protected ArrayList<AbstractNPC> npc;
    /**
     * Handles the users input, and updates the players properties accordingly.
     * @see #show #handleInput #combatExit
     */
    protected PlayerController controller;
    /**
     * Holds the layer of objects which the player cannot go through.
     */
    protected TiledMapTileLayer currentCollisionLayer;

    protected String currentMapState;

    protected ArrayList<NPCController> npcController;

    protected ArrayList<Location> allLocations;

    private Boolean pause;
    private static Texture pauseTexture;

    private Stack<TiledMap> maps;

    protected String overworldMap;

    private com.badlogic.gdx.audio.Music music;

    private com.badlogic.gdx.audio.Music sound;

    private Dialogue scene = new Dialogue();
    private StoryController storyController = new StoryController();
    private TutorialController1 tutorialController = new TutorialController1();

    protected Random random;

    public PlayScreen(SpacePiratesShoedown game){
        this.game = game;
        gamecam = new OrthographicCamera(480,480);
        gameport = new FitViewport(1600, 1600, gamecam);
        mapLoader = new TmxMapLoader();
        batch = new SpriteBatch();
        p = Player.getPlayer();
        hud = new HudScene(game.batch,p);
        maps = new Stack<TiledMap>();
        pauseTexture = new Texture("core/assets/pause.png");
        pause = false;
        music = Gdx.audio.newMusic(Gdx.files.internal("core/assets/Music/firstWorld.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
    }

/*
    public PlayScreen(SpacePiratesShoedown game, String mapName, SpriteBatch batch, Player p, PlayerController controller, int playerX, int playerY, ArrayList<AbstractNPC> npc, ArrayList<NPCController> npcController, int camX, int camY){
        this.game = game;
        gamecam = new OrthographicCamera(480,480);
        gameport = new FitViewport(1600, 1600, gamecam);
        mapLoader = new TmxMapLoader();
        overworldMap = mapName;
        currentMap = mapLoader.load(ASSETS_PATH + mapName);
        renderer = new OrthogonalTiledMapRenderer(currentMap);
        gamecam.position.set(playerX + camX, playerY + camY, 0);
        this.batch = batch;
        this.p = p;
        p.setPosition(playerX, playerY);
        int[] xbounds = {0, 1600};
        int[] ybounds = {0,1600};
        currentCollisionLayer = (TiledMapTileLayer) currentMap.getLayers().get(1);
        hud = new HudScene(game.batch,p);
        currentMapState = "Overworld";
        this.controller = new PlayerController(this.p,currentCollisionLayer,xbounds,ybounds,null);
        this.npc = npc;
        this.npcController = npcController;
        maps = new Stack<TiledMap>();
        pauseTexture = new Texture("core/assets/pause.png");
        pause = false;
        this.controller.reset();
    }
*/
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
            maps.push(currentMap);
            currentMap = mapLoader.load(ASSETS_PATH + "TestBattleScene.tmx");
            //BasicEnemy.WORLD = "Test Battle Screen";
            renderer = new OrthogonalTiledMapRenderer(currentMap);
            int[] xbounds = {32,320};
            int[] ybounds = {32,320};
            controller.changeCollisionLayer((TiledMapTileLayer) currentMap.getLayers().get(1),xbounds,ybounds);
            currentMapState = "House";
        }
        if(controller.getLeave()){
            dispose();
            Vector2 oldPosition = controller.popPosition();
            gamecam.position.x = (int) oldPosition.x;
            gamecam.position.y = (int) oldPosition.y;
            p.setPosition((int) oldPosition.x, (int) oldPosition.y);
            currentMap = mapLoader.load(ASSETS_PATH + this.overworldMap);
            renderer = new OrthogonalTiledMapRenderer(currentMap);
            int[] xbounds = {0, 1600};
            int[] ybounds = {0,1600};
            controller.changeCollisionLayer((TiledMapTileLayer) currentMap.getLayers().get(1),xbounds,ybounds);
            currentMapState = "Overworld";
        }
        if(controller.getFight()){
            game.setScreen(new CombatScreen(game, p, new BasicEnemy(160, 250, batch),this));
            currentMapState = "HouseFight";
        }

        /**
        if(controller.getNewWorld()){ //change: if they press a button on the edge of a map
            //dispose
            controller.reset();
            ArrayList<AbstractNPC> npcList = new ArrayList<AbstractNPC>();
            npcList.add(new NonInteractiveNPC(960,960,"Overworld", batch, ""));
            ArrayList<NPCController> npcControllerList = new ArrayList<NPCController>();
            npcControllerList.add(new NPCController((NonInteractiveNPC) npc.get(0), currentCollisionLayer));
           // game.setScreen(new PlayScreen(game, "HomeWorldMap2.tmx", batch, p, controller, 64, 864, npcList, npcControllerList,185,0));
            currentMapState = "Overworld";
        }
        */

        //Randomise the world schtuff
        if(controller.getCandy()){
            //dispose
            controller.reset();
            ArrayList<AbstractNPC> npcList = new ArrayList<AbstractNPC>();
            npcList.add(new NonInteractiveNPC(1088,512,"Overworld", batch, ""));
            ArrayList<NPCController> npcControllerList = new ArrayList<NPCController>();
            npcControllerList.add(new NPCController((NonInteractiveNPC) npc.get(0), currentCollisionLayer));
           // game.setScreen(new PlayScreen(game, "CandyLandMap1.tmx", batch, p, controller, 416, 1216, npcList, npcControllerList,0,0));
            currentMapState = "CandyLand";
        }
    }

    /**
     * Checks to see if any inputs are happening, and sets the gamecam.
     * @param <code>float</code> dt.
     */
    public void update(float dt){
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
        {
            pause = true;
            try
            {
                Thread.sleep(100);
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        handleInput(dt);
        for (int i = 0; i < npcController.size(); i++){
            if (npc.get(i).getWorld().equals(currentMapState)) {
                npcController.get(i).move(p);
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
        if(pause)
        {
            if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
            {
                pause = false;
                try
                {
                    Thread.sleep(100);
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
        else {
            update(delta); //render method keeps getting called
        }
        batch.setProjectionMatrix(gamecam.combined);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();

        batch.setProjectionMatrix(hud.stage.getCamera().combined); //setting the display what the hud should see
        hud.stage.draw(); //actually drawing the graphics
        batch.setProjectionMatrix(gamecam.combined);
        ArrayList<AbstractNPC> mapNPC = getMapNPC(currentMapState);
        if (mapNPC != null) {
            for (AbstractNPC npc : mapNPC) {
                if(npc.getAnimation() != null)
                    npc.getAnimation().render();
            }
        }
        p.getAnimation().render();
        int[] mapLayers = new int[currentMap.getLayers().size() - 1];
        for (int i = 1; i < currentMap.getLayers().size(); i++)
            mapLayers[i - 1] = (currentMap.getLayers().getIndex(currentMap.getLayers().get(i)));

        renderer.render(mapLayers);


        batch.begin();
        if(pause)
        {
            batch.draw(pauseTexture,gamecam.position.x - 240,gamecam.position.y - 240,480,480);
        }
        batch.end();

        changeMaps();

        tutorialController.create();
        tutorialController.render();
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
       currentMap.dispose();
       //player.dispose();
    }

    /**
     * Changes the screen once combat is finished.
     */
    public void combatExit(){ //make this unique to every map
        controller.setFight(false);
        TiledMapTile enemyTile = controller.getTileNearPlayerWithProperty("basicEnemy",32,32);
        enemyTile.getProperties().remove("basicEnemy");
        enemyTile.getProperties().remove("blocked");
        enemyTile.getProperties().put("invisible","true");
        currentMapState = "House";
        game.setScreen(this);
    }

    public abstract ArrayList<InteractiveNPC> getInteractiveNPC();

    public abstract ArrayList<NonInteractiveNPC> getNonInteractiveNPC();

    public abstract ArrayList<InteractiveNPCMoving> getInteractiveNPCMoving();

    public abstract boolean checkPosition(Location location, String world);

    public abstract ArrayList<AbstractNPC> getMapNPC(String world);

    public abstract void changeMaps();
}
