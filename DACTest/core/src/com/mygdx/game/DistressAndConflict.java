package com.mygdx.game;

import enums.State;
import game.GameManager;
import game.userinterface.UIManager;
import player.Player;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DistressAndConflict extends ApplicationAdapter {
	private GameManager gameManager;
	private UIManager uiManager;
	private int oldFps = 0;

	Logger logger = Logger.getLogger(DistressAndConflict.class.getName());

	public DistressAndConflict()  {
		ArrayList<Player> players = new ArrayList<>();
		players.add(new Player(0, "player1"));
		players.add(new Player(1, "player2"));
		players.add(new Player(2, "player3"));
		players.add(new Player(3, "player4"));

		this.gameManager = new GameManager(State.STARTED, 1, "", players, 0);
		this.uiManager = new UIManager(this.gameManager);
		gameManager.setUiManager(this.uiManager);
	}
	
	@Override
	public void create () {
		this.uiManager.create();
		this.gameManager.create();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameManager.render();
		uiManager.render();
		showFPS();
	}

	public void showFPS(){
		int curFPS = Gdx.graphics.getFramesPerSecond();
		if (curFPS != oldFps){
			oldFps = curFPS;
			logger.log(Level.INFO, "FPS: " + curFPS);
		}
	}
}
