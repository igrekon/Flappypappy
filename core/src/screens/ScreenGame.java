package screens;

import static screens.MyGdxGame.SCR_HEIGHT;
import static screens.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.ScreenUtils;

import characters.Bird;
import characters.Tube;
import components.MovingBackground;
import components.PointCounter;

public class ScreenGame implements Screen {


    MyGdxGame myGdxGame;
    Bird bird;
    PointCounter pointCounter;
    MovingBackground background;


    final int pointCounterMarginTop = 60;

    final int pointCounterMarginRight = 400;




    int tubeCount=3;
    int gamePoints;
    boolean isGameOver;
    Tube[] tubes;





    ScreenGame(MyGdxGame myGdxGame){
        this.myGdxGame=myGdxGame;

        tubes = new Tube[tubeCount];
        for (int i=0; i < tubeCount;i++ ){
            tubes[i] = new Tube(tubeCount,i);
        }
        initTubes();
        background = new MovingBackground("~/StudioProjects/Flappypappy/assets");

        bird = new Bird(20,SCR_HEIGHT /2,10,250,200);
        pointCounter = new PointCounter(SCR_WIDTH - pointCounterMarginRight, SCR_HEIGHT - pointCounterMarginTop);
    }

    @Override
    public void show() {
        gamePoints = 0;
        isGameOver = false;

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1,0,0,1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        if (Gdx.input.justTouched()) {
            bird.onClick();

        }
        background.move();
        bird.fly();
        if (!bird.isinField()){
            System.out.println("not in field");
            isGameOver = true;
        }
        for (Tube tube : tubes) {
            tube.move();

            if (tube.isHit(bird)) {
                System.out.println("hit");
                isGameOver = true;
            } else if (tube.needAddPoint(bird)) {
                gamePoints+=1;
                tube.setPointReceived();
                System.out.println(gamePoints);
            }
        }

        ScreenUtils.clear(0, 0.6f, 0.8f, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        background.draw(myGdxGame.batch);
        bird.draw(myGdxGame.batch);
        for (Tube tube : tubes) tube.draw(myGdxGame.batch);
        pointCounter.draw(myGdxGame.batch, gamePoints);

        myGdxGame.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        bird.dispose();
        for (int i =0; i < tubeCount;i++){
            tubes[i].dispose();
        }

    }
    void initTubes() {
        tubes = new Tube[tubeCount];
        for (int i = 0; i < tubeCount; i++) {
            tubes[i] = new Tube(tubeCount, i);
        }
    }



}
