package com.sps.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class ThirdHud
{
    private Label AttackLabel;

    private FitViewport viewport;
    public Stage stage;

    public ThirdHud(SpriteBatch sb)
    {
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);
    }

    public void update()
    {
        AttackLabel = new Label ("To Attack: press the key'Q'", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        formatting();
    }

    public void formatting(){
        stage = new Stage();
        Table table = new Table();
        table.bottom();
        table.setFillParent(true);
        table.add(AttackLabel).padLeft(20);
        stage.addActor(table);
    }
}
