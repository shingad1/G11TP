package com.sps.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sps.game.SpacePiratesShoedown;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new SpacePiratesShoedown(), config);
		config.width = 640; //width of the window
		config.height = 640; //height of the window;
		config.title = ("Space Pirates Shoedown");
		config.useGL30 = false;
		config.resizable = false;
	}
}
