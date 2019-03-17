package com.sps.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sps.game.Screens.MenuScreen;
import com.sps.game.Screens.SplashWorker;

/**
 * This class creates the game and launches the Menu Screen.
 * @author Miraj Shah, Miguel Abaquin, Devin Shingadia.
 * @version 1.0
 */

public class SpacePiratesShoedown extends Game {

	/**
	 *
	 * @see #create
	 */
	public SpriteBatch batch;
	private SplashWorker splashWorker;

	@Override
	public void create () {
		batch = new SpriteBatch(); //check if this is needed
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();//delegates menu method to whatever screen is active at the time
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public SplashWorker getSplashWorker() {
		return splashWorker;
	}

	public void setSplashWorker(SplashWorker splashWorker) {
		this.splashWorker = splashWorker;
	}
}
