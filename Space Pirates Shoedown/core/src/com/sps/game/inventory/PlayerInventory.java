package com.sps.game.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sps.game.controller.InventoryController;
import com.sps.game.controller.PlayerController;

public class PlayerInventory {
    public Stage stage;
    public SpriteBatch sb;
    private Viewport viewport;

    private Skin skin = new Skin(Gdx.files.internal("core/assets/pixthulhuui/pixthulhu-ui.json"));

    private List<String> inventory;

    private InventoryController inventoryController;
    private InputProcessor oldInput;
    private Table table;

    private Item clickedItem;
    private Image clickedImage;
    private Image imagePlaceholder = new Image();
    private Label descriptionPlaceholder = new Label("Pick an item", skin);



    public PlayerInventory(SpriteBatch sb, PlayerController playerController) {
        this.sb = sb;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);
        inventoryController = new InventoryController();
        inventory = inventoryController.getInventoryList();
    }


    private void formatting() {
        table = new Table();
        stage = new Stage();
        Label inventoryLabel = new Label("Inventory", skin);
        Label imageLabel = new Label("Item", skin);

        table.setDebug(true);
        table.defaults();
        table.center();
        table.setFillParent(true);
        table.add(inventoryLabel);
        table.add(imageLabel);
        table.row();
        table.add(inventory);
        table.add(imagePlaceholder);

        //Get the item from the inventory list
        inventory.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                clickedItem = inventoryController.findItem(inventory.getSelected());
                clickedImage = clickedItem.getImage();
                System.out.println(clickedItem.getName());
                imagePlaceholder.setDrawable(clickedImage.getDrawable());
                descriptionPlaceholder.setText(clickedItem.getDescription());
            }
        });

        table.row();
        table.row();
        descriptionPlaceholder.setScale(0.25f);
        table.add(descriptionPlaceholder).colspan(3);

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
            table.clearChildren();
            table.reset();
            Gdx.input.setInputProcessor(oldInput);
            oldInput = null;
        }
    }

    public void dispose() {
        stage.dispose();
    }

}
