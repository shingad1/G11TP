package com.sps.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sps.game.Controller.PlayerController;
import com.sps.game.Inventory2.Inventory;
import com.sps.game.Sprites.Player;

import java.util.ArrayList;

public class InventoryHud {

    private Player player;
    public Stage stage;
    private Viewport viewport;
    Label inventoryLabel;
    private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
    private List<String> inventory = new List<String>(skin);
    private List<String> merchant = new List(skin);
    private PlayerController playerController;
    private InputMultiplexer multiplexer = new InputMultiplexer();


    public InventoryHud(SpriteBatch sb, PlayerController playerController) {

        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);
        inventoryLabel = new Label("inventory", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        player = Player.getPlayer();
        this.playerController = playerController;

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
        Gdx.input.setInputProcessor(stage);
        InputProcessor processor = Gdx.input.getInputProcessor();
        multiplexer.addProcessor(processor);
    }

    public void update() {
        int count = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.I)) {
            formatting();
            show();
            count ++;
        }

        /* If the count value is uneven then it will dispose it - only carried out
            when pressing I will remove the inventory.
         */
/*
        if ((count % 2 == 0) && (Gdx.input.isKeyPressed(Input.Keys.I))) {
            dispose();
        }
*/
        if (Gdx.input.isKeyPressed(Input.Keys.O)) {
            //Gdx.input.setInputProcessor(playerController);
            stage.clear();
            multiplexer.removeProcessor(Gdx.input.getInputProcessor());
        }
    }

    public void dispose() {
        stage.dispose();
    }

}
