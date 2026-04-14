package characters;


import static screens.MyGdxGame.SCR_HEIGHT;
import static screens.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;


public class Tube {

    Texture textureUpperTube;
    Texture textureDownTube;
    int gapY;
    int gapHeight=400;
    SpriteBatch batch;
    int padding = 100;
    int width = 200;
    int height = 700;
    int distanceBetweenTubes;
    int speed = 10;
    boolean isPointReceived;

    int x;

    public Tube(int tubeCount, int tubeIdx){
        Random random = new Random();
        textureUpperTube = new Texture("tubes/tube_flipped.png");
        textureDownTube = new Texture("tubes/tube.png");
        gapY = gapHeight / 2 + padding + random.nextInt(SCR_HEIGHT - 2 * (padding+gapHeight / 2));
        distanceBetweenTubes =  (SCR_WIDTH+ width) / (tubeCount-1);
        x  = distanceBetweenTubes * tubeIdx + SCR_WIDTH;
    }

    public void draw(Batch batch){
        batch.draw(textureUpperTube, x, gapY + gapHeight /2, width, height);
        batch.draw(textureDownTube, x, gapY - gapHeight / 2 - height, width, height);

    }
    public void dispose(){
        textureDownTube.dispose();
        textureUpperTube.dispose();
    }
    public void  move(){
        Random random = new Random();
        x-=speed;
        if (x < -width){
            isPointReceived = false;
            x = SCR_WIDTH + distanceBetweenTubes;
            gapY = gapHeight / 2 + padding + random.nextInt(SCR_HEIGHT - 2 * (padding+gapHeight / 2));


        }
    }
    public boolean isHit(Bird bird){
        return bird.y <= gapY - gapHeight / 2 && bird.x + bird.width >= x && bird.x <= x + width;

    }
    public boolean needAddPoint(Bird bird){
        return bird.x > x + width && !isPointReceived;
    }
    public void  setPointReceived(){
        isPointReceived = true;
    }

}
