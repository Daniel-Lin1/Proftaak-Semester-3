package Game;

import Enums.GroundType;
import Enums.State;
import Player.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Color;

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

    public Map loadMap(String ImageLocation){
        //C:\Users\Marc-Antoine\Desktop\DistressAndConflict\core\assets
        //return CreateMapFromImage( new Pixmap(Gdx.files.internal("core/assets/imageToMapTestPng.png")));
        return CreateMapFromImage( new Pixmap(Gdx.files.internal(ImageLocation)));
    }

    private Map CreateMapFromImage(Pixmap pixmap){
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
                if(R == 34 && G == 177 && B == 76){
                    tile = new Tile(true, x, y, true, false, GroundType.Grass, null);
                    spawnpoints.add(new Point(x,y));
                }else if(R == 255 && G == 242 && B == 0){
                    tile = new Tile(false, x, y, true, false, GroundType.Grass, new Resource(Enums.Resource.Gold, 500));
                }else if(R == 195 && G == 195 && B == 195){
                    tile = new Tile(false, x, y, true, false, GroundType.Grass, new Resource(Enums.Resource.Stone, 600));
                }else if(R == 255 && G == 255 && B == 255){
                    tile = new Tile(true, x, y, true, false, GroundType.Grass, null);
                }else if(R == 153 && G == 217 && B == 234){
                    tile = new Tile(false, x, y, true, false, GroundType.Water, null);
                }else if(R == 181 && G == 230 && B == 29){
                    tile = new Tile(false, x, y, true, false, GroundType.Grass, new Resource(Enums.Resource.Wood, 100));
                }else if(R == 185 && G == 122 && B == 87){
                    tile = new Tile(false, x,y,false, false, GroundType.Stone, null);
                }else if(R == 255 && G == 174 && B == 201){
                    tile = new Tile(true, x, y, true, false, GroundType.Grass, new Resource(Enums.Resource.Food, 100));
                }else{
                    tile = new Tile(true, x, y, true, false, GroundType.Grass, null);
                    System.out.println("unassighned color in texture (load map from pixmap). replaced with an empty grass tile.");
                }
                System.out.println("color : " + R + " " + G + " " + B);
                tiles.add(tile);
            }
        }
        System.out.println(tiles.size());
        return map = new Map(10,5, tiles, spawnpoints );
    }
}
