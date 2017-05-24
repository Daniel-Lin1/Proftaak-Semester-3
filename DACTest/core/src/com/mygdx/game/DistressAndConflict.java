package com.mygdx.game;

import Enums.State;
import Game.GameManager;
import Game.UserInterface.UIManager;
import Multiplayer.GameManagerClient;
import Player.Account;
import Player.Player;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import sun.rmi.runtime.Log;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DistressAndConflict extends ApplicationAdapter {
	private Account user;
	private GameManager gameManager;
	private UIManager uiManager;
	private int OldFps = 0;

	public DistressAndConflict(Account account)  {
		this.user = account;
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player(0, "player1"));
		players.add(new Player(1, "player2"));
		players.add(new Player(2, "player3"));
		players.add(new Player(3, "player4"));

		this.gameManager = new GameManager(State.Finished, 1, "lel", players, 1);
		this.uiManager = new UIManager(this.gameManager);
		gameManager.setUiManager(this.uiManager);
	}

	public void host(){

	}
	public void join(){

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
		int CurFPS = Gdx.graphics.getFramesPerSecond();
		if (CurFPS != OldFps){
			OldFps = CurFPS;
			System.out.println("FPS: " + CurFPS);
		}
	}
}
