package com.sps.game.Screens;

import javafx.stage.Screen;

import java.util.Stack;

public class GameScreensManager {
    private Stack<Screen> screens;

    public GameScreensManager(){
        screens = new Stack<Screen>();
    }

    public void push(Screen screen){
        screens.push(screen);
    }

    public void pop(){
        screens.pop();
    }

    public void setNewScreen(Screen screen){
        screens.pop();
        screens.push(screen);
    }

}
