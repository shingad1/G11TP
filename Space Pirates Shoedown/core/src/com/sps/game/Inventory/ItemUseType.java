package com.sps.game.Inventory;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

public enum ItemUseType {
    ITEM_RESTORE_HEALTH(1),
    ITEM_RESTORE_MP(2),
    ITEM_DAMAGE(4),
    WEAPON_ONEHAND(8),
    WEAPON_TWOHAND(16),
    WAND_ONEHAND(32),
    WAND_TWOHAND(64),
    ARMOR_SHIELD(128),
    ARMOR_HELMET(256),
    ARMOR_CHEST(512),
    ARMOR_FEET(1024);
    private int _itemUseType;

    ItemUseType(int itemUseType) {
        this._itemUseType = itemUseType;
    }

    public int getValue() {
        return _itemUseType;
    }

    public Table getInventorySlotTable() {
        return _inventorySlotTable;
    }
    public Table getEquipSlotTable() {
        return _equipSlots;
    }

    public Array<InventoryItemLocation> getInventory(Table targetTable){
        Array<Cell> cells = targetTable.getCells();
        Array<InventoryItemLocation> items = new
                Array<InventoryItemLocation>();
        for(int i = 0; i < cells.size; i++){
            InventorySlot inventorySlot =
                    ((InventorySlot)cells.get(i).getActor());
            if( inventorySlot == null ) continue;
            int numItems = inventorySlot.getNumItems();
            if( numItems > 0 ){
                items.add(new InventoryItemLocation(i,inventorySlot.getTopInventoryItem().getItemTypeID().toString(), numItems));
            }
        }
        return items;
    }
}
