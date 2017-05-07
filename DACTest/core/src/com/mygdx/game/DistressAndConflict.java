package com.mygdx.game;

import Enums.State;
import game.GameManager;
import game.UserInterface.UIManager;
import Player.Account;
import Player.Player;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;

public class DistressAndConflict extends ApplicationAdapter {
	//private SpriteBatch batch;
	private Account user;
	private GameManager gameManager;
	private UIManager uiManager;
	private int OldFps = 0;

	public DistressAndConflict(Account user, GameManager gameManager, UIManager uiManager) {
		this.user = user;
		this.gameManager = gameManager;
		setUiManager(uiManager);
	}

	public DistressAndConflict() {
		this.user = new Account();
		this.gameManager = new GameManager(this, State.Finished, 1, "lel", new ArrayList<Player>());
		setUiManager(new UIManager(this, this.gameManager));
	}

	public GameManager getGameManager() {
		return gameManager;
	}

	public void setGameManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	public UIManager getUiManager() {
		return uiManager;
	}

	public void setUiManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}

	public void host(){

	}
	public void join(){

	}
	
	@Override
	public void create () {
		//http://www.gamefromscratch.com/post/2014/04/16/LibGDX-Tutorial-11-Tiled-Maps-Part-1-Simple-Orthogonal-Maps.aspx
		//gameManager.setTiledMap(new TmxMapLoader().load("assets/TestMap1.tmx"));
		gameManager.create();
		uiManager.create();

		//batch = new SpriteBatch();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameManager.render();
		uiManager.render();
		showFPS();

		//batch.setProjectionMatrix(gameManager.getOrthographicCamera().combined);
	}



	@Override
	public void dispose () {
		//batch.dispose();
	}

	public void showFPS(){//Created to optimize FPS, YaY 60 FPS!
		//https://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/Graphics.html#getFramesPerSecond--
		int CurFPS = Gdx.graphics.getFramesPerSecond();
		if (CurFPS != OldFps){
			OldFps = CurFPS;
			System.out.println("FPS: " + CurFPS);
		}
	}
}
