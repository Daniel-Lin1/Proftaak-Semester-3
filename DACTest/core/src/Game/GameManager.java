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
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
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

    /*public Map loadMap(String ImageLocation){
        return CreateMapFromImage( new Pixmap(Gdx.files.internal(ImageLocation)));
    }

    private Map CreateMapFromImage(Pixmap pixmap){
        //currently not in use!
        //https://github.com/mattdesl/lwjgl-basics/wiki/LibGDX-Textures
        ArrayList<Tile> tiles = new ArrayList<Tile>(pixmap.getHeight() * pixmap.getWidth());
        ArrayList<Point> spawnpoints = new ArrayList<Point>(8);
        Color color = new Color();
        for (int x=0; x<pixmap.getWidth(); x++) {
            for (int y=0; y<pixmap.getHeight(); y++) {
                int val = pixmap.getPixel(x, y);
                Color.rgba8888ToColor(color, val);
                int R = (int)(color.r * 255f);
                int G = (int)(color.g * 255f);
                int B = (int)(color.b * 255f);
                //int A = (int)(color.a * 255f);

                Tile tile; //= new Tile();
                if(R == 237 && G == 28 && B == 36){
                    tile = new Tile(true, x, y, true, false, GroundType.Grass, null);
                    spawnpoints.add(new Point(x,y));
                }else if(R == 255 && G == 242 && B == 0){
                    tile = new Tile(false, x, y, true, false, GroundType.Grass, new Resource(ResourceEnum.Gold, 500));
                }else if(R == 195 && G == 195 && B == 195){
                    tile = new Tile(false, x, y, true, false, GroundType.Grass, new Resource(ResourceEnum.Stone, 600));
                }else if(R == 255 && G == 255 && B == 255){
                    tile = new Tile(true, x, y, true, false, GroundType.Grass, null);
                }else if(R == 153 && G == 217 && B == 234){
                    tile = new Tile(false, x, y, true, false, GroundType.Water, null);
                }else if(R == 181 && G == 230 && B == 29){
                    tile = new Tile(false, x, y, true, false, GroundType.Grass, new Resource(ResourceEnum.Wood, 100));
                }else if(R == 185 && G == 122 && B == 87){
                    tile = new Tile(false, x,y,false, false, GroundType.Stone, null);
                }else if(R == 255 && G == 174 && B == 201){
                    tile = new Tile(true, x, y, true, false, GroundType.Grass, new Resource(ResourceEnum.Food, 100));
                }else{
                    tile = new Tile(true, x, y, true, false, GroundType.Grass, null);
                    System.out.println("unassighned color in texture (load map from pixmap). replaced with an empty grass tile.");
                    System.out.println("color : " + R + " " + G + " " + B);
                }

                tiles.add(tile);
            }
        }
        System.out.println(tiles.size());
        return map = new Map(10,5, tiles, spawnpoints );
    }*/
}
