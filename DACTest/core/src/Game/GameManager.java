package Game;

import Building.Building;
import Building.UnitProducingBuilding;
import Enums.BuildingType;
import Enums.State;

import Game.UserInterface.UIManager;
import Multiplayer.GameManagerClient;
import Player.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.OrthographicCameraControlClass;
import Game.Map.Map;
import Game.Map.TiledMapStage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Daniel on 26-3-2017.
 */

public class GameManager implements Observer{
    private State gamestate;
    private int lobbyID;
    private String password;
    private Map map;
    private ArrayList<Player> players;
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private Stage stage;
    private OrthographicCamera orthographicCamera;
    private GameManagerClient gmc;
    private float oldTime;

    private int OwnPlayerid;
    private int highestUnitID;
    private int highestBuildingID;
    private UIManager uiManager;

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

    public UIManager getUiManager() {
        return uiManager;
    }

    public void setUiManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }

    public GameManagerClient getGmc() {return gmc;}

    public GameManager(State gameState, int lobbyID, String password, ArrayList<Player> players, int ownPlayerId) {
        this.gamestate = gameState;
        this.lobbyID = lobbyID;
        this.password = password;
        this.players = players;
        this.OwnPlayerid = ownPlayerId;
        this.gmc = new GameManagerClient(this);
    }

    public void create() {
        // set camera
        orthographicCamera = new OrthographicCamera();
        orthographicCamera.setToOrtho(false, 1920, 1080);
        orthographicCamera.update();
        tiledMap = new TmxMapLoader().load("assets/TestMap3.tmx");
        map = new Map(tiledMap, "tmpNaam");
        gamecamera = new OrthographicCameraControlClass(800, tiledMap);

        //sets map
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        stage = new TiledMapStage(tiledMap, this);
        Gdx.input.setInputProcessor(stage);

        batch = new SpriteBatch();

        //todo review of dit de correcte plek ervoor is.
        createTownCenters();
    }

    public void render() {
        renderCameraAndTiledMap();
        batch.begin();
        map.render(batch);
        for (Player player : players) {
            player.render(batch);
        }
        batch.end();
    }

    public Player getOwnPlayer() {
        return getPlayers().get(OwnPlayerid);
    }

    private void renderCameraAndTiledMap() {
        orthographicCamera = gamecamera.render(orthographicCamera);
        orthographicCamera.update();
        tiledMapRenderer.render();
        stage.act();
        stage.draw();
        stage.getViewport().update((int) orthographicCamera.viewportWidth, (int) orthographicCamera.viewportHeight, false);
        stage.getViewport().setCamera(orthographicCamera);
        stage.getViewport().getCamera().update();
        batch.setProjectionMatrix(orthographicCamera.combined);
        tiledMapRenderer.setView(
                orthographicCamera.combined
                , 0
                , 0
                , tiledMap.getLayers().get(0).getProperties().get("width", Integer.class)//This works realy, really weird.
                , tiledMap.getLayers().get(0).getProperties().get("height", Integer.class)//This too
        );
    }

    private void createTownCenters(){
        for(int i=0; i<getPlayers().size(); i++){
            Point spawnPoint = map.getSpawnPoints().get(i);
            Point cord = map.getTileFromCord(spawnPoint).getCoordinate();
            Building townCenter = new UnitProducingBuilding(cord, 4, 4, BuildingType.TownCenter, 1000, map);
			townCenter.addObserver(this);
            if(map.checkBuildingPossible(townCenter)){
                map.setBuildingsTiles(townCenter);
                getPlayers().get(i).getBuildings().add(townCenter);
            }
        }
    }

    public int getHighestUnitId(){
        highestUnitID = 0;
        for(Player player: getPlayers())
        {
            highestUnitID = highestUnitID + player.getUnits().size();
        }
        return this.highestUnitID;
    }

    public int getHighestBuildingId(){
        highestBuildingID = 0;
        for(Player player: getPlayers())
        {
            highestBuildingID = highestBuildingID + player.getBuildings().size();
        }
        return this.highestBuildingID;
    }

    @Override
    public void update(Observable observable, Object o) {
        //todo hier update code voor multiplayer
    }
}
