package ch.cpnv.angrywirds.model;


import com.badlogic.gdx.math.Vector2;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.activities.Play;

/**
 * Created by Xavier on 06.05.18.
 */

public final class Wasp extends MovingObject {

    private static final float AGITATION = 100;
    private static final String PICNAME = "wasp.png";
    private static final int WIDTH = 60;
    private static final int HEIGHT = 60;

    public Wasp(Vector2 position, Vector2 speed) {
        super(position, WIDTH, HEIGHT, PICNAME, speed);
    }

    @Override
    public void accelerate(float dt) {
        // The wasp counters gravity with its flight. We will not express that in the code, we will just ignore gravity
        // The wasp only slightly alters its speed at random.
        // The trick to keep the wasp inside: the more the wasp is on the left, the more the folly vector will tend to be to the right, and vice-vera, and same for up/down
        // if the wasp is on the left of the screen, X is small and X / WORLD_WIDTH is a very small value.
        // So alea.nextFloat() minus something small is very likely to be a positive value, i.e: oriented towards the right of the screen
        // if the wasp is on the right of the screen, X is close to WORLD_WIDTH and thus X / WORLD_WIDTH is close to 1.
        // So alea.nextFloat() minus something close to 1 is very likely to be a negative value, i.e: oriented towards the left of the screen

        Vector2 folly = new Vector2(AngryWirds.alea.nextFloat()-(getX()/ Play.WORLD_WIDTH),AngryWirds.alea.nextFloat()-(getY()/Play.WORLD_HEIGHT));
        speed = speed.add(folly.scl(AGITATION*dt));
    }
}
