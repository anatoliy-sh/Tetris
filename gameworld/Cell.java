package pi4.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Анатолий on 12.12.2015.
 */
public class Cell {
    public Color color;

    Sprite sprite;

    public Cell(/*Texture texture,*/ Color color){
        this.color = color;
        sprite = new Sprite(new Texture(Gdx.files.internal("cell.png")));//texture
        sprite.setColor(color);
        sprite.setSize(9, 9);

    }
    public void setColor(Color color){
        this.color = color;
        sprite.setColor(color);
    }

    public void draw(SpriteBatch batch, int x, int y){

        //sprite.setPosition(x-10/2-sprite.getWidth()/2, y-20/2-sprite.getHeight()/2);
        sprite.setPosition(x*10,y*10);

        sprite.setColor(color);
        sprite.draw(batch);
    }

}
