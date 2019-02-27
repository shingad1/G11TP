package com.sps.game.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class pauseController {

    public Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
    public Stage stage = new Stage(new ScreenViewport());
    private Window pause;
    private TextButton continueButton;

    public pauseController(){
        pause = new Window("Pause", skin);
        continueButton = new TextButton("Continue", skin);
    }

    public void create(){
        continueButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                Gdx.app.log("continue button", "confirm");
                pause.setVisible(false);
                event.stop();
            }
        });

        pause.add(continueButton);
        //pause.setSize(stage.getWidth()/ 1.5f, stage.getHeight()/ 1.5f);
        pause.setPosition(stage.getWidth()/2 - pause.getWidth()/2, stage.getHeight()/2 - pause.getHeight());
        pause.setKeepWithinStage(false);
        pause.setMovable(false);

        stage.addActor(pause);
        Gdx.input.setInputProcessor(stage);
    }

    public void render()
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }
}
