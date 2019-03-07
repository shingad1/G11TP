
package com.sps.game.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


/*
This class is responsible for holding the collections of the user's inventory, merchant inventory, and the
     icons of the inventory items that are selected.
 */

public class InventoryController {
    //Skin used for each lists
    private Skin skin = new Skin(Gdx.files.internal("core/assets/pixthulhuui/pixthulhu-ui.json"));

    //The list that will be holding the players items
    public List<String> inventory = new List<String>(skin);

    //The list that will be holding the merchants items
    public List<String> merchantInventory = new List<String>(skin);

    //The list that holds the image of each item
    public List<Image> itemImageList = new List<Image>(skin);


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
        //Create the inventory objects
        setItems();


        //Providing the string representation for each item in the inventory list, to be printed, through calling the getName() method.
        inventory.setItems(fuel.getName(),
                           axe.getName(),
                           fluxCapacitor.getName(),
                           shoes.getName(),
                           hamster.getName(),
                           hammer.getName(),
                           piratesEye.getName(),
                           cucumber.getName());


        //Providing the string representation for each item in the merchant list, to be printed
        merchantInventory.setItems(shoeLaces.getName(),
                                   sword.getName());


        /*Each item object is responsible for it's own image.
            Create a list which holds the image of each object.
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

/*

    public Item matchStringToImage (String itemName) {
        Iterator<String> iterator = inventory.getItems().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(itemImageList.getName())) {

            }
        }
    }

*/

    //Setting the values of the items
    private void setItems() {
        fuel = new Item("Fuel", "Fuel for your rocket shoes", 1,  "core/assets/Inventory/images/shoe.png");
        axe = new Item("Axe", "An Axe to cut pirates in half", 2, "core/assets/Inventory/images/premium-sword.png");
        fluxCapacitor = new Item("Flux Capacitor", "Travel through time!", 3, "core/assets/Inventory/images/plate.png");
        shoes = new Item("Shoes", "Some fake shoes", 4, "core/assets/Inventory/images/shoe.png");
        hamster = new Item("Hamster", "A rare hamster", 5, "core/assets/Inventory/images/premium-axe.png");
        hammer = new Item("Hammer", "1.5x more effective at killing pirates", 6, "core/assets/Inventory/images/hammer.png");
        piratesEye = new Item("Pirates Eye", "Gives the ability to spot pirates a mile away!", 7, "core/assets/Inventory/images/silvershoe.png");
        cucumber = new Item("Cucumber", "A useless cucumber", 8, "core/assets/Inventory/images/wood.png");
        shoeLaces = new Item("Shoe Laces", "Some common shoe laces. Not edible", 9, "core/assets/Inventory/images/potion.png");
        sword = new Item("Sword", "Hack your way to the final boss", 10, "core/assets/Inventory/images/axe.png");
    }

    //Accessor methods

    public List<String> getInventoryList() {
        return inventory;
    }

    public List<String> getMerchantList() {
        return merchantInventory;
    }

    public List<Image> getImageList() { return itemImageList; }


    public Skin getSkin() {
        return skin;
    }


    public void setSkinConfig() {
        skin.getFont("font-export.fnt").getData().setScale(0.1f);
        skin.getFont("font-title-export.fnt").getData().setScale(0.1f);
        skin.getFont("font-subtitle-export.fnt").getData().setScale(0.1f);


    }


}
