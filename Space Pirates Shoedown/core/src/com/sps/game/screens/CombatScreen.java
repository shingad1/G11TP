package com.sps.game.screens;

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
import com.sps.game.controller.CombatController;
import com.sps.game.controller.CombatSystem;
import com.sps.game.scenes.CombatHud;
import com.sps.game.scenes.EnemyHud;
import com.sps.game.scenes.ThirdHud;
import com.sps.game.SpacePiratesShoedown;
import com.sps.game.sprites.AbstractEnemy;
import com.sps.game.sprites.BasicEnemy;
import com.sps.game.sprites.Player;

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
    /**
     * Holds the player information during the combat.
     * @see #update #render
     */
    private CombatHud playerHud;
    /**
     * holds the playerhud
     * @see #render(float)
     */
    private EnemyHud enemyHud;
    /**
     * holds the Enemyhud
     * @see #render(float) #update(float)
     */
    private ThirdHud thirdHud;
    /**
     * holds the thirdHud
     * @see #render(float) #update(float)
     */
    private AbstractEnemy enemy;
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
    /**
     * Holds the play screen
     */

    public CombatScreen(SpacePiratesShoedown game, Player p, AbstractEnemy e, PlayScreen playScreen, TiledMap map) {
        this.game = game;
        this.enemy = e;

        this.map = map;
        renderer = new OrthogonalTiledMapRenderer(this.map);
        gamecam = new OrthographicCamera(480, 480);
        gamecam.position.set(p.getX(),p.getY(),0);
        gameport = new FitViewport(1600, 1600, gamecam);
        batch = new SpriteBatch();
        playerHud = new CombatHud(batch,p,e);
        enemyHud = new EnemyHud(batch,e);
        thirdHud = new ThirdHud(batch);
        cs = new CombatSystem(p, e, batch);
        //enemy.setCombatSystem(cs);
        combatController = new CombatController(p, e, cs);
        this.playScreen = playScreen;
    }
    /**
     * Sets which controller is being used to handle user input
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(combatController);
    }
    /**
     * Updates the screen according to user input and the state of the combat.
     * @param <code>float</code> dt
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
        thirdHud.update(cs.getOptions());
        //thirdHud.update(); //needs to have an array as a parameter
    }
    /**
     * Clears the screen and draws the necessary textures.
     * @param <code>float</code>delta
     */
    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();
        enemyHud.stage.draw();
        playerHud.stage.draw();
        thirdHud.stage.draw();
        batch.setProjectionMatrix(gamecam.combined);
        cs.render();
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
     * Disposes the images so less memory is used.
     */
    @Override
    public void dispose() {
        map.dispose();
    }
    /**
     * Clears the combat screen and returns to the state the play screen was left in.
     */
    private void returnScreen(){
        dispose();
        playScreen.combatExit();
    }
}
