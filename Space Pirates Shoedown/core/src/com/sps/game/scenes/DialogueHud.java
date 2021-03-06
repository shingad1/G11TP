package com.sps.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sps.game.controller.PlayerController;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.FileHandler;

/**
 * This class launches the play screen, from where the play last left off.
 * @author Mahamuda Akhter, Miraj Shah
 * @version 1.0
 */
public class DialogueHud {
    /**
     * Holds the stage that will display the dialogue
     */
    public Stage stage;
    /**
     * Displays what the user can see
     */
    private Viewport viewport;
    /**
     * Holds the old input processor
     */
    private InputProcessor oldInput;
    /**
     * Integer that will display the current of the dialogue.
     */
    private int counter;
    /**
     * Holds the dialogue for a specific NPC.
     */
    private ArrayList<String> dialogue;
    /**
     * Holds all the dialogue for each NPC. The key is a String of the NPCs name and the value is an ArrayList
     * containing the dialogue for the specific NPC
     */
    private HashMap<String, ArrayList<String>> dialogHM;
    /**
     * The table holds the previous and next buttons as well as the dialogue itself.
     */
    private Table table;
    /**
     * Buttons that controls the movement of the dialogue.
     */
    private TextButton prevButton, nextButton;
    /**
     * Holds the button that is pressed.
     */
    private String buttonLogged = "";
    /**
     * Holds the skin...
     */
    public Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
    /**
     * Displays the current line of text.
     */
    Dialog textArea = new Dialog("", skin);
    /**
     * Holds the current line of text.
     */
    Label label;
    /**
     * Holds a boolean value, to indicate if an NPC should move or not. True if the NPC can move, otherwise false.
     */
    public static boolean move;

    public DialogueHud(SpriteBatch sb, PlayerController playerController) {
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);
        counter = 0;
        dialogue = new ArrayList<String>();
        dialogHM = new HashMap<String, ArrayList<String>>();

        try {
            readingFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a text box and displays that along with the previous and next buttons.
     */
    private void formatting() {
        stage = new Stage();
        table = new Table(skin);
        table.setWidth(stage.getWidth());
        table.setHeight(stage.getHeight() / 2);
        table.align(Align.center | Align.bottom);
        table.padBottom(10);
        table.setFillParent(true);

        table.setPosition(0, 0);

        prevButton = new TextButton("Previous", skin, "default");
        nextButton = new TextButton("Next", skin, "default");

        textArea.setColor(181.0f / 255.0f, 122.0f / 255.0f, 232.0f / 255.0f, 255.0f / 255.0f);

        prevButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                buttonLogged = "previous";
                clickFunction();
                event.stop();
            }
        });

        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                buttonLogged = "next";
                clickFunction();
                event.stop();
            }
        });

        table.add(textArea).size(stage.getWidth(), stage.getHeight() / 3).padBottom(5);
        table.row();
        table.add(prevButton).size(150, 50).align(Align.bottomLeft).padLeft(200);
        table.add(nextButton).size(150, 50).align(Align.bottomRight).padRight(200);

        stage.addActor(table);
    }

    /**
     *
     * @param npcName
     */
    public void show(String npcName) {
        set(npcName);
        oldInput = Gdx.input.getInputProcessor();
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Checks the users input, if B is pressed, it displays the text. If E is pressed, the dialogue button is closed.
     * @param npcName
     */
    public void update(String npcName) {
        if (Gdx.input.isKeyPressed(Input.Keys.B) && oldInput == null) {
            formatting();
            show(npcName);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.N) && oldInput != null) {
            stage.clear();
            dialogue.clear();
            counter = 0;
            Gdx.input.setInputProcessor(oldInput);
            oldInput = null;
            if(textArea.getContentTable() != null){ //check if needed
                textArea.getContentTable().clear();
            }
        }
    }

    /**
     * Disposes the stage.
     */
    public void dispose() {
        stage.dispose();
    }

    /**
     * Updates the dialogue, according to what button is pressed.
     */
    private void clickFunction(){
        if (buttonLogged.equals("previous") && counter > 0)
        {
            textArea.getContentTable().clear();
            counter --;
            setLabel();
            textArea.text(label);
        }

        if (buttonLogged.equals("next") && counter < dialogue.size() - 1)
        {
            textArea.getContentTable().clear();
            counter ++;
            setLabel();
            textArea.text(label);
            if(counter == dialogue.size() - 1) {
                counter = 0;
                dialogue.clear();
            }
        }
        buttonLogged = "";

    }

    /**
     * Sets the dialogue to be displayed according to what NPC the user is near.
     * @param npcName
     */
    private void set(String npcName)
    {
        for(String key : dialogHM.keySet()) {
            if (key.equals(npcName)) {
                for(int i = 0; i < dialogHM.get(key).size(); i++){
                    dialogue.add(dialogHM.get(key).get(i));
                }
            }
        }
        setLabel();
        textArea.text(label);
    }

    /**
     * Sets the label to be displayed according to the current line its on.
     */
    private void setLabel()
    {
        label = new Label(dialogue.get(counter), skin);
        label.setAlignment(Align.center);
        if(label.getText().length >= textArea.getWidth()){
            label.setWrap(true);
        }
    }

    /**
     * Reads the text file and adds it to the HashMap, separating it by each NPC.
     * @throws IOException
     */
    private void readingFile() throws IOException
    {
        FileHandle file = Gdx.files.internal("Dialogue.txt");
        String text = file.readString();

        String[] line = text.split("\n");

        for(int i = 0; i < line.length; i++)
        {
            ArrayList<String> val = new ArrayList<String>();
            String[] temp = line[i].split(";");

            for(int x = 1; x < temp.length; x++){
                val.add(temp[x]);
            }

            dialogHM.put(temp[0], val);
        }
    }

    /**
     * Returns a boolean indicating if a NPC can move or not. True if they can, False if they can't
     * @return boolean move
     */
    public static boolean getMove(){
        return move;
    }
}
