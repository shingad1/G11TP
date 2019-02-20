/*
package com.sps.game.Inventory;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.sps.game.Utility;

import java.awt.*;
import java.lang.reflect.Array;

public class InventoryUI extends Window {

    private int _numSlots = 50;
    private int _lengthSlotRow = 10;
    private Table _inventorySlotTable;
    private Table _playerSlotsTable;
    private Table _equipSlots;
    private final int _slotWidth = 52;
    private final int _slotHeight = 52;

    private DragAndDrop _dragAndDrop;

    public InventoryUI(Frame owner) {
        super("Inventory", Utility.STATUSUI_SKIN, "solidbackground");

        //create
        _inventorySlotTable = new Table();
        _inventorySlotTable.setName("Inventory_Slot_Table");
        _playerSlotsTable = new Table();
        _equipSlots = new Table();
        _equipSlots.setName("Equipment_Slot_Table");
        _equipSlots.defaults().space(10);
        InventorySlot headSlot = new InventorySlot(
                ItemUseType.ARMOR_HELMET.getValue(),
                new Image(Utility.ITEMS_TEXTUREATLAS.findRegion(
                        "inv_helmet")));
        InventorySlot leftArmSlot = new InventorySlot(
                ItemUseType.WEAPON_ONEHAND.getValue() |
                        ItemUseType.WEAPON_TWOHAND.getValue() |
                        ItemUseType.ARMOR_SHIELD.getValue() |
                        ItemUseType.WAND_ONEHAND.getValue() |
                        ItemUseType.WAND_TWOHAND.getValue(),
                new Image(Utility.ITEMS_TEXTUREATLAS.findRegion(
                        "inv_weapon"))
        );

        InventorySlot rightArmSlot = new InventorySlot(
                ItemUseType.WEAPON_ONEHAND.getValue() |
                        ItemUseType.WEAPON_TWOHAND.getValue() |
                        ItemUseType.ARMOR_SHIELD.getValue() |
                        ItemUseType.WAND_ONEHAND.getValue() |
                        ItemUseType.WAND_TWOHAND.getValue(),
                new Image(Utility.ITEMS_TEXTUREATLAS.findRegion(
                        "inv_shield"))
        );
        InventorySlot chestSlot = new InventorySlot(
                ItemUseType.ARMOR_CHEST.getValue(),
                new Image(Utility.ITEMS_TEXTUREATLAS.findRegion(
                        "inv_chest")));
        InventorySlot legsSlot = new InventorySlot(
                ItemUseType.ARMOR_FEET.getValue(),
                new Image(Utility.ITEMS_TEXTUREATLAS.findRegion(
                        "inv_boot")));

        _inventorySlotTooltip = new InventorySlotTooltip
                (Utility.STATUSUI_SKIN);
        headSlot.addListener(new InventorySlotTooltipListener(
                _inventorySlotTooltip));
        leftArmSlot.addListener(new InventorySlotTooltipListener(
                _inventorySlotTooltip));
        rightArmSlot.addListener(new InventorySlotTooltipListener(
                _inventorySlotTooltip));
        chestSlot.addListener(new InventorySlotTooltipListener(
                _inventorySlotTooltip));
        legsSlot.addListener(new InventorySlotTooltipListener(
                _inventorySlotTooltip));


        for(int i = 1; i <= _numSlots; i++){
            InventorySlot inventorySlot = new InventorySlot();
            _inventorySlotTable.add(
                    inventorySlot).size(_slotWidth, _slotHeight);
            if(i % _lengthSlotRow == 0){
                _inventorySlotTable.row();
            }

            _dragAndDrop.addTarget(new
                    InventorySlotTarget(inventorySlot));
    }

    _inventoryActors = new Array<Actor>();

        _inventoryActors.add(_inventorySlotTooltip);
    }
    public Array<Actor> getInventoryActors(){
        return _inventoryActors;
    }
}
*/
