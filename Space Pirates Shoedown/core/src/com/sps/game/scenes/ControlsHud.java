package com.sps.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sps.game.Utility;
import com.sps.game.controller.PlayerController;

/**
 * This class displays the controls to the user.
 * @author Miraj Shah, Devin Shingadia
 * @version 1.0
 */
public class ControlsHud {

    /**
     * Holds the stage that will displayed to the user.
     */
    public Stage stage;

    private SpriteBatch sb;

    private Viewport viewport;

    private Skin skin = new Skin(Gdx.files.internal("pixthulhuui/pixthulhu-ui.json"));

    private InputProcessor oldInput;

    private Texture controlsTexture;

    private Table table;

    private Image image;

    public ControlsHud(SpriteBatch sb){
        this.sb = sb;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport,sb);
        controlsTexture = new Texture(Gdx.files.internal("MenuResources/Controls1.png"));

    }

    public void formatting(){
        table = new Table();
        stage = new Stage();
        image = new Image(controlsTexture);
        table.setDebug(true);
        table.defaults();
        table.center();
        table.setFillParent(true);
        table.add(image);
        table.row();
        stage.addActor(table);
    }


    public void setInput(){
        oldInput = Gdx.input.getInputProcessor();
        Gdx.input.setInputProcessor(stage);
    }

    public void update(){
        if(Gdx.input.isKeyPressed(Input.Keys.C) && oldInput == null){
            formatting();
            setInput();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.V) && oldInput != null){
            stage.dispose();
            table.clearChildren();
            table.reset();
            Gdx.input.setInputProcessor(oldInput);
            oldInput = null;
        }
    }

}
