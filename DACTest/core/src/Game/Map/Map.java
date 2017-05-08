package Game.Map;

import Enums.GroundType;
import Enums.ResourceEnum;
import Game.Resource;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
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
    private ArrayList<Tile> tiles;
    private ArrayList<Point> spawnPoints;

    public Map(int sizeX, int sizeY, String mapName, ArrayList<Tile> tiles, ArrayList<Point> spawnPoints ) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.mapName = mapName;
        this.tiles = tiles;
        this.spawnPoints = spawnPoints;
    }

    public Map(TiledMap tiledMap, String mapName){
        CreateMapFromTiledMap(tiledMap);
        this.mapName = mapName;
        spawnPoints = new ArrayList<Point>();
    }

    public void CreateMapFromTiledMap(TiledMap tiledMap){
        TiledMapTileLayer tiledMapTileLayer = (TiledMapTileLayer)tiledMap.getLayers().get(0);
        this.sizeX = tiledMapTileLayer.getWidth();
        this.sizeY = tiledMapTileLayer.getHeight();
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        int number = 1;

        Pixmap mapenzoPNG = new Pixmap(Gdx.files.internal("assets/map1pixelversion.png"));
        Color color = new Color(0,0,0,0);
        for (int x=0; x<mapenzoPNG.getWidth(); x++) {
            for (int y=0; y<mapenzoPNG.getHeight(); y++) {


                number++;
                int val = mapenzoPNG.getPixel(x, y);
                Color.rgba8888ToColor(color, val);
                int R = (int)(color.r * 255f);
                int G = (int)(color.g * 255f);
                int B = (int)(color.b * 255f);
                Tile tile = new Tile();
                tile.setCordinate(new Point(x*16, y*16));

                if(R == 34 && G == 177 && B == 76){
                    tile = new Tile(true, true, false, GroundType.Grass, null);
                    spawnPoints.add(new Point(x,y));
                }else if(R == 255 && G == 242 && B == 0){
                    tile = new Tile(true, true, false, GroundType.Grass, new Resource(ResourceEnum.Gold, 500));
                }else if(R == 195 && G == 195 && B == 195){
                    tile = new Tile(true, true, false, GroundType.Grass, new Resource(ResourceEnum.Stone, 600));
                }else if(R == 255 && G == 255 && B == 255){
                    tile = new Tile(true, true, false, GroundType.Grass, null);
                }else if(R == 153 && G == 217 && B == 234){
                    tile = new Tile(false, false, false, GroundType.Water, null);
                }else if(R == 181 && G == 230 && B == 29){
                    tile = new Tile(true, true, false, GroundType.Grass, new Resource(ResourceEnum.Wood, 100));
                }else if(R == 185 && G == 122 && B == 87){
                    System.out.println("deze is als het goed is niet meer bestaand.");
                    tile = new Tile(true, true, false, GroundType.Grass, null);
                }else if(R == 255 && G == 174 && B == 201){
                    tile = new Tile(true, true, false, GroundType.Grass, new Resource(ResourceEnum.Food, 100) );
                }else{
                    tile = new Tile(true, true, false, GroundType.Grass, null);
                    System.out.println("unassighned color in texture (load map from pixmap). replaced with an empty grass tile.");
                }
                tiles.add(tile);
            }
        }
    }

    public void render(){
    }
}
