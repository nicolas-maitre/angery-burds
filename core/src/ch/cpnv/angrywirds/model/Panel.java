package ch.cpnv.angrywirds.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ch.cpnv.angrywirds.activities.Play;
import ch.cpnv.angrywirds.model.Data.SemanticWord;
import ch.cpnv.angrywirds.provider.Language;
import ch.cpnv.angrywirds.provider.TranslationDoesNotExistException;

public class Panel extends Sprite {

    private static final String PICNAME = "panel.png";
    private static final float WIDTH = 500;
    private static final float HEIGHT = 100;
    private static final float POSX = 20;
    private static final float POSY = Play.WORLD_HEIGHT-HEIGHT;
    private static final float TEXT_OFFSET_X = 50; // to place the text inside the panel
    private static final float TEXT_OFFSET_Y = 50;
    private BitmapFont font;
    private SemanticWord word;
    private String displayWord;

    public Panel (SemanticWord word, Language language) throws TranslationDoesNotExistException {
        super(new Texture(PICNAME));
        setBounds(POSX,POSY,WIDTH,HEIGHT);
        this.word = word;
        this.displayWord = word.getValue(language);
        font= new BitmapFont();
        font.setColor(Color.BLACK);
        font.getData().setScale(2);
    }

    public void draw(Batch batch)
    {
        super.draw(batch);
        font.draw(batch, displayWord, getX()+TEXT_OFFSET_X, getY()+TEXT_OFFSET_Y);
    }

    public SemanticWord getWord() {
        return word;
    }

}
