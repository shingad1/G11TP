package com.sps.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * This class contains the different labels with the information need for the player.
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia
 * @version 1.0
 */

public class HudScene  {
    /**
     * Creates a stage where graphics can be drawn on.
     */
    public Stage stage;
    /**
     * Sets up a separate camera for the HUD so that the hud stays stationary.
     */
    private Viewport viewport;
    /**
     * Temporary variable used for example, placeholder for the player's real gold.
     */
    private Integer gold;

    /**
     * Label that will display the value 'Gold'.
     */
    Label goldLabel;
    /**
     * Label that displays the value of the gold variable.
     */
    Label goldCountLabel;

    public HudScene(SpriteBatch sb){
        gold = 100;
        //Instantiating the viewport
        viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), new OrthographicCamera());
        //Instantiating the stage
        stage = new Stage(viewport, sb);

        //Creating a Table and instantiating it, used for layout of the HUD
        Table table = new Table();
        //All objects of the table will originate from top left of the table
        table.top().left();
        //The table will have the same width and height as its parent (screen)
        table.setFillParent(true);

        //Instantiating the goldLabel label with the BitmapFont font and the colour white
        goldLabel = new Label("Gold",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //Instantiating the goldCountLabel label with the BitmapFont font and the colour white
        goldCountLabel = new Label(String.format("%03d",gold),new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        //Adding the goldLabel label to the table with a padding of 10px from the top and left
        table.add(goldLabel).padTop(10).padLeft(10);
        //Going to a new row
        table.row();
        //Adding the goldCountLabel label to the table with padding of 10px from the left
        table.add(goldCountLabel).padLeft(10);

        //putting the table on the stage so that it can be drawn
        stage.addActor(table);
    }
}
