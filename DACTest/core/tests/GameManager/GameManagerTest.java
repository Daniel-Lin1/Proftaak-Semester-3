package GameManager;

import Building.Building;
import Enums.State;
import Game.GameManager;
import Game.Map.Map;
import Player.Player;
import Units.Unit;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.mygdx.game.DistressAndConflict;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Ixbitz on 19-4-2017 in DACTest
 */
public class GameManagerTest {

    @Test
    public void testTiledMap() {
        GameManager gameManager = new GameManager();
        TiledMap tiledMap = new TiledMap();
        gameManager.setTiledMap(tiledMap);

        TiledMap gmTiledMap = gameManager.getTiledMap();

        gmTiledMap == tiledMap ? true : false
        assert tiledMapSet : "Expected: " + tiledMap + ", Actual: " + gmTiledMap;
    }

    @Test
    public void testOrthographicCamera(){
        GameManager gameManager = new GameManager();
        OrthographicCamera orthographicCamera = new OrthographicCamera();
        gameManager.setOrthographicCamera(orthographicCamera);

        assert gameManager.getOrthographicCamera() == orthographicCamera : "Expected: " + orthographicCamera + ", Actual: " + gameManager.getOrthographicCamera();
    }

    @Test
    public void testGameState(){
        GameManager gameManager = new GameManager();
        gameManager.setGamestate(State.Started);

        assert gameManager.getGamestate() == State.Started : "Expected: " + State.Started + ", Actual: " + gameManager.getGamestate();
    }

    @Test
    public void testPassword(){
        GameManager gameManager = new GameManager();
        gameManager.setPassword("test");
        assert gameManager.getPassword() == "test" : "Expected: " + "Test" + ", Actual: " + gameManager.getPassword();
    }

    @Test
    public void testDistressAndConflict(){
        GameManager gameManager = new GameManager();
        DistressAndConflict distressAndConflict = new DistressAndConflict();
        gameManager.setDac(distressAndConflict);
        assert gameManager.getDac() == distressAndConflict : "Expected: " + distressAndConflict + ", Actual: " + gameManager.getDac();
    }

    @Test
    public void testLobbyID(){
        GameManager gameManager = new GameManager();
        gameManager.setLobbyID(1);
        assert gameManager.getLobbyID() == 1 : "Expected: 1, Actual: " + gameManager.getLobbyID();
    }

    @Test
    public void testPlayers(){
        GameManager gameManager = new GameManager();
        Player player1 = new Player();
        Player player2 = new Player();
        ArrayList<Player> playerArrayList = new ArrayList<Player>();
        playerArrayList.add(player1);
        playerArrayList.add(player2);
        gameManager.setPlayers(playerArrayList);

        assert gameManager.getPlayers() == playerArrayList : "Expected: " + playerArrayList + ", Actual: " + gameManager.getPlayers();
    }

    @Test
    public void testMap(){
        GameManager gameManager = new GameManager();
        Map map = new Map();
        gameManager.setMap(map);

        assert gameManager.getMap() == map : "Expected: " + map + ", Actual: " + gameManager.getMap();
    }

    @Test
    public void testStage(){
        //TODO: Needs improvement
        /*GameManager gameManager = new GameManager();
        Stage stage = new Stage();
        gameManager.setStage(stage);

        assert gameManager.getStage() == stage : "Expected: " + stage + ", Actual: " + gameManager.getStage();*/
        assertTrue(false);
    }

    @Test
    public void testUnits(){
        GameManager gameManager = new GameManager();
        ArrayList<Unit> unitArrayList = new ArrayList<>();
        gameManager.setUnits(unitArrayList);

        assert gameManager.getUnits() == unitArrayList : "Expected: " + unitArrayList + ", Actual: " + gameManager.getUnits();
    }

    @Test
    public void testBuildings(){
        GameManager gameManager = new GameManager();
        ArrayList<Building> buildingArrayList = new ArrayList<Building>();
        gameManager.setBuildings(buildingArrayList);

        assert gameManager.getBuildings() == buildingArrayList : "Expected: " + buildingArrayList + ", Actual: " + gameManager.getBuildings();
    }
}
