package Game;

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
    private ArrayList<Tile>Tiles;
    private ArrayList<Point> spawnPoints;

    public Map(int sizeX, int sizeY, ArrayList<Tile> tiles, ArrayList<Point> spawnPoints ) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.Tiles = tiles;
        this.spawnPoints = spawnPoints;
    }

    public void render(){
    }
}
