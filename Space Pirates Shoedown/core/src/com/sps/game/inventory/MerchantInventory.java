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
import com.sps.game.sprites.Player;

import java.util.ArrayList;


public class MerchantInventory {

    public Stage stage; //Handles the input and actors
    public final SpriteBatch sb; //Handles drawing
    private Viewport viewport;
    public Player player;

    //The JSON file which is used to format the lists to be displayed.
    private Skin skin = new Skin(Gdx.files.internal("core/assets/pixthulhuui/pixthulhu-ui.json"));


    private List<String> inventory; //List of inventory strings to be displayed
    private List<String> merchant;  //List of merchant strings to be displayed


    //Items that the merchant will not accept
    private ArrayList <String> rejectedItems = new ArrayList<String>();

    //Holds the items and initialises them
    private InventoryController inventoryController = InventoryController.getInstance();

    //Used for Opening and closing the inventory
    private InputProcessor oldInput;

    private Item clickedItem;
    private Image clickedImage;
    private Image imagePlaceholder = new Image();
    private Label descriptionPlaceholder = new Label("Pick an item", skin);
    private Label goldPlaceholder = new Label("Item gold value", skin);



    public MerchantInventory(final SpriteBatch sb, PlayerController playerController) {

        this.sb = sb;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);
        player = player.getPlayer();

        //List of inventory item strings to be displayed
        inventory = inventoryController.getInventoryList();
        //List of Merchant item strings to be displayed
        merchant = inventoryController.getMerchantList();

        //Rejected items that the merchant will not accept
        addRejectedItem("Hamster");
        addRejectedItem("Shoes");

        dragAndDropPlayer();
        dragAndDropMerchant();
    }

    public void dragAndDropMerchant() {
        DragAndDrop dnd = new DragAndDrop();
        dnd.addSource(new DragAndDrop.Source(merchant) {
            final Payload payload = new Payload();

            @Override
            public DragAndDrop.Payload dragStart(InputEvent inputEvent, float x, float y, int pointer) {
                String item = merchant.getSelected();
                payload.setObject(item);
                merchant.getItems().removeIndex(merchant.getSelectedIndex());
                payload.setDragActor(new Label(item, skin));
                payload.setInvalidDragActor(new Label("I don't want your " + item + "!", skin));
                payload.setValidDragActor(new Label("I'll buy your " + item + "\n" + "for" + +inventoryController.findItem(item).getGoldvalue()
                        + " gold!", skin));

                return payload;
            }


            @Override
            public void dragStop(InputEvent event, float x, float y, int pointer, Payload payload, Target target) {
                if (target == null){
                    merchant.getItems().add((String) payload.getObject());
                }
            }
        });

        dnd.addTarget(new Target(inventory) {
            @Override
            public boolean drag(Source source, Payload payload, float x, float y, int pointer) {

                //Create hud here?


                for (int i = 0; i < rejectedItems.size(); i++) {
                    //If the rejected item equals to the item that the payload is set to, then reject it.
                    if (rejectedItems.get(i).equals((payload.getObject()))) {
                        return !(rejectedItems.get(i).equals(payload.getObject()));

                    }
                }
                return !"Cucumber".equals(payload.getObject()); //Cucumber is rejected by default

            }

            @Override
            public void drop(Source source, Payload payload, float x, float y, int pointer) {

                if (Player.getPlayer().getGold() > inventoryController.findItem(merchant.getSelected()).getGoldvalue()) {
                    inventory.getItems().add((String) payload.getObject());
                    merchant.getItems().removeValue(payload.getObject().toString(), true);
                    System.out.println(inventoryController.findItem(payload.getObject().toString()).getName() + " Has been sold for: " +
                                       inventoryController.findItem(payload.getObject().toString()).getGoldvalue());

                    //Test to see if the item has been added to the merchants inventory
                    System.out.println("merchant: " + inventory.getItems() + "\n");

                    //Test to see if the item has been removed from the player's inventory
                    System.out.println("player: " + merchant.getItems() + "\n");


                } else {
                    merchant.getItems().add(payload.getObject().toString());
                    //Test to see if the item has been added to the merchants inventory
                    System.out.println("inventory: " + inventory.getItems() + "\n");

                    //Test to see if the item has been removed from the player's inventory
                    System.out.println("merchant: " + merchant.getItems() + "\n");
                }

                if (player.getGold() > inventoryController.findItem(payload.getObject().toString()).goldValue + 15) {
                    player.decreaseGold(inventoryController.findItem(payload.getObject().toString()).goldValue + 15);
                }

            }
        });
    }


    public void dragAndDropPlayer() {
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
                payload.setInvalidDragActor(new Label("I don't want your " + item + "!", skin));
                payload.setValidDragActor(new Label("I'll buy your " + item + "\n" + "for" + +inventoryController.findItem(item).getGoldvalue()
                        + " gold!", skin));

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
                for (int i = 0; i < rejectedItems.size(); i++) {
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
                player.increaseGold(inventoryController.findItem(payload.getObject().toString()).goldValue);
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

        table.setDebug(false);
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
                goldPlaceholder.setText("Gold Value: " + clickedItem.getGoldvalue());
            }
        });

        merchant.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                clickedItem = inventoryController.findItem(merchant.getSelected());
                clickedImage = clickedItem.getImage();
                System.out.println(clickedItem.getName());
                imagePlaceholder.setDrawable(clickedImage.getDrawable());
                descriptionPlaceholder.setText(clickedItem.getDescription());
                goldPlaceholder.setText("Gold Value: " + clickedItem.getGoldvalue());
            }
        });

        table.add(merchant).height(230);
        merchant.setWidth(200);
        table.add(imagePlaceholder);
        table.row();
        table.row();
        descriptionPlaceholder.setFontScale(0.75f, 0.75f);
        goldPlaceholder.setFontScale(0.75f, 0.75f);
        table.add(descriptionPlaceholder).colspan(3).row();
        table.add(goldPlaceholder).colspan(3);

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
            //inventory.setItems(inventoryController.inventory.getItems());
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
