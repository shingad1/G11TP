package com.sps.game.Controller;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sps.game.Screens.PlayScreen;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DialogueController extends ApplicationAdapter implements InputProcessor
{
    private SpriteBatch batch;
    private Sprite sprite;

    private int counter;
    private String[] dialogue;

    private HashMap<String, ArrayList<String>> dialogHM;
    private ArrayList<String> values;

    private Table table;
    private TextButton prevButton, nextButton, exitButton;

    private String buttonLogged = "";

    private InputMultiplexer inputMultiplexer;

    public Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
    public Stage stage = new Stage(new ScreenViewport());

    final Dialog textArea = new Dialog("Dialogues", skin);

    public DialogueController(){
        counter = 0;
        dialogue = new String[3];

        dialogue[0] = "";
        dialogue[1] = "";
        dialogue[2] = "";

        dialogHM = new HashMap<String, ArrayList<String>>();
        values = new ArrayList<String>();

        batch = new SpriteBatch();
        sprite = new Sprite(new Texture(Gdx.files.internal("core/assets/pause.png")));
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //textArea.text(dialogue[counter]);
    }

    public void create(){
        table = new Table();
        table.setWidth(stage.getWidth());
        table.setHeight(stage.getHeight()/2);
        table.align(Align.center | Align.bottom);
        table.padBottom(10);

        table.setPosition(0,0);

        table.add(textArea).size(stage.getWidth(),stage.getHeight()/2).padBottom(5);
        table.row();

        prevButton = new TextButton("Previous", skin, "default");
        nextButton = new TextButton("Next", skin, "default");
        exitButton = new TextButton("Exit", skin, "default");

        table.add(prevButton).size(150,50);
        table.row();
        table.add(exitButton).size(150,50);
        table.row();
        table.add(nextButton).size(150, 50);

        textArea.setColor(181.0f/255.0f,122.0f/255.0f,232.0f/255.0f,255.0f/255.0f);
        stage.addActor(table);

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

        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                //if(Gdx.input.isKeyPressed(Input.Keys.B)){
                Gdx.app.log("Exit button", "confirm exit");
                stage.clear();
                Gdx.input.setInputProcessor(inputMultiplexer);
                inputMultiplexer = null;
                PlayScreen.dialogBoolean = false;
                //batch.end();

            }
        });

        inputMultiplexer = new InputMultiplexer(stage, this);
        Gdx.input.setInputProcessor(inputMultiplexer);


        /*inputMultiplexer = new InputMultiplexer(stage, this);
        Gdx.input.setInputProcessor(inputMultiplexer);*/
    }

    @Override
    public void render()
    {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        sprite.draw(batch);
        batch.end();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    private void readingFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("core/src/com/sps/game/Dialogue.txt"));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String temp[] = line.split("\\;");

            values.add(temp[1]);
            values.add(temp[2]);
            values.add(temp[3]);
            dialogHM.put(temp[0], values);
        }
        bufferedReader.close();

        //textArea.text(dialogue[counter]);
    }

    public void set(String npcName)
    {
        try {
            readingFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (dialogHM.keySet().contains(npcName)) {
            dialogue[0] = values.get(0);
            dialogue[1] = values.get(1);
            dialogue[2] = values.get(2);
        }
        textArea.text(dialogue[counter]);
    }

    private void clickFunction(){
        if (buttonLogged.equals("previous") && counter > 0)
        {
            counter --;
            textArea.text(dialogue[counter]);
        }

        if (buttonLogged.equals("next") && counter < dialogue.length - 1)
        {
            counter ++;
            textArea.text(dialogue[counter]);
        }
        buttonLogged = "";
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        sprite.setFlip(!sprite.isFlipX(),sprite.isFlipY());
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
