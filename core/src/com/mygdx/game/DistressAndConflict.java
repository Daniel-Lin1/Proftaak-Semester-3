package com.mygdx.game;

import Player.Account;
import Game.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DistressAndConflict extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	private Account user;
	private GameManager gameManager;

	public void host(){

	}
	public void join(){

	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture(Gdx.files.internal("core/assets/badlogic.jpg"));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 20, 20);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
