package com.sps.game.Inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Scaling;
import com.sps.game.Utility;
import com.sps.game.Inventory.InventoryItem.ItemTypeID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class InventoryItemFactory {

    private Json json = new Json();

    private final String INVENTORY_ITEM = "assets/Inventory/inventory.json";

    private static InventoryItemFactory instance = null;

    private Hashtable<ItemTypeID, InventoryItem> _inventoryItemList;

    public static InventoryItemFactory getInstance(){
        if(instance == null){
            instance = new InventoryItemFactory();
        }
        return instance;
    }

    private InventoryItemFactory(){
        ArrayList<JsonValue> list = json.fromJson(ArrayList.class, Gdx.files.internal(INVENTORY_ITEM));
        _inventoryItemList = new Hashtable<ItemTypeID, InventoryItem>();

        for(JsonValue jsonVal : list){
            InventoryItem inventoryItem = json.readValue(InventoryItem.class, jsonVal);
            _inventoryItemList.put(inventoryItem.getItemTypeID(), inventoryItem);
        }
    }

    public InventoryItem getInventoryItem(ItemTypeID inventoryItemType){
        InventoryItem item = new InventoryItem(_inventoryItemList.get(inventoryItemType));
        item.setDrawable(new TextureRegionDrawable(Utility.ITEMS_TEXTUREATLAS.findRegion(item.getItemTypeID().toString())));
        item.setScaling(Scaling.none);
        return item;
    }
}
