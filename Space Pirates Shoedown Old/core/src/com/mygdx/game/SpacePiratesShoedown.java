package com.mygdx.game;

import com.mygdx.game.State.GameStateManager;
import com.mygdx.game.State.MenuState;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpacePiratesShoedown extends ApplicationAdapter {

	Texture img;

	public static int WIDTH;
	public static int HEIGHT;

	private GameStateManager gsm;
	private SpriteBatch batch; //should only have one of

	/**
	 States: Title, Create Player, Settings, Combat, Overworld, Inventory, Pause Menu
	 */
	public static String state = "title";

	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();

		//img = new Texture("badlogic.jpg");

		//Gets the size of the screen from the desktop Launcher
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();

		Gdx.gl.glClearColor(1, 0, 0, 1); //wipes clear the screen clean, and sb redraws everything
		gsm.push(new MenuState(gsm));

	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);

	/*
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	*/
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}
}
