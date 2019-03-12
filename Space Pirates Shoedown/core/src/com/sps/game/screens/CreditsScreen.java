package com.sps.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.sps.game.SpacePiratesShoedown;

public class CreditsScreen implements Screen {

    /**
     * Holds the path of the credits text file.
     */
    private static String CREDITS_PATH = "core/assets/credits.txt";

    /**
     * Holds the stage that will display the credits.
     */
    private Stage stage;
    /**
     * Holds an instance of the scroll pane
     */
    private ScrollPane scrollPane;
    /**
     * Holds an instance of the game.
     */
    private SpacePiratesShoedown _game;
    /**
     * Creates a new skin
     */
    private Skin skin = new Skin(Gdx.files.internal("core/assets/MenuResources/statusui.json"), new TextureAtlas(Gdx.files.internal("core/assets/MenuResources/statusui.atlas")));

    public CreditsScreen(SpacePiratesShoedown game){
        _game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        FileHandle file = Gdx.files.internal(CREDITS_PATH);
        String text = file.readString();
        Label label = new Label(text, skin, "credits");
        label.setAlignment(Align.top | Align.center);
        label.setWrap(true);
        scrollPane = new ScrollPane(label);
        scrollPane.addListener(new ClickListener(){
                            @Override
                            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                                return true;
                            }
                            @Override
                            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                                scrollPane.setScrollY(0);
                                scrollPane.updateVisualScroll();
                                _game.setScreen(new MenuScreen(_game));
                            }
                }
        );

        Table table = new Table();
        table.center();
        table.setFillParent(true);
        table.defaults().width(Gdx.graphics.getWidth());
        table.add(scrollPane);
        stage.addActor(table);
    }

    @Override
    public void show() {
        scrollPane.setVisible(true);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        if(delta == 0){
            return;
        }
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        scrollPane.setScrollY(scrollPane.getScrollY() + delta * 20);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().setScreenSize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        scrollPane.setVisible(false);
        scrollPane.setScrollY(0);
        scrollPane.updateVisualScroll();
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.clear();
        scrollPane = null;
        stage.dispose();
    }
}
