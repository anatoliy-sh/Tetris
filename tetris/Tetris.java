package pi4.tetris;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pi4.handlers.AssetLoader;
import pi4.screens.GameScreen;

public class Tetris extends Game {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		//batch = new SpriteBatch();
		Gdx.app.log("ZBGame", "created");
		AssetLoader.load();
		//img = new Texture("android/assets/badlogic.jpg");
		setScreen(new GameScreen());
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

	/*@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}*/
}
