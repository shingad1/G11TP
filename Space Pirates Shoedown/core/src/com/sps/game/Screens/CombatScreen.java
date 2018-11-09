package com.sps.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sps.game.Controller.CombatController;
import com.sps.game.Controller.CombatSystem;
import com.sps.game.Scenes.CombatHud;
import com.sps.game.Scenes.EnemyHud;
import com.sps.game.Scenes.ThirdHud;
import com.sps.game.SpacePiratesShoedown;
import com.sps.game.Sprites.BasicEnemy;
import com.sps.game.Sprites.Player;

/**
 * This class creates the combat screen, which is shown when the player fights with an enemy.
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia
 * @version 1.0
 */

public class CombatScreen implements Screen {

    /**
     * Holds a version of the game
     * @see #CombatScreen
     */
    private Game game;
    /**
     * Renders the texture resources
     * @see #CombatScreen
     */
    private SpriteBatch batch;

    /**
     * Constant field to direct where the file is located.
     * @see #CombatScreen
     */
    private static final String ASSETS_PATH = "core/assets/tiledAssets/";
    /**
     *Displays what the user will see
     * @see #CombatScreen
     */
    private Viewport gameport;
    /**
     * Holds the texture showing the player.
     * @see #render(float)
     */
    private Texture player;
    /**
     * Holds the texture showing the enemy.
     * @see #render(float)
     */
    private Texture enemy;
    /**
     * Holds the tmx file.
     * @see #CombatScreen
     */
    private TmxMapLoader mapLoader;
    /**
     * Displays the tmx file.
     * @see #CombatScreen
     */
    private TiledMap map;
    /**
     * Sets the view and displays it to the screen.
     * @see #CombatScreen
     */
    private OrthogonalTiledMapRenderer renderer;
    /**
     * Holds what the view port will display.
     * @see #CombatScreen
     */
    private OrthographicCamera gamecam;

    private CombatHud playerHud;
    /**
     * holds the playerhud
     * @see #render(float) #update(float)
     */
    private EnemyHud enemyHud;
    /**
     * holds the Enemyhud
     * @see #render(float) #update(float)
     */
    private ThirdHud ThirdHud;
    /**
     * holds the ThirdHud
     * @see #render(float) #update(float)
     */
    private BasicEnemy Enemy;
    /**
     * holds the enemy
     * @see #render(float) #update(float)
     */
    private CombatController combatController;
    /**
     * Holds the CombatController
     * @see #CombatScreen
     */
    private CombatSystem cs;
    /**
     * Holds the CombatSystem
     * @see #CombatScreen
     */
    private int tick;
    /**
     * Holds integer variable which has the information for the tick
     * @see #CombatScreen
     */

    private PlayScreen playScreen;

    public CombatScreen(SpacePiratesShoedown game, Player p, BasicEnemy e, PlayScreen playScreen) {
        this.game = game;
        this.Enemy = e;
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(ASSETS_PATH + "emptyBattleMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam = new OrthographicCamera(480, 480);
        gamecam.position.set(176,176,0);
        gameport = new FitViewport(1600, 1600, gamecam);
        player = new Texture(ASSETS_PATH + "singleCharacter.png");
        enemy = new Texture(ASSETS_PATH + "singleEnemy.png");
        batch = new SpriteBatch();
        playerHud = new CombatHud(batch,p,e);
        enemyHud = new EnemyHud(batch,e);
        ThirdHud = new ThirdHud(batch);
        cs = new CombatSystem(p, e);
        combatController = new CombatController(p, e, cs);
        tick = 0;
        this.playScreen = playScreen;
    }
    /**
     * this method sets the combat controller to input processor
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(combatController);
    }
    /**
     * this method updates the information on the screen
     * @param (dt)
     */
    public void update(float dt){
        if (cs.getFinished()){
            returnScreen();
        }
        gamecam.update();
        renderer.setView(gamecam);
        cs.update();
        playerHud.update();
        enemyHud.update();
        ThirdHud.update();
    }
    /**
     * this method renders the information on the screen and draws it
     * @param (delta)
     */
    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        enemyHud.stage.draw();
        playerHud.stage.draw();
        ThirdHud.stage.draw();
        batch.setProjectionMatrix(gamecam.combined);
        batch.begin();
        batch.draw(player, 160, 100, 32, 32);
        batch.draw(enemy, Enemy.getX(), Enemy.getY(), 32, 32);
        batch.end();
    }
    /**
     * this method resize the screen
     */
    @Override
    public void resize(int width, int height) {

    }
    /**
     * this method pause the screen
     */
    @Override
    public void pause() {

    }
    /**
     * this method resume the screen
     */
    @Override
    public void resume() {

    }
    /**
     * this method hides the screen
     */
    @Override
    public void hide() {

    }
    /**
     * this method disposes all the textures
     */
    @Override
    public void dispose() {
        map.dispose();
        player.dispose();
        enemy.dispose();
    }
    /**
     * this method exits the screen
     */
    private void returnScreen(){
        dispose();
        playScreen.combatExit();
    }
}
