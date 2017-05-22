package Building;

import Enums.BuildingType;
import Game.Map.Map;
import Game.Map.Tile;
import Game.TextureVault;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;


/**
 * Created by Daniel on 26-3-2017.
 */
public abstract class Building extends Observable implements Serializable {
    private int buildingID;
    private Point coordinate;
    private int sizeX;
    private int sizeY;
    private BuildingType buildingtype;
    private int health;
    private boolean selected;
    private ArrayList<ArrayList<Tile>> tiles;

    public Texture getSprite()
    {
        if (buildingtype == buildingtype.Towncenter)
        {
            return TextureVault.townCenter;
        }
        else
        {
            return null;
        }
    }

    public int getID()
    {
        return this.buildingID;
    }

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

    public void getBuildingType(BuildingType buildingtype) {
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

    public BuildingType getBuildingtype() {
        return buildingtype;
    }

    public void setBuildingtype(BuildingType buildingtype) {
        this.buildingtype = buildingtype;
    }

    public ArrayList<ArrayList<Tile>> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<ArrayList<Tile>> tiles) {
        this.tiles = tiles;
    }

    public String getUIInfo(){
        return "Building Type : " + buildingtype + "\n" +
                "Health : " + health;
    }
}
