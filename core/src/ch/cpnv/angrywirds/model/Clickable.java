package ch.cpnv.angrywirds.model;

import com.badlogic.gdx.math.Vector2;

public interface Clickable {
    public boolean checkClick(Vector2 clickPos);
    public void onClick();
}
