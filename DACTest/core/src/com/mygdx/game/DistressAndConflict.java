package com.mygdx.game;

import Enums.UnitType;
import Player.Account;
import Game.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import Units.OffensiveUnit;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;

import java.awt.*;
import java.util.ArrayList;

public class DistressAndConflict extends ApplicationAdapter implements InputProcessor {
	private SpriteBatch batch;
	private Texture img;
	private Account user;
	private GameManager gameManager;
	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;
	OrthographicCamera orthographicCamera;
	private int OldFps = 0;

	static final int SCROLL_SPEED = 10;
	static final int VIEWPORT_WIDTH = 950;
	static final int VIEWPORT_HEIGHT = 950;
	private OffensiveUnit unit;

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
		//http://www.gamefromscratch.com/post/2014/04/16/LibGDX-Tutorial-11-Tiled-Maps-Part-1-Simple-Orthogonal-Maps.aspx
		orthographicCamera = new OrthographicCamera();
		orthographicCamera.setToOrtho(false,VIEWPORT_WIDTH,VIEWPORT_HEIGHT);
		orthographicCamera.update();
		tiledMap = new TmxMapLoader().load("assets/TestMap1.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		Gdx.input.setInputProcessor(this);

		/*batch = new SpriteBatch();
		img = new Texture(Gdx.files.internal("assets/imageToMapTestPng.png"));
		img = new Texture(Gdx.files.internal("assets/map1.png"));
		Map tmp = gameManager.loadMap("assets/imageToMapTestPng.png");*/

		unit = new OffensiveUnit(new Point(400,400), UnitType.Knight, 1000, 1, 1, 100, 1, false);
	}

	@Override
	public void render () {
		/*Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 20, 20, 100, 100);
		batch.end();*/
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		orthographicCamera.update();

		System.out.println("CamX: " + orthographicCamera.position.x);
		System.out.printf("CamY: " + orthographicCamera.position.y);
		//Map scroll
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			if (orthographicCamera.position.x <= orthographicCamera.viewportWidth/2){
				System.out.println("TestXLEFT");
			}else{
				orthographicCamera.translate(-SCROLL_SPEED, 0);
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			if (orthographicCamera.position.x >= tiledMap.getLayers().get(0).getProperties().get("width", Integer.class) - (orthographicCamera.viewportWidth/2)){
				System.out.printf("TextXRight");
			}else {
				orthographicCamera.translate(SCROLL_SPEED, 0);
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)){
			if (orthographicCamera.position.y >= tiledMap.getLayers().get(0).getProperties().get("height", Integer.class) - (orthographicCamera.viewportHeight/2)){
				System.out.printf("TextYUp");
			}else {
				orthographicCamera.translate(0, SCROLL_SPEED);
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			if (orthographicCamera.position.y <= orthographicCamera.viewportHeight/2){

			}else{
				orthographicCamera.translate(0, -SCROLL_SPEED);
			}
		}
		tiledMapRenderer.setView(
				orthographicCamera.combined
				,0
				,0
				,tiledMap.getLayers().get(0).getProperties().get("width", Integer.class)//This works realy, really weird.
				,tiledMap.getLayers().get(0).getProperties().get("height", Integer.class)//This too
		);
		tiledMapRenderer.render();
		//stage.draw();
		showFPS();

		//batch.draw(unit.getSprite(), unit.getCoordinate().x, unit.getCoordinate().y, 16, 16);
		//batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.ESCAPE){
			Gdx.graphics.setWindowedMode(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
		}
		return false;
	}

	public void showFPS(){//Created to optimize FPS, YaY 60 FPS!
		//https://libgdx.badlogicgames.com/nightlies/docs/api/com/badlogic/gdx/Graphics.html#getFramesPerSecond--
		int CurFPS = Gdx.graphics.getFramesPerSecond();
		if (CurFPS != OldFps){
			OldFps = CurFPS;
			System.out.println("FPS: " + CurFPS);
		}
	}
	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
