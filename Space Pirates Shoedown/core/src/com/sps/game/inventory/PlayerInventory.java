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


/**
 * This class is responsible for creating the inventory view, which consists of a list of the user items, as well as the
 * an image of the item selected. Additionally, the item gold value is also shown to the user. It also contains scene2d elements,
 * which are used to display the table.
 *
 * @Author Devin Shingadia
 * @see com.sps.game.screens.PlayScreen
 * @Version 1.0
 */
public class PlayerInventory {

    /**
     *   The stage is used to handle the input and the actors.
     *   The table is added to the stage as an actor.
     */
    public Stage stage;

    /**
     * The SpriteBatch object is used to draw elements.
     */
    public SpriteBatch sb;

    /**
     * Used to make the inventory view compatible with multiple devices.
     */
    private Viewport viewport;

    /**
     * Skin object used to format the table. A Json file is passed into the skin object to provide
     * element specific ui.
     */
    private Skin skin = new Skin(Gdx.files.internal("pixthulhuui/pixthulhu-ui.json"));

    /**
     * The list of inventory strings to be displayed
     */
    private List<String> inventory;

    /**
     *  Holds the items and initialises them
     */
    private InventoryController inventoryController = InventoryController.getInstance();


    /**
     * Used for opening and closing the inventory
     */
    private InputProcessor oldInput;

    /**
     * The table which provides the structure to the inventory view.
     */
    private Table table;

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
     * Creates a PlayerInventory Object, which sets up the spritebatch, viewport,
     * stage, and gets the inventory data from the controller.
     * @param sb The SpriteBatch that is used to display the table
     * @param playerController The player controller object
     */
    public PlayerInventory(SpriteBatch sb, PlayerController playerController) {
        this.sb = sb;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);
        inventory = inventoryController.getInventoryList();
    }

    /**
     *   Sets up the table, which holds the list of items and their images, and their gold value.
     *   Uses the JSON skin file for formatting and displaying the lists.
     *   Adds the table to the  stage.
     *
     *   Called in {@link #update()} when the merchant view is opened.
     */
    private void formatting() {
        table = new Table();
        stage = new Stage();
        Label inventoryLabel = new Label("Inventory", skin);
        Label imageLabel = new Label("Item", skin);

        table.setDebug(false);
        table.defaults();
        table.center();
        table.setFillParent(true);
        table.add(inventoryLabel);
        table.add(imageLabel);
        table.row();
        table.add(inventory).height(200f);
        table.add(imagePlaceholder);

        //Get the item from the inventory list
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
        descriptionPlaceholder.setFontScale(0.75f, 0.75f);
        goldPlaceholder.setFontScale(0.75f, 0.75f);

        table.row();
        table.row();
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
        Gdx.input.setInputProcessor(stage);       //Set the input to now work on the inventory.
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
            table.clearChildren();
            table.reset();
            Gdx.input.setInputProcessor(oldInput);
            oldInput = null;
        }
    }

    /**
     * Calls the dispose method on the stage object to free resources.
     */
    public void dispose() {
        stage.dispose();
    }

}
