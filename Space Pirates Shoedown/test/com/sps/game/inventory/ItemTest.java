package com.sps.game.inventory;

import com.badlogic.gdx.graphics.Texture;
import com.sps.game.controller.InventoryController;
import org.junit.Test;


import static org.junit.Assert.*;

public class ItemTest {


    @Test
    public void getGoldvalue() {
        Item fuel = new Item("Fuel", 10, "Fuel for your rocket shoes", 1, "core/assets/Inventory/images/shoe.png");

        assertEquals(10, fuel.getGoldvalue());
    }

    @Test
    public void getImage() {
        Item fuel = new Item("Fuel", 10, "Fuel for your rocket shoes", 1, "Inventory/images/shoe.png");

        assertNotNull(fuel.getImage());
    }


    @Test
    public void getDescription() {
        Item fuel = new Item("Fuel", 10, "Fuel for your rocket shoes", 1, "Inventory/images/shoe.png");

        assertEquals("Fuel for your rocket shoes", fuel.getDescription());
    }

    @Test
    public void getName() {
        Item fuel = new Item("Fuel", 10, "Fuel for your rocket shoes", 1, "Inventory/images/shoe.png");
        assertEquals("Fuel", fuel.getName());
    }

}