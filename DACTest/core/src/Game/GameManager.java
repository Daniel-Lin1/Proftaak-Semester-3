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
import com.mygdx.game.DistressAndConflict;
import com.mygdx.game.OrthographicCameraControlClass;
import Game.Map.Map;
import Game.Map.TiledMapStage;

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
    private ArrayList<Player> players;
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    private Stage stage;
    private OrthographicCamera orthographicCamera;
    private GameManagerClient gmc;

    private int OwnPlayerid;
    private int highestUnitID;
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

    public GameManager(State gamestate, int lobbyID, String password, ArrayList<Player> players, int ownPlayerid) {
        this.gamestate = gamestate;
        this.lobbyID = lobbyID;
        this.password = password;
        this.players = players;
        this.OwnPlayerid = ownPlayerid;
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
            renderUnits(player);
            renderBuildings(player);
        }
        batch.end();
    }

    public Player getOwnPlayer() {
        return getPlayers().get(OwnPlayerid);
    }

    private void renderUnits(Player player) {
        for (int i = 0; i < player.getUnits().size() && !player.getUnits().isEmpty(); i++) {
            player.getUnits().get(i).move();
            batch.draw(player.getUnits().get(i).getSprite(), player.getUnits().get(i).getPosition().x *16, player.getUnits().get(i).getPosition().y*16, 16, 16);
            if (player.getUnits().get(i).getSelected() == true) {
                batch.draw(player.getUnits().get(i).getSelectedSprite(), player.getUnits().get(i).getPosition().x*16, player.getUnits().get(i).getPosition().y*16, 16, 16);
            }
        }
    }

    private void renderBuildings(Player player) {
        for (int i = 0; i < player.getBuildings().size() && !player.getBuildings().isEmpty(); i++) {
            batch.draw(player.getBuildings().get(i).getSprite(), player.getBuildings().get(i).getCoordinate().x*16, player.getBuildings().get(i).getCoordinate().y*16, player.getBuildings().get(i).getSizeX()*16, player.getBuildings().get(i).getSizeY()*16);
            if (player.getBuildings().get(i).getSelected()) {
                batch.draw(player.getBuildings().get(i).getSelectedSprite(), player.getBuildings().get(i).getCoordinate().x*16, player.getBuildings().get(i).getCoordinate().y*16, player.getBuildings().get(i).getSizeX() *16, player.getBuildings().get(i).getSizeY()*16);
            }
        }
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
            Point tmp = map.getSpawnPoints().get(i);
            Point cord = map.getTileFromCord(tmp.x, tmp.y).getCoordinate();
            Building townCenter = new UnitProducingBuilding(cord, 4, 4, BuildingType.Towncenter, 1000, map);
            if(townCenter.checkBuildingPossible()){
                townCenter.setBuildingsTilesOccupide(townCenter);
                getPlayers().get(i).getBuildings().add(townCenter);
            }
        }
    }

    public int getHighestUnitIDPlus1(){
        this.highestUnitID += 1;
        return this.highestUnitID;
    }
}
