package ch.cpnv.angrywirds;

import com.badlogic.gdx.Game;

import java.util.Random;
import java.util.Stack;

import ch.cpnv.angrywirds.activities.Play;
import ch.cpnv.angrywirds.activities.Welcome;

public class AngryWirds extends Game {

    public static Random alea; // random generator object. Static for app-wide use

    public static Stack<Game> pages;

    @Override
    public void create() {
        alea = new Random();
        pages = new Stack<Game>();
        pages.push(new Welcome());
    }

    @Override
    public void render() {
        pages.peek().render();
    }

}
