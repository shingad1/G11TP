package com.sps.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.sps.game.Sprites.BasicEnemy;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


public class EnemyHud
{
    private int enemyHealth;
    Label enemyHealthLabel;
    Label enemyHealthCountLabel;

    private FitViewport viewport;
    public Stage stage;

    public EnemyHud(SpriteBatch sb, BasicEnemy enemy)
    {
        enemyHealth = enemy.getHealth();
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);
    }

    public void update(){
        enemyHealthLabel = new Label("Enemy Health",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        enemyHealthCountLabel = new Label(String.format("%d", enemyHealth), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        formatting();
    }

    public void formatting(){
        stage = new Stage();
        Table table = new Table();
        //table.top();
        table.top().right();
        table.setFillParent(true);
        table.add(enemyHealthLabel).padLeft(20);
        table.row();
        table.add(enemyHealthCountLabel).padLeft(20);
        stage.addActor(table);
    }
}
