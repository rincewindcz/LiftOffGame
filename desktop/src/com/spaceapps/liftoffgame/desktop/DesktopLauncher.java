package com.spaceapps.liftoffgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.spaceapps.liftoffgame.LiftOffGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1024;
        config.height = 600;
        config.resizable = false;
		new LwjglApplication(LiftOffGame.getInstance(), config);
	}
}
