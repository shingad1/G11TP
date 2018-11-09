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
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sps.game.Sprites.BasicEnemy;
import com.sps.game.Sprites.Player;


/**
 * This class will display the relevant information the user will need when in a battle with an enemy
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia
 * @version 1.0
 */

public class CombatHud
{
    /**
     * Creates a stage where graphics can be drawn on.
     */
    public Stage stage;
    /**
     * Sets up a separate camera for the HUD so that the hud stays stationary.
     */
    private Viewport viewport;
    /**
     * Holds the player
     */
    private Player player;
    /**
     * Label that will display the value 'Player Health'.
     */
    Label playerHealthLabel;
    /**
     * Label that will display the value of the playerHealth field.
     */
    Label playerHealthCountLabel;

    public CombatHud(SpriteBatch sb, Player player, BasicEnemy enemy){
        this.player = player;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);

    }
    /**
     * this method updates the players information on the screen, also calls onto another method
     */
    public void update(){
        playerHealthLabel = new Label("Player Health",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        playerHealthCountLabel = new Label(String.format("%d",player.getHP()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        formatting();
    }
    /**
     * this method formats the labels so it displayed on the correct position
     */
    public void formatting(){
        stage = new Stage();
        Table table = new Table();
        table.top().left();
        table.setFillParent(true);
        table.add(playerHealthLabel).padRight(20);
        table.row();
        table.add(playerHealthCountLabel).padRight(20);
        stage.addActor(table);
    }

}
