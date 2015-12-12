package pi4.tetris.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pi4.tetris.Tetris;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Tetris(), config);
		config.title = "Tetris";
		config.width = 272;
		config.height = 408;
		new LwjglApplication(new Tetris(), config);
	}
}
