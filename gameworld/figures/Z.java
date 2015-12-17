package pi4.gameworld.figures;

import pi4.gameworld.GameWorld;
import pi4.gameworld.IFigure;

/**
 * Created by Евгений on 13.12.2015.
 */
public class Z implements IFigure{
    private TetrisPoint[] cells;
    private int [][] bmap;
    private boolean isDone;
    private int bias;
    private boolean down;
    private boolean rotate;
    private int grad;

    private int color;

    public boolean getIsDone(){
        return isDone;
    }
    public int getColor(){return color;}

    public TetrisPoint[] getCells(){
        return cells;
    }

    public Z(int[][] bmap, int color){
        cells = new TetrisPoint[4];
        this.bmap = bmap;
        this.color = color;
        isDone = false; //упала ли она
        fullCells();
        bias = 0;
        down = false;
        grad = 0;
    }
    //��������� �������
    private void fullCells(){ //формировка фигуры
        cells[0] = new TetrisPoint(4,0);
        cells[1] = new TetrisPoint(5,0);
        cells[2] = new TetrisPoint(5,1);
        cells[3] = new TetrisPoint(6,1);
    }
    //��������� �������
    public void update(float delta){
        moveDown();
    }

    private void moveDown(){ //движение вниз
        if(!checkNextYMove()) { //валидность Y координаты
            if (rotate || down) {
                if (rotate && !checkNextXMove()) {
                    rotate();
                    grad++;
                }
                if (down) {
                    while (!checkNextYMove()) {
                        for (int i = 0; i < 4; i++) {
                            cells[i].setY(cells[i].getY() + 1);
                        }
                    }
                }
            }
            else {
                for (int i = 0; i < 4; i++) {
                    cells[i].setY(cells[i].getY() + 1);
                }
            }
        }
        if(!checkNextXMove()) { //валидность Х координаты
            for (int i = 0; i < 4; i++) {
                cells[i].setX(cells[i].getX() + bias);
            }
        }
        bias = 0;
        down = false;
        rotate = false;
    }
    //�������� ��
    public boolean checkNextYMove(){
        if(cells[3].getY()+1 >= GameWorld.CountCellY
                || cells[0].getY()+1 >= GameWorld.CountCellY) //не вышла ли за низ. Если на +1 уже пол, фигура упала
            isDone = true;
        if (!isDone) {
            switch(grad) {
                case 0: {
                    isDone = bmap[cells[3].getX()][cells[3].getY() + 1] != 0;
                    isDone = isDone || bmap[cells[2].getX()][cells[2].getY() + 1] != 0;//не налезла ли на другие фигуры
                    isDone = isDone || bmap[cells[0].getX()][cells[0].getY() + 1] != 0;
                    return isDone;
                }
                case 1: {
                    isDone = bmap[cells[1].getX()][cells[1].getY() + 1] != 0;
                    isDone = isDone || bmap[cells[3].getX()][cells[3].getY() + 1] != 0;//не налезла ли на другие фигуры
                    return isDone;
                }
            }
        }

        return isDone;
    }
    public boolean checkNextXMove(){
        boolean flag = false;

        if (bias == 1)
            if(cells[3].getX() + bias >= GameWorld.CountCellX)
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
            case 20:
                down = true;
                break;
            case 62:
                rotate = true;
                break;
        }

    }

    public void clearPastPosition(){
        for (int i = 0; i < 4; i++) {
            bmap[cells[i].getX()][cells[i].getY()] = 0;
        }
    }

    public void rotate() {
        switch(grad) {
            case 0: {
                cells[0].setX(cells[0].getX() + 2);
                cells[1].setX(cells[1].getX() + 1);
                cells[1].setY(cells[1].getY() + 1);
                cells[3].setX(cells[3].getX() - 1);
                cells[3].setY(cells[3].getY() + 1);
                break;
            }
            case 1: {
                cells[0].setX(cells[0].getX() - 2);
                cells[1].setX(cells[1].getX() - 1);
                cells[1].setY(cells[1].getY() - 1);
                cells[3].setX(cells[3].getX() + 1);
                cells[3].setY(cells[3].getY() - 1);
                grad = 0;
                break;
            }
        }
    }
}
