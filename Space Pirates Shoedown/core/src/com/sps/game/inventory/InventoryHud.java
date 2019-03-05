package com.sps.game.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sps.game.Controller.PlayerController;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.badlogic.gdx.graphics.Texture;


import java.util.ArrayList;
import java.util.Iterator;


public class InventoryHud {

    public Stage stage;
    public SpriteBatch sb;
    private Viewport viewport;
    Label inventoryLabel;
    private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
    private List<String> inventory = new List<String>(skin);
    private List<String> merchant = new List<String>(skin);
    private List<Image> itemImages = new List<Image>(skin);

    private ArrayList <String> rejectedItems = new ArrayList<String>();
    private InventoryController inventoryController;

    private Texture texture = new Texture("core/assets/Inventory/images/sword.png");
    Image swordImage = new Image(texture);
    Image swordImage2;

    private InputProcessor oldInput;


    public InventoryHud(SpriteBatch sb, PlayerController playerController) {

        //swordImage.setPosition(100, 100);
        this.sb = sb;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);
        stage.addActor(swordImage);


        inventoryLabel = new Label("inventory", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        inventoryController = new InventoryController();
        inventory = inventoryController.getInventoryList();
        merchant = inventoryController.getMerchantList();
        itemImages = inventoryController.getImageList();

        rejectedItems.add("Hamster");
        rejectedItems.add("Shoes");

        DragAndDrop dnd = new DragAndDrop();
        dnd.addSource(new DragAndDrop.Source(inventory) {
            final Payload payload = new Payload();

            @Override
            public DragAndDrop.Payload dragStart(InputEvent inputEvent, float x, float y, int pointer) {
                String item = inventory.getSelected();
                payload.setObject(item);
                inventory.getItems().removeIndex(inventory.getSelectedIndex());
                payload.setDragActor(new Label(item, skin));
                payload.setInvalidDragActor(new Label(item + " (\"No thanks!\")", skin));
                payload.setValidDragActor(new Label(item + " (\"I'll buy this!\")", skin));

                return payload;
            }


            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
                if (target == null) {
                    inventory.getItems().add((String) payload.getObject());
                }
            }
        });

        dnd.addTarget(new Target(merchant) {
            @Override
            public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
                for(int i = 0; i < rejectedItems.size(); i++) {
                    if (rejectedItems.get(i).equals((payload.getObject()))) {
                        return !(rejectedItems.get(i).equals(payload.getObject()));
                    }
                }
                return !"Cucumber".equals(payload.getObject());

            }


            @Override
            public void drop(Source source, Payload payload, float x, float y, int pointer) {
                merchant.getItems().add((String) payload.getObject());
            }
        });
    }
/*
    private void addImageToStage() {
        for (Image image : itemImages) {
            stage.addActor(image);
        }
    }

*/
    private void formatting() {

        stage = new Stage();
        Label inventorylabel = new Label("Inventory", skin);
        Label merchantLabel = new Label ("Merchant", skin);
        Label imageLabel = new Label ("Icon", skin);

        Table table = new Table(skin);
        table.setDebug(true);
        table.defaults();
        table.center();
        table.setFillParent(true);
        table.add(inventorylabel);

        table.add(merchantLabel);
        table.add(imageLabel);
        table.row();
        table.add(inventory);
        table.add(merchant);

        table.add(itemImages.getSelected());
        stage.addActor(itemImages); //because we want it to draw
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
            //stage.clear();
            stage.dispose();
            Gdx.input.setInputProcessor(oldInput);
            oldInput = null;
        }
    }


    private void addRejectedItem(String item) {
        rejectedItems.add(item);
    }

    private ArrayList getRejectedItems() {
        return rejectedItems;
    }

    public void dispose() {
        stage.dispose();
    }

}
