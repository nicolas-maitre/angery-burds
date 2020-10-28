package ch.cpnv.angrywirds.activities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Random;
import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.model.Bird;
import ch.cpnv.angrywirds.model.Data.Vocabulary;
import ch.cpnv.angrywirds.model.ObjectOutOfBoundsException;
import ch.cpnv.angrywirds.model.Panel;
import ch.cpnv.angrywirds.model.PhysicalObject;
import ch.cpnv.angrywirds.model.Pig;
import ch.cpnv.angrywirds.model.SceneCollapseException;
import ch.cpnv.angrywirds.model.Scenery;
import ch.cpnv.angrywirds.model.Slingshot;
import ch.cpnv.angrywirds.model.TNT;
import ch.cpnv.angrywirds.model.Wasp;
import ch.cpnv.angrywirds.providers.VocProvider;
import static ch.cpnv.angrywirds.model.Scenery.BLOCK_SIZE;

public class Play extends Game implements InputProcessor {

    public static Random alea; // random generator object. Static for app-wide use

    public static final float WORLD_WIDTH = 1600;
    public static final float WORLD_HEIGHT = 900;
    public static final int FLOOR_HEIGHT = 120;
    public static final int MIN_STRENGTH = 50;
    public static final float BG_CAM_SCALE = 2;

    private Scenery scene;
    private Bird tweety;
    private Wasp waspy;

    private VocProvider vocSource = VocProvider.getInstance();
    private Vocabulary voc;
    private Panel panel;
    private BitmapFont scoreDisp;

    private int scoreVal;

    private Slingshot slingshot;

    private SpriteBatch batch;
    private SpriteBatch backgroundBatch;
    private SpriteBatch interfaceBatch;
    private Texture background;
    private Texture foreground;

    private OrthographicCamera backgroundCam;
    private OrthographicCamera camera;
    private OrthographicCamera interfaceCam;
    private long cameraStartStamp = -1;
    private long cameraDuration = -1;
    private float cameraStartZoom = 1f;
    private float cameraEndZoom = 1f;
    private Vector2 cameraStartPos;
    private Vector2 cameraEndPos;

    private Vector2 lastTouchPos;
    private Vector2 lastRealTouchPos;

    private boolean birdTouched = false;

    public enum State{
        READY, PLAYING, END, PAUSE
    }
    public State state;

    public Play(){
        create();
    }

    @Override
    public void create() {
        alea = new Random();
        batch = new SpriteBatch();
        backgroundBatch = new SpriteBatch();
        interfaceBatch = new SpriteBatch();
        backgroundCam = new OrthographicCamera();
        backgroundCam.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        interfaceCam = new OrthographicCamera();
        interfaceCam.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        interfaceCam.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        interfaceCam.update();
        Gdx.input.setInputProcessor(this);
        reload();
    }
    public void reload(){
        background = new Texture(Gdx.files.internal("newbackground.png"));
        foreground = new Texture(Gdx.files.internal("foreground.png"));

        waspy = new Wasp(new Vector2(WORLD_WIDTH / 2, WORLD_HEIGHT / 2), new Vector2(0, 0));
        tweety = new Bird(new Vector2(200, WORLD_HEIGHT / 4), new Vector2(0, 0));
        slingshot = new Slingshot(this, 100, FLOOR_HEIGHT, 50, 200, tweety);
        restart();
    }
    public void restart(){
        //camera.position.set((camera.viewportWidth/2), camera.viewportHeight/2, 0);
        //camera.update();
        voc = vocSource.pickAVoc();
        buildMap();
        buildPanel();
        resetBird();
//        camera.position.set(WORLD_WIDTH/2, WORLD_HEIGHT/2, 0);
    }
    public void endGame(){
        state = State.END;
        restart();
    }
    public void resetBird(){
        state = State.READY;
        slingshot.reset();
        animateCam(new Vector2((WORLD_WIDTH / 2) / 2, (WORLD_HEIGHT/2)/2), 2, 1500);
    }
    public void buildMap(){
        scene = new Scenery();
        //scene.addFloor();
        for (int i=0; i<150; i++) {
            try {
                scene.dropElement(new PhysicalObject(new Vector2(alea.nextFloat()*WORLD_WIDTH, 0), BLOCK_SIZE, BLOCK_SIZE, "block.png"));
            } catch (ObjectOutOfBoundsException e) {
                Gdx.app.log("ANGRY", "Object out of bounds: "+e.getMessage());
            } catch (SceneCollapseException e) {
                Gdx.app.log("ANGRY", "Unstable object: "+e.getMessage());
            }
        }
        int pigsLeft = 5;
        while (pigsLeft > 0) {
            try {
                scene.dropElement(new Pig(new Vector2(alea.nextFloat()*WORLD_WIDTH,FLOOR_HEIGHT+ BLOCK_SIZE),voc.pickAWord()));
                pigsLeft--;
            } catch (ObjectOutOfBoundsException e) {
                Gdx.app.log("ANGRY", "Pig out of bounds: "+e.getMessage());
            } catch (SceneCollapseException e) {
                Gdx.app.log("ANGRY", "Unstable pig: "+e.getMessage());
            }
        }
        int TNTLeft = 5;
        while (TNTLeft > 0) {
            try {
                scene.dropElement(new TNT(new Vector2(alea.nextFloat()*WORLD_WIDTH,FLOOR_HEIGHT+ BLOCK_SIZE),0));
                TNTLeft--;
            } catch (ObjectOutOfBoundsException e) {
                Gdx.app.log("ANGRY", "TNT out of bounds: "+e.getMessage());
            } catch (SceneCollapseException e) {
                Gdx.app.log("ANGRY", "Unstable TNT: "+e.getMessage());
            }
        }
    }
    public void buildPanel(){
        panel = new Panel(scene.pickAWord());
        scoreVal = 3; // allow a few mistakes before game over
        scoreDisp= new BitmapFont();
        scoreDisp.setColor(Color.BLACK);
        scoreDisp.getData().setScale(2);
    }
    public void launchBird(Vector2 speed){
        tweety.setSpeed(speed);
        state = State.PLAYING;
        //test cam animation
        animateCam(new Vector2((WORLD_WIDTH/2), WORLD_HEIGHT/2), 1, 2500);
    }
    public void update() {
        float dt = Gdx.graphics.getDeltaTime();

        if (dt > 1.0f) // ignore major lapses like the one at the beginning of the app
        {
            return;
        }
        // --------- Bird
        if(state == State.PLAYING){
            tweety.accelerate(dt);
            tweety.move(dt);
            if(tweety.getY() < FLOOR_HEIGHT) 
                resetBird();
            PhysicalObject phob = scene.objectHitBy(tweety);
            if (phob != null) {
                if (phob instanceof Pig) {
                    if (((Pig)phob).getWord() == panel.getWord()) {
                        scoreVal++;
                        scene.removeElement(phob);
                    } else {
                        scoreVal--;
                        if (scoreVal == 0) { // Game over
                            AngryWirds.pages.push(new GameOver());
                        }
                    }
                } else {
                    scene.removeElement(phob);
                }
                resetBird();
            }
        }
        

        // --------- Wasp
        waspy.accelerate(dt);
        waspy.move(dt);

        //cam
        updateCam(dt);
    }

    public enum AnimationTimingFunction{
        LINEAR, EASE
    }
    public void updateCam(float dt){
        //camera
//        float zoomLevel = 2f;
//        float visibleViewPortWidth = camera.viewportWidth / zoomLevel;
        long currentTimeProgression = TimeUtils.timeSinceMillis(cameraStartStamp);
        if(currentTimeProgression <= cameraDuration){
            float progression = currentTimeProgression/(float)cameraDuration;
            float animatedProgression = getSmoothProgression(progression, AnimationTimingFunction.EASE);
            //set
            float cam_x = animatedProgression*(cameraEndPos.x - cameraStartPos.x) + cameraStartPos.x;
            float cam_y = animatedProgression*(cameraEndPos.y - cameraStartPos.y) + cameraStartPos.y;
            float cam_zoom = animatedProgression*(cameraEndZoom - cameraStartZoom) + cameraStartZoom;
            camera.position.set(cam_x, cam_y, 0);
            camera.zoom = 1f/cam_zoom;
            camera.update();
            updateBgCam();
        }
    }
    public void updateBgCam(){
        backgroundCam.position.x = camera.position.x / BG_CAM_SCALE + camera.viewportHeight/2;
        backgroundCam.position.y = camera.position.y / BG_CAM_SCALE + camera.viewportWidth/4;
        //backgroundCam.position.set(new Vector3(camera.position).scl(1f/BG_CAM_SCALE));
        backgroundCam.zoom = camera.zoom;
        backgroundCam.update();
    }
    public void animateCam(Vector2 position, float zoom, long duration, AnimationTimingFunction timingFunction/*, TimingFunction timingFunction*/){
        cameraStartStamp = TimeUtils.millis();
        cameraDuration = duration;
        cameraStartPos = new Vector2(camera.position.x, camera.position.y);
        cameraEndPos = position;
        cameraStartZoom = 1f/camera.zoom;
        cameraEndZoom = zoom;
    }
    public void animateCam(Vector2 position, float zoom, long duration){
        animateCam(position, zoom, duration, AnimationTimingFunction.EASE);
    }
    //source: https://en.wikipedia.org/wiki/Smoothstep
    float getSmoothProgression(float progression, AnimationTimingFunction timingFunction) {
        if(timingFunction == AnimationTimingFunction.EASE) {
            return progression * progression * (3 - 2 * progression);
        }else{
            return progression;
        }
    }

    @Override
    public void render() {
        update();

        backgroundBatch.begin();
        backgroundBatch.setProjectionMatrix(backgroundCam.combined);
        backgroundBatch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        backgroundBatch.end();

        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.draw(foreground, 0, 0, camera.viewportWidth, camera.viewportHeight);
        //tweety.draw(batch); is now drawn in slingshot
        waspy.draw(batch);
        scene.draw(batch);
        slingshot.render(batch);
        batch.end();

        interfaceBatch.begin();
        interfaceBatch.setProjectionMatrix(interfaceCam.combined);
        panel.draw(interfaceBatch);
        interfaceBatch.end();
    }
    private void displayScore(SpriteBatch batch)
    {
        scoreDisp.draw(batch, "Score: "+scoreVal, WORLD_WIDTH-150, WORLD_HEIGHT-60);
    }

    public Vector2 getCameraActualPos(int x, int y){
        Vector3 actualPos = camera.unproject(new Vector3(x, y, 0));
        return new Vector2(actualPos.x, actualPos.y);
    }
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        lastRealTouchPos = new Vector2(screenX, screenY);
        lastTouchPos = getCameraActualPos(screenX, screenY);
        birdTouched = tweety.getBoundingRectangle().contains(lastTouchPos);
        if(!birdTouched){
            scene.handleTouchDown(lastTouchPos); // handle effect on pigs
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(birdTouched) {
            Vector2 launchVector = slingshot.getLaunchVector();
            if (state != State.READY || launchVector.len() < MIN_STRENGTH) {
                return true;
            }
            launchBird(launchVector);
        }else{
            scene.handleTouchUp(getCameraActualPos(screenX, screenY)); // handle effect on pigs
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(state != State.READY) {
            return false;
        }
        if(lastRealTouchPos == null){
            return false;
        }
        Vector2 realTouchPos = new Vector2(screenX, screenY);
        Vector2 touchPos = getCameraActualPos(screenX, screenY);
        Vector2 realDiffPos = new Vector2(realTouchPos).sub(lastRealTouchPos);
        Vector2 diffPos = new Vector2(touchPos).sub(lastTouchPos);
        if(birdTouched) {
            //move bird
            Vector2 slingOrigin = slingshot.getOrigin();
            tweety.setPosition(MathUtils.clamp(tweety.getX() + diffPos.x, -tweety.getWidth() / 2, slingOrigin.x), MathUtils.clamp(tweety.getY() + diffPos.y, FLOOR_HEIGHT, slingOrigin.y));
        } else {
            camera.position.add(-realDiffPos.x * camera.zoom, realDiffPos.y * camera.zoom, 0);
            camera.update();
            updateBgCam();
        }
        lastRealTouchPos = realTouchPos;
        lastTouchPos = touchPos;
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
