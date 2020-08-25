package ch.cpnv.angrywirds.model;

import com.badlogic.gdx.math.Vector2;

public class TextualObject extends PhysicalObject {

    private String text;

    public TextualObject (Vector2 position, float width, float height, String picname, String text) {
        super(position, width, height, picname);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
