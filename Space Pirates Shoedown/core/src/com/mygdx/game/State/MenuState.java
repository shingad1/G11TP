package com.mygdx.game.State;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.SpacePiratesShoedown;

public class MenuState extends State {
    private Texture playButton;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        playButton = new Texture("playbutton.png");
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        //sb has to open and close, everything inside will be rendered
        sb.begin();
        sb.draw(playButton, (SpacePiratesShoedown.WIDTH / 4) - (playButton.getWidth() /4),(SpacePiratesShoedown.HEIGHT / 4), SpacePiratesShoedown.WIDTH, SpacePiratesShoedown.HEIGHT);
        sb.end();
    }
}
