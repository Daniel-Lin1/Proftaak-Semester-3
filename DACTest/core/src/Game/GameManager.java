package Game;

import Building.Building;
import Building.UnitProducingBuilding;
import Enums.*;
import Player.Player;
import Units.OffensiveUnit;
import Units.Unit;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.DistressAndConflict;
import com.mygdx.game.OrthographicCameraControlClass;
import com.mygdx.game.TiledMapStage;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Daniel on 26-3-2017.
 */
public class GameManager {
    private State gamestate;
    private int lobbyID;
    private String password;
    private Map map;
    private ArrayList<Player>participants;
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private Stage stage;
    private OrthographicCamera orthographicCamera;
    private DistressAndConflict dac;
    private ArrayList<Unit> units = new ArrayList<Unit>();
    private ArrayList<Building> buildings = new ArrayList<Building>();
    private OrthographicCameraControlClass gamecamera;
    //Stage en Skin voor UI inladen
    private Stage UIStage;
    private Skin UISkin;
    private SpriteBatch UIBatch;

    public GameManager(DistressAndConflict dac, State gamestate, int lobbyID, String password, ArrayList<Player> participants) {
        this.gamestate = gamestate;
        this.lobbyID = lobbyID;
        this.password = password;
        this.participants = participants;
        this.dac = dac;

//        // set camera
//        orthographicCamera = new OrthographicCamera();
//        orthographicCamera.setToOrtho(false,1920,1080);
//        orthographicCamera.update();
//        gamecamera = new OrthographicCameraControlClass(10, tiledMap);
//
//        //set tiles en stage goed enzo
//        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
//        stage = new TiledMapStage(tiledMap);
//
//        //Gdx.input.setInputProcessor(stage);
//
//        tiledMapRenderer.setView(
//                orthographicCamera.combined
//                ,0
//                ,0
//                ,tiledMap.getLayers().get(0).getProperties().get("width", Integer.class)//This works realy, really weird.
//                ,tiledMap.getLayers().get(0).getProperties().get("height", Integer.class)//This too
//        );

//        buildings.add(new UnitProducingBuilding(new Point(48, 32), 64, 64, BuildingType.Towncenter, 1000));
//        units.add(buildingTowncenter.produceUnit(UnitType.Knight));
    }

    public void create (){
        // set camera
        orthographicCamera = new OrthographicCamera();
        orthographicCamera.setToOrtho(false,1920,1080);
        orthographicCamera.update();
        tiledMap = new TmxMapLoader().load("assets/TestMap1.tmx");
        gamecamera = new OrthographicCameraControlClass(10, tiledMap);

        //set tiles en stage goed enzo
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        stage = new TiledMapStage(tiledMap, dac);
        Gdx.input.setInputProcessor(stage);

        //UI inladen van bestanden
        UISkin = new Skin(Gdx.files.internal("assets/UI/medieval.json"));
        UISkin.addRegions(new TextureAtlas(Gdx.files.internal("assets/UI/medieval.atlas")));
    }

    public void Render(){
        orthographicCamera = gamecamera.Render(orthographicCamera);
        orthographicCamera.update();
        tiledMapRenderer.render();
        stage.act();
        stage.draw();
        stage.getViewport().update((int)orthographicCamera.viewportWidth, (int)orthographicCamera.viewportHeight, false);
        stage.getViewport().setCamera(orthographicCamera);
        stage.getViewport().getCamera().update();



        tiledMapRenderer.setView(
                orthographicCamera.combined
                ,0
                ,0
                ,tiledMap.getLayers().get(0).getProperties().get("width", Integer.class)//This works realy, really weird.
                ,tiledMap.getLayers().get(0).getProperties().get("height", Integer.class)//This too
        );


        //forloop voor participants
        // render items / buildings from you and other players.


    }

    public void renderUI(){
        UIBatch = new SpriteBatch();
        UIBatch.begin();
        UIBatch.draw(UISkin.getSprite("buttonlong_brown"),350,0,1570, 200);
        UIBatch.draw(UISkin.getSprite("buttonSquare_brown"),0,0,350,250);
        UIBatch.end();
    }

    public TiledMap getTiledMap() {
        return tiledMap;
    }

    public void setTiledMap(TiledMap tiledMap) {
        this.tiledMap = tiledMap;
    }

    public TiledMapRenderer getTiledMapRenderer() {
        return tiledMapRenderer;
    }

    public void setTiledMapRenderer(TiledMapRenderer tiledMapRenderer) {
        this.tiledMapRenderer = tiledMapRenderer;
    }

    public OrthographicCamera getOrthographicCamera() {
        return orthographicCamera;
    }

    	public void addUnit(int x, int y) {
		Vector3 coordinate = new Vector3(x, y, 0);
		orthographicCamera.project(coordinate);
		units.add(new OffensiveUnit(new Point(x, y), UnitType.Knight, 10, 10, 10, 10, 10, true));
	}
}
