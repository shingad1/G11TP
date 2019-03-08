package com.sps.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sps.game.SpacePiratesShoedown;
import com.sps.game.profile.ProfileManager;

public class LoadScreen implements Screen {

    /**
     * Constant field to direct where the file is located.
     */
    private static final String ASSETS_PATH = "core/assets/";
    /**
     * Holds a version of the game.
     * @see #render
     */
    private SpacePiratesShoedown game;
    /**
     * Holds background image for the Menu Screen.
     * @see #render #dispose
     */
    private Texture background;
    /**
     * Holds what the view port will display
     */
    private OrthographicCamera gamecam;
    /**
     * Displays what the user will see
     * @see #resize
     */
    private Viewport gameport;
    /**
     * Holds the list of all the game profiles
     */
    private List listProfiles;

    private Stage stage; //may change

    /**
     * Music of the load screen
     */
    private com.badlogic.gdx.audio.Music music;

    public LoadScreen(final SpacePiratesShoedown game){
        this.game = game;
        //loadButton = new Texture(ASSETS_PATH + "LoadButton.png");
        //following may need to change
        stage = new Stage();
        TextButton loadButton = new TextButton("Load", new Skin(Gdx.files.internal("core/assets/MenuResources/statusui.json"), new TextureAtlas(Gdx.files.internal("core/assets/MenuResources/statusui.atlas"))));
        TextButton backButton = new TextButton("Back", new Skin(Gdx.files.internal("core/assets/MenuResources/statusui.json"), new TextureAtlas(Gdx.files.internal("core/assets/MenuResources/statusui.atlas"))));

        ProfileManager.getInstance().storeAllProfiles();
        listProfiles = new List(new Skin(Gdx.files.internal("core/assets/MenuResources/statusui.json"), new TextureAtlas(Gdx.files.internal("core/assets/MenuResources/statusui.atlas"))), "inventory"); //takes in a skin and string
        Array<String> list = ProfileManager.getInstance().getProfilelList();
        listProfiles.setItems(list);

        ScrollPane scrollPane = new ScrollPane(listProfiles);
        scrollPane.setOverscroll(false, false);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setScrollbarsOnTop(true);

        Table table = new Table();
        Table bottomTable = new Table();
        //layout of menu
        table.center();
        table.setFillParent(true);
        table.padBottom(loadButton.getHeight());
        table.add(scrollPane).center();

        bottomTable.setHeight(loadButton.getHeight());
        bottomTable.setWidth(Gdx.graphics.getWidth());
        bottomTable.center();
        bottomTable.add(loadButton).padRight(50);//to change
        bottomTable.add(backButton).padRight(50);

        stage.addActor(table);
        stage.addActor(bottomTable);

        //Listeners
        loadButton.addListener(new ClickListener() {
                                    @Override
                                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                                        return true;
                                    }

                                    @Override
                                    public void touchUp(InputEvent event, float a, float y, int pointer, int button){
                                        if(listProfiles.getSelected() == null)
                                            return;
                                        String fileName = listProfiles.getSelected().toString();
                                        if(fileName != null && !fileName.isEmpty()){
                                            FileHandle file = ProfileManager.getInstance().getProfileFile(fileName);
                                            ProfileManager.getInstance().loadProfile();
                                            if(file != null){
                                                ProfileManager.getInstance().setCurrentProfile(fileName);
                                                PlayScreen.setGameState(PlayScreen.GameState.Loading);
                                                //LoadScreen.this.notify(); for changing audio

                                                game.setScreen(new HomeWorldScreen(game, new Vector2(0,0),736, 1280));//will need to change
                                            }
                                        }
                                    }
        });
        backButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button){
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float a, float y, int pointer, int button){
                dispose();
                game.setScreen(new MenuScreen(game));
            }
        });

        music = Gdx.audio.newMusic(Gdx.files.internal("core/assets/Music/bensound-newdawn.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
    }

    @Override
    public void show() {
        Array<String> list = ProfileManager.getInstance().getProfilelList();
        listProfiles.setItems(list);
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
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.clear();
        stage.dispose();
        music.dispose();
    }
}
