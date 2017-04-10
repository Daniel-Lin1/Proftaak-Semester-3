package com.mygdx.game;

import Building.UnitProducingBuilding;
import Enums.BuildingType;
import Enums.UnitType;
import Game.GameManager;
import Player.Account;
import Units.OffensiveUnit;
import Units.Unit;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.awt.*;
import java.util.ArrayList;

public class DistressAndConflict extends ApplicationAdapter implements InputProcessor {
	private SpriteBatch batch;
	private Texture img;
	private Account user;
	private GameManager gameManager;
	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;
	private Stage stage;
	private OrthographicCamera orthographicCamera;
	private int OldFps = 0;

	private ArrayList<Unit> units = new ArrayList<Unit>();

	static final int SCROLL_SPEED = 10;
	static final int VIEWPORT_WIDTH = 1920;
	static final int VIEWPORT_HEIGHT = 1080;
	private OffensiveUnit unit;
	private UnitProducingBuilding buildingTowncenter;


	public OrthographicCamera getOrthographicCamera() {
		return orthographicCamera;
	}

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

		stage = new TiledMapStage(tiledMap, this);
		Gdx.input.setInputProcessor(stage);

		batch = new SpriteBatch();
		buildingTowncenter = new UnitProducingBuilding(new Point(48, 32), 64, 64, BuildingType.Towncenter, 1000);
		unit = buildingTowncenter.produceUnit(UnitType.Knight);
		units.add(unit);

	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		orthographicCamera.update();
		stage.act();
		stage.draw();
		stage.getViewport().update((int)orthographicCamera.viewportWidth, (int)orthographicCamera.viewportHeight, false);
		stage.getViewport().setCamera(orthographicCamera);
		stage.getViewport().getCamera().update();

		//Map scroll
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			if (orthographicCamera.position.x <= orthographicCamera.viewportWidth/2){
			}else{
				orthographicCamera.translate(-SCROLL_SPEED, 0);
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
			if (orthographicCamera.position.x >= tiledMap.getLayers().get(0).getProperties().get("width", Integer.class) - (orthographicCamera.viewportWidth/2)){
			}else {
				orthographicCamera.translate(SCROLL_SPEED, 0);
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)){
			if (orthographicCamera.position.y >= tiledMap.getLayers().get(0).getProperties().get("height", Integer.class) - (orthographicCamera.viewportHeight/2-200)){
			}else {
				orthographicCamera.translate(0, SCROLL_SPEED);
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
			if (orthographicCamera.position.y <= orthographicCamera.viewportHeight/2-200){

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
		//showFPS();

		batch.setProjectionMatrix(orthographicCamera.combined);
		batch.begin();
		for (int i = 0; i < units.size() && units.size() != 0; i++)
		{
			batch.draw(units.get(i).getSprite(), units.get(i).getCoordinate().x, units.get(i).getCoordinate().y, 16, 16);
		}
		batch.draw(buildingTowncenter.getSprite(), buildingTowncenter.getCoordinate().x, buildingTowncenter.getCoordinate().y, buildingTowncenter.getSizeX(), buildingTowncenter.getSizeY());
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
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

	public void addUnit(int x, int y) {
		Vector3 coordinate = new Vector3(x, y, 0);
		orthographicCamera.project(coordinate);
		units.add(new OffensiveUnit(new Point(x, y), UnitType.Knight, 10, 10, 10, 10, 10, true));
	}
	public Point isoto(){
		return null;
	}
}
