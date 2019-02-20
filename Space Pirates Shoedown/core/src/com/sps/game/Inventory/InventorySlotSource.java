package com.sps.game.Inventory;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class InventorySlotSource extends DragAndDrop.Source
{
    private InventorySlot _sourceSlot;
    private DragAndDrop _dragAndDrop;

    public InventorySlotSource(Actor actor) {
        super(actor);
    }

    @Override
    public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
        DragAndDrop.Payload payload = new DragAndDrop.Payload();
        _sourceSlot = (InventorySlot) getActor().getParent();
        _sourceSlot.decrementItemCount();
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
}
