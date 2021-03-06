package com.sps.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.sps.game.inventory.Item;
import com.sps.game.sprites.Player;


/**
 *  This class will hold the various collections which are necessary for the player and merchant inventory.
 *  Specifically, the collection of player items, merchant items, and item images.
 *  @author Devin Shingadia
 *  @version 1.0
 */

public class InventoryController {

    private static InventoryController instance = new InventoryController();

    /**
     * Skin object, used for the display of the inventory.
     * @see #inventory
     * @see #merchantInventory
     * @see #itemImageList
     */
    private Skin skin = new Skin(Gdx.files.internal("pixthulhuui/pixthulhu-ui.json"));

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
    public Item shoe;
    public Item hammer;
    public Item piratesEye;
    public Item fakeShoe;
    public Item shoeLaces;
    public Item sword;
    public Item socks;
    public Item foundItem;


    /**
     * Creates an InventoryController object, populating the collections.
     */
    private InventoryController() {
        setItems();


        allItems.setItems(fuel,
                          axe,
                          fluxCapacitor,
                shoe,
                          hammer,
                          piratesEye,
                          fakeShoe,
                          shoeLaces,
                          sword,
                          socks
                                );


        /**
         * Providing the string representation for each item in the inventory list, to be printed, through calling the getName() method.
         */
        inventory.setItems(socks.getName(),
                           fuel.getName(),
                           fakeShoe.getName()
                );


        /**
         * Providing the string representation for each item in the merchant list, to be printed
         */
        merchantInventory.setItems(sword.getName(), fluxCapacitor.getName(), axe.getName(), piratesEye.getName()); //to have more


        /**
         * List of images, which are recieved from the getImage() method called on each item.
         */
        itemImageList.setItems(fuel.getImage(),
                               axe.getImage(),
                               fluxCapacitor.getImage(),
                               shoe.getImage(),
                               hammer.getImage(),
                               piratesEye.getImage(),
                               fakeShoe.getImage(),
                               shoeLaces.getImage(),
                               sword.getImage());

        }

        public static InventoryController getInstance() {
            return instance;
        }

    /**
     * Creates the items used in the collections.
     * @see #inventory
     * @see #merchantInventory
     * @see #itemImageList
     */
    private void setItems() {
        fuel = new Item("Fuel", 10, "Fuel for your rocket shoe", 1,  "Inventory/images/fuel.png");
        axe = new Item("Axe", 20, "An Axe to cut pirates in half", 2, "Inventory/images/premium-axe.png");
        fluxCapacitor = new Item("Flux Capacitor", 200,"Travel through time!", 3, "Inventory/images/flux-capacitor.png");
        shoe = new Item("Shoe", 50,  "Your old shoe", 4, "Inventory/images/shoe.png");
        hammer = new Item("Hammer", 25, "1.5x more effective at killing pirates", 6, "Inventory/images/hammer.png");
        piratesEye = new Item("Pirates Eye", 250, "Gives the ability to spot pirates a mile away!", 7, "Inventory/images/eye.png");
        fakeShoe = new Item("Fake Shoe", 0, "A fake shoe. Made out of newspaper.", 8, "Inventory/images/fakeShoe.png");
        shoeLaces = new Item("Shoe Laces", 25, "Some common shoe laces. Not edible", 9, "Inventory/images/shoeLaces.png");
        sword = new Item("Sword", 100, "Hack your way to the final boss", 10, "Inventory/images/regularSword.png");
        socks = new Item("Socks", 3, "Next best substitute for shoe", 11, "Inventory/images/sock.png");
    }

    /**
     * Accessor method for the user inventory list.
     * @return A list of strings which is the user's inventory.
     */
    public List<String> getInventoryList() {
        return inventory;
    }

    /**
     * Accessor method for the merchant's inventory list
     * @return A list of strings which is the merchant's inventory
     */
    public List<String> getMerchantList() {
        return merchantInventory;
    }

    /**
     * Finds an item, given the item name. Iterates through the allItems collection using an enhanced for loop.
     * @param itemName The string representation of the item to be found.
     * @return An item object which has the .getName() output, which is the same as the parameter.
     */
    public Item findItem(String itemName) {
        Item foundItem = null;

        for (Item item : allItems.getItems()) {
            if (itemName.equals(item.getName())) {
                foundItem =  item;
            }
        }
        return foundItem;
    }

    /**
     * Accessor method for the gold value.
     * @param item The item object, which passed in, contains a method to return the gold value.
     * @return An Integer which is the gold value of the item in question.
     */
    public Integer getGoldValue(Item item) {
        return item.getGoldvalue();
    }

    /**
     * Returns a list of images, called imageList.
     * @return A list of images.
     */
    public List<Image> getImageList() { return itemImageList; }

    /**
     * Adds item to the user inventory collection.
     * @param foundItem The item to be added. 
     */
    public void addToInventory(Item foundItem) {
        this.foundItem = foundItem;
        String itemString = foundItem.getName();
        if (!inventory.getItems().contains(foundItem.getName(), false)) {
            inventory.getItems().add(foundItem.getName());

        }

        System.out.println("player: " + inventory.getItems() + "\n");
    }



}
