package com.sps.game.scenes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sps.game.controller.InventoryController;
import com.sps.game.controller.PlayerController;
import com.sps.game.inventory.Item;
import com.sps.game.inventory.MerchantInventory;
import com.sps.game.inventory.PlayerInventory;

import java.awt.*;

/**
 * This class is what the user will see, when they pick up an item
 *
 */
public class ItemHud {
    /**
     * Holds the stage that will display the screen for the pick up item
     */
    public Stage stage;
    /**
     * Displays what the user cna see
     */
    private Viewport viewport;
    /**
     * Holds the old input processor
     */
    private InputProcessor oldInput;
    /**
     * The label which will display the item name
     */
    private Label itemLabel;
    /**
     * Get the items from the inventoryController
     */
    private InventoryController inventoryController;
    /**
     * The user will see the table
     */
    private Table table;
    /**
     * The skin file for the table and associated components
     */
    private Skin skin = new Skin(Gdx.files.internal("core/assets/pixthulhuui/pixthulhu-ui.json"));
    private Boolean isPickedUp;
    private TextButton yesButton, noButton;
    public SpriteBatch sb;
    private Image imagePlaceholder = new Image();
    private PlayerInventory playerInventory;
    private MerchantInventory merchantInventory;
    private String buttonClicked;
    private Item nearbyItem;

    public ItemHud(SpriteBatch sb, PlayerController playerController) {
        inventoryController = new InventoryController();
        playerInventory = new PlayerInventory(sb, playerController);
        merchantInventory = new MerchantInventory(sb, playerController);
        this.sb = sb;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);
        itemLabel = new Label("Item name", skin);
        isPickedUp = false;

    }

    private void formatting() {
        stage = new Stage();
        table = new Table(skin);
        table.setWidth(stage.getWidth() / 3);
        table.setHeight(stage.getHeight() / 3);
        table.align(Align.center);
        table.setFillParent(true);
        table.setDebug(true);

        yesButton = new TextButton("Yes", skin, "default");
        noButton = new TextButton("No", skin, "default");


        //TODO: Replace the image with image of item which is nearby.
        nearbyItem = inventoryController.allItems.getItems().get(5);
        Drawable imageDrawable = nearbyItem.getImage().getDrawable();
        imagePlaceholder.setDrawable(imageDrawable);


        yesButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                buttonClicked = "yes";
                clickFunction();

            }
        });

        noButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                buttonClicked = "no";
                clickFunction();


            }
        });

        itemLabel.setText("Do you want to pick up this item?");
        table.add(itemLabel).colspan(2).row();
        table.row();
        table.add(imagePlaceholder).colspan(2).row();
        table.add(yesButton).size(150, 50).align(Align.bottomLeft).padLeft(200);
        table.add(noButton).size(150, 50).align(Align.bottomRight).padRight(200);
        stage.addActor(table);
    }

    public void clickFunction() {
        if (buttonClicked.equals("yes")) {
            System.out.println("Yes clicked");
            inventoryController.addToInventory(inventoryController.findItem(nearbyItem.getName()));
        }

        if (buttonClicked.equals("no")) {
            System.out.println("No clicked");
            stage.dispose();
            Gdx.input.setInputProcessor(oldInput);
            oldInput = null;
        }
    }

    public void setInput() {
        oldInput = Gdx.input.getInputProcessor();
        Gdx.input.setInputProcessor(stage);
    }

    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.H) && oldInput == null) {
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

    public void dispose(){
        stage.dispose();
    }



}
