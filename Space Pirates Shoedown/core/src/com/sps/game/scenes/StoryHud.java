package com.sps.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sps.game.controller.PlayerController;

public class StoryHud {
    private FitViewport viewport;
    public Stage stage;
    private int counter;
    private Table table;
    private TextButton prevButton, nextButton, exitButton;
    private String buttonLogged;
    public Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
    private Texture[] tutorialTexture;
    private static String ASSETS_PATH = "core/assets/SP/";
    private SpriteBatch batch;
    private Sprite sprite;
    private InputProcessor oldInput;

    public StoryHud(SpriteBatch sb){
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);
        counter = 0;
        buttonLogged = "";

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

        batch = new SpriteBatch();

        prevButton = new TextButton("Previous", skin, "default");
        nextButton = new TextButton("Next", skin, "default");
        exitButton = new TextButton("Exit", skin, "default");
    }

    public void formatting() {
        stage = new Stage();
        table = new Table(skin);
        table.setWidth(stage.getWidth());
        table.setHeight(stage.getHeight());
        table.align(Align.center | Align.bottom);
        table.padBottom(10);
        table.setFillParent(true);

        table.setPosition(0, 0);

        prevButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                buttonLogged = "previous";
                clickFunction();
                event.stop();
            }
        });

        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                buttonLogged = "next";
                clickFunction();
                event.stop();
            }
        });

        table.add(prevButton).size(150, 50).align(Align.bottomLeft);
        table.add(nextButton).size(150, 50).align(Align.bottomRight);

        stage.addActor(table);

        //batch = new SpriteBatch();
        //sprite = new Sprite(tutorialTexture[counter]);

        Gdx.input.setInputProcessor(stage);
    }

    public void render(){
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //batch = new SpriteBatch();
        sprite = new Sprite(tutorialTexture[counter]);

        batch.begin();
        sprite.draw(batch);
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

    }

    public void show() {
        render();

        oldInput = Gdx.input.getInputProcessor();
        Gdx.input.setInputProcessor(stage);
    }

    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.N) && oldInput == null) {
            formatting();
            show();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.M) && oldInput != null) {
            stage.clear();
            dispose();
            counter = 0;
            Gdx.input.setInputProcessor(oldInput);
            oldInput = null;
        }
    }

    public void dispose(){
        stage.dispose();
    }

    private void clickFunction(){
        if (buttonLogged.equals("previous") && counter > 0)
        {
            sprite = new Sprite(tutorialTexture[counter]);
            counter --;
        }

        if (buttonLogged.equals("next") && counter < tutorialTexture.length - 1)
        {
            sprite = new Sprite(tutorialTexture[counter]);
            counter ++;
        }
        buttonLogged = "";

    }
}
