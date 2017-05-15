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
	//private SpriteBatch batch;
	private Account user;
	private GameManager gameManager;
	private UIManager uiManager;
	private int OldFps = 0;
	private static final Logger LOG = Logger.getLogger(DistressAndConflict.class.getName());
	private GameManagerClient gmc;

	public DistressAndConflict(Account account) throws RemoteException {
		this.user = account;
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player(0, "player1"));
		players.add(new Player(1, "player2"));
		players.add(new Player(2, "player3"));

		this.gameManager = new GameManager(State.Finished, 1, "lel", players, 0);
		this.uiManager = new UIManager(this.gameManager);

		gameManager.setUiManager(this.uiManager);

		//TODO : Kan dit beter ?
		this.gmc = new GameManagerClient(gameManager);
		gmc.connectToPublisherActionPerformed();
	}

	public void host(){

	}
	public void join(){

	}
	
	@Override
	public void create () {
		try {
			this.uiManager.create();
			this.gameManager.create();
		} catch (RemoteException e) {
			LOG.log(Level.INFO, e.getMessage());
		}
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



	@Override
	public void dispose () {
		//batch.dispose();
	}

	public void showFPS(){
		int CurFPS = Gdx.graphics.getFramesPerSecond();
		if (CurFPS != OldFps){
			OldFps = CurFPS;
			System.out.println("FPS: " + CurFPS);
		}
	}
}
