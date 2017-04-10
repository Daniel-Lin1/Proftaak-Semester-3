package Game.Map;

import Game.Tile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Daniel on 26-3-2017.
 */
public class Map {
    private int sizeX;
    private int sizeY;
    private String mapName;
    private ArrayList<Tile>Tiles;
    private ArrayList<Point> spawnPoints;

    public Map(int sizeX, int sizeY, String mapName, ArrayList<Tile> tiles, ArrayList<Point> spawnPoints ) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.mapName = mapName;
        this.Tiles = tiles;
        this.spawnPoints = spawnPoints;
    }

    public void render(){
    }
}
