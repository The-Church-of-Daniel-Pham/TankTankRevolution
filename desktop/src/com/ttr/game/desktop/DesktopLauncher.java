package com.ttr.game.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ttr.TankTankRevolution;
import com.ttr.utils.Constants;

//Authors: Gokul Swaminathan

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration(); // new config
		config.title = "Tank Tank Revolution";
		config.addIcon("menu/dpham_32.png", FileType.Internal);	// app icon; cannot put in assets since this class is created earlier
		config.resizable = false;	// cannot resize window manually
		config.vSyncEnabled = false; // vertical sync is true
		config.foregroundFPS = 0; // setting to 0 disables foreground fps throttling
		config.backgroundFPS = 30; // limits to 30 fps when in background

		new LwjglApplication(new TankTankRevolution(), config); // creates the openGL window
		Constants.updateWindow();							// update window mode and resolution
	}
}