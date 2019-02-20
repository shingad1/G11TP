package com.sps.game.Inventory;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

import java.awt.image.renderable.ParameterBlock;

public class InventorySlotSource extends DragAndDrop.Source
{
    private InventorySlot _sourceSlot;
    private DragAndDrop _dragAndDrop;

    public InventorySlotSource(InventorySlot sourceSlot, DragAndDrop dragAndDrop) {
        super(sourceSlot.getTopInventoryItem());
        this._sourceSlot= sourceSlot;
        this._dragAndDrop= dragAndDrop;
    }

    @Override
    public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
        DragAndDrop.Payload payload = new DragAndDrop.Payload();
        _sourceSlot = (InventorySlot) getActor().getParent();
        _sourceSlot.decrementItemCount(true);
        payload.setDragActor(getActor());
        _dragAndDrop.setDragActorPosition(-x, -y + getActor().getHeight());
        return payload;
    }

    @Override
    public void dragStop (InputEvent event, float x, float y, int
            pointer, DragAndDrop.Payload payload, DragAndDrop.Target target) {
        if( target == null ){
            _sourceSlot.add(payload.getDragActor());
        }
    }

    public InventorySlot getSourceSlot() {
        return null;
    }
}
