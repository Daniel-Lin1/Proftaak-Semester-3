package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.DistressAndConflict;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Distress and Conflict";
		config.useGL30 = true;
		//config.resizable = false;
		//config.fullscreen = true;
		//config.height = 1080;
		//config.width = 1920;
		config.foregroundFPS = 60;
		new LwjglApplication(new DistressAndConflict(), config);
	}
}
