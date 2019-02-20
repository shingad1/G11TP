package com.sps.game.Inventory;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.sps.game.Utility;

import javax.swing.*;
import java.awt.*;

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
    int _itemUseType;
    Component _playerSlotsTable;
    Table _equipSlots;
    JPopupMenu playerSlotsTable;

    ItemUseType(int itemUseType) {
        this._itemUseType = itemUseType;
    }

    public int getValue() {
        return _itemUseType;
    }

    public Table getInventorySlotTable() {
        return inventorySlotTable;
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

    public void createTable()
    {
        _playerSlotsTable.setBackground(new Image(
                new NinePatch(Utility.STATUSUI_TEXTUREATLAS.createPatch(
                        "dialog"))).

                getDrawable());

        _equipSlots.add();
        _equipSlots.add(headSlot).size(slotWidth, _slotHeight);
        _equipSlots.row();
        _equipSlots.add(leftArmSlot).size(slotWidth, _slotHeight);
        _equipSlots.add(chestSlot).size(slotWidth, _slotHeight);
        _equipSlots.add(rightArmSlot).size(
                        slotWidth, _slotHeight);
        _equipSlots.row();
        _equipSlots.add();
        _equipSlots.right().add(legsSlot).size(slotWidth, _slotHeight);

        playerSlotsTable.add((Action) _equipSlots);
        playerSlotsTable.add(_playerSlotsTable).padBottom(20).row();
        playerSlotsTable.add(_inventorySlotTable).row();
        playerSlotsTable.pack();
    }
}
