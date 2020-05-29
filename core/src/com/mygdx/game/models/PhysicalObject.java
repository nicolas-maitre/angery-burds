package com.mygdx.game.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PhysicalObject extends Sprite {
    public PhysicalObject(String textureSrc, Rectangle startRectangle){
        super(new Texture(textureSrc));
        setOrigin(startRectangle.x, startRectangle.y);
        setBounds(
                startRectangle.x - (startRectangle.width/2),
                startRectangle.y - (startRectangle.height/2),
                startRectangle.width,
                startRectangle.height
        );
    }
}
