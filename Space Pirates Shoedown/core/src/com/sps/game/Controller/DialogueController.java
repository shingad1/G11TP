package com.sps.game.Controller;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
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

public class DialogueController extends ApplicationAdapter implements InputProcessor
{
    private SpriteBatch batch;
    private Sprite sprite;

    int counter;
    String[] dialogue;

    private Table table;
    private TextButton prevButton, nextButton, exitButton;

    private String buttonLogged = "";

    private PlayScreen playScreen;
    private Boolean temp,temp1;

    public Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
    public Stage stage = new Stage(new ScreenViewport());

    final Dialog textArea = new Dialog("Dialogues", skin);

    public DialogueController()
    {
        counter = 0;
        dialogue = new String[3];

        dialogue[0] = "";
        dialogue[1] = "";
        dialogue[2] = "";

        textArea.text(dialogue[counter]);
    }

    public void create(String npcName) throws IOException {
        table = new Table();
        table.setWidth(stage.getWidth());
        table.setHeight(stage.getHeight()/2);
        table.align(Align.center | Align.bottom);
        table.padBottom(10);

        table.setPosition(0,0);

        prevButton = new TextButton("Previous", skin, "default");
        nextButton = new TextButton("Next", skin, "default");
        exitButton = new TextButton("Exit", skin, "default");

        prevButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("previous button", "confirm previous");
                buttonLogged = "previous";
                clickFunction();
                event.stop();
            }
        });

        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Next button", "confirm next");
                buttonLogged = "next";
                clickFunction();
                event.stop();
            }
        });

        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                stage.dispose();
                Color oldColour = sprite.getColor();
                sprite.setColor(oldColour.r, oldColour.g, oldColour.b, 0);
                //batch.begin();
                //batch.dispose();
                //batch.end();
                /*temp = false;
                playScreen.setBool(temp);*/
            }
        });

        table.add(textArea).size(stage.getWidth(),stage.getHeight()/2).padBottom(5);
        table.row();
        table.add(prevButton).size(150,50);
        table.row();
        table.add(exitButton).size(150,50);
        table.row();
        table.add(nextButton).size(150, 50);


        textArea.setColor(181.0f/255.0f,122.0f/255.0f,232.0f/255.0f,255.0f/255.0f);
        stage.addActor(table);

        batch = new SpriteBatch();
        sprite = new Sprite(new Texture(Gdx.files.internal("core/assets/pause.png")));
        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage, this);
        Gdx.input.setInputProcessor(inputMultiplexer);

        /*openFile();
        readFile(npcName);
        closeFile();*/

        readingFile(npcName);
        //textArea.text(dialogue[counter]);
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

    /*public void openFile()
    {
        try{
            scanner = new Scanner(new File("core/src/com/sps/game/Dialogue.txt"));
        }catch (Exception e){
            System.out.println("Could not find file :(");
        }
    }

    public void readFile(String npcName)
    {
        while(scanner.hasNext())
        {
            String name = scanner.next();
            String first = scanner.next();
            String second = scanner.next();
            String third = scanner.next();

            if(npcName.equals(name))
            {
                dialogue[0] = first;
                dialogue[1] = second;
                dialogue[2] = third;
            }
        }
        textArea.text(dialogue[counter]);
    }

    public void closeFile()
    {
        scanner.close();
    }*/

    private void readingFile(String npcName) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader( "core/src/com/sps/game/Dialogue.txt"));

        String line;
        while((line = bufferedReader.readLine()) != null)
        {
            String temp[] = line.split("\\;");

            String name = temp[0];
            String first = temp[1];
            String second = temp[2];
            String third = temp[3];
            //String aBoolean = temp[4];

            if(npcName.equals(name))
            {
                //if(aBoolean.equals("false")) {
                    dialogue[0] = first;
                    dialogue[1] = second;
                    dialogue[2] = third;

                    /*String tempFile = "temp.txt";
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tempFile, true));
                    PrintWriter printWriter = new PrintWriter(bufferedWriter);

                    printWriter.println(dialogue[0] + ";" + dialogue[1] + ";" + dialogue[2] + ";" + "true");
                    tempFile.renameTo();
                }*/
            }
        }
        bufferedReader.close();
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
