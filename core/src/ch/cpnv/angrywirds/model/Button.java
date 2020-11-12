package ch.cpnv.angrywirds.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Button extends TextualObject implements Clickable {
    private BitmapFont textDisplay;
    public boolean isHidden = false;

    public Button(Rectangle rectangle, String text) {
        super(rectangle, "button_bg.png", text);
        textDisplay = new BitmapFont();
        textDisplay.setColor(Color.WHITE);
        textDisplay.getData().setScale(2);
    }

    @Override
    public void draw(Batch batch) {
        if(isHidden){
            return;
        }
        super.draw(batch);
        Rectangle thisBoudingRect = this.getBoundingRectangle();
        textDisplay.draw(batch, this.text, thisBoudingRect.x + 50, thisBoudingRect.y + thisBoudingRect.height - 10);
    }

    //could and should be moved to a "clickable" interface or class
    public boolean checkClick(Vector2 clickPos){
        if(isHidden){
            return false;
        }
        if(this.getBoundingRectangle().contains(clickPos)){
            onClick();
            return true;
        }
        return false;
    }
    public void onClick(){
        System.out.println("onclick!");
    }
}
