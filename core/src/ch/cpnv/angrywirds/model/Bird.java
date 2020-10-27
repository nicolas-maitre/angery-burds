package ch.cpnv.angrywirds.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.activities.Play;

/**
 * Created by Xavier on 06.05.18.
 */

public final class Bird extends MovingObject {

    private static final String PICNAME = "bird.png";
    public static final int WIDTH = 60;
    public static final int HEIGHT = 60;

    public Bird() {
        super(new Vector2(0, 0), WIDTH, HEIGHT, PICNAME, new Vector2(0, 0));
    }

    public Bird(Vector2 position, Vector2 speed) {
        super(position, WIDTH, HEIGHT, PICNAME, speed);
    }

    @Override
    public void accelerate(float dt) {
        speed.y += GRAVITY*dt; // That bird is a poor glider
    }

}
