package com.mygdx.game.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class MovingObject extends PhysicalObject {
    public final static float GRAVITY = -50;
    public Vector2 speed;
    public MovingStatus status = MovingStatus.FROZEN;

    public MovingObject(String textureSrc, Rectangle startRectangle, Vector2 startSpeed){
        super(textureSrc, startRectangle);
        this.speed = startSpeed;
    }
    public Vector2 getPosition(){
        return new Vector2(this.getX(), this.getY());
    }
    public void move(float timeScale)
    {
        if(status == MovingStatus.FROZEN) {
            return;
        }
        translate(speed.x * timeScale, speed.y * timeScale);
    }
    public abstract void accelerate(float timeScale);
    public boolean collideWith(Rectangle rectangle){
        return this.getBoundingRectangle().overlaps(rectangle);
    }
    public boolean collideWidth(Sprite sprite){
        return collideWith(sprite.getBoundingRectangle());
    }
}
enum MovingStatus{
    FROZEN,
    MOVING,
    DRAGGED
}