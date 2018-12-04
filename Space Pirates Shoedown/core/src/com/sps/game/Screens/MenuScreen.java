package com.sps.game.Screens;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
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
     * Holds the quit button texture.
     */
    private Texture quitButton;
    /**
     * Holds the texture of the twitterButton.
     */
    private Texture twitterButton;
    /**
     * Holds the texture of the logo.
     */
    private Texture logoViewed;

    private com.badlogic.gdx.audio.Music music;

    private com.badlogic.gdx.audio.Music sound;

    public MenuScreen(SpacePiratesShoedown game){
        this.game = game;
        background = new Texture(ASSETS_PATH + "spacebackground.jpg");
        logo = new Texture(ASSETS_PATH + "transparentlogo.png");
        logoViewed = new Texture(ASSETS_PATH + "transparentlogo.png");
        playButton = new Texture(ASSETS_PATH + "PlayButton.png");
        quitButton = new Texture(ASSETS_PATH + "QuitButton.png");
        twitterButton = new Texture(ASSETS_PATH + "twitter.png");
        gamecam = new OrthographicCamera();
        gameport = new ScreenViewport(gamecam);
        music = Gdx.audio.newMusic(Gdx.files.internal(ASSETS_PATH + "Music/bensound-newdawn.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();

        sound = Gdx.audio.newMusic(Gdx.files.internal(ASSETS_PATH + "Music/click.wav"));
        sound.setVolume(0.1f);
    }


    @Override
    public void show() {
        
    }

    /**
     * Checks to see if the user has touched the screen and displays the play screen.
     */
    public void handleInput(){
        if((Gdx.input.getX() > ((logo.getWidth() / 2) - 160)) && (Gdx.input.getX() < ((logo.getWidth() / 2) + 10))){
               if((Gdx.input.getY() > ((background.getHeight() / 2) - 190)) && (Gdx.input.getY() < ((logo.getHeight() / 2) + 85))) {
                   if (Gdx.input.justTouched()) {
                       sound.play();
                       dispose();
                       game.setScreen(new PlayScreen(game));
                   }
               }
        }

        if((Gdx.input.getX() > ((logo.getWidth() / 2) - 160)) && (Gdx.input.getX() < ((logo.getWidth() / 2) + 10))){
            if((Gdx.input.getY() > ((background.getHeight() / 2) + 10)) && (Gdx.input.getY() < ((logo.getHeight() / 2) + 285))) {
                if (Gdx.input.justTouched()) {
                    sound.play();
                    Gdx.app.exit();
                }
            }
        }

        if((Gdx.input.getX() > ((logo.getWidth() / 2) - 210)) && (Gdx.input.getX() < ((logo.getWidth() / 2) + 150))){
            if((Gdx.input.getY() > ((background.getHeight() / 2) + 220))) {
                if (Gdx.input.justTouched()) {
                    Gdx.net.openURI("https://twitter.com/SpacePirateSD");
                }
            }
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
        game.batch.draw(logoViewed,18,Gdx.graphics.getHeight()-logo.getHeight() + 80, 600, 374); //positioned at top centre of the screen
        game.batch.draw(playButton, ((Gdx.graphics.getWidth() / 2) - (playButton.getWidth() / 2)), ((Gdx.graphics.getHeight() / 2) - (playButton.getWidth() / 2) + 30));
        game.batch.draw(quitButton, ((Gdx.graphics.getWidth() / 2) - (playButton.getWidth() / 2)), ((Gdx.graphics.getHeight() / 2) - (playButton.getWidth() / 2) - 160));
        game.batch.draw(twitterButton, ((Gdx.graphics.getWidth() / 2) - (twitterButton.getWidth() / 2)), ((Gdx.graphics.getHeight() / 2) - (playButton.getWidth() / 2) - 210));
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
        quitButton.dispose();
        twitterButton.dispose();
        background.dispose();
        music.dispose();
    }
}
