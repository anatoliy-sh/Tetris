package pi4.gameworld.figures;

/**
 * Created by Анатолий on 13.12.2015.
 */
public class TetrisPoint {
    private int x;
    private int y;

    public TetrisPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
