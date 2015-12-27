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
                map[i][j] = new Cell(AssetLoader.cell,Color.WHITE);
            }
        for (int i = 0; i < COUNTNEXT; i++)
            for (int j = 0; j < COUNTNEXT; j++) {
                nextFigure[i][j] = new Cell(AssetLoader.cell,Color.WHITE);
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
                nextFigure[i][j].setColor(Color.WHITE);
            }
        for (int i = 0; i < 4; i++)
            nextFigure[tmp[i].getX() - 4][tmp[i].getY()].setColor(returnColor(myWorld.getNextFigure().getColor()));
    }

    public void render() {
        //Gdx.app.log("GameRenderer", "render");

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      batcher.begin();
        if (myWorld.isMenu()) {
            drawMenu();
        }
        else if (myWorld.isGameOver()){
            drawGameOver();
        }else{
            //рисование поля
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
        batcher.end();

    }
    private void drawMenu() {
        int length = ("" + "Tetris").length();
        AssetLoader.shadow.draw(batcher, "" + "Tetris",
                68 - (3 * length), 150 - 82);
        AssetLoader.font.draw(batcher, "" + "Tetris",
                68 - (3 * length), 150 - 83);
        for (SimpleButton button : menuButtons) {
            button.draw(batcher);
        }
    }

    private void drawScore() {
        int length = ("" + myWorld.getScore()).length();
        AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(),
                150 - (3 * length), 150);
        AssetLoader.font.draw(batcher, "" + myWorld.getScore(),
                151 - (3 * length), 151);
    }

    private void drawGameOver() {
        AssetLoader.gameOver.play();
        int length = ("" + "GameOver").length();
        AssetLoader.shadow.draw(batcher, "" + "GameOver",
                68 - (3 * length), 150 - 82);
        AssetLoader.font.draw(batcher, "" + "GameOver",
                68 - (3 * length), 150 - 83);
        length = ("" + myWorld.getScore()).length();
        AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(),
                150 - (3 * length), 150);
        AssetLoader.font.draw(batcher, "" + myWorld.getScore(),
                151 - (3 * length), 151);
    }

    private Color returnColor(int index) {
        switch (index) {
            case 0:
                return Color.WHITE;
            case 1:
                return Color.RED;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.BLUE;
            case 4:
                return Color.YELLOW;
            case 5:
                return Color.CORAL;
            case 6:
                return Color.CYAN;
            case 7:
                return Color.VIOLET;
        }
        return Color.WHITE;
    }
}
