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

    int [][] bmap;

    IFigure curFigure;

    public IFigure getCurFigure(){
        return curFigure;
    }

    public GameWorld(){
        map = new Cell[CountCellX][CountCellY];
        bmap = new int[CountCellX][CountCellY];
        createMap();
        randomFigure();
        time = 0;
    }

    private void createMap(){
        for (int i = 0; i < CountCellX; i++)
            for (int j = 0; j < CountCellY; j++){
                    map[i][j] = new Cell(Color.WHITE);
                    bmap[i][j] = 0;
            }
    }

    private void randomFigure(){
        int r = ran.nextInt(7);
        switch (r){
            case 1:
                curFigure = new T(bmap,1);
                break;
            case 2:
                curFigure = new Q(bmap,1);
                break;
            case 3:
                curFigure = new J(bmap,1);
                break;
            case 4:
                curFigure = new L(bmap,1);
                break;
            case 5:
                curFigure = new S(bmap,1);
                break;
            case 6:
                curFigure = new Z(bmap,1);
                break;
            case 0:
                curFigure = new I(bmap,1);
                break;
        }
    }

    public void update(float delta) {
        time+=delta;
        //Gdx.app.log("GameWorld", "update"+time);
        //���
        if(time > 0.5 ) {
            curFigure.clearPastPosition();
            curFigure.update(delta);
            for(int i = 0; i< 4; i++) {
                bmap[curFigure.getCells()[i].getX()][curFigure.getCells()[i].getY()] = curFigure.getColor();
            }
            //��������� ������
            if(curFigure.getIsDone())
                randomFigure();
            time = 0;
        }
        for (int i = 0; i < CountCellX; i++)
            for (int j = 0; j < CountCellY; j++){
                switch (bmap[i][j]) {
                    case 0:
                         map[i][j].setColor(Color.WHITE);
                        break;
                    case 1:
                        map[i][j].setColor(Color.RED);
                }

            }
    }
    public Cell[][] getMap() {
        return map;
    }
}
