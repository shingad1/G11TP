package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.SpacePiratesShoedown;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = ("Space Pirates Shoedown");
		config.width = 100;
		config.height = 100;
		config.useGL30 = false;
		config.resizable = false;
		new LwjglApplication(new SpacePiratesShoedown(), config);
	}
}
