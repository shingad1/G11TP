package com.sps.game.inventory;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Item {

    public String name;
    public int id;
    public String description;
    public Texture imageTexture; //The inventoryTexture which is passed into the itemImage object.
    public Image itemImage; //The image object which is displayed in the inventoryTable in class inventoryHud
    public String filepath; //The filepath for the items icon


    public Item (String name, String description, int id, String filePath) {
        this.filepath = filePath;
        imageTexture = new Texture(filePath); //Create a texture which takes in the file path of the item
        itemImage = new Image(imageTexture);  //Create an image object which takes in the texture
        this.name = name;                     //Name of the object (it's string output)
        this.description = description;       //Object Description
        this.id = id;                         //Object ID
    }


    //Accessor and Mutator Methods

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
