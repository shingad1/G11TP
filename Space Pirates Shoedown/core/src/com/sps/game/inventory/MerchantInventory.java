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

/**
 * This class consists of the merchant and inventory view. A MerchantInventory object is created and displayed to the user
 * when the user is nearby a merchant. It contains scene2d elements, which are used to display the table. The table consists
 * of a list for the user's inventory, and another list of the merchant's inventory element's. The user can drag and drop
 * from one inventory list to another. A skin object is used to provide a retro-like feel to the table.
 *
 *
 * @Author Devin Shingadia
 * @see com.sps.game.screens.PlayScreen
 * @Version 1.0
 */

public class MerchantInventory {

    /**
     *   The stage is used to handle the input and the actors.
     *   The table is added to the stage as an actor.
     */
    public Stage stage;

    /**
     * The SpriteBatch object is used to draw elements.
     */
    public final SpriteBatch sb;
    /**
     * Used to make the inventory view compatible with multiple devices.
     */
    private Viewport viewport;
    /**
     * The player object used to get the player specific data.
     */
    public Player player;

    /**
     * Skin object used to format the table. A Json file is passed into the skin object to provide
     * element specific ui.
     */
    private Skin skin = new Skin(Gdx.files.internal("core/assets/pixthulhuui/pixthulhu-ui.json"));

    /**
     * The list of inventory strings to be displayed
     */
    private List<String> inventory;

    /**
     * The list of merchant strings to be displayed
     */
    private List<String> merchant;


    //Items that the merchant will not accept
    private ArrayList <String> rejectedItems = new ArrayList<String>();

    //Holds the items and initialises them
    private InventoryController inventoryController = InventoryController.getInstance();

    //Used for Opening and closing the inventory
    private InputProcessor oldInput;

    /**
     * The current item in list that the user has clicked.
     */
    private Item clickedItem;

    /**
     * The image of the current item that the user has clicked on.
     */
    private Image clickedImage;

    /**
     * Image placeholder which updates depending on the user input.
     */
    private Image imagePlaceholder = new Image();

    /**
     * Description placeholder which updates depending on the item that the user has clicked on.
     */
    private Label descriptionPlaceholder = new Label("Pick an item", skin);
    /**
     * Gold placeholder which updates depending on the item that the user has clicked on.
     */
    private Label goldPlaceholder = new Label("Item gold value", skin);


    /**
     * Creates a MerchantInventory object, which sets up the user and merchant lists, the sprite batch, viewport, stage,
     * and the player object. The rejected items are also set up, which are the items that the merchant refuses to purchase.
     *
     * Also sets up the drag and drop functionality through the methods {@link #dragAndDropMerchant()} and {@link #dragAndDropPlayer()}
     * @param sb The spritebatch that is used to display the table.
     * @param playerController
     *
     *
     */
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

    /**
     * Sets up the drag and drop functionality for the merchant list.
     * Provides the merchant response depending on which items you drag onto the merchant
     * Provides the functionality for the player gold value calculation when an item is transferred from the player
     * list to the merchant list.
     *
     * This method is similar to {@link #dragAndDropPlayer()}
     */
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
                    System.out.println(inventoryController.findItem(payload.getObject().toString()).getName() + " Has been bought for: " +
                                       inventoryController.findItem(payload.getObject().toString()).getGoldvalue());

                    //Test to see if the item has been added to the merchants inventory
                    System.out.println("inventory: " + inventory.getItems() + "\n");
                    //Test to see if the item has been removed from the player's inventory
                    System.out.println("merchant: " + merchant.getItems() + "\n");


                } else {
                    merchant.getItems().add(payload.getObject().toString());
                    //Test to see if the item has been added to the merchants inventory
                    System.out.println("inventory: " + inventory.getItems() + "\n");

                    //Test to see if the item has been removed from the player's inventory
                    System.out.println("merchant: " + merchant.getItems() + "\n");
                }

                if (player.getGold() >= inventoryController.findItem(payload.getObject().toString()).goldValue) {
                    player.decreaseGold(inventoryController.findItem(payload.getObject().toString()).goldValue);
                }

            }
        });
    }

    /**
     * Sets up the drag and drop functionality for the player list.
     * Provides the player response depending on which items you drag onto the player list
     * Provides the functionality for the player gold value calculation when an item is transferred from the merchant to player.
     *
     * This method is similar to {@link #dragAndDropMerchant()}
     */

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
                    if (rejectedItems.get(i).equals((payload.getObject()))) {
                        return !(rejectedItems.get(i).equals(payload.getObject()));

                    }
                }
                return !"Cucumber".equals(payload.getObject());

            }

            @Override
            public void drop(Source source, Payload payload, float x, float y, int pointer) {

                merchant.getItems().add((String) payload.getObject());
                inventory.getItems().removeValue(payload.getObject().toString(), true);
                player.increaseGold((inventoryController.findItem(payload.getObject().toString()).goldValue) - 15);
                //Test to see if the item has been added to the merchants inventory
                System.out.println("merchant: " + merchant.getItems() + "\n");

                //Test to see if the item has been removed from the player's inventory
                System.out.println("player: " + inventory.getItems() + "\n");
            }
        });
    }

    /**
     *   Sets up the table, which holds the list of items and their images.
     *   Uses the JSON skin file for formatting and displaying the lists.
     *
     *   Called in {@link #update()} when the merchant view is opened.
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
        table.add(inventory).height(230);
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

        table.add(merchant).height(230).width(230);
        table.add(imagePlaceholder);
        table.row();
        table.row();
        descriptionPlaceholder.setFontScale(0.75f, 0.75f);
        goldPlaceholder.setFontScale(0.75f, 0.75f);
        table.add(descriptionPlaceholder).colspan(3).row();
        table.add(goldPlaceholder).colspan(3);

        stage.addActor(table);
    }

    /**
     * Gets the old input from the user and sets the input to the stage, which is the inventory system.
     * This method is called when the user pressed 'I' in {@link #update()}
     */
    public void setInput() {
        oldInput = Gdx.input.getInputProcessor();
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * The update method which is called in the PlayScreen class. This method will handle the user input and
     * determines which user input is required to get the inventory view to show up.
     *
     * If the user has pressed 'I' AND The oldInput field is null, meaning that the inventory system is up. Similar
     * logic is used to close the table, with the O key.
     *
     * Calls the {@link #formatting()} and {@link #setInput()} methods to set up the table and user input.
       *
     * @see com.sps.game.screens.PlayScreen#update(float)
     *
     */
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

    /**
     * Adds a rejected item to the rejected items ArrayList.
     * @param item The item that is to be added to the ArrayList
     */
    private void addRejectedItem(String item) {
        rejectedItems.add(item);
    }

    /**
     * Returns the ArrayList of rejected items.
     * @return Rejected items ArrayList. {@link #rejectedItems}
     */
    private ArrayList getRejectedItems() {
        return rejectedItems;
    }

    /**
     * Disposes the stage resources.
     */
    public void dispose() {
        stage.dispose();
    }

}
