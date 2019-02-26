package com.sps.game.Scenes;

import com.badlogic.gdx.Gdx;
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

    public InventoryHud(SpriteBatch sb, Player p) {
        //final Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

//        stage.setDebugAll(true);
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);
        inventoryLabel = new Label("inventory", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        player = p;

        inventory.setItems("Axe", "Fuel", "Helmet", "Flux Capacitor", "Shoes", "Hamster", "Hammer", "Pirates Eye", "Cucumber");
    }

    private void formatting() {
        stage = new Stage();
        Table table = new Table(skin);
        table.center();
        table.setFillParent(true);
        table.add("Player").row();
        table.add(inventory);
        stage.addActor(table);
    }

    public void update() {
        formatting();
    }

    public void dispose() {
        stage.dispose();
    }
}