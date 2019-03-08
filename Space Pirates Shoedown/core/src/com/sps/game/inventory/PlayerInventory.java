package com.sps.game.inventory;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sps.game.controller.PlayerController;

public class PlayerInventory {
    public Stage stage;
    public SpriteBatch sb;
    private Viewport viewport;

    private Skin skin = new Skin(Gdx.files.internal("core/assets/pixthulhuui/pixthulhu-ui.json"));

    private List<String> inventory;
    private List<Image> itemImages;

    private InventoryController inventoryController;
    private InputProcessor oldInput;


    public PlayerInventory(SpriteBatch sb, PlayerController playerController) {
        this.sb = sb;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);

        inventoryController = new InventoryController();
        inventory = inventoryController.getInventoryList();
        itemImages = inventoryController.getImageList();
    }

    private void formatting() {
        stage = new Stage();
        Label inventoryLabel = new Label("Inventory", skin);
        Label imageLabel = new Label("Item", skin);

        Table table = new Table(skin);
        table.setDebug(true);
        table.defaults();
        table.center();
        table.setFillParent(true);
        table.add(inventoryLabel);
        table.add(imageLabel);
        table.row();
        table.add(inventory);
        table.add(itemImages.getSelected());


        stage.addActor(itemImages);
        stage.addActor(table);
    }
    public void setInput() {
        oldInput = Gdx.input.getInputProcessor(); //Get the old input from the user.
        Gdx.input.setInputProcessor(stage);       //Set the input to now work on the inventory.
    }

    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.I) && oldInput == null) {
            formatting();
            setInput();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.O) && oldInput != null) {
            stage.dispose();
            Gdx.input.setInputProcessor(oldInput);
            oldInput = null;
        }
    }

    public void dispose() {
        stage.dispose();
    }

}
