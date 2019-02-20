package com.sps.game.Inventory;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Array;

import java.awt.image.renderable.ParameterBlock;

public class InventorySlotTarget extends DragAndDrop.Target {

    ParameterBlock _dragAndDrop;
    InventorySlot _targetSlot;


    public InventorySlotTarget(Actor actor) {
        super(actor);
    }

    @Override
    public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        return false;
    }

    @Override
    public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x,
                     float y, int pointer) {
        InventoryItem sourceActor = (InventoryItem)
                payload.getDragActor();
        InventoryItem targetActor =
                _targetSlot.getTopInventoryItem();
        InventorySlot sourceSlot =
                ((InventorySlotSource)source).getSourceSlot();
        if( sourceActor == null ) {
            return;
        }
        //First, does the slot accept the source item type?
        if( !_targetSlot.doesAcceptItemUseType(
                sourceActor.getItemUseType())) {
            //Put item back where it came from,
            //slot doesn't accept item
            sourceSlot.add(sourceActor);
            return;
        }

        if( !_targetSlot.hasItem() ){
            _targetSlot.add(sourceActor);
        }else{
            //If the same item and stackable, add
            if( sourceActor.isSameItemType(targetActor) &&
                    sourceActor.isStackable()){
                _targetSlot.add(sourceActor);
            }else{
                //If they aren't the same items or
                //the items aren't stackable, then swap
                InventorySlot.swapSlots(
                        sourceSlot, _targetSlot, sourceActor);
            }
        }
    }

    public void populateInventory(Table targetTable,
                                  Array<InventoryItemLocation> inventoryItems){
        Array<Cell> cells = targetTable.getCells();
        for(int i = 0; i < inventoryItems.size; i++){
            InventoryItemLocation itemLocation =
                    inventoryItems.get(i);
            InventoryItem.ItemTypeID itemTypeID = InventoryItem.ItemTypeID.valueOf(
                    itemLocation.getItemTypeAtLocation());
            InventorySlot inventorySlot =
                    ((InventorySlot)cells.get(
                            itemLocation.getLocationIndex()).getActor());
            inventorySlot.clearAllInventoryItems();
            for( int index = 0;
                 index < itemLocation.getNumberItemsAtLocation();
                 index++ ){
                inventorySlot.add(
                        InventoryItemFactory.getInstance(
                        ).getInventoryItem(itemTypeID));
                _dragAndDrop.addSource(new
                        InventorySlotSource(inventorySlot, _dragAndDrop));

            }
        }
    }
}
