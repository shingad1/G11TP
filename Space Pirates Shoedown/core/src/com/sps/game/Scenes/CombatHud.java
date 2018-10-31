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


/**
 * This class will display the relevant information the user will need when in a battle with an enemy
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia
 * @version 1.0
 */

public class CombatHud {

    /**
     * Creates a stage where graphics can be drawn on.
     */
    private Stage stage;
    /**
     * Sets up a separate camera for the HUD so that the hud stays stationary.
     */
    private Viewport viewport;
    /**
     * Holds the value of the player health.
     */
    private Integer playerHealth;
    /**
     * Holds the value of the enemies health.
     */
    private Integer enemyHealth;
    /**
     * Label that will display the value 'Player Health'.
     */
    Label playerHealthLabel;
    /**
     * Label that will display the value 'Enemy Health'.
     */
    Label enemyHealthLabel;
    /**
     * Label that will display the value of the playerHealth field.
     */
    Label playerHealthCountLabel;
    /**
     * Label that will display the value of the enemyHealth field.
     */
    Label enemyHealthCountLabel;

    public CombatHud(SpriteBatch sb){
        playerHealth = 100; //link to player class
        enemyHealth = 100; //link to enemy class

        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);

        playerHealthLabel = new Label("Player Health",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        playerHealthCountLabel = new Label(String.format("03d",playerHealth), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        enemyHealthLabel = new Label("Enemy Health",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        enemyHealthCountLabel = new Label(String.format("03d", enemyHealth), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
    }

}
