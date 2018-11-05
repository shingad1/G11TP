package com.sps.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class ScreenController {

    private Game game;
    private Screen combatScreen;

    public ScreenController(Game game){
        this.game = game;
        //combatScreen = screen;
    }

    public void switchToCombatScreen(){
        combatScreen = new CombatScreen(game);
    }
}
