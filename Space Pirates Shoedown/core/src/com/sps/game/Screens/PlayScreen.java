package com.sps.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
//import com.sun.tools.internal.ws.processor.model.ModelVisitor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * This class launches the play screen, from where the play last left off.
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia
 * @version 1.0
 */

public class PlayScreen implements Screen
{

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
    private ArrayList<AbstractNPC> npc;
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

    private ArrayList<NPCController> npcController;

    private ArrayList<Location> allLocations;

    private Boolean pause;
    private static Texture pauseTexture;

    private Stack<TiledMap> maps;

    private Texture idleOne;

    private String overworldMap;

    private com.badlogic.gdx.audio.Music music;

    private com.badlogic.gdx.audio.Music sound;

    public PlayScreen(SpacePiratesShoedown game){
        this.game = game;
        gamecam = new OrthographicCamera(480,480);
        gameport = new FitViewport(1600, 1600, gamecam);
        mapLoader = new TmxMapLoader();
        overworldMap = "testMap.tmx";
        map = mapLoader.load(ASSETS_PATH + "testMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(736, 1280, 0);
        npcTexture = new Texture(ASSETS_PATH + "../npcIdle.png");
        cryingNPCTexture = new Texture(ASSETS_PATH + "../Graphics and Sprites/Home World NPCs/Crying NPC/CryingNPC2.png");
        idleOne = new Texture(ASSETS_PATH + "../Graphics and Sprites/Home World NPCs/InteractiveNPC_Idle/glassesNPC_Behind.png");
        batch = new SpriteBatch();
        p = new Player(736,1280,batch);
        npc = new ArrayList<AbstractNPC>();
        npc.add(new NonInteractiveNPC(960,960,"Overworld", batch, ""));
        npc.add(new InteractiveNPC(800,640,"Overworld",batch, "Linda"));
        npc.add(new NonInteractiveNPC(576, 672,"Overworld", batch, "Merchant"));
        npc.add(new InteractiveNPCMoving(768, 1216, "Overworld", batch, "", "Bob"));
        npc.add(new InteractiveNPCMoving(768, 960,"Overworld", batch,"", "Ellie"));
        npc.add(new InteractiveNPCMoving(768, 832, "Overworld", batch, "", "Mo"));
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
        npcController = new ArrayList<NPCController>();
        npcController.add(new NPCController((NonInteractiveNPC) npc.get(0), collisionLayer));
        npcController.add(new NPCController((NonInteractiveNPC) npc.get(2), collisionLayer));
        npcController.add(new NPCController((InteractiveNPCMoving) npc.get(3), collisionLayer));
        npcController.add(new NPCController((InteractiveNPCMoving) npc.get(4), collisionLayer));
        npcController.add(new NPCController((InteractiveNPCMoving) npc.get(5), collisionLayer));
        maps = new Stack<TiledMap>();

        pauseTexture = new Texture("core/assets/pause.png");
        pause = false;

        music = Gdx.audio.newMusic(Gdx.files.internal("core/assets/Music/firstWorld.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();

    }


    public PlayScreen(SpacePiratesShoedown game, String mapName, SpriteBatch batch, Player p, PlayerController controller, int playerX, int playerY, ArrayList<AbstractNPC> npc, ArrayList<NPCController> npcController, int camX, int camY){
        this.game = game;
        gamecam = new OrthographicCamera(480,480);
        gameport = new FitViewport(1600, 1600, gamecam);
        mapLoader = new TmxMapLoader();
        overworldMap = mapName;
        map = mapLoader.load(ASSETS_PATH + mapName);
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(playerX + camX, playerY + camY, 0);
        this.batch = batch;
        this.p = p;
        p.setPosition(playerX, playerY);
        int[] xbounds = {0, 1600};
        int[] ybounds = {0,1600};
        collisionLayer = (TiledMapTileLayer) map.getLayers().get(1);
        hud = new HudScene(game.batch,p);
        mapState = "Overworld";
        this.controller = new PlayerController(this.p,collisionLayer,xbounds,ybounds,null);
        this.npc = npc;
        this.npcController = npcController;
        maps = new Stack<TiledMap>();
        pauseTexture = new Texture("core/assets/pause.png");
        pause = false;
        this.controller.reset();
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
            maps.push(map);
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
            map = mapLoader.load(ASSETS_PATH + this.overworldMap);
            renderer = new OrthogonalTiledMapRenderer(map);
            int[] xbounds = {0, 1600};
            int[] ybounds = {0,1600};
            controller.changeCollisionLayer((TiledMapTileLayer) map.getLayers().get(1),xbounds,ybounds);
            mapState = "Overworld";
        }
        if(controller.getFight()){
            game.setScreen(new CombatScreen(game, p, new BasicEnemy(160, 250, batch),this));
            mapState = "HouseFight";
        }
        if(controller.getNewWorld()){
            //dispose
            controller.reset();
            ArrayList<AbstractNPC> npcList = new ArrayList<AbstractNPC>();
            npcList.add(new NonInteractiveNPC(960,960,"Overworld", batch, ""));
            ArrayList<NPCController> npcControllerList = new ArrayList<NPCController>();
            npcControllerList.add(new NPCController((NonInteractiveNPC) npc.get(0), collisionLayer));
            game.setScreen(new PlayScreen(game, "HomeWorldMap2.tmx", batch, p, controller, 64, 864, npcList, npcControllerList,185,0));
            mapState = "Overworld";
        }
        if(controller.getCandy()){
            //dispose
            controller.reset();
            ArrayList<AbstractNPC> npcList = new ArrayList<AbstractNPC>();
            npcList.add(new NonInteractiveNPC(1088,512,"Overworld", batch, ""));
            ArrayList<NPCController> npcControllerList = new ArrayList<NPCController>();
            npcControllerList.add(new NPCController((NonInteractiveNPC) npc.get(0), collisionLayer));
            game.setScreen(new PlayScreen(game, "CandyLandMap1.tmx", batch, p, controller, 416, 1216, npcList, npcControllerList,0,0));
            mapState = "CandyLand";
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
            if (npc.get(i).getWorld().equals(mapState)) {
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
        for (int i = 0; i < npc.size(); i++) {
            if (npc.get(i).getWorld().equals(mapState)) {
                if(npc.get(i).getAnimation() != null) {
                    npc.get(i).getAnimation().render();
                }
            }
        }
        p.getAnimation().render();

        controller.npcInteraction((getInteractiveNPC()), "Linda");
        controller.npcmoving(getInteractiveNPCMoving(), "Bob");
        controller.npcmoving(getInteractiveNPCMoving(), "Ellie");
        controller.npcmoving(getInteractiveNPCMoving(), "Mo");

        batch.begin();
        if(pause)
        {
            batch.draw(pauseTexture,gamecam.position.x - 240,gamecam.position.y - 240,480,480);
        }
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

    public ArrayList<InteractiveNPC> getInteractiveNPC(){
        ArrayList<InteractiveNPC> interactiveNPCs = new ArrayList<InteractiveNPC>();
        for(AbstractNPC nonPlayingCharacter : npc){
            if(nonPlayingCharacter.getClass() == InteractiveNPC.class){
                interactiveNPCs.add((InteractiveNPC) nonPlayingCharacter);
            }
        }
        return interactiveNPCs;
    }

    public ArrayList<NonInteractiveNPC> getNonInteractiveNPC(){
        ArrayList<NonInteractiveNPC> nonInteractiveNPCs = new ArrayList<NonInteractiveNPC>();
        for(AbstractNPC nonPlayingCharacter : npc){
            if(nonPlayingCharacter.getClass() == NonInteractiveNPC.class){
                nonInteractiveNPCs.add((NonInteractiveNPC) nonPlayingCharacter);
            }
        }
        return nonInteractiveNPCs;
    }

    public ArrayList<InteractiveNPCMoving> getInteractiveNPCMoving(){
        ArrayList<InteractiveNPCMoving> InteractiveNPCsMoving = new ArrayList<InteractiveNPCMoving>();
        for(AbstractNPC InteractiveNPC : npc){
            if(InteractiveNPC.getClass() == InteractiveNPCMoving.class){
                InteractiveNPCsMoving.add((InteractiveNPCMoving) InteractiveNPC);
            }
        }
        return InteractiveNPCsMoving;
    }


}
