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
import com.sps.game.Controller.PlayerController;


public class PlayerInventory {
    public Stage stage;
    public SpriteBatch sb;
    private Viewport viewport;
    public Table table;


    private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

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
        table = new Table(skin);
    }


    public void formatting() {
        Label inventoryLabel = new Label("Inventory", skin);
        Label imageLabel = new Label ("Item", skin);

        //Label imageLabel = new Label("Icon", skin);
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

        /*
        If the user has pressed 'I' AND The oldInput field is null, meaning that the inventory system is not up.
        This if statement should bring up the inventory system.
         */
        if (Gdx.input.isKeyPressed(Input.Keys.I) && oldInput == null) {
            formatting(); //Create the table, etc.
            setInput();   //Set the new input to be onto the stage; transfer control of input to inventory system.
            //Also sets oldInput field to be not null.
        }

        /*
        If the user has pressed O AND oldInput is not null, meaning that they have pressed 'I' before pressing 'O'.
         */
        if (Gdx.input.isKeyPressed(Input.Keys.O) && oldInput != null) {
            stage.dispose();
            Gdx.input.setInputProcessor(oldInput); //Set the input to be the game.
            oldInput = null; //Sets oldInput to be null, so next time the user presses 'I' the inventory should show.
        }
    }

    public void dispose() {
        stage.dispose();
    }

    public Stage getStage() {
        return stage;
    }

}
