package com.sps.game.screens;

import java.awt.*;

public class DesktopSplashLauncher implements SplashWorker {
    @Override
    public void closeSplashScreen() {
        SplashScreen splashScreen = SplashScreen.getSplashScreen();
        if(splashScreen != null){
            splashScreen.close();
        }
    }
}
