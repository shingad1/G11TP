package com.sps.game.sprites;

import com.badlogic.gdx.math.Vector2;
import com.sps.game.animation.EnemyAnimation;
import com.sps.game.controller.CombatSystem;
import com.sps.game.controller.MoveList;
import com.sps.game.maps.MapFactory;
import com.sps.game.screens.Fighter;

import java.util.HashMap;

public abstract class AbstractEnemy extends Fighter {

    /**
     * Holds the enemies X coordinate.
     */
    public int x;
    /**
     * Holds the enemies Y coordinate.
     */
    public int y;

    protected Location location;

    protected CombatSystem system;

    protected HashMap<String, EnemyAnimation> fightAnimation;

    protected HashMap<String, EnemyAnimation> animation;
    /**
     * Abstract method that will return the enemies X coordinate.
     * @return <code>int</code> X coordinate
     */
    public abstract int getX();

    /**
     * Abstract method that will return the enemies Y coordinate.
     * @return <code>int</code> Y coordinate
     */
    public abstract int getY();

    public abstract void battleMove(MoveList moveList);

    public void setCombatSystem(CombatSystem system){this.system = system;}

    public abstract HashMap<String,EnemyAnimation> getFightAnimation();

    public abstract EnemyAnimation getAnimation();

    public abstract String getName();

    public abstract Location getLocation();

    /**
     * Returns the world the enemy is in.
     * @return MapFactory.MapType world.
     */
    public abstract MapFactory.MapType getWorld();

}
