package com.sps.game.scenes;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sps.game.controller.PlayerController;

public class TutorialHud {
    public Stage stage;
    private SpriteBatch batch;
    private Sprite sprite;
    private InputProcessor oldInput;
    private OrthographicCamera gamecam;
    private Texture texture;

    public TutorialHud(OrthographicCamera gamecam) {
        this.gamecam = gamecam;
        batch = new SpriteBatch();
        sprite = new Sprite(new Texture(Gdx.files.internal("core/assets/MenuResources/Controls1.png")));
        texture = new Texture(Gdx.files.internal("core/assets/MenuResources/Controls1.png"));
    }

    public void show(){
        batch.begin();
        //batch.draw(sprite, gamecam.position.x - 240,gamecam.position.y - 240,480,480);
        sprite.draw(batch);
        batch.end();

        oldInput = Gdx.input.getInputProcessor();
        Gdx.input.setInputProcessor(stage);
    }

    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.T) && oldInput == null) {
            show();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.Y) && oldInput != null) {
            batch.dispose();
            Gdx.input.setInputProcessor(oldInput);
            oldInput = null;
        }
    }
}
