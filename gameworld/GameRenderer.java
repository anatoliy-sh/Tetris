package pi4.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import pi4.gameworld.figures.TetrisPoint;
import pi4.handlers.AssetLoader;
import pi4.handlers.InputHandler;
import pi4.ui.SimpleButton;

import java.util.List;

/**
 * Created by Анатолий on 12.12.2015.
 */
public class GameRenderer {
    private final int COUNTNEXT = 4;
    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    private List<SimpleButton> menuButtons;

    private Cell[][] map;

    private Cell[][] nextFigure;


    private int[][] bmap;

    public GameRenderer(GameWorld world) {

        myWorld = world;
        this.menuButtons = ((InputHandler) Gdx.input.getInputProcessor())
                .getMenuButtons();
        map = new Cell[GameWorld.CountCellX][GameWorld.CountCellY];
        nextFigure = new Cell[COUNTNEXT][COUNTNEXT];
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 200, 204);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);


        createMap();
    }

    private void createMap() {
        for (int i = 0; i < GameWorld.CountCellX; i++)
            for (int j = 0; j < GameWorld.CountCellY; j++) {
                map[i][j] = new Cell(AssetLoader.cell, Color.WHITE);
            }
        for (int i = 0; i < COUNTNEXT; i++)
            for (int j = 0; j < COUNTNEXT; j++) {
                nextFigure[i][j] = new Cell(AssetLoader.cell, Color.BLACK);
            }
    }

    private void setMap() {
        for (int i = 0; i < GameWorld.CountCellX; i++)
            for (int j = 0; j < GameWorld.CountCellY; j++) {
                map[i][j].setColor(returnColor(bmap[i][j]));
            }
    }

    private void fillNextFigure() {
        TetrisPoint[] tmp = myWorld.getNextFigure().getCells();
        for (int i = 0; i < COUNTNEXT; i++)
            for (int j = 0; j < COUNTNEXT; j++) {
                nextFigure[i][j].setColor(Color.BLACK);
            }
        for (int i = 0; i < 4; i++)
            nextFigure[tmp[i].getX() - 4][tmp[i].getY()].setColor(returnColor(myWorld.getNextFigure().getColor()));
    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batcher.begin();
        if (myWorld.isMenu()) {
            drawMenu();
        } else if (myWorld.isGameOver()) {
            drawGameOver();
        } else {
            drawGame();
        }
        batcher.end();

    }

    private void drawGame() {
        bmap = myWorld.getBMap();
        setMap();
        if (myWorld.getGenerateFig()) {
            myWorld.setGenerateFig(false);
            fillNextFigure();
        }

        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 20; j++)
                map[i][j].draw(batcher, i, j);
        for (int i = 0; i < COUNTNEXT; i++)
            for (int j = 0; j < COUNTNEXT; j++)
                nextFigure[i][j].draw(batcher, i + 12, j + 3);

        drawScore();
    }

    private void drawMenu() {
        batcher.draw(AssetLoader.logoTexture, 0, 0,
                AssetLoader.logoTexture.getWidth(), AssetLoader.logoTexture.getHeight());
        int length = ("Click \nto \nstart").length();
        AssetLoader.font.draw(batcher, "Click \nto \nstart",
                51 - (3 * length), 91);
    }

    private void drawScore() {
        int length = ("" + myWorld.getScore()).length();
        AssetLoader.font.draw(batcher, "" + myWorld.getScore(),
                151 - (3 * length), 151);
        length = ("Score").length();
        AssetLoader.font.draw(batcher, "Score",
                131 - (3 * length), 121);
        length = ("Lvl").length();
        AssetLoader.font.draw(batcher, "Lvl",
                131 - (3 * length), 81);
        length = ("" + myWorld.getLvl()).length();
        AssetLoader.font.draw(batcher, "" + myWorld.getLvl(),
                151 - (3 * length), 101);
    }

    private void drawGameOver() {
        //AssetLoader.gameOver.play();
        int length = ("Game Over").length();
        AssetLoader.shadow.draw(batcher, "Game Over",
                58 - (3 * length), 150 - 82);
        AssetLoader.font.draw(batcher, "Game Over",
                68 - (3 * length), 150 - 83);
        length = ("" + myWorld.getScore()).length();
        AssetLoader.font.draw(batcher, "" + myWorld.getScore(),
                151 - (3 * length), 151);
        length = ("Click to start").length();
        AssetLoader.font.draw(batcher, "Click to restart",
                68 - (3 * length), 91);
    }

    private Color returnColor(int index) {
        switch (index) {
            case 0:
                return Color.WHITE;
            case 1:
                return Color.BROWN;
            case 2:
                return Color.OLIVE;
            case 3:
                return Color.ROYAL;
            case 4:
                return Color.GOLDENROD;
            case 5:
                return Color.SLATE;
            case 6:
                return Color.DARK_GRAY;
            case 7:
                return Color.MAROON;
        }
        return Color.WHITE;
    }
}
