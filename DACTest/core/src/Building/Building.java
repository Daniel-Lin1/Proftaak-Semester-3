package building;

import enums.BuildingType;
import game.map.Tile;
import game.TextureVault;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Logger;


/**
 * Created by Daniel on 26-3-2017.
 */
public abstract class Building extends Observable implements Serializable {
    private int buildingID;
    private Point position;
    private Point coordinate;
    private int sizeX;
    private int sizeY;
    private BuildingType buildingtype;
    private int health;
    private boolean selected;
    private int hitPerSecond;
    private int hitDamage;
    private int range;
    private ArrayList<ArrayList<Tile>> tiles;

    private static final Logger LOGGER = Logger.getLogger(Building.class.getName());

    public Texture getSprite()
    {
        switch (buildingtype) {
            case TOWN_CENTER: return TextureVault.townCenter;
            case BARRACKS: return null;
            case ARCHERY: return null;
            case FARM: return null;
            case STABLE: return null;
            case LUMBERJACK:  return null;
            case MINING_CAMP:  return null;
            case TOWER:  return null;
            default:
                LOGGER.fine("Missing texture for building");
                return null;
        }
    }

    public int getID()
    {
        return this.buildingID;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position){ this.position = position;}

    public Texture getSelectedSprite()
    {
        return TextureVault.selected;
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
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

    public BuildingType getBuildingType() {
        return buildingtype;
    }

    public void setBuildingType(BuildingType buildingtype) {
        this.buildingtype = buildingtype;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean getSelected() { return selected; }

    public boolean isSelected() {
        return selected;
    }

    public int getHitPerSecond() {
        return hitPerSecond;
    }

    public void setHitPerSecond(int hitPerSecond) {
        this.hitPerSecond = hitPerSecond;
    }

    public int getHitDamage() {
        return hitDamage;
    }

    public void setHitDamage(int hitDamage) {
        this.hitDamage = hitDamage;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public ArrayList<ArrayList<Tile>> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<ArrayList<Tile>> tiles) {
        this.tiles = tiles;
    }

    public String getUIInfo(){
        return "building Type : " + buildingtype + "\n" +
                "Health : " + health;
    }
}
