package com.sps.game.Controller;

import com.sps.game.Sprites.BasicEnemy;
import com.sps.game.Sprites.Player;

public class CombatSystem {

    private Player player;

    private BasicEnemy enemy;

    public CombatSystem(Player p, BasicEnemy e){
        this.player = p;
        this.enemy = e;
    }

    
}
