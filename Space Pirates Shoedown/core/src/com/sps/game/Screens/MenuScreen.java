package com.sps.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sps.game.Scenes.HudScene;
import com.sps.game.SpacePiratesShoedown;

/**
 * This class sets-up the Menu Screen with the relevant images.
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia.
 * @version 1.0
 */

public class MenuScreen implements Screen {

    /**
     * Constant field to direct where the file is located.
     */
    private static final String ASSETS_PATH = "core/assets/";
    /**
     * Holds a version of the game.
     * @see #handleInput #render
     */
    private SpacePiratesShoedown game;
    /**
     * Holds background image for the Menu Screen.
     * @see #render #dispose
     */
    private Texture background;
    /**
     * Holds the logo of the game, for the Menu Screen.
     * @see #render #dispose
     */
    private Texture logo;
    /**
     * Holds the play button, to allow the user transition to the play screen.
     * @see #render #dispose
     */
    private Texture playButton;
    /**
     * Holds what the view port will display
     */
    private OrthographicCamera gamecam;
    /**
     * Displays what the user will see
     * @see #resize
     */
    private Viewport gameport;
    /**
     * The playbutton that is shown if the user hovers over it.
     */
    private Texture activatedPlayButton;

    public MenuScreen(SpacePiratesShoedown game){
        this.game = game;
        background = new Texture(ASSETS_PATH + "spacebackground.jpg");
        logo = new Texture(ASSETS_PATH + "transparentlogo.png");
        playButton = new Texture(ASSETS_PATH + "playButton.png");
        activatedPlayButton = new Texture(ASSETS_PATH + "activatedPlayButton.png");
        gamecam = new OrthographicCamera();
        gameport = new ScreenViewport(gamecam);
    }

    @Override
    public void show() {
        
    }

    /**
     * Checks to see if the user has touched the screen and displays the play screen.
     */
    public void handleInput(){
        if (Gdx.input.justTouched()) {
            game.setScreen(new PlayScreen(game));
            dispose();
        }
    }

    /**
     * Displays the different textures onto the screen.
     * @param <code>float</code> delta.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1); //for the alpha
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //actually clears the screen
        handleInput();
        game.batch.begin();
        game.batch.draw(background,-((background.getWidth() - Gdx.graphics.getWidth())/2),Gdx.graphics.getHeight() - background.getHeight()); //Texture = background, x = centre of image to center of screen, y = top of image to top of screen
        game.batch.draw(logo,0,Gdx.graphics.getHeight()-logo.getHeight(), 640, 399); //positioned at top centre of the screen

        //Checks to see if Mouse pointer is same as PlayButton Location. If so, it will draw activatedPlayButton
        int x = Gdx.graphics.getWidth() / 2 - playButton.getWidth();
        int y = Gdx.graphics.getHeight() / 2 - playButton.getHeight();

        if ((Gdx.input.getX() < x + playButton.getWidth() && Gdx.input.getX() > x) && (Gdx.input.getY() < y + playButton.getHeight() && Gdx.input.getY() > y)) {
            game.batch.draw(activatedPlayButton, ((Gdx.graphics.getWidth() / 2) - (activatedPlayButton.getWidth() / 2)), ((Gdx.graphics.getHeight() / 2) - (activatedPlayButton.getWidth() / 2)));
        } else {
            game.batch.draw(playButton, ((Gdx.graphics.getWidth() / 2) - (playButton.getWidth() / 2)), ((Gdx.graphics.getHeight() / 2) - (playButton.getWidth() / 2)));
        }
        game.batch.end();
    }

    /**
     * Changes the size of the ViewPort.
     * @param <code>width</code>
     * @param <code>height</code>
     */
    @Override
    public void resize(int width, int height) {
        gameport.update(width, height);
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
        logo.dispose();
        playButton.dispose();
        activatedPlayButton.dispose();
        background.dispose();
    }
}
