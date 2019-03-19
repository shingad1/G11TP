package com.sps.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
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
import com.sps.game.maps.Map;
import com.sps.game.maps.MapFactory;
import com.sps.game.screens.PlayScreen;
import com.sps.game.sprites.AbstractEnemy;
import com.sps.game.sprites.Location;

import java.util.ArrayList;

public class BuyHud {

        public Stage stage;
        private Viewport viewport;
        private InputProcessor oldInput;
        private InventoryController inventoryController = InventoryController.getInstance();
        private Table table;
        private Skin skin = new Skin(Gdx.files.internal("core/assets/pixthulhuui/pixthulhu-ui.json"));
        private Boolean isSelected;
        private TextButton yesButton, noButton;
        public SpriteBatch sb;
        private Image imagePlaceholder = new Image();
        private PlayerInventory playerInventory;
        private MerchantInventory merchantInventory;
        private String buttonClicked;
        private Label pickUpLabel;
        private Item buyItem;
        private Label itemName;
        private PlayScreen playScreen;


        public BuyHud(SpriteBatch sb, PlayerController playerController) {
            playerInventory = new PlayerInventory(sb, playerController);
            merchantInventory = new MerchantInventory(sb, playerController);
            this.sb = sb;
            viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
            stage = new Stage(viewport, sb);
            pickUpLabel = new Label("Do you want to buy this item?", skin);
            itemName = new Label("Item name", skin);
            isSelected = false;

        }

        private void formatting() {
            stage = new Stage();
            table = new Table(skin);
            table.setWidth(stage.getWidth() / 3);
            table.setHeight(stage.getHeight() / 3);
            table.align(Align.center);
            table.setFillParent(true);
            table.setDebug(false);

            yesButton = new TextButton("Yes", skin, "default");
            noButton = new TextButton("No", skin, "default");


            //TODO: Replace the image with image of item which is dragged to the player inventory
            buyItem = inventoryController.allItems.getItems().get(5);
            Drawable imageDrawable = buyItem.getImage().getDrawable();
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

            pickUpLabel.setText("Do you want to buy this item?");
            itemName.setText(buyItem.getName());
            imagePlaceholder.setScale(1.25f, 1.25f);




            table.add(pickUpLabel).colspan(2).row();
            pickUpLabel.setFontScale(0.8f, 0.8f);
            table.row();
            table.add(imagePlaceholder).colspan(2).row();
            table.add(itemName).colspan(2).row();
            itemName.setFontScale(0.6f, 0.6f);
            table.add(yesButton).size(150, 50).align(Align.bottomLeft).padLeft(175).padTop(25);
            table.add(noButton).size(150, 50).align(Align.bottomRight).padRight(175).padTop(25);
            stage.addActor(table);
        }

        public void clickFunction() {
            if (buttonClicked.equals("yes")) {
                System.out.println("Yes clicked");
                inventoryController.addToInventory(inventoryController.findItem(buyItem.getName()));
                Gdx.input.setInputProcessor(oldInput);
                stage.dispose();
                oldInput = null;
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
            if ((merchantInventory.getBuyHudOpen()) && (oldInput == null)) {
                formatting();
                setInput();
                merchantInventory.toggleBuyHudOpen();
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

