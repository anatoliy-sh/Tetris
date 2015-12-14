package pi4.gameworld;

import pi4.gameworld.figures.TetrisPoint;

/**
 * Created by Анатолий on 13.12.2015.
 */
public interface IFigure {
     boolean getIsDone();
     TetrisPoint[] getCells();
     int getColor();

     void update(float delta);

     void onKeyDown(int code);

     void clearPastPosition();
}
