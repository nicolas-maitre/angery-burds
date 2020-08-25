package ch.cpnv.angrywirds.model;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Xavier on 06.05.18.
 */

public final class Pig extends TextualObject {

    private static final String PICNAME = "pig.png";
    private static final int WIDTH = 60;
    public static final int HEIGHT = 60; // made public for the bubble placement

    public Pig(Vector2 position, String word) {
        super(position, WIDTH, HEIGHT, PICNAME, word);
    }

    public String getWord() {
        return super.getText();
    }

}
