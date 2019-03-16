package com.sps.game.inventory;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * This class represents an inventory item object, which is shown when the player brings up their own
 * inventory.
 * @author Devin Shingadia
 * @version 1.0
 */
public class Item {

    /**
     * The name of the item
     */
    public String name;
    /**
     * The ID of the item
     */
    public int id;
    /**
     * The description of the item
     */
    public String description;

    /**
     * The inventoryTexture which passed into the ItemImage object.
     */
    public Texture imageTexture;
    /**
     * The Image object which is displayed in the inventoryTable and merchant table.
     * @see com.sps.game.controller.InventoryController#itemImageList {@link #getImage()}
     *
     */
    public Image itemImage;

    /**
     * The file path for the icon displayed for the current item
     * @see #getImage()
     * @see #setImage(String)
     */
    public String filepath;

    /**
     * The gold value of the item
     */
    public int goldValue;

    /**
     * Creates an item object which can be stored in a scene2d list and stored in a table.
     * @param name The name of the
     * @param description
     * @param id
     * @param filePath
     */
    public Item (String name, int goldValue, String description, int id, String filePath) {
        this.filepath = filePath;
        imageTexture = new Texture(filePath); //Create a texture which takes in the file path of the item
        itemImage = new Image(imageTexture);  //Create an image object which takes in the texture
        this.name = name;                     //Name of the object (it's string output)
        this.description = description;       //Object Description
        this.id = id;                         //Object ID
        this.goldValue = goldValue;

    }


    //Accessor and Mutator Methods

    public int getGoldvalue() { return goldValue; }
    public Image getImage() { return itemImage; }
    public void setImage (String filepath) {
        this.filepath = filepath;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String desc) {
        description = desc;
    }
    public String getName() { return name; }
    public void setName(String nameSet) { name = nameSet; }
}
