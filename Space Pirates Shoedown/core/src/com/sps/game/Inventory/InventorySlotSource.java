package com.sps.game.Inventory;

public class InventorySlotSource extends Source
{
    @Override
    public Payload dragStart(InputEvent event, float x, float y, int pointer) {
        Payload payload = new Payload();
        _sourceSlot = (InventorySlot)getActor().getParent();
        _sourceSlot.decrementItemCount();
        payload.setDragActor(getActor());
        _dragAndDrop.setDragActorPosition(
                -x, -y + getActor().getHeight());
        return payload;
    }

    @Override
    public void dragStop (InputEvent event, float x, float y, int
            pointer, Payload payload, Target target) {
        if( target == null ){
            _sourceSlot.add(payload.getDragActor());
        }
    }
}
