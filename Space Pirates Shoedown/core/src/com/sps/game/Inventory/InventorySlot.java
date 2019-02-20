package com.sps.game.Inventory;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.utils.Array;

public class InventorySlot extends Stack {

    private Label _numItemsLabel;
    private int _numItemsVal, _filterItemType;

    public InventorySlot()
    {
        _numItemsVal = 0;
    }

    public  InventorySlot(int filterItemType, Image customBackgroundDecal)
    {
        _numItemsVal = 0;
        this._filterItemType = filterItemType;
    }

    public static void swapSlots(InventorySlot sourceSlot, InventorySlot targetSlot, InventoryItem sourceActor)
    {

    }

    public void decrementItemCount()
    {
        _numItemsVal -= _numItemsVal;
    }

    public void incrementItemCount()
    {
        _numItemsVal += _numItemsVal;

    }

    public void add(Actor actor){}

    public void add(Array array){}

    public Array AllInventoryItems() {
        return null;
    }

    public int getNumItems() {
        return _numItemsVal;
    }

    public InventoryItem getTopInventoryItem() {
        return null;
    }

    public void clearAllInventoryItems() {
        _numItemsVal = 0;
    }

    public boolean hasItem() {
        //if(){}
        return false;
    }

    public boolean doesAcceptItemUseType(int itemUseType) {
        return false;
    }
}
