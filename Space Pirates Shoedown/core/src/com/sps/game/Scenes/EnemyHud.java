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
    private BasicEnemy enemy;
    Label enemyHealthLabel;
    Label enemyHealthCountLabel;

    private FitViewport viewport;
    public Stage stage;

    public EnemyHud(SpriteBatch sb, BasicEnemy enemy)
    {
        this.enemy = enemy;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);
    }

    public void update(){
        enemyHealthLabel = new Label("Enemy Health",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        enemyHealthCountLabel = new Label(String.format("%d", enemy.getHealth()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        formatting();
    }

    public void formatting()
    {
        stage = new Stage();//formats the labels
        Table table = new Table(); //creates a table so it can be formatted properly
        table.top().right(); //places it at the top right
        table.setFillParent(true);
        table.add(enemyHealthLabel).padLeft(20); //it is padded
        table.row();
        table.add(enemyHealthCountLabel).padLeft(20);
        stage.addActor(table);
    }
}
