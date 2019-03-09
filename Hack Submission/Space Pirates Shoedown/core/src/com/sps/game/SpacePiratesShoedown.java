package com.sps.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sps.game.Screens.MenuScreen;

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
}
