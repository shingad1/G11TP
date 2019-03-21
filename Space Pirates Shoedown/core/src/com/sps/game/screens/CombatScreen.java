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
import com.sps.game.maps.MapFactory;
import com.sps.game.scenes.CombatHud;
import com.sps.game.scenes.EnemyHud;
import com.sps.game.scenes.ThirdHud;
import com.sps.game.SpacePiratesShoedown;
import com.sps.game.scenes.WinHud;
import com.sps.game.sprites.*;

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
    private SpacePiratesShoedown game;
    /**
     * Renders the texture resources
     * @see #CombatScreen
     */
    private SpriteBatch batch;

    /**
     * Constant field to direct where the file is located.
     * @see #CombatScreen
     */
    private static final String ASSETS_PATH = "tiledAssets/";
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
     *
     */
    private WinHud winHud;
    /**
     * holds the playerhud
     * @see #render(float)
     */
    private CombatHud playerHud;
    /**
     * holds the Enemyhud
     * @see #render(float) #update(float)
     */
    private EnemyHud enemyHud;
    /**
     * holds the thirdHud
     * @see #render(float) #update(float)
     */
    private ThirdHud thirdHud;
    /**
     * holds the enemy
     * @see #render(float) #update(float)
     */
    private AbstractEnemy enemy;
    /**
     * Holds the CombatController
     * @see #CombatScreen
     */
    private CombatController combatController;
    /**
     * Holds the CombatSystem
     * @see #CombatScreen
     */
    private CombatSystem cs;
    /**
     * Holds integer variable which has the information for the tick
     * @see #CombatScreen
     */
    private int tick;
    /**
     * Holds the play screen
     */
    private PlayScreen playScreen;

    private boolean playerDied;

    public CombatScreen(SpacePiratesShoedown game, SpriteBatch sb, Player p, AbstractEnemy e, PlayScreen playScreen, TiledMap map, Location pp, Location ep) {
        this.game = game;
        this.enemy = e;

        this.map = map;
        renderer = new OrthogonalTiledMapRenderer(this.map);
        gamecam = new OrthographicCamera(480, 480);
        gamecam.position.set((pp.getX() + ep.getX()) / 2, (pp.getY() + ep.getY()) /2,0);
        gameport = new FitViewport(1600, 1600, gamecam);
        this.batch = sb;
        playerHud = new CombatHud(batch,p,e);
        enemyHud = new EnemyHud(batch,e);
        thirdHud = new ThirdHud(batch);
        winHud = new WinHud(batch);
        cs = new CombatSystem(p, e, batch, pp, ep);
        combatController = new CombatController(p, e, cs);
        this.playScreen = playScreen;
        playerDied = false;
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
            if(!cs.didPlayerDie()) {
                winHud.update();
            } else {
                game.setScreen(new GameOverScreen(game, false));
            }
            returnScreen();
        }
        gamecam.update();
        renderer.setView(gamecam);
        cs.update();
        playerDied = cs.didPlayerDie();
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
        winHud.stage.draw();
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
    private void returnScreen() {
        dispose();
        if (winHud.getFinished()) {
            if(enemy instanceof HeadEnemy) {
                if (PlayScreen.oldState.equals(MapFactory.MapType.HomeWorldMap2)) {
                    PlayScreen.flags[0] = true;
                } else if (PlayScreen.oldState.equals(MapFactory.MapType.CandyWorld2)) {
                    PlayScreen.flags[1] = true;
                } else if (PlayScreen.oldState.equals(MapFactory.MapType.TropicalWorld2)) {
                    PlayScreen.flags[2] = true;
                } else if (PlayScreen.currentMapState.equals(MapFactory.MapType.GraveyardWorld1)) {
                    dispose();
                    game.setScreen(new CreditsScreen(game));
                }
            }
            playScreen.combatExit();
            winHud.setFinished(false);
        }
    }
}
