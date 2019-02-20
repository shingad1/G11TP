package com.sps.game.Inventory;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.utils.Array;

public abstract class InventorySlot extends Stack {

    private Label _numItemsLabel;
    private int _numItemsVal, _filterItemType;

    public InventorySlot()
    {
        _numItemsVal = 0;
    }

    public  InventorySlot(int filterItemType, Image customBackgroundDecal)
    {
    }

    public abstract void decrementItemCount();

    public abstract void incrementItemCount();

    public abstract void add(Actor actor);

    public abstract void add(Array array);

    public abstract Array AllInventoryItems();
}
