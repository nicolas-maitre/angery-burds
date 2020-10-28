package ch.cpnv.angrywirds.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import ch.cpnv.angrywirds.AngryWirds;
import ch.cpnv.angrywirds.activities.Welcome;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new AngryWirds(), config);
	}
}
