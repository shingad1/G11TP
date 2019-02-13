package com.sps.game.Scenes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Dialogue extends ApplicationAdapter implements InputProcessor
{
    private Stage stage;
    private Skin skin;

    private SpriteBatch batch;
    private Sprite sprite;

    int counter;
    String[] dialogue;

    private Table table;
    private TextButton prevButton, nextButton;

    private String buttonLogged = "";

    public Dialogue()
    {
        counter = 0;
        dialogue = new String[3];

        dialogue[0] = "hi";
        dialogue[1] = "whats up";
        dialogue[2] = "bye";

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void create()
    {
        final Dialog textArea = new Dialog("Dialogues", skin, "default");
        textArea.text(dialogue[counter]);

        table = new Table();
        table.setWidth(stage.getWidth());
        table.setHeight(stage.getHeight()/2);
        table.align(Align.center | Align.bottom);
        table.padBottom(10);

        table.setPosition(0,0);

        prevButton = new TextButton("Previous", skin, "default");
        nextButton = new TextButton("Next", skin, "default");

        prevButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("previous button", "confirm previous");
                buttonLogged = "previous";
                clickFunction(textArea);
                event.stop();
            }
        });

        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Next button", "confirm next");
                buttonLogged = "next";
                clickFunction(textArea);
                event.stop();
            }
        });

        table.add(textArea).size(stage.getWidth(),stage.getHeight()/2).padBottom(5);
        table.row();
        table.add(prevButton).size(150,50).align(Align.bottomLeft).padLeft(170);
        table.add(nextButton).size(150, 50).align(Align.bottomRight).padRight(170);

        textArea.setColor(181.0f/255.0f,122.0f/255.0f,232.0f/255.0f,255.0f/255.0f);
        stage.addActor(table);

        batch = new SpriteBatch();
        sprite = new Sprite(new Texture(Gdx.files.internal("core/assets/pause.png")));
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage, this);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render()
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        sprite.draw(batch);
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        sprite.setFlip(!sprite.isFlipX(),sprite.isFlipY());
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    private void clickFunction(Dialog SS){
        if (buttonLogged.equals("previous") && counter > 0)
        {
            counter --;
            SS.text(dialogue[counter]);
        }

        if (buttonLogged.equals("next") && counter < dialogue.length - 1)
        {
            counter ++;
            SS.text(dialogue[counter]);
        }
        buttonLogged = "";
    }
}
