package Building;

import Enums.BuildingType;
import Game.Map.Map;
import Game.Map.Tile;
import Game.TextureVault;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by Daniel on 26-3-2017.
 */
public abstract class Building implements Serializable {
    private Point coordinate;
    private int sizeX;
    private int sizeY;
    private BuildingType buildingtype;
    private int health;
    private boolean selected;
    private Map map;

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

    public BuildingType getBuildingtype() {
        return buildingtype;
    }

    public void setBuildingtype(BuildingType buildingtype) {
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

    public void setMap(Map map) {
        this.map = map;
    }

    public boolean isSelected() {
        return selected;
    }

    public Map getMap() {
        return map;
    }

    public boolean checkBuildingPossible(){
        for(int i=0; i<sizeX; i++){
            for(int j=0; j<sizeY; j++){
                Tile tile = map.getTileFromCord(coordinate.x + i, coordinate.y + j);
                if(!tile.isBuildable() || tile.isOccupied() || tile.getResource() != null){
                    return false;
                }
            }
        }
        return true;
    }

    public void setBuildingsTilesOccupide(Building building){
        for(int i=0; i<sizeX; i++){
            for(int j=0; j<sizeY; j++){
                Tile tile = map.getTileFromCord(coordinate.x + i, coordinate.y + j);
                tile.setOccupied(true);
            }
        }
    }

    public String getUIInfo(){
        return "Building Type : " + buildingtype + "\n" +
                "Health : " + health;
    }
}
