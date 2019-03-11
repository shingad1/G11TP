package com.sps.game.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sps.game.controller.PlayerController;
import com.sps.game.controller.InventoryController;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;


import java.util.ArrayList;


public class MerchantInventory {

    public Stage stage; //Handles the input and actors
    public SpriteBatch sb; //Handles drawing
    private Viewport viewport;

    //The JSON file which is used to format the lists to be displayed.
    private Skin skin = new Skin(Gdx.files.internal("core/assets/pixthulhuui/pixthulhu-ui.json"));


    private List<String> inventory; //List of inventory strings to be displayed
    private List<String> merchant;  //List of merchant strings to be displayed


    //Items that the merchant will not accept
    private ArrayList <String> rejectedItems = new ArrayList<String>();

    //Holds the items and initialises them
    private InventoryController inventoryController;

    //Used for Opening and closing the inventory
    private InputProcessor oldInput;

    private Item clickedItem;
    private Image clickedImage;
    private Image imagePlaceholder = new Image();
    private Label descriptionPlaceholder = new Label("Pick an item", skin);


    public MerchantInventory(SpriteBatch sb, PlayerController playerController) {

        this.sb = sb;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);



        inventoryController = new InventoryController();

        //List of inventory item strings to be displayed
        inventory = inventoryController.getInventoryList();
        //List of Merchant item strings to be displayed
        merchant = inventoryController.getMerchantList();


        //Rejected items that the merchant will not accept
        addRejectedItem("Hamster");
        addRejectedItem("Shoes");

        //Drag and drop functionality
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
                    //If the rejected item equals to the item that the payload is set to, then reject it.
                    if (rejectedItems.get(i).equals((payload.getObject()))) {
                        return !(rejectedItems.get(i).equals(payload.getObject()));

                    }
                }
                return !"Cucumber".equals(payload.getObject()); //Cucumber is rejected by default

            }


            @Override
            public void drop(Source source, Payload payload, float x, float y, int pointer) {

                merchant.getItems().add((String) payload.getObject());
                inventory.getItems().removeValue(payload.getObject().toString(), true);

                //Test to see if the item has been added to the merchants inventory
                System.out.println("merchant: " + merchant.getItems() + "\n");

                //Test to see if the item has been removed from the player's inventory
                System.out.println("player: " + inventory.getItems() + "\n");
            }
        });
    }

    /*
    Sets up the table, which holds the list of items and their images.
    Uses the JSON skin file for formatting and displaying the lists.
     */
    private void formatting() {

        Label inventorylabel = new Label("Inventory", skin);
        Label merchantLabel = new Label ("Merchant", skin);
        Label imageLabel = new Label ("Item", skin);

        Table table = new Table(skin);

        table.setDebug(true);
        table.defaults();
        table.center();
        table.setFillParent(true);

        table.add(inventorylabel);

        table.add(merchantLabel);
        table.add(imageLabel);
        table.row();
        table.row();
        table.add(inventory);
        inventory.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                clickedItem = inventoryController.findItem(inventory.getSelected());
                clickedImage = clickedItem.getImage();
                System.out.println(clickedItem.getName());
                imagePlaceholder.setDrawable(clickedImage.getDrawable());
                descriptionPlaceholder.setText(clickedItem.getDescription());
            }
        });

        merchant.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                clickedItem = inventoryController.findItem(merchant.getSelected());
                clickedImage = clickedItem.getImage();
                System.out.println(clickedItem.getName());
                imagePlaceholder.setDrawable(clickedImage.getDrawable());
                descriptionPlaceholder.setText(clickedItem.getDescription());
            }
        });

        table.add(merchant).height(230);
        merchant.setWidth(200);
        table.add(imagePlaceholder);
        table.row();
        table.row();
        descriptionPlaceholder.setScale(0.25f);
        table.add(descriptionPlaceholder).colspan(3);

        stage.addActor(table);
    }

    /*
    Gets the old input from the user and sets the input to the stage, which is the inventory system.
    This method is called when the user presses 'I'
     */
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
