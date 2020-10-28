package ch.cpnv.angrywirds.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.activities.Play;

public class Slingshot {
    final static float SLING_STRENGTH = 3;
    private Play game;
    private Rectangle rectangle;
    private Bird bird;
    private PhysicalObject backBase;
    private PhysicalObject frontBase;
    private PhysicalObject backRubber;
    private PhysicalObject frontRubber;
    public Slingshot(Play game, Rectangle rectangle, Bird bird){
        this.game = game;
        this.rectangle = rectangle;
        this.bird = bird;
        build();
    }
    public Slingshot(Play game, float x, float y, float width, float height, Bird bird){
        this(game, new Rectangle(x,y,width,height), bird);
    }
    public void build(){
        backBase = new PhysicalObject(rectangle, "slingshot1.png");
        frontBase = new PhysicalObject(rectangle, "slingshot2.png");
    }
    public void reset(){
        Vector2 origin = getOrigin();
        bird.setPosition(origin.x, origin.y);
        bird.setSpeed(new Vector2(0,0));
    }
    public void render(SpriteBatch batch){
        backBase.draw(batch);
        bird.draw(batch);
        frontBase.draw(batch);
    }
    public Vector2 getOrigin(){
        return new Vector2(rectangle.x + rectangle.width / 2 - bird.getWidth() / 2, rectangle.y + rectangle.height - bird.getHeight());
    }
    public Rectangle getRectangle() {
        return rectangle;
    }
    public Vector2 getLaunchVector(){
        Vector2 origin = getOrigin();
        return origin.sub(bird.getX(), bird.getY()).scl(SLING_STRENGTH);
    }
}
