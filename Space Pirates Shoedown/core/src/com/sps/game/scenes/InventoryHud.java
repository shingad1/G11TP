package com.sps.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sps.game.controller.PlayerController;

public class InventoryHud {

    public Stage stage;
    private Viewport viewport;
    Label inventoryLabel;
    private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
    private List<String> inventory = new List<String>(skin);
    private List<String> merchant = new List(skin);

    private InputProcessor oldInput;


    public InventoryHud(SpriteBatch sb, PlayerController playerController) {

        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);
        inventoryLabel = new Label("inventory", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        //In the future, change this to actual instances of class item.
        inventory.setItems("Axe",
                           "Fuel",
                           "Helmet",
                           "Flux Capacitor",
                           "Shoes",
                           "Hamster",
                           "Hammer",
                           "Pirates Eye",
                           "Cucumber");


        merchant.setItems("Shoe Laces",
                          "Sword"
                         );
    }

    private void formatting() {
        stage = new Stage();
        Table table = new Table(skin);
        table.center();
        table.setFillParent(true);
        table.add("Player").row();
        table.add("Merchant").padLeft(150).row();
        table.add(inventory);
        table.add(merchant);

        stage.addActor(table);
    }


    public void show() {
        oldInput = Gdx.input.getInputProcessor();
        Gdx.input.setInputProcessor(stage);
    }

    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.I) && oldInput == null) {
            formatting();
            show();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.O) && oldInput != null) {
            stage.clear();
            Gdx.input.setInputProcessor(oldInput);
            oldInput = null;
        }
    }

    public void dispose() {
        stage.dispose();
    }

}
