package characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import screens.MyGdxGame;

public class Bird{
    int x,y;
    int width, height;
    int speed;
    Texture[] framesArray = new Texture[]{
            new Texture("birdTiles/bird0.png"),
            new Texture("birdTiles/bird1.png"),
            new Texture("birdTiles/bird2.png"),
            new Texture("birdTiles/bird1.png"),
    };
    int frameCounter;
    int jumpHeight;
    final  int maxHeightOfJump = 200;
    boolean jump;

    public Bird(int x, int y, int speed, int width, int height){
        this.x =x;
        this.y =y;
        this.speed = speed;
        this.width = width;
        this.height = height;





    }

    public void onClick() {
        jump =true;
        jumpHeight = maxHeightOfJump + y;


    }
    public void fly(){
        if (y>=jumpHeight){
            jump=false;
        }

        if (jump) {
            y += speed;
        }else{
            y-=speed;
        }

    }

    public void draw(Batch batch){
        int frameMultiplier = 10;
        batch.draw(framesArray[frameCounter / frameMultiplier],x, y, width, height);
        if (frameCounter++ == framesArray.length * frameMultiplier -1) frameCounter = 0;
    }

    public void dispose(){
        for (Texture texture : framesArray)
            texture.dispose();
    }


    public boolean isinField() {
            if (y + height < 0) return false;
            if (y > MyGdxGame.SCR_HEIGHT) return false;


        return false;
    }
}