package ch.cpnv.angrywirds.activities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.model.Button;
import ch.cpnv.angrywirds.model.Clickable;

public class Welcome extends Game implements InputProcessor {

    public static final float WORLD_WIDTH = 1600;
    public static final float WORLD_HEIGHT = 900;
    public static final float LEFT_MARGIN = 400;

    private ArrayList<Clickable> clickables;

    private SpriteBatch batch;
    private Texture background;
    private BitmapFont title;
    private BitmapFont subTitle;

    private Button playButton;
    private ArrayList<Button> fromButtons;
    private ArrayList<Button> toButtons;

    private String fromLang;
    private String toLang;

    private OrthographicCamera camera;

    public Welcome() {
        create();
    }

    @Override
    public void create() {
        clickables = new ArrayList<Clickable>();

        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("background.jpg"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        title= new BitmapFont();
        title.setColor(Color.ROYAL);
        title.getData().setScale(6);

        subTitle = new BitmapFont();
        subTitle.setColor(Color.ROYAL);
        subTitle.getData().setScale(4);

        Gdx.input.setInputProcessor(this);

        //buttons
        ;
        playButton = new Button(new Rectangle(LEFT_MARGIN - 320, WORLD_HEIGHT - 250, 300, 50), "Go!"){
            @Override
            public void onClick() {
                AngryWirds.pages.push(new Play());
            }
        };
        playButton.isHidden = true;

        final ArrayList<String> languages = new ArrayList<String>(Arrays.asList("Français", "English", "Español"));
        fromButtons = new ArrayList<Button>();
        toButtons = new ArrayList<Button>();
        for (int indCol = 0; indCol < 2; indCol++) {
            final int colNum = indCol;
            final ArrayList<Button> currentList = (colNum==0)?fromButtons:toButtons;
            for (int indLang = 0; indLang < languages.size(); indLang++) {
                final String lang = languages.get(indLang);
                currentList.add(new Button(new Rectangle(LEFT_MARGIN + (colNum * 400), WORLD_HEIGHT - (400 + indLang * 70), 300, 50), lang) {
                    @Override
                    public void onClick() {
                        System.out.println("click on " + lang);
                        if(colNum == 0){
                            fromLang = lang;
                        }else{
                            toLang = lang;
                        }
                        for (Button button:currentList) {
                            button.isHidden = true;
                        }
                        if(fromLang != null && toLang != null){
                            playButton.isHidden = false;
                        }
                    }
                });
            }
        }

        clickables.add(playButton);
        clickables.addAll(fromButtons);
        clickables.addAll(toButtons);
    }

    public void update() {
        float dt = Gdx.graphics.getDeltaTime();
    }

    @Override
    public void render() {
        update();
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        batch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);
        title.draw(batch,"Hello",LEFT_MARGIN,WORLD_HEIGHT - 100);
        subTitle.draw(batch, "Exercice de " + (fromLang!=null ? fromLang:"(choisir)") + " en " + (toLang!=null ? toLang:"(choisir)"), LEFT_MARGIN, WORLD_HEIGHT - 200);
        playButton.draw(batch);
        for (Button button:fromButtons) {
            button.draw(batch);
        }
        for (Button button:toButtons) {
            button.draw(batch);
        }
        batch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector2 clickPos = getCameraActualPos(screenX, screenY);
        for(Clickable clickable:clickables) {
            clickable.checkClick(clickPos);
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public Vector2 getCameraActualPos(int x, int y){
        Vector3 actualPos = camera.unproject(new Vector3(x, y, 0));
        return new Vector2(actualPos.x, actualPos.y);
    }
}
