package pi4.gameworld.figures;

import android.graphics.Point;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import pi4.gameworld.Cell;
import pi4.gameworld.GameWorld;
import pi4.gameworld.IFigure;
import pi4.tetris.Tetris;

/**
 * Created by Евгений on 13.12.2015.
 */
public class T implements IFigure{
    private TetrisPoint[] cells;
    private int [][] bmap;
    private boolean isDone;
    private int bias;


    private int color;

    public boolean getIsDone(){
        return isDone;
    }
    public int getColor(){return color;}

    public TetrisPoint[] getCells(){
        return cells;
    }

    public T(int[][] bmap, int color){
        cells = new TetrisPoint[4];
        this.bmap = bmap;
        this.color = color;
        isDone = false; //упала ли она
        fullCells();
        bias = 0;
    }
    //��������� �������
    private void fullCells(){ //формировка фигуры
        cells[0] = new TetrisPoint(4,0);
        cells[1] = new TetrisPoint(5,0);
        cells[2] = new TetrisPoint(6,0);
        cells[3] = new TetrisPoint(5,1);
    }
    //��������� �������
    public void update(float delta){
        moveDown();
    }

    private void moveDown(){ //движение вниз
        if(!checkNextYMove()) { //валидность Y координаты
            for (int i = 0; i < 4; i++) {
                cells[i].setY(cells[i].getY() + 1);
            }
        }
        if(!checkNextXMove()) { //валидность Х координаты
            for (int i = 0; i < 4; i++) {
                cells[i].setX(cells[i].getX() + bias);
            }
        }
        bias = 0;
    }
    //�������� ��
    public boolean checkNextYMove(){
        //for(int i = 2; i< 4; i++)  //проверяется днище
            if(cells[3].getY()+1 >= GameWorld.CountCellY) //не вышла ли за низ. Если на +1 уже пол, фигура упала
                isDone = true;
            if (!isDone) {
                isDone = bmap[cells[3].getX()][cells[3].getY() + 1] != 0;
                isDone = isDone || bmap[cells[2].getX()][cells[2].getY() + 1] != 0;//не налезла ли на другие фигуры
                isDone = isDone || bmap[cells[0].getX()][cells[0].getY() + 1] != 0;

            }

        return isDone;
    }
    public boolean checkNextXMove(){
        boolean flag = false;
        int start = 0,end = 0;
        /*switch (bias){ //bias показывает, куда нажал пользователь, вправо или влево
            case 1:
                start = 1;
                end = 3;
                break;
            case -1:
                start = 0;
                end = 3;
                break;
        }*/
        /*for(int i = start; i<= end; i+=2) {
            if(cells[i].getX()+bias >= GameWorld.CountCellX || cells[i].getX()+bias<0)
                flag = true;
            if (!flag)
                flag = bmap[cells[i].getX()+bias][cells[i].getY()] != 0;
        }
        return flag;*/
        if (bias == 1)
        if(cells[2].getX() + bias >= GameWorld.CountCellX)
            flag = true;
        if(bias == -1)
        if(cells[0].getX() + bias <0)
            flag = true;


        return flag;

    }

    public void onKeyDown(int code){

            switch (code) {
                case 21:
                    bias = -1;
                    break;
                case 22:
                    bias = 1;
                    break;
            }

    }

    public void clearPastPosition(){
        for (int i = 0; i < 4; i++) {
            bmap[cells[i].getX()][cells[i].getY()] = 0;
        }
    }
}
