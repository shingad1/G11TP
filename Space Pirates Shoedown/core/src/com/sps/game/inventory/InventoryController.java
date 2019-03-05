package com.sps.game.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class InventoryController {
    private Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

    //The list that will be holding the players items
    public List<String> inventory = new List<String>(skin);

    //The list that will be holding the merchants items
    public List<String> merchantInventory = new List<String>(skin);

    //List of all available items in the game
    public Item fuel;
    public Item axe;
    public Item fluxCapacitor;
    public Item shoes;
    public Item hamster;
    public Item hammer;
    public Item piratesEye;
    public Item cucumber;
    public Item shoeLaces;
    public Item sword;



    public InventoryController() {
        setItems();

        inventory.setItems(fuel.getName(),
                           axe.getName(),
                           fluxCapacitor.getName(),
                           shoes.getName(),
                           hamster.getName(),
                           hammer.getName(),
                           piratesEye.getName(),
                           cucumber.getName());


        merchantInventory.setItems(shoeLaces.getName(),
                                   sword.getName());
        }

    //Setting the values of the items
    private void setItems() {
        fuel = new Item("Fuel", "Fuel for your rocket shoes", 1);
        axe = new Item("Axe", "An Axe to cut pirates in half", 2);
        fluxCapacitor = new Item("Flux Capacitor", "Travel through time!", 3);
        shoes = new Item("Shoes", "Some fake shoes", 4);
        hamster = new Item("Hamster", "A rare hamster", 5);
        hammer = new Item("Hammer", "1.5x more effective at killing pirates", 6);
        piratesEye = new Item("Pirates Eye", "Gives the ability to spot pirates a mile away!", 7);
        cucumber = new Item("Cucumber", "A useless cucumber", 8);
        shoeLaces = new Item("Shoe Laces", "Some common shoe laces. Not edible", 9);
        sword = new Item("Sword", "Hack your way to the final boss", 10);
    }

    public List<String> getInventoryList() {
        return inventory;
    }

    public List<String> getMerchantList() {
        return merchantInventory;
    }


}
