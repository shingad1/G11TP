package com.mygdx.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.SpacePiratesShoedown;

public class MenuState extends State {
    private Texture playButton;
    private Texture background;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        playButton = new Texture(ASSETS_PATH + "playbutton.png");
        background = new Texture(ASSETS_PATH + "Test_Menu.jpg"); //Placeholder for the real menu image
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        //sb has to open and close, everything inside will be rendered
        sb.begin();
        sb.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight()); //Texture = background, x = 0, y = 0 (bottom-left of screen), width = width of screen, height = height of screen
        sb.draw(playButton, ((Gdx.graphics.getWidth() / 2) - (playButton.getWidth() /2)),((Gdx.graphics.getHeight() / 2) - (playButton.getWidth() / 2)), 128, 128);
        sb.end();
    }

    @Override
    public void dispose() {
        playButton.dispose();
    }
}
