package Game;

import Building.Building;
import Building.UnitProducingBuilding;
import Enums.BuildingType;
import Enums.State;
import Enums.UnitType;

import Player.Player;
import Units.OffensiveUnit;
import Units.Unit;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.DistressAndConflict;
import com.mygdx.game.OrthographicCameraControlClass;
import Game.Map.Map;
import Game.Map.Tile;
import Game.Map.TiledMapStage;

import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Daniel on 26-3-2017.
 */

public class GameManager {
    private State gamestate;
    private int lobbyID;
    private String password;
    private Map map;
    private ArrayList<Player> players;
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private Stage stage;
    private OrthographicCamera orthographicCamera;
    private DistressAndConflict dac;

    private ArrayList<Unit> units = new ArrayList<Unit>();
    private ArrayList<Building> buildings = new ArrayList<Building>();

    private OrthographicCameraControlClass gamecamera;
    //Stage en Skin voor UI inladen
    private SpriteBatch batch;

    public State getGamestate() {
        return this.gamestate;
    }

    public void setGamestate(State gamestate) {
        this.gamestate = gamestate;
    }

    public int getLobbyID() {
        return this.lobbyID;
    }

    public void setLobbyID(int lobbyID) {
        this.lobbyID = lobbyID;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map getMap() {
        return this.map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Stage getStage() {
        return this.stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setOrthographicCamera(OrthographicCamera orthographicCamera) {
        this.orthographicCamera = orthographicCamera;
    }

    public DistressAndConflict getDac() {
        return this.dac;
    }

    public void setDac(DistressAndConflict dac) {
        this.dac = dac;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public OrthographicCameraControlClass getGamecamera() {
        return this.gamecamera;
    }

    public void setGamecamera(OrthographicCameraControlClass gamecamera) {
        this.gamecamera = gamecamera;
    }

    public SpriteBatch getBatch() {
        return this.batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
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

    public ArrayList<Unit> getUnits() {
        return this.units;
    }

    public ArrayList<Building> getBuildings() {
        return this.buildings;
    }


    //ToDo : marc moet deze classe opschonen! wat een puinhoop kneus.

    public GameManager(DistressAndConflict dac, State gamestate, int lobbyID, String password, ArrayList<Player> participants) throws RemoteException {
        super();
        this.gamestate = gamestate;
        this.lobbyID = lobbyID;
        this.password = password;
        this.players = players;
        this.dac = dac;
    }

    public GameManager() throws RemoteException {
        super();

    }

    public void create () throws RemoteException {
        // set camera
        orthographicCamera = new OrthographicCamera();
        orthographicCamera.setToOrtho(false,1920,1080);
        orthographicCamera.update();
        tiledMap = new TmxMapLoader().load("assets/TestMap3.tmx");
        gamecamera = new OrthographicCameraControlClass(800, tiledMap);

        //generateTiles(tiledMap);

        //set tiles en stage goed enzo
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        stage = new TiledMapStage(tiledMap, dac, this);
        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();
        UnitProducingBuilding uPB = new UnitProducingBuilding(new Point(48, 320), 64, 64, BuildingType.Towncenter, 1000);
        buildings.add(uPB);
    }

    //TODO: Fix this method
    public ArrayList<Tile> generateTiles(TiledMap tiledMap){//Function to generate tiles based on the map.
        TiledMapTileLayer tiledMapTileLayer = (TiledMapTileLayer)tiledMap.getLayers().get(0);
        //Init vars
        int tilesHorizontal = tiledMapTileLayer.getWidth();
        int tilesVertical = tiledMapTileLayer.getHeight();
        //int totalTiles = tilesHorizontal * tilesVertical;

        //int tileHeight = (int)tiledMapTileLayer.getTileHeight();
        //int tileWidth = (int)tiledMapTileLayer.getTileWidth();
        ArrayList<Tile> tiles = new ArrayList<Tile>();

        //int tmpTilesHorizontal = 10;
        //int tmpTilesVertical = 10;
        //int tmpTotalTiles = tmpTilesHorizontal * tmpTilesVertical;
        int number = 1;


        for (int i = 1; i < tilesVertical+1; i++) {
            for (int j = tilesHorizontal; j >= 1; j--) {

                System.out.println("#" + number + " | X:" + i*16 + " | Y:" + j*16);
                number++;
            }
        }
        return tiles;
    }
    public void render(){
        orthographicCamera = gamecamera.render(orthographicCamera);
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
        batch.setProjectionMatrix(orthographicCamera.combined);
        batch.begin();
        for (int i = 0; i < units.size() && !units.isEmpty(); i++)
        {
            //TODO: Simplify this? fix dit.
            units.get(i).move();
            batch.draw(units.get(i).getSprite(), units.get(i).getPosition().x, units.get(i).getPosition().y, 16, 16);
            if (units.get(i).getSelected() == true)
            {
                batch.draw(units.get(i).getSelectedSprite(), units.get(i).getPosition().x, units.get(i).getPosition().y, 16, 16);
            }
        }

        for (int i = 0; i < buildings.size() && !buildings.isEmpty(); i++)
        {
            batch.draw(buildings.get(i).getSprite(), buildings.get(i).getCoordinate().x, buildings.get(i).getCoordinate().y, buildings.get(i).getSizeX(), buildings.get(i).getSizeY());
            if (buildings.get(i).getSelected())
            {
                batch.draw(buildings.get(i).getSelectedSprite(), buildings.get(i).getCoordinate().x, buildings.get(i).getCoordinate().y, buildings.get(i).getSizeX(), buildings.get(i).getSizeY());
            }
        }
       batch.end();
    }

    public void addUnit(int x, int y) {
		Vector3 coordinate = new Vector3(x, y, 0);
		orthographicCamera.project(coordinate);
		units.add(new OffensiveUnit(new Point(x, y), UnitType.Knight, 10, 10, 10, 10, 10, true));
	}


}
