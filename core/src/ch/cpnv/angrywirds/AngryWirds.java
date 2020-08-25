package ch.cpnv.angrywirds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

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

public class AngryWirds extends Game {

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

    @Override
    public void create() {
        alea = new Random();

        tweety = new Bird(new Vector2(0, WORLD_HEIGHT / 4), new Vector2(200, 400));
        waspy = new Wasp(new Vector2(WORLD_WIDTH / 2, WORLD_HEIGHT / 2), new Vector2(0, 0));

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

        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("background.jpg"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

    }

    public void update() {
        float dt = Gdx.graphics.getDeltaTime();
        if (dt < 1.0f) // ignore major lapses like the one at the beginning of the app
        {

            // --------- Bird
            tweety.accelerate(dt);
            tweety.move(dt);

            // --------- Wasp
            waspy.accelerate(dt);
            waspy.move(dt);
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
