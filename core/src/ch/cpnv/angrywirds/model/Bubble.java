package ch.cpnv.angrywirds.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Xavier on 12.05.18.
 */

public final class Bubble extends TextualObject {

    private static final String PICNAME = "bubble.png";
    private static final int WIDTH = 260;
    private static final int HEIGHT = 160;
    private static final int BUBBLE_OFFSET = 20; // to center the spike on the head
    private static final int TEXT_OFFSET_X = 40; // to place the text inside the bubble
    private static final int TEXT_OFFSET_Y = 100;

    private BitmapFont font;

    /**
     * Bubble will appear for the given pig
     *
     */
    public Bubble(Pig pig) {
        super(new Vector2 (pig.getX(),pig.getY()), WIDTH, HEIGHT, PICNAME, pig.getWordForDisplay());
        setBounds(pig.getX()-WIDTH/2-BUBBLE_OFFSET, pig.getY()+Pig.HEIGHT, WIDTH, HEIGHT);
        font= new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(2);
    }

    public void draw(Batch batch)
    {
        super.draw(batch);
        font.draw(batch, getText(), getX()+TEXT_OFFSET_X, getY()+TEXT_OFFSET_Y);
    }
}
