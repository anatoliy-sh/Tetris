package pi4.Handlers;

/**
 * Created by Анатолий on 13.12.2015.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import pi4.gameworld.GameWorld;
import pi4.gameworld.IFigure;

public class InputHandler implements InputProcessor {

    private GameWorld world;

    public InputHandler(GameWorld world) {
        this.world = world;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        Gdx.app.log("InputHandler", "keydown"+keycode);

        world.getCurFigure().onKeyDown(keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}