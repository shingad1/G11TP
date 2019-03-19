package com.sps.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.sps.game.SpacePiratesShoedown;
import com.sps.game.Utility;

/**
 * This class creates the game over screen and displays the appropriate error message according to the state of the player
 * @author Miraj Shah
 * @version 1.0
 */
public class GameOverScreen implements Screen {
    /**
     * Holds a string stating that at the game is over.
     */
    private static final String GAME_OVER = "Game Over";
    /**
     * Holds a message that will be displayed if the players health is 0.
     */
    private static final String DEATH_MESSAGE = "You have been beaten by the Space Pirates, and you will never get your shoes back!";
    /**
     * Holds a message that will de diplayed if the player defeats the final boss.
     */
    private static final String WIN_MESSAGE = "Congratulations you have beaten the Space Pirates, and you've get everyone's shoes. Well Done!";
    /**
     * Holds the stage that will be displayed, containing all the information to be displayed.
     */
    private Stage stage;
    /**
     * Holds an instance of the game.
     */
    private SpacePiratesShoedown game;

    public GameOverScreen(final SpacePiratesShoedown game, boolean isAlive){
        this.game = game;

        stage = new Stage();
        Label gameOverLabel = new Label(GAME_OVER, Utility.STATUSUI_SKIN);
        gameOverLabel.setAlignment(Align.center);
        Label message;
        TextButton continueButton = new TextButton("Continue", Utility.STATUSUI_SKIN);
        Table table = new Table();

        if(isAlive){
            message = new Label(WIN_MESSAGE, Utility.STATUSUI_SKIN);
        } else{
            message = new Label(DEATH_MESSAGE, Utility.STATUSUI_SKIN);
        }

        table.setFillParent(true);
        table.add(gameOverLabel);
        table.add(message).pad(50,50,50,50).expandX().fillX().row();
        table.row();
        table.add(continueButton).pad(50,50,50,50);
        stage.addActor(table);

        continueButton.addListener(new ClickListener(){
                                        @Override
                                        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                                            return true;
                                        }
                                        @Override
                                        public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                                            game.setScreen(new MenuScreen(game));//or the credits screen
                                        }
                                    });
    }

    /**
     * Shows the stage and sets the input processor
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Renders the stage and all its contents to the user.
     * @param float delta
     */
    @Override
    public void render(float delta) {
        if(delta == 0){
            return;
        }
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
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

    /**
     * Resets the processor.
     */
    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    /**
     * Clears the stage and disposes of all its elements.
     */
    @Override
    public void dispose() {
        stage.clear();
        stage.dispose();
    }
}
