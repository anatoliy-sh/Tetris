package pi4.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import pi4.gameworld.GameRenderer;
import pi4.gameworld.GameWorld;

/**
 * Created by Анатолий on 12.12.2015.
 */
public class GameScreen implements Screen {
    public GameScreen() {
        Gdx.app.log("GameScreen", "Attached");
        world = new GameWorld(); // initialize world
        renderer = new GameRenderer(world); // initialize renderer
    }
    private GameWorld world;
    private GameRenderer renderer;
    @Override
    public void render(float delta) {
        /*// установим цвет бэкграцнда нашего экрана (RGB = 10, 15, 230), с прозрачностью 1 (100%)
        Gdx.gl.glClearColor(10/255.0f, 15/255.0f, 230/255.0f, 1f);

        // заполним экран указанным цветом
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // выведем в консоль количество кадров в секунду
        Gdx.app.log("GameScreen FPS", (1/delta) + "");*/
        world.update(delta); // GameWorld updates
        renderer.render(); // GameRenderer renders
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void dispose() {
        // Leave blank
    }
}
