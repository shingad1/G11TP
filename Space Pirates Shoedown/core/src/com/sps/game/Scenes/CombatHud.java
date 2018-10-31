package com.sps.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class CombatHud {

    private Stage stage;
    private Viewport viewport;

    private Integer playerHealth;
    private Integer enemyHealth;

    Label playerHealthLabel;
    Label enemyHealthLabel;
    Label playerHealthCountLabel;
    Label enemyHealthCountLabel;

    public CombatHud(SpriteBatch sb){
        playerHealth = 100; //link to player class
        enemyHealth = 100; //link to enemy class

        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);

        playerHealthLabel = new Label("Player Health",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        playerHealthCountLabel = new Label(String.format("03d",playerHealth), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        enemyHealthLabel = new Label("Enemy Health",new Label.LabelStyle(new BitmapFont(), Color.WHITE));

    }

}
