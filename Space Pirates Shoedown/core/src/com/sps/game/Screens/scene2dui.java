package com.sps.game.Screens;

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

public class scene2dui extends ApplicationAdapter implements InputProcessor
{
    private Stage stage;
    private Skin skin;

    private SpriteBatch batch;
    private Sprite sprite, sprite1;

    int counter;
    String[] dialogue;

    private Table table;
    private TextButton prevButton, nextButton;

    public scene2dui()
    {
        counter = 0;
        dialogue = new String[3];

        dialogue[0] = "hi";
        dialogue[1] = "whats up";
        dialogue[2] = "bye";

        create();
        render();
        //System.out.println(dialogue[counter]);
    }

    @Override
    public void create()
    {
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(new ScreenViewport());

        table = new Table();
        table.setWidth(stage.getWidth());
        table.setHeight(stage.getHeight()/2);
        table.align(Align.center | Align.bottom);
        table.padBottom(30);

        table.setPosition(0,0);

        prevButton = new TextButton("Previous", skin, "default");
        nextButton = new TextButton("Next", skin, "default");

        final Dialog SS = new Dialog("Dialogues", skin, "default");

        prevButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("previous button", "confirm previous");
                event.stop();
            }
        });

        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Nex button", "confirm next");
                event.stop();
            }
        });

        table.add(SS).size(615,300).padBottom(30);
        table.row();
        table.add(prevButton).size(200,100).padBottom(30);
        table.row();
        table.add(nextButton).size(200, 50);

        if (counter > 0)
        {
            counter --;

            SS.text(dialogue[counter]);
            //System.out.println(SS.text(dialogue[counter]).show(stage));
        }

        if (counter < dialogue.length - 1)
        {
            counter ++;

            SS.text(dialogue[counter]);
        }

        stage.addActor(table);

        batch = new SpriteBatch();
        sprite = new Sprite(new Texture(Gdx.files.internal("core/assets/pause.png")));
        //sprite1 = new Sprite(new Texture(Gdx.files.internal("core/assets/QuitButton.png")));
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        /*Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                sprite.setFlip(false,!sprite.isFlipY());
            }
        },10,10,10000);*/

        InputMultiplexer im = new InputMultiplexer(stage, this);
        Gdx.input.setInputProcessor(im);
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
        return true;
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

    /*private void setupListeners()
    {
        *//*scene2dui scene = new scene2dui();
        scene.create();
        scene.render(); *//*

        prevButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (counter > 0)
                {
                    counter --;
                    dialog.text(dialogue[counter]);
                    dialog.show(stage);
                }
            }
        });

        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (counter < dialogue.length - 1)
                {
                    counter ++;
                    dialog.text(dialogue[counter]);
                    dialog.show(stage);
                }
            }
        });
    }*/

//    public void frame()
//    {
//        JFrame frame = new JFrame();
//        frame.setVisible(true);
//        frame.setSize(400,400);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        JPanel panel = new JPanel();
//        JButton button = new JButton("prev");
//
//        panel.add(button);
//        frame.add(panel);
//    }
}
