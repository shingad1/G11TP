package com.mygdx.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.SpacePiratesShoedown;

public class MenuState extends State {
    private Texture playButton;
    private Texture background;
    private Texture logo;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        playButton = new Texture(ASSETS_PATH + "playbutton.png");
        background = new Texture(ASSETS_PATH + "spacebackground.jpg"); //Placeholder for the real menu image
        logo = new Texture(ASSETS_PATH + "transparentlogo.png");
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
        sb.draw(background,-((background.getWidth() - Gdx.graphics.getWidth())/2),Gdx.graphics.getHeight() - background.getHeight()); //Texture = background, x = centre of image to center of screen, y = top of image to top of screen
        sb.draw(logo,(Gdx.graphics.getWidth() - logo.getWidth())/2,Gdx.graphics.getHeight()-logo.getHeight()); //positioned at top centre of the screen
        sb.draw(playButton, ((Gdx.graphics.getWidth() / 2) - (playButton.getWidth() /2)),((Gdx.graphics.getHeight() / 2) - (playButton.getWidth() / 2)));
        sb.end();
    }

    @Override
    public void dispose() {
        playButton.dispose();
    }
}
