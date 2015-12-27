package pi4.tetris;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import pi4.handlers.AssetLoader;
import pi4.screens.GameScreen;

public class Tetris extends Game {
	
	@Override
	public void create () {

		Gdx.app.log("ZBGame", "created");
		try {
			AssetLoader.load();
		}
		catch (NullPointerException e) {
			Gdx.app.error("NullPointerException", "create",e);
		}
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
