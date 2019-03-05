package com.sps.game.inventory;

//TODO: Image for each item

public class Item {

    public String name;
    public int Id;
    public String description;
    public int rarity;


    public int hp;
    public int dmg;
    public int Goldvalue;
    public boolean equipped = false;


    public Item (String name, String description, int Id) {
        this.name = name;
        this.description = description;
        this.Id = Id;
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
