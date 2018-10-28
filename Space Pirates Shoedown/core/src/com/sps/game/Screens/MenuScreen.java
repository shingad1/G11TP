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

public class MenuScreen implements Screen {

    private static final String ASSETS_PATH = "core/assets/";
    private SpacePiratesShoedown game;
    private Texture background;
    private Texture logo;
    private Texture playButton;
    private OrthographicCamera gamecam; //what the view port displays
    private Viewport gameport;
    private HudScene hud; //Adding a new hud, when a playscreen is created move this to there

    public MenuScreen(SpacePiratesShoedown game){
        this.game = game;
        background = new Texture(ASSETS_PATH + "spacebackground.jpg");
        logo = new Texture(ASSETS_PATH + "transparentlogo.png");
        playButton = new Texture(ASSETS_PATH + "playButton.png");
        gamecam = new OrthographicCamera();
        gameport = new ScreenViewport(gamecam);
        hud = new HudScene(game.batch); //instantiating the new hud
    }

    @Override
    public void show() {
        
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1); //for the alpha
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //actually clears the screen
        //game.batch.setProjectionMatrix(gamecam.combined);//render only what the camera can see
        game.batch.begin();
        game.batch.draw(background,-((background.getWidth() - Gdx.graphics.getWidth())/2),Gdx.graphics.getHeight() - background.getHeight()); //Texture = background, x = centre of image to center of screen, y = top of image to top of screen
        game.batch.draw(logo,0,Gdx.graphics.getHeight()-logo.getHeight(), 640, 399); //positioned at top centre of the screen
        game.batch.draw(playButton, ((Gdx.graphics.getWidth() / 2) - (playButton.getWidth() /2)),((Gdx.graphics.getHeight() / 2) - (playButton.getWidth() / 2)));
        game.batch.end();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined); //setting the display what the hdu should see
        hud.stage.draw(); //actually drawing the graphics
    }

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

    @Override
    public void dispose() {

    }
}
