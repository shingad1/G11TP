package com.sps.game.scenes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sps.game.controller.InventoryController;
import com.sps.game.controller.PlayerController;
import com.sps.game.inventory.Item;
import com.sps.game.sprites.Player;

/**
 * This class consists of what the user will view when they finish a battle with an enemy.
 * It will present the user with a view of the item that they have encountered, as well as options
 * which the user will be able to select, depending on whether they want the item or not.
 *
 * @Author Devin Shingadia
 * @see com.sps.game.screens.PlayScreen
 * @Version 1.0
 */

public class WinHud {
    /**
     *   The stage is used to handle the input and the actors.
     *   The table is added to the stage as an actor.
     */
    public Stage stage;
    /**
     * Used to make the inventory view compatible with multiple devices.
     */
    private Viewport viewport;
    /**
     * Holds the old input processor
     */
    private InputProcessor oldInput;
    /**
     * Get the item from the inventoryController.
     */
    private InventoryController inventoryController = InventoryController.getInstance();
    /**
     * The table that the user will interact with.
     */
    private Table table;

    /**
     * Skin object used to format the table. A Json file is passed into the skin object to provide
     * element specific ui.
     */
    private Skin skin = new Skin(Gdx.files.internal("core/assets/pixthulhuui/pixthulhu-ui.json"));

    /**
     * Boolean attribute which is true if the user has picked up the item, or false instead.
     */
    private Boolean isPickedUp;

    /**
     * The buttons displayed on the table.
     */
    private TextButton okButton;

    /**
     * The SpriteBatch object is used to draw elements.
     */
    public SpriteBatch sb;

    /**
     * Image placeholder which updates depending on the user input.
     */
    private Image imagePlaceholder = new Image();

    /**
     * Assigned to the button that the user clicks on.
     */
    private String buttonClicked;

    /**
     * The label which describes the item.
     */
    private Label pickUpLabel;

    /**
     * The item itself which is added to the user inventory.
     */
    private Item nearbyItem;

    /**
     * The name of the item itself which is added ot hte user inventory.
     */
    private Label itemName;
    /**
     * The gold placeholder which is updated depending on how much gold the user finds after winning their battle.
     */
    private Label goldLabel;
    /**
     * The player object used to update their gold value
     */
    private Player player;
    /**
     * The gold value that will be added to the player's inventory.
     */
    private int goldValue;

    private boolean finished;

    /**
     * Creates a new WinHud object, which sets up the various elements required to add a new element to the
     * inventory of the user.
     *
     * @see com.sps.game.screens.PlayScreen#update(float)
     *
     * @param sb The spritebatch that is used to display the table.
     * @param playerController A PlayerController object
     */
    public WinHud(SpriteBatch sb) {

        this.sb = sb;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);
        pickUpLabel = new Label("Do you want to pick up this item?", skin);
        itemName = new Label("Item name", skin);
        goldLabel = new Label ("Gold amount", skin);
        goldValue = 100;

        player = Player.getPlayer();

        isPickedUp = false;

    }

    /**
     *   Sets up the table, which holds the item and it's associating image.
     *   Uses the JSON skin file for formatting and displaying the lists.
     *
     *   Called in {@link #update()}
     */
    private void formatting() {
        stage = new Stage();
        table = new Table(skin);
        table.setWidth(stage.getWidth() / 3);
        table.setHeight(stage.getHeight() / 3);
        table.align(Align.center);
        table.setFillParent(true);
        table.setDebug(false);

        okButton = new TextButton("Ok", skin, "default");


        //TODO: Replace the image with image of item which is nearby.
        nearbyItem = inventoryController.allItems.getItems().get(3);
        Drawable imageDrawable = nearbyItem.getImage().getDrawable();
        imagePlaceholder.setDrawable(imageDrawable);


        okButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                buttonClicked = "ok";
                clickFunction();
            }
        });


        pickUpLabel.setText("Do you want to pick up this item?");
        itemName.setText(nearbyItem.getName());
        imagePlaceholder.setScale(1.25f, 1.25f);

        table.add(pickUpLabel).colspan(2).row();
        pickUpLabel.setFontScale(0.8f, 0.8f);
        table.row();
        table.add(imagePlaceholder).colspan(2).row();
        table.add(itemName).colspan(2).row();
        itemName.setFontScale(0.6f, 0.6f);

        //gold label
        goldLabel.setText("You have also found: " + 100 + " gold.");
        goldLabel.setFontScale(0.70f);
        table.add(goldLabel).colspan(2).row();
        //yes button
        table.add(okButton).size(150, 50).align(Align.bottom).padLeft(52).padTop(30).row();


        stage.addActor(table);
    }

    /**
     * Functionality for the actions that have been carried out, depending on the user input.
     * Adds the element to the user inventory, if clicked yes.
     * Handles the switching of the input.
     *
     * Called in the formatting method {@link #formatting()}
     */
    public void clickFunction() {
        if (buttonClicked.equals("ok")) {
            System.out.println("Ok clicked");
            inventoryController.addToInventory(inventoryController.findItem(nearbyItem.getName()));
            //player.increaseGold(goldValue);
            Gdx.input.setInputProcessor(oldInput);
            stage.dispose();
            oldInput = null;
        }

    }

    /**
     * Sets the input to the stage, which is the user interface of the Itemhud.
     */
    public void setInput() {
        oldInput = Gdx.input.getInputProcessor();
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * The update method which is called in the PlayScreen class. This method will handle the user input and
     * determines which user input is required to get the inventory view to show up.
     *
     * Calls the {@link #formatting()} and {@link #setInput()} methods to set up the table and user input.
     *
     * @see com.sps.game.screens.PlayScreen#update(float)
     *
     */
    public void update() {
        //if (Gdx.input.isKeyPressed(Input.Keys.H) && oldInput == null) {
            formatting();
            setInput();
        //}

        if (Gdx.input.isKeyPressed(Input.Keys.O) && oldInput != null) {
            stage.dispose();
            table.clearChildren();
            table.reset();
            Gdx.input.setInputProcessor(oldInput);
            finished = true;
            oldInput = null;
            player.increaseGold(100);
        }
    }

    /**
     * Disposes the stage to free resources.
     */
    public void dispose(){
        stage.dispose();
    }

    public boolean getFinished(){
        return finished;
    }

    public void setFinished(boolean newVal){
        finished = newVal;
    }

}
