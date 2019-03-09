package com.sps.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sps.game.controller.PlayerController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import static javax.swing.UIManager.get;

public class DialogueHud {
    public Stage stage;
    private Viewport viewport;

    private InputProcessor oldInput;

    private int counter;
    private String[] dialogue;

    private HashMap<String, String[]> dialogHM;
    //private Array<String> values;
    private String[] val;

    private Table table;
    private TextButton prevButton, nextButton;

    private String buttonLogged = "";

    public Skin skin = new Skin(Gdx.files.internal("uiskin.json"));

    Dialog textArea = new Dialog("Dialogues", skin);

    Label label;


    public DialogueHud(SpriteBatch sb, PlayerController playerController) {

        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport, sb);
        counter = 0;
        dialogue = new String[3];

        dialogue[0] = "";
        dialogue[1] = "";
        dialogue[2] = "";

        dialogHM = new HashMap<String, String[]>();
        //values = new Array<String>(3);

        /*values.set(0, "");
        values.set(1, "");
        values.set(2, "");*/

        val = new String[3];
        val[0] = "";
        val[1] = "";
        val[2] = "";

    }

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
                Gdx.app.log("previous button", "confirm previous");
                System.out.println("Previous");
                buttonLogged = "previous";
                clickFunction();
                event.stop();
            }
        });

        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Next button", "confirm next");
                System.out.println("Next");
                buttonLogged = "next";
                clickFunction();
                event.stop();
            }
        });

        table.add(textArea).size(stage.getWidth(), stage.getHeight() / 2).padBottom(5);
        table.row();
        table.add(prevButton).size(150, 50).align(Align.bottomLeft).padLeft(200);
        table.add(nextButton).size(150, 50).align(Align.bottomRight).padRight(200);

        stage.addActor(table);
    }


    public void show(String npcName) {

        set(npcName);

        oldInput = Gdx.input.getInputProcessor();
        Gdx.input.setInputProcessor(stage);
    }

    public void update(String npcName) {
        if (Gdx.input.isKeyPressed(Input.Keys.B) && oldInput == null) {
            formatting();
            show(npcName);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.E) && oldInput != null) {
            stage.clear();
            Gdx.input.setInputProcessor(oldInput);
            oldInput = null;
        }
    }

    public void dispose() {
        stage.dispose();
    }

    private void clickFunction(){
        if (buttonLogged.equals("previous") && counter > 0)
        {
            textArea.getContentTable().clear();
            counter --;
            //textArea.text(dialogue[counter]).pack();
            setLabel();
            textArea.text(label);
        }

        if (buttonLogged.equals("next") && counter < dialogue.length - 1)
        {
            textArea.getContentTable().clear();
            counter ++;
            //textArea.text(dialogue[counter]).pack();
            setLabel();
            textArea.text(label);
        }
        buttonLogged = "";

    }

    private void set(String npcName)
    {
        try {
            readingFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (dialogHM.keySet().contains(npcName)) {
            dialogue[0] = val[0];
            dialogue[1] = val[1];
            dialogue[2] = val[2];
        }//dialogHM.get(npcName).get(2)//values.get(0)

        setLabel();
        textArea.text(label);
    }

    private void setLabel()
    {
        label = new Label(dialogue[counter], skin);
        label.setAlignment(Align.center);
        //textArea.getContentTable().add(label).width(850).row();
        if(label.getText().length >= textArea.getWidth()){
            label.setWrap(true);
        }
    }

    private void readingFile() throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader( "core/src/com/sps/game/Dialogue.txt"));

        String line;
        while((line = bufferedReader.readLine()) != null)
        {
            System.out.println(line);
            String[] temp = line.split(";");

            System.out.println(temp.toString());

            val[0] = temp[1];
            val[1] = temp[2];
            val[2] = temp[3];

            /*values.set(0, temp[1]);
            values.set(1, temp[2]);
            values.set(2, temp[3]);*/

            //values.add(temp[1], temp[2], temp[3]);
            /*values.add(temp[1]);
            values.add(temp[2]);
            values.add(temp[3]);*/

            dialogHM.put(temp[0], val);
        }
        bufferedReader.close();
    }
}
