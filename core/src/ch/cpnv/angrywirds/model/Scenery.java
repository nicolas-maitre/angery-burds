package ch.cpnv.angrywirds.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.activities.Play;
import ch.cpnv.angrywirds.model.Data.Word;

/**
 * Contains all the static items to display in our world
 * Created by Xavier on 12.05.18.
 */

public final class Scenery {

    public static final int BLOCK_SIZE = 30;
    private static final float X_MIN = 250;
    private static final float X_MAX = Play.WORLD_WIDTH-150;
    private static final float Y_MAX = Play.WORLD_HEIGHT * 0.75f;

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

    public void removeElement (PhysicalObject el)
    {
        scene.remove(el);
    }

    public void dropElement (PhysicalObject el) throws ObjectOutOfBoundsException, SceneCollapseException {
        // Check horizontal placement
        if (el.getX() < X_MIN || el.getX() > X_MAX) throw new ObjectOutOfBoundsException(el.toString());
        // Best case: on the floor
        el.setY(Play.FLOOR_HEIGHT);
        // Use a rectangle to detect "collison" on the way down
        Rectangle fallPath = new Rectangle(el.getX(),0,el.getWidth(),Play.WORLD_HEIGHT);
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
        for (int i = 5; i < Play.WORLD_WIDTH / BLOCK_SIZE; i++) {
            addElement(new PhysicalObject(new Vector2(i * BLOCK_SIZE, Play.FLOOR_HEIGHT), BLOCK_SIZE, BLOCK_SIZE, "block.png"));
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

    /**
     * Handles interactions of the user with the elements of the scenery
     * @param point
     */
    public void handleTouchDown(Vector2 point)
    {
        for (PhysicalObject po : scene) {
            if (po instanceof Pig) { // Only pigs can be touched for now
                Pig p = (Pig)po;
                if (p.getBoundingRectangle().contains(point)) {
                    p.sayWord();
                }
            }
        }
    }

    /**
     * Handles interactions of the user with the elements of the scenery
     * @param point
     */
    public void handleTouchUp(Vector2 point)
    {
        for (PhysicalObject po : scene) {
            if (po instanceof Pig) { // Only pigs can be touched for now
                ((Pig)po).shutUp();
            }
        }
    }

    public Word pickAWord()
    {
        // build a list of pigs
        ArrayList<Pig> pigs = new ArrayList<Pig>();
        for (PhysicalObject physobj : scene) {
            if (physobj instanceof Pig) {
                pigs.add((Pig)physobj);
            }
        }
        // Pick one at random
        return pigs.get(AngryWirds.alea.nextInt(pigs.size())).getWord();
    }

    /**
     * Returns the object of the scenery that has been hit by the moving object, null if none
     * @param mob
     * @return
     */
    public PhysicalObject objectHitBy (MovingObject mob)
    {
        for (PhysicalObject el : scene) {
            if (el.getBoundingRectangle().overlaps(mob.getBoundingRectangle())) {
                return el;
            }
        }
        return null;
    }
}
