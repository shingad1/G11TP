package com.sps.game.screens;

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
import com.sps.game.controller.*;
import com.sps.game.inventory.MerchantInventory;
import com.sps.game.scenes.DialogueHud;
import com.sps.game.scenes.HudScene;
import com.sps.game.SpacePiratesShoedown;
import com.sps.game.sprites.*;
import com.sps.game.maps.Map;
import com.sps.game.maps.MapFactory;
import com.sps.game.maps.MapManager;
import com.sps.game.profile.ProfileManager;
import java.util.ArrayList;
import java.util.Random;

/**
 * This abstract class contains all the methods common to each subclass.
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia and Mahamuda Akhter
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
    protected static OrthographicCamera gamecam;
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
     * Holds a list of the enemies in the room
     */
    protected ArrayList<AbstractEnemy> enemies;
    /**
     * Handles the users input, and updates the players properties accordingly.
     * @see #show #handleInput #combatExit
     */
    protected PlayerController controller;
    /**
     * Holds the layer of objects which the player cannot go through.
     */
    protected TiledMapTileLayer currentCollisionLayer;
    /**
     * Holds the current state of the Map.
     */
    protected static MapFactory.MapType currentMapState;
    /**
     * Holds a list of NPC Controllers.
     */
    protected ArrayList<NPCController> npcController;
    /**
     * Holds a list of all the NPC locations.
     */
    protected ArrayList<Location> allLocations;
    /**
     * Checks to see if the pause button has been activated. True for pause otherwise false.
     */
    private boolean pause;
    /**
     * Holds the texture of the pause button
     */
    private static Texture pauseTexture;
    /**
     * Holds the music track that will be played in the world.
     */
    protected com.badlogic.gdx.audio.Music music;
    /**
     * Holds an instance of the MapManager for saving that state of it.
     */
    private MapManager mapManager;
    /**
     * Creates and holds a story controller
     */
    private StoryController storyController = new StoryController();
    /**
     * Creates and holds a tutorial controller
     */
    private TutorialController1 tutorialController = new TutorialController1();
    /**
     * Holds a random number generator.
     */
    protected Random random;
    /**
     * Holds a boolean value to display a dialogue box. True if the dialogue box should appear, otherwise false.
     */
    private boolean dialogBoolean = true;
    /**
     * Holds the MapType of the map, before the player enters the house.
     */
    protected static MapFactory.MapType oldState;

    int count = 0;

    /**
     * Holds the different states the game can be in.
     */
    public static enum GameState{
        Saving,
        Loading,
        Running
    }

    /**
     * Holds the current state of the game
     */
    private static GameState gameState;

    private DialogueHud dialogueHud;

    MiniMapScreen miniMapScreen;

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
        dialogueHud = new DialogueHud(game.batch, controller);
        pauseTexture = new Texture("core/assets/pause.png");
        pause = false;
        //MiniMapScreen = new MiniMapScreen(getWorldMapByWorld(mapManager.getCurrentMapType()));
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
        if(controller.getFight()){
            //game.setScreen(new CombatScreen(game, p, new BasicEnemy(160, 250, batch),this));
            //currentMapState = "HouseFight";
        }
    }

    /**
     * Checks to see if any inputs are happening, and sets the gamecam.
     * @param <code>float</code> dt.
     */
    public void update(float dt) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            pause = true;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        handleInput(dt);
        for (int i = 0; i < npcController.size(); i++) {
            if (npc.get(i).getWorld().equals(currentMapState)) {
                npcController.get(i).move();
            }
        }

        gamecam.update();
        renderer.setView(gamecam);
        hud.update();
        merchantInventory.update();

        for (AbstractNPC npcTemp : getInteractiveNPC()) {
            if (controller.npcInProximity(npcTemp)) {
                dialogueHud.update(npcTemp.getName());
            }
        }
        if(getClass().equals(HouseInteriorScreen.class)) {
            for (AbstractEnemy enemy : enemies) {
                if (controller.enemyInProximity(enemy)) {
                    dialogueHud.update(enemy.getName());
                }
            }
        }
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

        ArrayList<AbstractEnemy> mapEnemy = getMapEnemy(currentMapState);
        if (mapEnemy != null){
            for(AbstractEnemy enemy : mapEnemy){
                if(enemy.getAnimation() != null)
                    enemy.getAnimation().render();
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
        dialogueHud.stage.draw();

        batch.begin();
        if(pause)
        {
            batch.draw(pauseTexture,gamecam.position.x - 240,gamecam.position.y - 240,480,480);
        }
        batch.end();

        changeMaps();

        //MiniMapScreen.miniMap();
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
       music.dispose();
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
        game.setScreen(this);
    }

    /**
     * Sets the state of the game.
     * @param gs
     */
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

    /**
     * Returns an ArrayList containing all the InteractiveNPC's in the world.
     * @return ArrayList interactiveNPCs
     */
    public ArrayList<AbstractNPC> getInteractiveNPC() {
        ArrayList<AbstractNPC> interactiveNPCs = new ArrayList<AbstractNPC>();
        for(AbstractNPC nonPlayingCharacter : npc){
            if(nonPlayingCharacter.getClass() == InteractiveNPC.class){
                interactiveNPCs.add((InteractiveNPC) nonPlayingCharacter);
            }
            else  if(nonPlayingCharacter.getClass() == InteractiveNPCMoving.class){
                interactiveNPCs.add((InteractiveNPCMoving) nonPlayingCharacter);
            }
        }
        return interactiveNPCs;
    }

    /**
     * Returns an ArrayList containing all the NonInteractiveNPC's in the world.
     * @return ArrayList nonInteractiveNPCs
     */
    public ArrayList<NonInteractiveNPC> getNonInteractiveNPC() {
        ArrayList<NonInteractiveNPC> nonInteractiveNPCs = new ArrayList<NonInteractiveNPC>();
        for(AbstractNPC nonPlayingCharacter : npc){
            if(nonPlayingCharacter.getClass() == NonInteractiveNPC.class){
                nonInteractiveNPCs.add((NonInteractiveNPC) nonPlayingCharacter);
            }

        }
        return nonInteractiveNPCs;
    }

    /**
     * Returns an ArrayList containing all the Interactive NPCs that can move in the world.
     * @return ArrayList interactiveNPCsMoving
     */
    public ArrayList<InteractiveNPCMoving> getInteractiveNPCMoving() {
        ArrayList<InteractiveNPCMoving> interactiveNPCsMoving = new ArrayList<InteractiveNPCMoving>();
        for(AbstractNPC InteractiveNPC : npc){
            if(InteractiveNPC.getClass() == InteractiveNPCMoving.class){
                interactiveNPCsMoving.add((InteractiveNPCMoving) InteractiveNPC);
            }
        }
        return interactiveNPCsMoving;
    }
    /**
     * Changes the map that is rendered once the player is on a certain location or going of the screen
     */
    public abstract boolean checkPosition(Location location, MapFactory.MapType world);

    /**
     * Returns an ArrayList of Abstract NPCS, which holds every NPC on the Map.
     * @param map
     * @return
     */
    public ArrayList<AbstractNPC> getMapNPC(MapFactory.MapType map) {
        ArrayList<AbstractNPC> result = new ArrayList<AbstractNPC>();
        for (int i = 0; i < npc.size(); i++){
            if (npc.get(i).getWorld().equals(map)){
                result.add(npc.get(i));
            }
        }
        return result;
    }

    /**
     * Changes the map that is rendered once the player is on a certain location or going of the screen.
     */
    public abstract void changeMaps();
    /**
     * Returns a map from the array according to the vector2 value passed in as a parameter.
     * @param selector
     * @return
     */
    public abstract Map getMap(Vector2 selector);
    /**
     * Returns a Vector2 value to get a Map according to the map type specified in the parameter.
     * @param map
     * @return
     */
    public abstract Vector2 getWorldMapByWorld(MapFactory.MapType map);

    public abstract ArrayList<AbstractEnemy> getMapEnemy(MapFactory.MapType map);

    /**
     * Adds the locations of the all the NPCs in to an ArrayList.
     * @param selectedMap
     */
    public void changeNpcLocations(Map selectedMap) {
        for (AbstractNPC nonPlayingCharacter : npc) {
            if (nonPlayingCharacter.getWorld().equals(selectedMap.getCurrentMapType()))
                allLocations.add(nonPlayingCharacter.getLocation());
        }
    }

    public void addEnemiesLocations(Map selectedMap){
        for (AbstractEnemy enemy : enemies) {
            allLocations.add(enemy.getLocation());
        }
    }
    /**
     * Returns the current type of the map.
     * @return MapFactory.MapType currentMapState
     */
    public static MapFactory.MapType getCurrentMapType(){
        return currentMapState;
    }
}
