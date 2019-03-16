package com.sps.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
/**
 * This class is responsible for the third label which advice the users on what to do
 * @author Miguel Abaquin, Mahamuda Akhter
 * @version 1.0
 */
public class ThirdHud
{
    /**
     * Holds the Attack labels information
     * @see #update(String[] array)
     */
    private Label AttackLabel; //contains the text displayed to the label
    /**
     * Sets up a separate camera for the HUD so that the hud stays stationary.
     * @see #ThirdHud
     */
    private FitViewport viewport;
    /**
     * Creates a stage where graphics can be drawn on.
     * @see #ThirdHud
     */
    public Stage stage;

    public ThirdHud(SpriteBatch sb)
    {
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);
    }
    /**
     * this method updates the information on the screen, also calls onto another method
     */
    public void update(String[] array)
    {
        AttackLabel = new Label ("Press the key 'Q'" + array[0] + "\n" +
                "Press the key 'W'" + array[1] + "\n" +
                "Press the key 'E'" + array[2] + "\n" +
                "Press the key 'R'" + array[3] + "\n" +
                "Press the key 'A'" + array[4] + "\n" +
                "Press the key 'S'" + array[5] + "\n" +
                "Press the key 'D'" + array[6] + "\n", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        formatting();
    }
    /**
     * this method formats the labels so it displayed on the correct position
     */
    public void formatting(){
        stage = new Stage();
        Table table = new Table();
        table.bottom();
        table.setFillParent(true);
        table.add(AttackLabel).padLeft(20);
        stage.addActor(table);
    }
}
