package Game.Map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

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
    }

    public void CreateMapFromTiledMap(TiledMap tiledMap){
        TiledMapTileLayer tiledMapTileLayer = (TiledMapTileLayer)tiledMap.getLayers().get(0);
        this.sizeX = tiledMapTileLayer.getWidth();
        this.sizeY = tiledMapTileLayer.getHeight();

        ArrayList<Tile> tiles = new ArrayList<Tile>();

        int number = 1;

        for (int i = 1; i < this.sizeX+1; i++) {
            for (int j = this.sizeY; j >= 1; j--) {
                System.out.println("#" + number + " | X:" + i*16 + " | Y:" + j*16);
                number++;
            }
        }
        this.tiles = tiles;
    }

    public void render(){
    }
}
