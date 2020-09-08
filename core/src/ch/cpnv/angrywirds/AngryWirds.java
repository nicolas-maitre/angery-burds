package ch.cpnv.angrywirds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Random;

import ch.cpnv.angrywirds.model.Bird;
import ch.cpnv.angrywirds.model.ObjectOutOfBoundsException;
import ch.cpnv.angrywirds.model.PhysicalObject;
import ch.cpnv.angrywirds.model.Pig;
import ch.cpnv.angrywirds.model.SceneCollapseException;
import ch.cpnv.angrywirds.model.Scenery;
import ch.cpnv.angrywirds.model.TNT;
import ch.cpnv.angrywirds.model.Wasp;

import static ch.cpnv.angrywirds.model.Scenery.BLOCK_SIZE;

public class AngryWirds extends Game implements InputProcessor {

    public static Random alea; // random generator object. Static for app-wide use

    public static final float WORLD_WIDTH = 1600;
    public static final float WORLD_HEIGHT = 900;
    public static final int FLOOR_HEIGHT = 120;

    private Scenery scene;
    private Bird tweety;
    private Wasp waspy;

    private SpriteBatch batch;
    private Texture background;

    private OrthographicCamera camera;
    private long cameraStartStamp = -1;
    private long cameraDuration = -1;
    private float cameraStartZoom = 1f;
    private float cameraEndZoom = 1f;
    private Vector2 cameraStartPos;
    private Vector2 cameraEndPos;

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
        throwBird();
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public enum State{
        READY, PLAYING, END, PAUSE
    }
    public State state;

    @Override
    public void create() {
        alea = new Random();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        Gdx.input.setInputProcessor(this);
        reload();
    }
    public void reload(){
        background = new Texture(Gdx.files.internal("background.jpg"));
        tweety = new Bird(new Vector2(0, WORLD_HEIGHT / 4), new Vector2(200, 400));
        waspy = new Wasp(new Vector2(WORLD_WIDTH / 2, WORLD_HEIGHT / 2), new Vector2(0, 0));
        restart();
    }
    public void restart(){
        //camera.position.set((camera.viewportWidth/2), camera.viewportHeight/2, 0);
        //camera.update();
        buildMap();
        resetBird();
//        camera.position.set(WORLD_WIDTH/2, WORLD_HEIGHT/2, 0);
    }
    public void endGame(){
        state = State.END;
        restart();
    }
    public void resetBird(){
        state = State.READY;
        tweety = new Bird(new Vector2(0, WORLD_HEIGHT / 4), new Vector2(200, 400));
        animateCam(new Vector2((WORLD_WIDTH / 2) / 2, (WORLD_HEIGHT/2)/2), 2, 4000);
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
                scene.dropElement(new Pig(new Vector2(alea.nextFloat()*WORLD_WIDTH,FLOOR_HEIGHT+ BLOCK_SIZE),"?"));
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
    public void throwBird(){
        state = State.PLAYING;
        //test cam animation
        animateCam(new Vector2((WORLD_WIDTH/2), WORLD_HEIGHT/2), 1, 6000);
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
            if(tweety.getY() < -tweety.getHeight()){
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
        }
    }
    public void animateCam(Vector2 position, float zoom, long duration, AnimationTimingFunction timingFunction/*, TimingFunction timingFunction*/){
        long currentTime = TimeUtils.millis();
        cameraStartStamp = currentTime;
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
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        tweety.draw(batch);
        waspy.draw(batch);
        scene.draw(batch);
        batch.end();
    }

}
