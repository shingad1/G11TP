package com.sps.game.screens;

import com.badlogic.gdx.graphics.Texture;
import com.sps.game.scenes.StoryHud;

public class StoryScreen {

    private Texture[] tutorialTexture;
    private static String ASSETS_PATH = "core/assets/SP/";

    public StoryScreen(){
        tutorialTexture = new Texture[13];

        tutorialTexture[0] = new Texture(ASSETS_PATH + "Slide1.png");
        tutorialTexture[1] = new Texture(ASSETS_PATH + "Slide2.png");
        tutorialTexture[2] = new Texture(ASSETS_PATH + "Slide3.png");
        tutorialTexture[3] = new Texture(ASSETS_PATH + "Slide4.png");
        tutorialTexture[4] = new Texture(ASSETS_PATH + "Slide5.png");
        tutorialTexture[5] = new Texture(ASSETS_PATH + "Slide6.png");
        tutorialTexture[6] = new Texture(ASSETS_PATH + "Slide7.png");
        tutorialTexture[7] = new Texture(ASSETS_PATH + "Slide8.png");
        tutorialTexture[8] = new Texture(ASSETS_PATH + "Slide9.png");
        tutorialTexture[9] = new Texture(ASSETS_PATH + "Slide10.png");
        tutorialTexture[10] = new Texture(ASSETS_PATH + "Slide11.png");
        tutorialTexture[11] = new Texture(ASSETS_PATH + "Slide12.png");
        tutorialTexture[12] = new Texture(ASSETS_PATH + "Slide13.png");
    }
}
