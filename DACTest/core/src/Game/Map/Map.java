package game.map;

        import building.Building;
        import enums.GroundType;
        import enums.ResourceEnum;
        import game.Resource;
        import units.Unit;
        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.graphics.Pixmap;
        import com.badlogic.gdx.graphics.g2d.Batch;
        import com.badlogic.gdx.maps.tiled.TiledMap;
        import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
        import com.badlogic.gdx.graphics.Color;

        import java.awt.*;
        import java.util.*;
        import java.util.List;
        import java.util.logging.Level;
        import java.util.logging.Logger;

/**
 * Created by Daniel on 26-3-2017.
 */
public class Map {
    private int sizeX;
    private int sizeY;
    private String mapName;
    private List<ArrayList<Tile>> tiles;
    private List<Point> spawnPoints;

    Logger logger = Logger.getLogger(Map.class.getName());

    public Map(TiledMap tiledMap, String mapName){
        this.mapName = mapName;
        this.tiles = new ArrayList<>();
        this.spawnPoints = new ArrayList<>();
        createMapFromTiledMap(tiledMap);
        this.sizeX = tiles.size();
        this.sizeY = tiles.get(0).size();
    }

    public List<ArrayList<Tile>> getTiles() {
        return tiles;
    }

    public String getMapName() {
        return mapName;
    }

    public List<Point> getSpawnPoints() {
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

    public void setHostiles(Unit unit) {
        Point tileAbove = new Point(unit.getPosition().x, unit.getPosition().y + 1);
        Point tileUnder = new Point(unit.getPosition().x, unit.getPosition().y - 1);
        Point tileLeft = new Point(unit.getPosition().x - 1, unit.getPosition().y);
        Point tileRight = new Point(unit.getPosition().x + 1, unit.getPosition().y);

        if (this.getTileFromCord(tileAbove).getUnit() != null) {
            unit.setInBattleWith(this.getTileFromCord(tileAbove).getUnit());
        }
        if (this.getTileFromCord(tileUnder).getUnit() != null) {
            unit.setInBattleWith(this.getTileFromCord(tileUnder).getUnit());
        }
        if (this.getTileFromCord(tileLeft).getUnit() != null) {
            unit.setInBattleWith(this.getTileFromCord(tileLeft).getUnit());
        }
        if (this.getTileFromCord(tileRight).getUnit() != null) {
            unit.setInBattleWith(this.getTileFromCord(tileRight).getUnit());
        }
    }

    public void createMapFromTiledMap(TiledMap tiledMap){
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
                int r = (int)(color.r * 255f);
                int g = (int)(color.g * 255f);
                int b = (int)(color.b * 255f);
                Tile tile;

                if(r == 237 && g == 28 && b == 36){ //rood
                    tile = new Tile(tileID,true, true, GroundType.GRASS, null);
                    spawnPoints.add(new Point(x,y));
                }else if(r == 255 && g == 242 && b == 0){ //geel
                    tile = new Tile(tileID,true, true, GroundType.GRASS, new Resource(ResourceEnum.GOLD, 500));
                }else if(r == 195 && g == 195 && b == 195){ //grijs
                    tile = new Tile(tileID, true, true, GroundType.GRASS, new Resource(ResourceEnum.STONE, 600));
                }else if(r == 255 && g == 255 && b == 255){ //wit
                    tile = new Tile(tileID, true, true, GroundType.GRASS, null);
                }else if(r == 153 && g == 217 && b == 234){ //blauw
                    tile = new Tile(tileID, false, false, GroundType.WATER, null);
                }else if(r == 181 && g == 230 && b == 29){ //groen
                    tile = new Tile(tileID, true, true, GroundType.GRASS, new Resource(ResourceEnum.WOOD, 100));
                }else if(r == 255 && g == 174 && b == 201){ //roze
                    tile = new Tile(tileID,true, true, GroundType.GRASS, new Resource(ResourceEnum.FOOD, 100) );
                }else{
                    tile = new Tile(tileID,true, true, GroundType.GRASS, null);
                    logger.log(Level.INFO, "unassighned color in texture (load map from pixmap). replaced with an empty grass tile.");
                }
                tile.setCoordinate(new Point(x, y));
                tiles.get(x).add(tile);
                tileID++;
            }
        }
    }
    public void render(Batch batch){
        for (ArrayList<Tile> moreTiles : tiles){
            for (Tile tile : moreTiles) {
                tile.render(batch, sizeY);
            }
        }
    }

    public Tile getTileFromCord(Point coords){
        if(coords.x == 150){
            coords.x = 149;
        } //op een hele cheez manier een glitch van die laatste rij (buiten de map clicken) gefixed. :)
        return tiles.get(coords.x).get((-1* (((coords.y)+1) - tiles.get(0).size())));
    }

    public boolean checkTileIfWalkable(Point point){
        Tile tile = getTileFromCord(point);
        if(tile.isOccupied() || tile.getResource() != null || !tile.isWalkable()){
            return false;
        }
        return true;
    }

    public boolean checkBuildingPossible(Building building){
        for(int i=0; i<building.getSizeX(); i++){
            for(int j=0; j<building.getSizeY(); j++){
                Tile tile = getTileFromCord(new Point(building.getCoordinate().x + i, building.getCoordinate().y + j));
                if(!tile.isBuildable() || tile.isOccupied() || tile.getResource() != null){
                    return false;
                }
            }
        }
        return true;
    }

    public java.util.List<int[]> getFlatMapForPathFinding(Point position){
        ArrayList<int[]> grid = new ArrayList<>();
        for (int x=0; x<getSizeX(); x++) {
            for (int y=0; y<getSizeY(); y++) {
                if (!checkTileIfWalkable(new Point(x, y)) && !(x == position.x && y == position.y)){
                    int[] point = new int[2];
                    point[0] = x;
                    point[1] = y;
                    grid.add(point);
                }
            }
        }
        return grid;
    }

    public void setBuildingsTiles(Building building){
        ArrayList<ArrayList<Tile>> tilesList = new ArrayList<>();
        for(int i=0; i<building.getSizeX(); i++){
            tilesList.add(i, new ArrayList<Tile>());
            for(int j=0; j<building.getSizeY(); j++){
                Point point = new Point(building.getCoordinate().x, building.getCoordinate().y);
                getTileFromCord(new Point(point.x + i, point.y + j)).setBuilding(building);
                tilesList.get(i).add(getTileFromCord(point));
            }
        }
        building.setTiles(tilesList);
    }
}
