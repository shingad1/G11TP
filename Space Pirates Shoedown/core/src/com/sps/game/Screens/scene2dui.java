package com.sps.game.Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class scene2dui extends ApplicationAdapter
{
    private Stage stage;
    private Skin skin;

    private SpriteBatch batch;
    private Sprite sprite, sprite1;

    private Table table;
    private TextButton prevButton, nextButton;

    @Override
    public void create()
    {
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        stage = new Stage(new ScreenViewport());

        table = new Table();
        table.setWidth(stage.getWidth());
        table.align(Align.center| Align.bottom);

        table.setPosition(0,0);

        prevButton = new TextButton("prev",skin);
        //prevButton.addListener((EventListener) this);
        nextButton = new TextButton("next",skin);
        //nextButton.addListener((EventListener) this);

        table.padBottom(30);
        table.add(prevButton).size(150,50).padRight(250);
        table.add(nextButton).size(150,50);

        stage.addActor(table);

        batch = new SpriteBatch();

        sprite = new Sprite(new Texture(Gdx.files.internal("core/assets/playbutton.png")));
        //sprite1 = new Sprite(new Texture(Gdx.files.internal("core/assets/QuitButton.png")));
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/2);
        Gdx.input.setInputProcessor(stage);
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
        //prevButton.addListener(new ActionListener())
        {
            //public void actionPerformed(ActionEvent click)
            {
                //JOptionPane.showMessageDialog("clicked");
            }
        }

        /*scene2dui scene = new scene2dui();
        scene.create();
        scene.render(); */
    }
}
