package com.mygdx.game;

import Player.Account;
import Game.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class DistressAndConflict extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	private Account user;
	private GameManager gameManager;

	public DistressAndConflict(Account user, GameManager gameManager) {
		this.user = user;
		this.gameManager = gameManager;
	}

	public DistressAndConflict() {
		this.user = new Account();
		this.gameManager = new GameManager();
	}

	public void host(){

	}
	public void join(){

	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture(Gdx.files.internal("assets/map1.png"));
		Map tmp = gameManager.loadMap("assets/map1.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 20, 20, 100, 100);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
