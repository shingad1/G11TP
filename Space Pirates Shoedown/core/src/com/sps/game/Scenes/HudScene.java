package com.sps.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sps.game.Sprites.Player;

/**
 * This class contains the different labels with the information need for the player.
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia
 * @version 1.0
 */

public class HudScene  {
    /**
     * Stores the player character so the values of the player can be retrieved for the hud
     * @see #update()
     */
    private Player player;
    /**
     * Creates a stage where graphics can be drawn on.
     * @see #HudScene
     */
    public Stage stage;
    /**
     * Sets up a separate camera for the HUD so that the hud stays stationary.
     * @see #HudScene
     */
    private Viewport viewport;
    /**
     * Temporary variable used for example, placeholder for the player's real gold.
     * @see #update()
     */
    private Integer gold;

    /**
     * Label that will display the value 'Gold'.
     * @see #update()
     */
    Label goldLabel;
    /**
     * Label that displays the value of the gold variable.
     * @see #update()
     */
    Label goldCountLabel;

    private Image pause, story, backpack;

    private Texture saveTexture;

    private TextureRegion saveTextureRegion;

    private TextureRegionDrawable saveTextureRegionDrawable;

    private ImageButton saveButton;

    //public Inventory inventory;


    public HudScene(SpriteBatch sb, Player p){
        gold = 100;
        //Instantiating the viewport
        viewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), new OrthographicCamera());
        //Instantiating the stage
        stage = new Stage(viewport, sb);
        //Instantiating the goldLabel label with the BitmapFont font and the colour white
        goldLabel = new Label("Gold",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //Instantiating the goldCountLabel label with the BitmapFont font and the colour white
        goldCountLabel = new Label(String.format("%03d",gold),new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        pause = new Image(new Texture("core/assets/pause.png"));
        story = new Image(new Texture("core/assets/story.png"));
        backpack = new Image(new Texture("core/assets/Inventory/backpack.png"));

        saveTexture = new Texture("core/assets/Buttons/SaveButton.png");
        saveTextureRegion = new TextureRegion(saveTexture);
        saveTextureRegionDrawable = new TextureRegionDrawable(saveTextureRegion);
        saveButton = new ImageButton(saveTextureRegionDrawable);
        player = p;
      //  inventory = new Inventory();
    }
    /**
     * this method updates the GoldCounterLabel and also calls onto another method
     */
    public void update(){
        goldCountLabel = new Label(String.format("%02d",player.getGold()),new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        formatting();
    }
    /**
     * this method formats the labels so it displayed on the correct position
     */
    private void formatting(){
        stage = new Stage();
        //Creating a Table and instantiating it, used for layout of the HUD
        Table table = new Table();
        //All objects of the table will originate from top left of the table
        table.top().left();
        //The table will have the same width and height as its parent (screen)
        table.setFillParent(true);
        //Adding the goldLabel label to the table with a padding of 10px from the top and left
        table.add(goldLabel).padTop(10).padLeft(10);
        /*//Going to a new row
        table.row();*/
        //Adding the goldCountLabel label to the table with padding of 10px from the left
        table.add(goldCountLabel).padLeft(10);
        //putting the table on the stage so that it can be drawn
        table.add(pause).size(50,50).padLeft(20);
        table.add(story).size(50,50).padLeft(20);
        table.add(backpack).size(50, 50).pad(20);
        stage.addActor(table);
        //stage.addActor(saveButton);
        //Gdx.input.setInputProcessor(stage);
/*
        saveButton.addListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                ProfileManager.getInstance().saveProfile();
                System.out.println("Game Saved");
                return true;
            }
        });*/
    }
}
