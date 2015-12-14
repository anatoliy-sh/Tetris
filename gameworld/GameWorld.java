package pi4.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import pi4.gameworld.figures.*;

/**
 * Created by �������� on 12.12.2015.
 */
public class GameWorld {
    public static final int CountCellX = 10;
    public static final int CountCellY = 20;
    private float time;

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
        curFigure =new L(bmap,6);
        time = 0;
    }

    private void createMap(){
        for (int i = 0; i < CountCellX; i++)
            for (int j = 0; j < CountCellY; j++){
                    map[i][j] = new Cell(Color.WHITE);
                    bmap[i][j] = 0;
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
                bmap[curFigure.getCells()[i].getX()][curFigure.getCells()[i].getY()] = curFigure.getColor(); //
            }
            //��������� ������
            if(curFigure.getIsDone()) //упала ли фигура
                curFigure = new L(bmap,6); //создание новой фигуры (падает следующая)
            time = 0;
        }
        for (int i = 0; i < CountCellX; i++) //обновление мапы
            for (int j = 0; j < CountCellY; j++){
                switch (bmap[i][j]) {
                    case 0:
                         map[i][j].setColor(Color.WHITE);
                        break;
                    case 1:
                        map[i][j].setColor(Color.RED);
                    case 2:
                        map[i][j].setColor(Color.GREEN);
                    case 3:
                        map[i][j].setColor(Color.BLUE);
                    case 4:
                        map[i][j].setColor(Color.YELLOW);
                    case 5:
                        map[i][j].setColor(Color.PURPLE);
                    case 6:
                        map[i][j].setColor(Color.BROWN);
                }

            }
    }
    public Cell[][] getMap() {
        return map;
    }
}
