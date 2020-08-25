package ch.cpnv.angrywirds.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ch.cpnv.angrywirds.AngryWirds;

/**
 * Contains all the static items to display in our world
 * Created by Xavier on 12.05.18.
 */

public final class Scenery {

    public static final int BLOCK_SIZE = 30;
    private static final float X_MIN = 250;
    private static final float X_MAX = AngryWirds.WORLD_WIDTH-150;
    private static final float Y_MAX = AngryWirds.WORLD_HEIGHT * 0.75f;

    private ArrayList<PhysicalObject> scene;

    public Scenery() {
        scene = new ArrayList<PhysicalObject>();
    }

    /**
     * Add one piece of scenary
     * @param el
     */
    public void addElement (PhysicalObject el)
    {
        scene.add(el);
    }

    public void dropElement (PhysicalObject el) throws ObjectOutOfBoundsException, SceneCollapseException {
        // Check horizontal placement
        if (el.getX() < X_MIN || el.getX() > X_MAX) throw new ObjectOutOfBoundsException(el.toString());
        // Best case: on the floor
        el.setY(AngryWirds.FLOOR_HEIGHT);
        // Use a rectangle to detect "collison" on the way down
        Rectangle fallPath = new Rectangle(el.getX(),0,el.getWidth(),AngryWirds.WORLD_HEIGHT);
        for (PhysicalObject p : scene) {
            if (p.getBoundingRectangle().overlaps(fallPath) && (p.getY()+p.getHeight() > el.getY())) {
                // Check if object can stand
                if (el.getX()+el.getWidth()/2 < p.getX() || el.getX()+el.getWidth()/2 > p.getX()+p.getWidth()) throw new SceneCollapseException(el.toString());
                el.setY(p.getY()+p.getHeight());
                // Check max height of scenery
                if (el.getY()+el.getHeight() > Y_MAX) throw new ObjectOutOfBoundsException(el.toString());
            }
        }
        scene.add(el);
    }

    /**
     * Lay down a line of blocks to act a floor to the scene
     */
    public void addFloor()
    {
        for (int i = 5; i < AngryWirds.WORLD_WIDTH / BLOCK_SIZE; i++) {
            addElement(new PhysicalObject(new Vector2(i * BLOCK_SIZE, AngryWirds.FLOOR_HEIGHT), BLOCK_SIZE, BLOCK_SIZE, "block.png"));
        }
    }
    /**
     * Render the whole scenary
     * @param batch
     */
    public void draw(Batch batch)
    {
        for (PhysicalObject p : scene) p.draw(batch);
    }

}
