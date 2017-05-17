package Game.Map;

import Enums.GroundType;
import Enums.ResourceEnum;
import Game.Resource;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.graphics.Color;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Daniel on 26-3-2017.
 */
public class Map {
    private int sizeX;
    private int sizeY;
    private String mapName;
    private ArrayList<ArrayList<Tile>> tiles;
    private ArrayList<Point> spawnPoints;

    public ArrayList<ArrayList<Tile>> getTiles() {
        return tiles;
    }

    public String getMapName() {
        return mapName;
    }

    public ArrayList<Point> getSpawnPoints() {
        return spawnPoints;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public Map(TiledMap tiledMap, String mapName){
        this.mapName = mapName;
        this.tiles = new ArrayList<ArrayList<Tile>>();
        this.spawnPoints = new ArrayList<Point>();
        CreateMapFromTiledMap(tiledMap);
        this.sizeX = tiles.size();
        this.sizeY = tiles.get(0).size();
    }

    public void CreateMapFromTiledMap(TiledMap tiledMap){
        TiledMapTileLayer tiledMapTileLayer = (TiledMapTileLayer)tiledMap.getLayers().get(0);
        this.sizeX = tiledMapTileLayer.getWidth();
        this.sizeY = tiledMapTileLayer.getHeight();
        int tileID = 0;

        Pixmap mapenzoPNG = new Pixmap(Gdx.files.internal("assets/map1pixelversionbmp.bmp"));
        Color color = new Color(0,0,0,0);
        for (int x=0; x<mapenzoPNG.getWidth(); x++) {
            tiles.add(new ArrayList<Tile>());
            for (int y=0; y<mapenzoPNG.getHeight(); y++) {

                int val = mapenzoPNG.getPixel(x, y);
                Color.rgba8888ToColor(color, val);
                int R = (int)(color.r * 255f);
                int G = (int)(color.g * 255f);
                int B = (int)(color.b * 255f);
                Tile tile;

                if(R == 237 && G == 28 && B == 36){ //rood
                    tile = new Tile(tileID,true, true, false, GroundType.Grass, null);
                    spawnPoints.add(new Point(x,y));
                }else if(R == 255 && G == 242 && B == 0){ //geel
                    tile = new Tile(tileID,true, true, false, GroundType.Grass, new Resource(ResourceEnum.Gold, 500));
                }else if(R == 195 && G == 195 && B == 195){ //grijs
                    tile = new Tile(tileID, true, true, false, GroundType.Grass, new Resource(ResourceEnum.Stone, 600));
                }else if(R == 255 && G == 255 && B == 255){ //wit
                    tile = new Tile(tileID, true, true, false, GroundType.Grass, null);
                }else if(R == 153 && G == 217 && B == 234){ //blauw
                    tile = new Tile(tileID, false, false, false, GroundType.Water, null);
                }else if(R == 181 && G == 230 && B == 29){ //groen
                    tile = new Tile(tileID, true, true, false, GroundType.Grass, new Resource(ResourceEnum.Wood, 100));
                }else if(R == 255 && G == 174 && B == 201){ //roze
                    tile = new Tile(tileID,true, true, false, GroundType.Grass, new Resource(ResourceEnum.Food, 100) );
                }else{
                    tile = new Tile(tileID,true, true, false, GroundType.Grass, null);
                    System.out.println("unassighned color in texture (load map from pixmap). replaced with an empty grass tile.");
                    System.out.println("r :" + R +" g :" + G +" b :"+ B);
                }
                tile.setCoordinate(new Point(x, y));
                tiles.get(x).add(tile);
                tileID++;
            }
        }
    }
    public void render(Batch batch){
        //todo render resources
        for (ArrayList<Tile> tiles : tiles){
            for (Tile tile : tiles) {
                tile.render(batch);
            }
        }
    }

    public Tile getTileFromCord(int x, int y){
        if(x == 150){x = 149;} //op een hele cheez manier een glitch van die laatste rij (buiten de map clicken) gefixed. :)
        return tiles.get(x).get((-1* (((y)+1) - tiles.get(0).size())));
    }

    public boolean checkTileIfWalkable(Point point){
        if(getTileFromCord(point.x, point.y).isOccupied()){
            return false;
        }
        if(getTileFromCord(point.x, point.y).getResource() != null){
            return false;
        }
        if(!getTileFromCord(point.x, point.y).isWalkable()){
            return false;
        }
        return true;
    }
}
