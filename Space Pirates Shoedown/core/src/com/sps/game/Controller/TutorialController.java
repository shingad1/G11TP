package com.sps.game.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TutorialController extends InputAdapter
{
    private Texture slide1;
    private Texture slide2;
    private Texture slide3;
    private Texture slide4;
    private Texture slide5;
    private Texture slide6;
    private Texture slide7;
    private Texture slide8;
    private Texture slide9;
    private Texture slide10;
    private Texture slide11;
    private Texture slide12;

    private Texture[] bob;
    private int counter;
    private SpriteBatch batch;

    private static final String ASSETS_PATH = "core/assets/SP/";

    public TutorialController()
    {
        bob = new Texture[12];
        counter = 0;
        batch = new SpriteBatch();

        slide1 = new Texture("core/assets/SP/Slide1.png");
        slide2 = new Texture(ASSETS_PATH + "Slide2.png");
        slide3 = new Texture(ASSETS_PATH + "Slide3.png");
        slide4 = new Texture(ASSETS_PATH + "Slide4.png");
        slide5 = new Texture(ASSETS_PATH + "Slide5.png");
        slide6 = new Texture(ASSETS_PATH + "Slide6.png");
        slide7 = new Texture(ASSETS_PATH + "Slide7.png");
        slide8 = new Texture(ASSETS_PATH + "Slide8.png");
        slide9 = new Texture(ASSETS_PATH + "Slide9.png");
        slide10 = new Texture(ASSETS_PATH + "Slide10.png");
        slide11 = new Texture(ASSETS_PATH + "Slide11.png");
        slide12 = new Texture(ASSETS_PATH + "Slide11.png");

        handleInput();
    }

    public void handleInput(){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            if (counter < bob.length - 1)
            {
                counter ++;
                //dialoguesTextArea.setText(dialogue[counter]);
            }
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            if (counter > 0)
            {
                counter --;
            }
        }
    }

    public void render() {
        batch.begin();
        batch.draw(slide1, 0,0);
        batch.draw(slide2, 0,0);
        batch.draw(slide3, 0,0);
        batch.draw(slide4, 0,0);
        batch.draw(slide5, 0,0);
        batch.draw(slide6, 0,0);
        batch.draw(slide7, 0,0);
        batch.draw(slide8, 0,0);
        batch.draw(slide9, 0,0);
        batch.draw(slide10, 0,0);
        batch.draw(slide11, 0,0);
        batch.draw(slide12, 0,0);
        batch.end();
    }

    public void setImages()
    {
        bob[0] = slide1;
        bob[1] = slide2;
        bob[2] = slide3;
        bob[3] = slide4;
        bob[4] = slide5;
        bob[5] = slide6;
        bob[6] = slide7;
        bob[7] = slide8;
        bob[8] = slide9;
        bob[9] = slide10;
        bob[10] = slide11;
        bob[11] = slide11;
    }
}
