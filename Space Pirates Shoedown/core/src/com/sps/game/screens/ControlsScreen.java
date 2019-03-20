package com.sps.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sps.game.SpacePiratesShoedown;
import com.sps.game.Utility;

import java.io.IOException;

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

    public ControlsScreen(final SpacePiratesShoedown game){
        this.game = game;
        batch = new SpriteBatch();
        gamecam = new OrthographicCamera(480,480);
        controlsTexture = new Texture(Gdx.files.internal("MenuResources/Controls1.png"));
    }

    public void handleInput() throws IOException{
        if((Gdx.input.getX() > ((controlsTexture.getWidth() / 2) - 480)) && (Gdx.input.getX() < ((controlsTexture.getWidth() / 2) + 480))){
            if((Gdx.input.getY() > ((controlsTexture.getHeight() / 2) - 480)) && (Gdx.input.getY() < ((controlsTexture.getHeight() / 2) + 250))) {
                if (Gdx.input.justTouched()) {
                    dispose();
                    game.setScreen(new MenuScreen(game));
                }
            }
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        try {
            handleInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
        batch.begin();
        batch.draw(controlsTexture, gamecam.position.x + 50, gamecam.position.x + 120,580,480);
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
