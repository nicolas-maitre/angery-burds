package com.mygdx.game.models;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import org.w3c.dom.css.Rect;

public class Bird extends MovingObject {
    public Bird(){
        super("bird.png", new Rectangle(200, 200, 200, 200), new Vector2(0, 0));
        status = MovingStatus.FROZEN;
    }

    @Override
    public void accelerate(float timeScale) {
        speed.y += GRAVITY * timeScale;
    }
}
