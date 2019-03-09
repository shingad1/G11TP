package com.sps.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sps.game.SpacePiratesShoedown;

import java.io.IOException;

/**
 * This class sets-up the Menu Screen with the relevant images.
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia.
 * @version 1.0
 */

public class MenuScreen implements Screen {

    /**
     * Constant field to direct where the file is located.
     */
    private static final String ASSETS_PATH = "core/assets/MenuResources/";
    /**
     * Holds a version of the game.
     * @see #handleInput #render
     */
    private SpacePiratesShoedown game;

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
     * Holds the quit button texture.
     */
    private Texture quitButton;
    /**
     * Holds the load button texture.
     */
    private Texture loadButton;
    /**
     * Holds the credits button texture.
     */
    private Texture creditsButton;
    /**
     * Holds the texture of the twitterButton.
     */
    private Texture twitterButton;
    /**
     * Holds the texture of the logo.
     */
    private Texture logoViewed;
    /**
     * Holds the story button texture.
     */
    private static Texture storyTexture;
    /**
     * Holds the StoryController button texture.
     */
    private Texture tutorialTexture;

    /**
     * Music of the menu screen
     */
    private com.badlogic.gdx.audio.Music music;
    /**
     * Button on-click sound
     */
    private com.badlogic.gdx.audio.Music sound;


    public MenuScreen(SpacePiratesShoedown game){
        this.game = game;
        background = new Texture(ASSETS_PATH + "spacebackground.jpg");
        logo = new Texture(ASSETS_PATH + "transparentlogo.png");
        logoViewed = new Texture(ASSETS_PATH + "transparentlogo.png");
        playButton = new Texture(ASSETS_PATH + "PlayButton.png");
        quitButton = new Texture(ASSETS_PATH + "QuitButton.png");
        loadButton = new Texture(ASSETS_PATH + "LoadButton.png");
        creditsButton = new Texture(ASSETS_PATH + "CreditsButton.png");
        twitterButton = new Texture(ASSETS_PATH + "twitter.png");
        storyTexture = new Texture(ASSETS_PATH + "story.png");
        //tutorialTexture = new Texture(ASSETS_PATH + "StoryController.png");
        gamecam = new OrthographicCamera();
        gameport = new ScreenViewport(gamecam);

        music = Gdx.audio.newMusic(Gdx.files.internal("core/assets/Music/bensound-newdawn.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();

        sound = Gdx.audio.newMusic(Gdx.files.internal("core/assets/Music/click.wav"));
        sound.setVolume(0.1f);
    }


    @Override
    public void show() {
        
    }

    /**
     * Checks to see if the user has touched the screen and displays the play screen.
     */
    public void handleInput() throws IOException {
        if((Gdx.input.getX() > ((logo.getWidth() / 2) - 280)) && (Gdx.input.getX() < ((logo.getWidth() / 2) - 110))){
               if((Gdx.input.getY() > ((background.getHeight() / 2) - 190)) && (Gdx.input.getY() < ((logo.getHeight() / 2) + 85))) {
                   if (Gdx.input.justTouched()) {
                       sound.play();
                       dispose();
                       game.setScreen(new HomeWorldScreen(game, new Vector2(0,0),736, 1280));
                   }
               }
        }

        if((Gdx.input.getX() > ((logo.getWidth() / 2) - 110)) && (Gdx.input.getX() < ((logo.getWidth() / 2)))){
            if((Gdx.input.getY() > ((background.getHeight() / 2) - 190)) && (Gdx.input.getY() < ((logo.getHeight() / 2) + 85))) {
                if(Gdx.input.justTouched()){
                    dispose();
                    game.setScreen(new LoadScreen(game));
                }
            }
        }

        if((Gdx.input.getX() > ((logo.getWidth() / 2) - 110)) && (Gdx.input.getX() < ((logo.getWidth() / 2)))){
            if((Gdx.input.getY() > ((background.getHeight() / 2) + 10)) && (Gdx.input.getY() < ((logo.getHeight() / 2) + 285))) {
                if(Gdx.input.justTouched()){
                    dispose();
                    game.setScreen(new CreditsScreen(game));
                }
            }
        }

        if((Gdx.input.getX() > ((logo.getWidth() / 2) - 280)) && (Gdx.input.getX() < ((logo.getWidth() / 2) - 110))){
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

        /*
            if((Gdx.input.getY() > ((storyTexture.getHeight() / 2) + 220 ))&& (Gdx.input.getX() < ((storyTexture.getWidth() / 2) + 150))) {
                if (Gdx.input.justTouched()) {
                    StoryController1 dialog = new StoryController1();
                    dialog.pack();
                    dialog.setVisible(true);
                }
            }

        if((Gdx.input.getX() > Gdx.graphics.getWidth() - 100) && Gdx.input.getY() > 10 ){
            if (Gdx.input.justTouched()) {
                TutorialController dialog = new TutorialController();
                dialog.pack();
                dialog.setVisible(true);
            }
        }
        */
    }


    /**
     * Displays the different textures onto the screen.
     * @param <code>float</code> delta.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1); //for the alpha
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //actually clears the screen
        try {
            handleInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
        game.batch.begin();
        game.batch.draw(background,-((background.getWidth() - Gdx.graphics.getWidth())/2),Gdx.graphics.getHeight() - background.getHeight()); //Texture = background, x = centre of image to center of screen, y = top of image to top of screen
        game.batch.draw(logoViewed,18,Gdx.graphics.getHeight()-logo.getHeight() + 80, 600, 374); //positioned at top centre of the screen
        game.batch.draw(playButton, ((Gdx.graphics.getWidth() / 2) - (playButton.getWidth() / 2) - 120), ((Gdx.graphics.getHeight() / 2) - (playButton.getWidth() / 2) + 30));
        game.batch.draw(quitButton, ((Gdx.graphics.getWidth() / 2) - (playButton.getWidth() / 2) - 120), ((Gdx.graphics.getHeight() / 2) - (playButton.getWidth() / 2) - 160));
        game.batch.draw(loadButton, ((Gdx.graphics.getWidth() / 2) - (playButton.getWidth() / 2) + 120), ((Gdx.graphics.getHeight() / 2) - (playButton.getWidth() / 2) + 30));
        game.batch.draw(creditsButton, ((Gdx.graphics.getWidth() / 2) - (playButton.getWidth() / 2) + 120), ((Gdx.graphics.getHeight() / 2) - (playButton.getWidth() / 2) - 160));
        game.batch.draw(twitterButton, ((Gdx.graphics.getWidth() / 2) - (twitterButton.getWidth() / 2)), ((Gdx.graphics.getHeight() / 2) - (playButton.getWidth() / 2) - 210));
        //game.batch.draw(storyTexture, 0,0);
        //game.batch.draw(tutorialTexture, (Gdx.graphics.getWidth()) - 100, 0);
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
        loadButton.dispose();
        creditsButton.dispose();
        twitterButton.dispose();
        background.dispose();
        music.dispose();
    }
}