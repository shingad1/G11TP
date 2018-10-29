package com.sps.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sps.game.Controller.PlayerController;
import com.sps.game.Scenes.HudScene;
import com.sps.game.SpacePiratesShoedown;
import com.sps.game.Sprites.Player;

public class PlayScreen implements Screen {

    private static final String ASSETS_PATH = "core/assets/tiledassets/";
    private SpacePiratesShoedown game;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera gamecam; //what the view port displays
    private Viewport gameport;
    private HudScene hud;
    private Boolean buttonPressed = false; //Used to stop the player being able to input while an action is being taken
    private int tickCount = 0; //Used to break down an action into separate ticks

    //Box2D Variables
    private World world;
    private Box2DDebugRenderer b2dr;

    //Adding a sprite
    private SpriteBatch batch;
    private Texture player;
    private Player p;
    private PlayerController controller;

    public PlayScreen(SpacePiratesShoedown game){
        this.game = game;
        gamecam = new OrthographicCamera(480,480);
        gameport = new FitViewport(1600, 1600, gamecam);
        hud = new HudScene(game.batch);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(ASSETS_PATH + "testMap.tmx"); //tmx file of map itself;    HackMap.tmx
        renderer = new OrthogonalTiledMapRenderer(map); //renders the tmx file provided
        gamecam.position.set(464, 944, 0); //positions gamecam

        player = new Texture(ASSETS_PATH + "tempCharacter.png");
        batch = new SpriteBatch();
        p = new Player(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        controller = new PlayerController(p);
        /*
        world = new World(new Vector2(0, 0), true); //2nd param makes any 'not awake' object are not processed in collisions
        b2dr = new Box2DDebugRenderer();

        //need to change
        BodyDef bdef = new BodyDef(); //for every object in the game, defines what body consists off, used in world
        PolygonShape shape = new PolygonShape(); //used in fixture def
        FixtureDef fdef = new FixtureDef(); //for every object in the game, used in bodydef
        Body body;
            for(MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
                Rectangle rect = ((RectangleMapObject) object).getRectangle();
                bdef.type = BodyDef.BodyType.StaticBody;
                bdef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);
                body = world.createBody(bdef);
                shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
                fdef.shape = shape;
                body.createFixture(fdef);
            }

            //for house
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject)object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX()+rect.getWidth()/2, rect.getY() + rect.getHeight() /2);
            body = world.createBody(bdef);
            shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
        */
    }

    @Override
    public void show() {
        //specify which controller to use
        Gdx.input.setInputProcessor(controller);
    }

    public void handleInput(float dt){
        if(Gdx.input.isTouched() && !(buttonPressed)){

            buttonPressed = true;
        }
        if (buttonPressed && tickCount < 16){
            gamecam.position.x += 2; //temp, changes position of game cam
            tickCount++;
        } else {
            buttonPressed = false;
            tickCount = 0;
        }
    }

    //checks to see if any inputs are happening
    public void update(float dt){
        handleInput(dt);
        //world.step(1/60f, 6,2); //affects how 2 bodies react during a collision; 60 times a second
        gamecam.update(); //update every iteration of the game cycle
        renderer.setView(gamecam); //renders only what the gamecam can see
    }

    @Override
    public void render(float delta) {
        update(delta); //render method keeps getting called
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render();

//        b2dr.render(world, gamecam.combined);
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined); //setting the display what the hud should see
        hud.stage.draw(); //actually drawing the graphics
        batch.begin();
        batch.draw(player, p.getX(), p.getY(), 140, 110); //may want to create a settings class
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
