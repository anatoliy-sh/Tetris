package pi4.gameworld;

import com.badlogic.gdx.graphics.Color;
import pi4.gameworld.figures.Q;
import pi4.gameworld.figures.T;
import pi4.gameworld.figures.J;
import pi4.gameworld.figures.L;
import pi4.gameworld.figures.S;
import pi4.gameworld.figures.Z;
import pi4.gameworld.figures.I;

import java.util.Random;

/**
 * Created by �������� on 12.12.2015.
 */
public class GameWorld {
    public static final int CountCellX = 10;
    public static final int CountCellY = 20;
    private float time;

    Random ran = new Random();

    Cell[][] map;

    int[][] bmap;

    IFigure curFigure;

    public IFigure getCurFigure() {
        return curFigure;
    }

    public GameWorld() {
        map = new Cell[CountCellX][CountCellY];
        bmap = new int[CountCellX][CountCellY];
        createMap();
        randomFigure();
        time = 0;
    }

    private void createMap() {
        for (int i = 0; i < CountCellX; i++)
            for (int j = 0; j < CountCellY; j++) {
                map[i][j] = new Cell(Color.WHITE);
                bmap[i][j] = 0;
            }
    }

    private void randomFigure() {
        int r = 0;//ran.nextInt(7);
        switch (r) {
            case 1:
                curFigure = new T(bmap, 1);
                break;
            case 2:
                curFigure = new Q(bmap, 2);
                break;
            case 3:
                curFigure = new J(bmap, 3);
                break;
            case 4:
                curFigure = new L(bmap, 4);
                break;
            case 5:
                curFigure = new S(bmap, 5);
                break;
            case 6:
                curFigure = new Z(bmap, 6);
                break;
            case 0:
                curFigure = new I(bmap, 7);
                break;
        }
    }

    public void update(float delta) {
        time += delta;
        //Gdx.app.log("GameWorld", "update"+time);

        if (time > 0.5) {
            curFigure.clearPastPosition();
            curFigure.update(delta);
            for (int i = 0; i < 4; i++) {
                bmap[curFigure.getCells()[i].getX()][curFigure.getCells()[i].getY()] = curFigure.getColor();
            }
            if (curFigure.getIsDone())
                //curFigure = new Z(bmap,1);
                randomFigure();
            time = 0;
        }
        for (int i = 0; i < CountCellX; i++)
            for (int j = 0; j < CountCellY; j++) {
                switch (bmap[i][j]) {
                    case 0:
                        map[i][j].setColor(Color.WHITE);
                        break;
                    case 1:
                        map[i][j].setColor(Color.RED);
                        break;
                    case 2:
                        map[i][j].setColor(Color.GREEN);
                        break;
                    case 3:
                        map[i][j].setColor(Color.BLUE);
                        break;
                    case 4:
                        map[i][j].setColor(Color.YELLOW);
                        break;
                    case 5:
                        map[i][j].setColor(Color.CORAL);
                        break;
                    case 6:
                        map[i][j].setColor(Color.CYAN);
                        break;
                    case 7:
                        map[i][j].setColor(Color.VIOLET);
                        break;
                }

            }
    }

    public Cell[][] getMap() {
        return map;
    }
}
