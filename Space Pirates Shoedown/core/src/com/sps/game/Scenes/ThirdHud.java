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
/**
 * This class is responsible for the third label which advice the users on what to do
 * @author Miguel Abaquin, Mahamuda Akhter
 * @version 1.0
 */
public class ThirdHud
{
    /**
     * Holds the Attack labels information
     * @see #update()
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
    public void update(String[] options)
    {
        AttackLabel = new Label (
                "Q: " + options[0] +
                     "W: " + options[1] +
                     "E: " + options[2] +
                     "R: " + options[3] +
                     "\n" +
                     "A: " + options[4] +
                     "S: " + options[5] +
                     "D: " + options[6]
                ,new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        formatting();
    }
    /**
     * this method formats the labels so it displayed on the correct position
     */
    public void formatting(){
        stage = new Stage();
        Table table = new Table();
        table.left();
        table.setFillParent(true);
        table.add(AttackLabel).padLeft(20);
        stage.addActor(table);
    }
}
