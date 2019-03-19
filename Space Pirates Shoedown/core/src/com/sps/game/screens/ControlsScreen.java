package com.sps.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.sps.game.SpacePiratesShoedown;

/**
 * This class displays the controls of the game to the user.
 * @author Miraj Shah
 * @version 1.0
 */
public class ControlsScreen implements Screen {
    /**
     * Holds the stage that will hold the back button.
     */
    private Stage stage;
    /**
     * Holds an instance of the game.
     */
    private SpacePiratesShoedown game;
    /**
     * Holds the texture showing the controls
     */
    private Texture controlsTexture;
    /**
     * Holds everything that will be displayed to the user.
     */
    private SpriteBatch batch;

    private OrthographicCamera gamecam;

    public ControlsScreen(SpacePiratesShoedown game){
        this.game = game;
        stage = new Stage();
        batch = new SpriteBatch();
        gamecam = new OrthographicCamera(480,480);

        Gdx.input.setInputProcessor(stage);
        controlsTexture = new Texture(Gdx.files.internal("core/assets/MenuResources/Controls1.png"));
        Table table = new Table();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(controlsTexture, gamecam.position.x, gamecam.position.x,1000,582);
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
        controlsTexture.dispose();
    }
}
