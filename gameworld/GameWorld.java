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

    private Random ran = new Random();

    private int[][] bmap;

    private IFigure curFigure;

    private IFigure nextFigure;

    private boolean generateFig;

    public boolean getGenerateFig(){
        return generateFig;
    }

    public void setGenerateFig(boolean value){
        generateFig = value;
    }

    public IFigure getCurFigure() {
        return curFigure;
    }
    public IFigure getNextFigure() {
        return nextFigure;
    }
    public int[][] getBMap() {
        return bmap;
    }

    public GameWorld() {
        bmap = new int[CountCellX][CountCellY];
        createMap();
        curFigure = randomFigure();
        nextFigure = randomFigure();
        time = 0;
    }

    private void createMap() {
        for (int i = 0; i < CountCellX; i++)
            for (int j = 0; j < CountCellY; j++) {
                bmap[i][j] = 0;
            }
    }

    public void update(float delta) {
        time++;
        //Gdx.app.log("GameWorld", "update"+time);

        if (time > 10) {
            curFigure.clearPastPosition();
            curFigure.update(delta);
            for (int i = 0; i < 4; i++) {
                bmap[curFigure.getCells()[i].getX()][curFigure.getCells()[i].getY()] = curFigure.getColor();
            }
            if (curFigure.getIsDone()) {
                curFigure = nextFigure;
                nextFigure = randomFigure();
            }

            time = 0;
        }
    }


    private IFigure randomFigure() {
        int r = ran.nextInt(7); //0;
        generateFig = true;
        switch (r) {
            case 1:
                return new T(bmap, 1);
            case 2:
                return new Q(bmap, 2);
            case 3:
                return new J(bmap, 3);
            case 4:
                return new L(bmap, 4);
            case 5:
                return new S(bmap, 5);
            case 6:
                return new Z(bmap, 6);
            case 0:
                return new I(bmap, 7);
        }
        return null;
    }




}
