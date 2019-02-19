package com.sps.game.Controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class StoryController extends ApplicationAdapter implements InputProcessor {

    private Stage stage;
    private Skin skin;

    private Table table;
    private TextButton prevButton, nextButton;

    private String buttonLogged = "";

    private Texture[] tutorialTexture;
    private int counter;

    private static String ASSETS_PATH = "core/assets/SP/";

    private SpriteBatch batch;
    private Sprite sprite;

    public StoryController()
    {
        counter = 0;
        tutorialTexture = new Texture[13];

        tutorialTexture[0] = new Texture(ASSETS_PATH + "Slide1.png");
        tutorialTexture[1] = new Texture(ASSETS_PATH + "Slide2.png");
        tutorialTexture[2] = new Texture(ASSETS_PATH + "Slide3.png");
        tutorialTexture[3] = new Texture(ASSETS_PATH + "Slide4.png");
        tutorialTexture[4] = new Texture(ASSETS_PATH + "Slide5.png");
        tutorialTexture[5] = new Texture(ASSETS_PATH + "Slide6.png");
        tutorialTexture[6] = new Texture(ASSETS_PATH + "Slide7.png");
        tutorialTexture[7] = new Texture(ASSETS_PATH + "Slide8.png");
        tutorialTexture[8] = new Texture(ASSETS_PATH + "Slide9.png");
        tutorialTexture[9] = new Texture(ASSETS_PATH + "Slide10.png");
        tutorialTexture[10] = new Texture(ASSETS_PATH + "Slide11.png");
        tutorialTexture[11] = new Texture(ASSETS_PATH + "Slide12.png");
        tutorialTexture[12] = new Texture(ASSETS_PATH + "Slide13.png");

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(new ScreenViewport());

        batch = new SpriteBatch();
        sprite = new Sprite((tutorialTexture[counter]));
    }

    public void create()
    {
        table = new Table();
        table.setWidth(stage.getWidth());
        table.setHeight(stage.getHeight());
        table.align(Align.center | Align.bottom);
        table.padBottom(30);

        table.setPosition(0,0);

        prevButton = new TextButton("Previous", skin, "default");
        nextButton = new TextButton("Next", skin, "default");

        prevButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               buttonLogged = "previous";
               imageController();
                event.stop();
            }
        });

        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               buttonLogged = "next";
               imageController();
                event.stop();
            }
        });

        table.add(prevButton).size(150,50).align(Align.bottomLeft).padRight(30);
        table.add(nextButton).size(150, 50).align(Align.bottomRight);

        stage.addActor(table);

        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage, this);
        Gdx.input.setInputProcessor(inputMultiplexer);
        sprite.setSize(stage.getWidth(), stage.getHeight());
    }

    @Override
    public void render()
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        sprite.draw(batch);
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    private void imageController(){
        if (buttonLogged.equals("previous") && counter > 0)
        {
            counter --;
            sprite = new Sprite((tutorialTexture[counter]));
        }

        if (buttonLogged.equals("next") && counter < tutorialTexture.length - 1)
        {
            counter ++;
            sprite = new Sprite((tutorialTexture[counter]));

        }
        buttonLogged = "";
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
