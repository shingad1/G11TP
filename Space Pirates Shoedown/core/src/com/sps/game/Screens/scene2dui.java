package com.sps.game.Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import javax.swing.*;

public class scene2dui extends ApplicationAdapter
{
    private Stage stage;
    private Skin skin;

    private SpriteBatch batch;
    private Sprite sprite, sprite1;

    public TextButton prevButton, nextButton;
    public Dialog dialog;

    int counter;
    String[] dialogue;

    private Table table;
    @Override
    public void create()
    {

        counter = 0;
        dialogue = new String[3];
        dialogue[0] = "hi";
        dialogue[1] = "whats up";
        dialogue[2] = "bye";

        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(new ScreenViewport());

        table = new Table();
        table.setWidth(stage.getWidth());
        table.setHeight(stage.getHeight()/2);
        table.align(Align.center| Align.bottom);
        table.padBottom(30);

        table.setPosition(0,0);

        prevButton = new TextButton("Previous", skin);
        nextButton = new TextButton("Next", skin);

        dialog = new Dialog(dialogue[counter], skin);

        table.add(prevButton).size(200,50);
        table.add(nextButton).size(200,50);
        stage.addActor(table);

        setupListeners();

        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();
        sprite = new Sprite(new Texture(Gdx.files.internal("core/assets/playbutton.png")));
        sprite1 = new Sprite(new Texture(Gdx.files.internal("core/assets/QuitButton.png")));
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/2);
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

    private void setupListeners()
    {
        /*scene2dui scene = new scene2dui();
        scene.create();
        scene.render(); */

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
    }

    public void frame()
    {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JButton button = new JButton("prev");

        panel.add(button);
        frame.add(panel);
    }
}
