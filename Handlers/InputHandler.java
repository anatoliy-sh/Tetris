package pi4.handlers;

/**
 * Created by Анатолий on 13.12.2015.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import pi4.gameworld.GameWorld;
import pi4.ui.SimpleButton;


import java.util.ArrayList;
import java.util.List;

public class InputHandler implements InputProcessor {

    private GameWorld world;
    private List<SimpleButton> menuButtons;
    private SimpleButton playButton;

    public InputHandler(GameWorld world) {
        this.world = world;
        menuButtons = new ArrayList<SimpleButton>();
        playButton = new SimpleButton(
                200/ 2 - (AssetLoader.playButtonUp.getRegionWidth() / 2),
                 204/2, 29, 16, AssetLoader.playButtonUp,
                AssetLoader.playButtonDown);
        menuButtons.add(playButton);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log("InputHandler", "touchDown");
        if (world.isMenu()) {
            playButton.isClicked(screenX, screenY);
            AssetLoader.fon.play();
            world.start();
        }
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        Gdx.app.log("InputHandler", "keydown"+keycode);
        if(keycode == 20)
            AssetLoader.down.play();
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
        if (world.isMenu()) {
            playButton.isClicked(screenX, screenY);

        }
        return true;
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

    public List<SimpleButton> getMenuButtons() {
        return menuButtons;
    }

}