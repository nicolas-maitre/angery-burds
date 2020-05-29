package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.models.Bird;

public class AngeryBurds extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Bird bird;

	@Override
	public void create () {
		batch = new SpriteBatch();
		bird = new Bird();

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(128, 128, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();



		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
