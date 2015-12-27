package pi4.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static Texture texture, logoTexture,cell, music;
	public static TextureRegion  playButtonUp, playButtonDown, upLvl, downLvl, buttonMusic;
	public static Sound line, gameOver, down;
	public static Music fon;
	public static BitmapFont font, shadow;
	private static Preferences prefs;

	public static void load() throws NullPointerException{


		line = Gdx.audio.newSound(Gdx.files.internal("core/data/line.wav"));
		gameOver = Gdx.audio.newSound(Gdx.files.internal("core/data/game_over.wav"));
		down = Gdx.audio.newSound(Gdx.files.internal("core/data/down.wav"));
		fon = Gdx.audio.newMusic(Gdx.files.internal("core/data/fon.mp3"));
		logoTexture = new Texture(Gdx.files.internal("core/data/fon.jpg"));

		texture = new Texture(Gdx.files.internal("core/data/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		music = new Texture((Gdx.files.internal("core/data/music.png")));
		buttonMusic = new TextureRegion(music, 0,0,72,72);
		buttonMusic.flip(false, true);

		cell = new Texture(Gdx.files.internal("core/data/cell.png"));

		playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
		playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
		playButtonUp.flip(false, true);
		playButtonDown.flip(false, true);

		upLvl = new TextureRegion(texture, 152, 70, 10, 10);
		downLvl = new TextureRegion(texture, 165, 70, 10, 10);

		font = new BitmapFont(Gdx.files.internal("core/data/text.fnt"));
		font.getData().setScale(.25f, -.25f);

		shadow = new BitmapFont(Gdx.files.internal("core/data/shadow.fnt"));
		shadow.getData().setScale(.25f, -.25f);

		prefs = Gdx.app.getPreferences("ZombieBird");

		if (!prefs.contains("highScore")) {
			prefs.putInteger("highScore", 0);
		}
	}



	public static void dispose() {
		// We must dispose of the texture when we are finished.
		//texture.dispose();
		logoTexture.dispose();
		line.dispose();
		gameOver.dispose();
		down.dispose();
		cell.dispose();
		font.dispose();
		shadow.dispose();
	}

}