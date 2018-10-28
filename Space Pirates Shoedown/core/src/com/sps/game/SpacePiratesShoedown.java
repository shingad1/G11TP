package com.sps.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sps.game.Screens.MenuScreen;

public class SpacePiratesShoedown extends Game {
	public SpriteBatch batch;
	public Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");
		setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();//delegates menu method to whatever screen is active at the time
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
