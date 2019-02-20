package com.sps.game.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Scaling;
import com.sps.game.Utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class InventoryItemFactory {

    private Json json = new Json();

    private final String INVENTORY_ITEM = "assets/Inventory/inventory.json";

    private static InventoryItemFactory instance = null;

    private Hashtable<InventoryItem.ItemTypeID, InventoryItem> inventoryItemList;

    public static InventoryItemFactory getInstance(){
        if(instance == null){
            instance = new InventoryItemFactory();
        }
        return instance;
    }

    private InventoryItemFactory(){
        ArrayList<JsonValue> list = json.fromJson(ArrayList.class, Gdx.files.internal(INVENTORY_ITEM));
        HashMap<InventoryItem.ItemTypeID, InventoryItem> inventoryItemList = new Hashtable<InventoryItem.ItemTypeID, InventoryItem>();
        for(JsonValue jsonVal : list){
            InventoryItem inventoryItem = json.readValue(InventoryItem.class, jsonVal);
            inventoryItemList.put(inventoryItem.getItemTypeID(), inventoryItem);
        }
    }

    public InventoryItem getInventoryItem(InventoryItem.ItemTypeID inventoryItemType){
        InventoryItem item = new InventoryItem(inventoryItemList.get(inventoryItemType));
        item.setDrawable(new TextureRegionDrawable(Utility.ITEMS_TEXTUREATLAS.findRegion(item.getItemTypeID().toString())));
        item.setScaling(Scaling.none);
        return item;
    }
}
