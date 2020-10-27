package ch.cpnv.angrywirds.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

import ch.cpnv.angrywirds.model.Data.Word;

/**
 * Created by Xavier on 06.05.18.
 */

public final class Pig extends TextualObject {

    private static final String PICNAME = "pig.png";
    private static final int WIDTH = 60;
    public static final int HEIGHT = 60; // made public for the bubble placement

    private Word word; // The word with both languages
    private Bubble scream = null; // A bubble to show the pig's word to the player

    public Pig(Vector2 position, Word word) {
        super(position, WIDTH, HEIGHT, PICNAME, word.getValue1());
        this.word = word;
    }

    public String getWordForDisplay() {
        return super.getText();
    }

    public Word getWord() {
        return word;
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
        if (scream != null) scream.draw(batch);
    }

    public void sayWord() {
        this.scream = new Bubble(this); // create a new bubble for my word
    }

    public void shutUp() {
        this.scream = null;
    }
}
