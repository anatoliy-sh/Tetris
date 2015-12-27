package pi4.handlers;

/**
 * Created by �������� on 13.12.2015.
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
    private SimpleButton playButton, upLvlBut, downLvlBut, musicButton;

    public InputHandler(GameWorld world) {
        this.world = world;
        menuButtons = new ArrayList<SimpleButton>();
        playButton = new SimpleButton(
                170 - (AssetLoader.playButtonUp.getRegionWidth() / 2),
                180, 29, 16, AssetLoader.playButtonUp,
                AssetLoader.playButtonDown);
        upLvlBut = new SimpleButton(
                170 - (AssetLoader.upLvl.getRegionWidth() / 2),
                80, 10, 10, AssetLoader.upLvl,
                AssetLoader.upLvl);
        downLvlBut = new SimpleButton(
                170 - (AssetLoader.downLvl.getRegionWidth() / 2),
                100, 10, 10, AssetLoader.downLvl,
                AssetLoader.downLvl);
        musicButton = new SimpleButton(120, 180, 20, 20, AssetLoader.buttonMusic, AssetLoader.buttonMusic);
        menuButtons.add(downLvlBut);
        menuButtons.add(playButton);
        menuButtons.add(upLvlBut);
        menuButtons.add(musicButton);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Gdx.app.log("InputHandler", "touchDown");
        Gdx.app.log("InputHandler", "" + screenX);
        Gdx.app.log("InputHandler", "" + screenY);
        if (world.isMenu()) {

            AssetLoader.fon.play();
            AssetLoader.fon.setLooping(true);
            world.start();
        }
        if (world.isRunning()) {
            if (playButton.isClicked(screenX, screenY))
                world.restart();
            else if (downLvlBut.isClicked(screenX, screenY))
                world.downLvl();
            else if (upLvlBut.isClicked(screenX, screenY))
                world.upLvl();
            else if (musicButton.isClicked(screenX, screenY))
                if (AssetLoader.fon.isPlaying())
                    AssetLoader.fon.pause();
                else
                    AssetLoader.fon.play();
        }
        if (world.isGameOver())
            world.restart();
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        Gdx.app.log("InputHandler", "keydown" + keycode);
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