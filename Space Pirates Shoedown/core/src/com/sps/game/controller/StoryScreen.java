package com.sps.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sps.game.screens.MenuScreen;
import java.io.IOException;
import com.sps.game.SpacePiratesShoedown;
import com.sps.game.screens.MenuScreen;


public class StoryScreen {
    private SpacePiratesShoedown game;
    public Stage stage;
    private int counter;
    private Table table;
    private TextButton prevButton, nextButton, exitButton;
    private String buttonLogged;
    public Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
    private Texture[] tutorialTexture;
    private static String ASSETS_PATH = "SP/";
    private SpriteBatch batch;
    private Image image;
    private OrthographicCamera gamecam;

    public StoryScreen(final SpacePiratesShoedown game){
        this.game = game;
        batch = new SpriteBatch();
        gamecam = new OrthographicCamera(480, 480);
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

        prevButton = new TextButton("Previous", skin, "default");
        nextButton = new TextButton("Next", skin, "default");
        exitButton = new TextButton("Exit", skin, "default");
    }

    public void formatting() {
        table = new Table();
        stage = new Stage();
        image = new Image(tutorialTexture[counter]);
        table.setSize(stage.getWidth(), stage.getHeight());
        table.align(Align.center);
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

        table.add(image);
        table.row();
        table.add(prevButton).size(150, 50).align(Align.bottomLeft);
        table.add(nextButton).size(150, 50).align(Align.bottomRight);

        stage.addActor(table);
    }

    public void handleInput() throws IOException {
        if((Gdx.input.getX() > ((tutorialTexture[counter].getWidth() / 2) - 480)) && (Gdx.input.getX() < ((tutorialTexture[counter].getWidth() / 2) + 480))){
            if((Gdx.input.getY() > ((tutorialTexture[counter].getHeight() / 2) - 480)) && (Gdx.input.getY() < ((tutorialTexture[counter].getHeight() / 2) + 250))) {
                if (Gdx.input.justTouched()) {
                    dispose();
                    game.setScreen(new MenuScreen(game));
                }
            }
        }
    }

    public void dispose() {
        tutorialTexture[counter].dispose();
    }

    public void render(float delta) {
        try {
            handleInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
        batch.begin();
        batch.draw(tutorialTexture[counter], gamecam.position.x + 50, gamecam.position.x + 120,580,480);
        batch.end();
    }

    private void imageChanger(){
        table.clearChildren();
        image = new Image(tutorialTexture[counter]);
        image.setSize(table.getWidth(), table.getHeight());

        table.add(image);
        table.row();
        table.add(prevButton).size(150, 50).align(Align.bottomLeft);
        table.add(nextButton).size(150, 50).align(Align.bottomRight);
    }

    private void clickFunction(){
        if (buttonLogged.equals("previous") && counter > 0)
        {
            imageChanger();
            counter --;
        }

        if (buttonLogged.equals("next") && counter < tutorialTexture.length)
        {
            imageChanger();
            counter ++;
        }
        buttonLogged = "";

    }
}
