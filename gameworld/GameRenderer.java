package pi4.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by �������� on 12.12.2015.
 */
public class GameRenderer {
    private GameWorld myWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;

    private SpriteBatch batcher;

    public GameRenderer(GameWorld world) {

        myWorld = world;
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 200, 204);
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        batcher = new SpriteBatch();
        batcher.setProjectionMatrix(cam.combined);
    }

    public void render() {
        //Gdx.app.log("GameRenderer", "render");

        /*
         * 1. �� ������ ������ ������ ���, ����� ��������� �� �������� � ������ �� ��������������� ��������
         */
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
       /*
        // 2. �� ������������ ���������� �������


        // ������� shapeRenderer �������� ������������ �����
        shapeRenderer.begin(ShapeType.Filled);

        // �������� RGB Color 87, 109, 120, �� ����������
        shapeRenderer.setColor(87 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);

        // ������������ ������� �� myWorld (���������� ShapeType.Filled)
        shapeRenderer.rect(myWorld.getRect().x, myWorld.getRect().y,
                myWorld.getRect().width, myWorld.getRect().height);



        // ������� shapeRenderer ���������� ���������
        // �� ������ ������ ��� ��� ������
        shapeRenderer.end();


         //3. �� ������������ ����� ��� ��������


        // ������� shapeRenderer ���������� ����� ��������� �����
        shapeRenderer.begin(ShapeType.Line);

        // �������� ���� RGB Color 255, 109, 120, �� ����������
        shapeRenderer.setColor(255 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);

        // ������������ ������� �� myWorld (Using ShapeType.Line)
        shapeRenderer.rect(myWorld.getRect().x, myWorld.getRect().y,
                myWorld.getRect().width, myWorld.getRect().height);

        shapeRenderer.end();
*/

       //��������� ����
        batcher.begin();
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 20; j++)
                myWorld.getMap()[i][j].draw(batcher, i, j);

        batcher.end();



    }
}
