package Game.Map;

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

    public Map(){

    }

    public void render(){
    }
}
