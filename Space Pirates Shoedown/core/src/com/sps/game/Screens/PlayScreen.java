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
import com.sps.game.Controller.DialogueController;
import com.sps.game.inventory.MerchantInventory;
import com.sps.game.inventory.PlayerInventory;
import com.sps.game.Scenes.HudScene;
import com.sps.game.SpacePiratesShoedown;
import com.sps.game.Sprites.*;
import com.sps.game.maps.Map;
import com.sps.game.maps.MapFactory;
import com.sps.game.maps.MapManager;
import com.sps.game.profile.ProfileManager;


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
    protected SpacePiratesShoedown game;
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
     * Holds instance of the MerchantInventory class, which displays vital Inventory information to the user.
     * @see #render
     */
    private MerchantInventory merchantInventory;

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

    protected static MapFactory.MapType currentMapState;

    protected ArrayList<NPCController> npcController;

    protected ArrayList<Location> allLocations;

    private Boolean pause;

    private static Texture pauseTexture;

    private Stack<TiledMap> maps;

    protected String overworldMap;

    private com.badlogic.gdx.audio.Music music;

    private com.badlogic.gdx.audio.Music sound;
    private MapManager mapManager;
    private DialogueController dialogController = new DialogueController();
    private StoryController storyController = new StoryController();
    private TutorialController1 tutorialController = new TutorialController1();

    protected Random random;

    private boolean dialogBoolean = true;

    public static enum GameState{
        Saving,
        Loading,
        Running
    }

    private static GameState gameState;

    public PlayScreen(SpacePiratesShoedown game){
        this.game = game;
        mapManager = new MapManager();
        setGameState(GameState.Running);
        gamecam = new OrthographicCamera(480,480);
        gameport = new FitViewport(1600, 1600, gamecam);
        mapLoader = new TmxMapLoader();
        mapManager.setPlayer(p);
        mapManager.setCamera(gamecam);
        batch = new SpriteBatch();
        p = Player.getPlayer();
        hud = new HudScene(game.batch,p);
        merchantInventory  = new MerchantInventory(game.batch,controller);
        maps = new Stack<TiledMap>();
        pauseTexture = new Texture("core/assets/pause.png");
        pause = false;
        music = Gdx.audio.newMusic(Gdx.files.internal("core/assets/Music/firstWorld.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();

    }

    /**
     * Specifies which controller will be used to check the input.
     */
    @Override
    public void show() {
        ProfileManager.getInstance().addObserver(mapManager);
        setGameState(GameState.Loading);
        Gdx.input.setInputProcessor(controller);
        if(renderer == null){
            renderer = new OrthogonalTiledMapRenderer(mapManager.getCurrentTiledMap());
        }
    }

    /**
     * Checks the users input, changing the screen accordingly.
     * @param <code>float</code>dt
     */
    public void handleInput(float dt){
        controller.action(gamecam);
        /*if(controller.getEntered()){
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
            //currentMapState = "House";
        }*/
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
            //currentMapState = "Overworld";
        }
        if(controller.getFight()){
            game.setScreen(new CombatScreen(game, p, new BasicEnemy(160, 250, batch),this));
            //currentMapState = "HouseFight";
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
                npcController.get(i).move();
            }
        }

        gamecam.update();
        renderer.setView(gamecam);
        hud.update();
        merchantInventory.update();
    }

    /**
     * Clears the screen and draws the necessary textures.
     * @param <code>float</code>delta
     */
    @Override
    public void render(float delta) {
        if(mapManager.hasMapChanged()){
            renderer.setMap(mapManager.getCurrentTiledMap());
            gamecam.update();
            mapManager.setMapChanged(false);
        }
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


        batch.setProjectionMatrix(gamecam.combined);
        ArrayList<AbstractNPC> mapNPC = getMapNPC(currentMapState);
        if (mapNPC != null) {
            for (AbstractNPC npc : mapNPC) {
                if(npc.getAnimation() != null)
                    npc.getAnimation().render();
            }
        }
        p.getAnimation().render();
        int[] mapLayers = new int[currentMap.getLayers().size() - 3];
        for (int i = 3; i < currentMap.getLayers().size(); i++)
            mapLayers[i - 3] = (currentMap.getLayers().getIndex(currentMap.getLayers().get(i)));

        renderer.render(mapLayers);
        batch.setProjectionMatrix(hud.stage.getCamera().combined); //setting the display what the hud should see
        hud.stage.draw(); //actually drawing the graphics
        merchantInventory.stage.draw(); //drawing the user hud

        batch.begin();
        if(pause)
        {
            batch.draw(pauseTexture,gamecam.position.x - 240,gamecam.position.y - 240,480,480);
        }
        batch.end();

        changeMaps();

        //tutorialController.create();
        //tutorialController.render();
/*

        if(dialogBoolean)
        {
            try {
                dialogController.create("Linda");
            } catch (IOException e) {
                e.printStackTrace();
            }
            dialogController.render();
        }
        dialogController.render();

        dialogBoolean = false;

        dialogBoolean = false;


        dialogBoolean = false;*/


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
       //currentMapState = "House";
        game.setScreen(this);
    }

    public static void setGameState(GameState gs){
        switch (gs){
            case Saving:
                ProfileManager.getInstance().saveProfile();
                //gameState = GameState.Saving;
                break;
            case Loading:
                ProfileManager.getInstance().loadProfile();
                gameState = GameState.Running;
                break;
            case Running:
                gameState = GameState.Running;
                break;
                default:
                    gameState = GameState.Running;
                    break;
        }
    }

    public abstract ArrayList<InteractiveNPC> getInteractiveNPC();

    public abstract ArrayList<NonInteractiveNPC> getNonInteractiveNPC();

    public abstract ArrayList<InteractiveNPCMoving> getInteractiveNPCMoving();

    public abstract boolean checkPosition(Location location, MapFactory.MapType world);

    public abstract ArrayList<AbstractNPC> getMapNPC(MapFactory.MapType map);

    public abstract void changeMaps();

    public abstract Map getMap(Vector2 selector);

    public abstract Vector2 getWorldMapByWorld(MapFactory.MapType map);

    public abstract void changeNpcLocations(Map selectedMap);
}
