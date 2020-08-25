package ch.cpnv.angrywirds.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Xavier on 03.05.18.
 */

public class PhysicalObject extends Sprite {

    public PhysicalObject(Vector2 position, float width, float height, String picname) {
        super(new Texture(picname));
        setBounds(position.x, position.y, width, height);
    }

    /**
     * Returns true if the current object is in collision (rectangle overlap) with the object passed
     *
     */
    public boolean collidesWith(PhysicalObject o)
    {
        return this.getBoundingRectangle().overlaps(o.getBoundingRectangle());
    }

    @Override
    public String toString()
    {
        return getClass().getSimpleName()+" at ("+this.getX()+","+this.getY()+")";
    }

}
