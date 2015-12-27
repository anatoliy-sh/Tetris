package pi4.gameworld;

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

    private int score = 0;
    private int lvl = 1;
    private float time;
    private int speed;
    private GameState currentState;

    public enum GameState {
        MENU, READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    private Random rnd = new Random();

    private int[][] bmap;
    private IFigure curFigure;
    private IFigure nextFigure;
    private boolean generateFig;

    public int getScore(){
        return score;
    }
    public boolean getGenerateFig() {
        return generateFig;
    }
    public int getLvl(){
        return lvl;
    }
    public void setGenerateFig(boolean value) {
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
        currentState = GameState.MENU;
        bmap = new int[CountCellX][CountCellY];
        createMap();
        curFigure = randomFigure();
        nextFigure = randomFigure();
        speed = 20;
        time = 0;
    }

    private void createMap() {
        for (int i = 0; i < CountCellX; i++)
            for (int j = 0; j < CountCellY; j++) {
                bmap[i][j] = 0;
            }
    }

    public void update(float delta) {
        if(currentState == GameState.RUNNING) {
            time++;
            if (time > speed) {
                curFigure.clearPastPosition();
                curFigure.update(delta);
                for (int i = 0; i < 4; i++) {
                    bmap[curFigure.getCells()[i].getX()][curFigure.getCells()[i].getY()] = curFigure.getColor();
                }
                if (curFigure.getIsDone()) {
                    checkLines();
                    curFigure = nextFigure;
                    nextFigure = randomFigure();
                }
                time = 0;
            }
        }
    }

    private void checkLines() {
        checkGameOver();
        boolean flDestroyLine;
        for (int j = 0; j < CountCellY; j++) {
            flDestroyLine = true;
            for (int i = 0; i < CountCellX; i++) {
                if (flDestroyLine)
                    flDestroyLine = bmap[i][j] > 0;
            }
            if (flDestroyLine)
                destroyLine(j);
        }
    }

    private void destroyLine(int jLine) {
        if(score / lvl == 1000) {
            lvl++;
            speed-=2;
        }
        for (int i = 0; i < CountCellX; i++) {
            bmap[i][jLine] = 0;
        }
        for (int i = 0; i < CountCellX; i++)
            for (int j = jLine; j > 1; j--) {
                bmap[i][j] = bmap[i][j-1];
            }
        score+=100;
    }

    private void checkGameOver(){
        for (int i = 0; i < CountCellX; i++) {
           if(bmap[i][1] > 0) {
               currentState = GameState.GAMEOVER;
               break;
           }
        }
    }
    private IFigure randomFigure() {
        generateFig = true;
        switch (rnd.nextInt(7)) {
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
    public void start() {
        currentState = GameState.RUNNING;
    }
    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }
    public boolean isMenu() {
        return currentState == GameState.MENU;
    }
    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

}
