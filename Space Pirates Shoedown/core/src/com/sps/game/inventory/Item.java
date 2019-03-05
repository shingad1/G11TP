package com.sps.game.inventory;

//TODO: Image for each item

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Item {

    public String name;
    public int Id;
    public String description;
    public Texture imageTexture;
    public Image itemImage;
    public String filepath;


    public Item (String name, String description, int Id, String filePath) {
        this.filepath = filePath;
        imageTexture = new Texture(filePath);
        itemImage = new Image(imageTexture);
        this.name = name;
        this.description = description;
        this.Id = Id;
    }

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
