package com.sps.game.screens;

public interface Fighter {

    public abstract void changeHP(int diff);

    public abstract void changeAttack(int diff);

    public abstract void changeDefence(int diff);

    public abstract int getAttack();

    public abstract int getHP();

    public abstract int getDefence();
}
