package com.sps.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.sps.game.inventory.Item;


/**
 *  This class will hold the various collections which are necessary for the player and merchant inventory.
 *  Specifically, the collection of player items, merchant items, and item images.
 *  @author Devin Shingadia
 *  @version 1.0
 */

public class InventoryController {

    /**
     * Skin object, used for the display of the inventory.
     * @see #inventory
     * @see #merchantInventory
     * @see #itemImageList
     */
    private Skin skin = new Skin(Gdx.files.internal("core/assets/pixthulhuui/pixthulhu-ui.json"));

    /**
     * Collection for the users items
     * @see com.sps.game.inventory.MerchantInventory
     * @see com.sps.game.inventory.PlayerInventory
     */
    public List<String> inventory = new List<String>(skin);


    /**
     * Collection for the merchants items
     * @see com.sps.game.inventory.MerchantInventory
     */
    public List<String> merchantInventory = new List<String>(skin);

    /**
     * Collection for the merchants items
     * @see com.sps.game.inventory.PlayerInventory
     * @see com.sps.game.inventory.MerchantInventory
     */
    public List<Image> itemImageList = new List<Image>(skin);

    public List<Item> allItems = new List<Item>(skin);


    /**
     * Item objects which will be used in all collections.
     * @see #inventory
     * @see #merchantInventory
     * @See #itemImageList
     */
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
    public Item socks;


    /**
     * Creates an InventoryController object, populating the collections.
     */
    public InventoryController() {
        setItems();


        allItems.setItems(fuel,
                          axe,
                          fluxCapacitor,
                          shoes,
                          hamster,
                          hammer,
                          piratesEye,
                          cucumber,
                          shoeLaces,
                          sword,
                          socks
                                );


        /**
         *         Providing the string representation for each item in the inventory list, to be printed, through calling the getName() method.
         */
        inventory.setItems(socks.getName(),
                           shoeLaces.getName(),
                           cucumber.getName(),
                           fluxCapacitor.getName()
        );


        /**
         *         Providing the string representation for each item in the merchant list, to be printed
         */
        merchantInventory.setItems(sword.getName());


        /**
         * List of images, which are recieved from the getImage() method called on each item.
         */
        itemImageList.setItems(fuel.getImage(),
                               axe.getImage(),
                               fluxCapacitor.getImage(),
                               shoes.getImage(),
                               hamster.getImage(),
                               hammer.getImage(),
                               piratesEye.getImage(),
                               cucumber.getImage(),
                               shoeLaces.getImage(),
                               sword.getImage());

        }

    /**
     * Creates the items used in the collections.
     * @see #inventory
     * @see #merchantInventory
     * @see #itemImageList
     */
    private void setItems() {
        fuel = new Item("Fuel", 10, "Fuel for your rocket shoes", 1,  "core/assets/Inventory/images/shoe.png");
        axe = new Item("Axe", 20, "An Axe to cut pirates in half", 2, "core/assets/Inventory/images/premium-sword.png");
        fluxCapacitor = new Item("Flux Capacitor", 200,"Travel through time!", 3, "core/assets/Inventory/images/plate.png");
        shoes = new Item("Shoes", 50,  "Some fake shoes", 4, "core/assets/Inventory/images/shoe.png");
        hamster = new Item("Hamster", 1, "A rare hamster", 5, "core/assets/Inventory/images/premium-axe.png");
        hammer = new Item("Hammer", 25, "1.5x more effective at killing pirates", 6, "core/assets/Inventory/images/hammer.png");
        piratesEye = new Item("Pirates Eye", 250, "Gives the ability to spot pirates a mile away!", 7, "core/assets/Inventory/images/silvershoe.png");
        cucumber = new Item("Cucumber", 0, "A useless cucumber", 8, "core/assets/Inventory/images/wood.png");
        shoeLaces = new Item("Shoe Laces", 25, "Some common shoe laces. Not edible", 9, "core/assets/Inventory/images/potion.png");
        sword = new Item("Sword", 100, "Hack your way to the final boss", 10, "core/assets/Inventory/images/axe.png");
        socks = new Item("Socks", 3, "Next best substitute for shoes", 11, "core/assets/Inventory/images/potion.png");
    }

    /**
     *
     * @return
     */
    public List<String> getInventoryList() {
        return inventory;
    }

    public List<String> getMerchantList() {
        return merchantInventory;
    }

    public Item findItem(String itemName) {
        Item foundItem = null;

        for (Item item : allItems.getItems()) {
            if (itemName.equals(item.getName())) {
                foundItem =  item;
            }
        }
        return foundItem;
    }

    public Integer getGoldValue(Item item) {
        return item.getGoldvalue();
    }

    public List<Image> getImageList() { return itemImageList; }



}
