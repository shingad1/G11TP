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
     */
    private Game game;
    /**
     * Renders the texture resources
     */
    private SpriteBatch batch;

    /**
     * Constant field to direct where the file is located.
     */
    private static final String ASSETS_PATH = "core/assets/tiledAssets/";
    /**
     *Displays what the user will see
     */
    private Viewport gameport;
    /**
     * Holds the texture showing the player.
     */
    private Texture player;
    /**
     * Holds the texture showing the enemy.
     */
    private Texture enemy;
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
     * @see
     */
    private OrthogonalTiledMapRenderer renderer;
    /**
     * Holds what the view port will display.
     * @see
     */
    private OrthographicCamera gamecam;

    private CombatHud playerHud;

    private EnemyHud enemyHud;

    private ThirdHud ThirdHud;

    private BasicEnemy Enemy;

    private CombatController combatController;

    private int tick;

    public CombatScreen(SpacePiratesShoedown game, Player p, BasicEnemy e) {
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
        combatController = new CombatController(p, e);
        tick = 0;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(combatController);
    }

    public void update(float dt){
        gamecam.update();
        renderer.setView(gamecam);
        playerHud.update();
        enemyHud.update();
        ThirdHud.update();
    }

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
