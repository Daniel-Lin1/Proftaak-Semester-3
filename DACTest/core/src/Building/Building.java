package Building;

import Enums.BuildingType;
import Game.TextureVault;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

/**
 * Created by Daniel on 26-3-2017.
 */
public abstract class Building {
    private Point coordinate;
    private int sizeX;
    private int sizeY;
    private BuildingType buildingtype;
    private int health;
    private Texture sprite;
    private boolean selected;
    private Texture selectedSprite;

    public void searchSprite() {
        selectedSprite = TextureVault.selected;
        if (buildingtype == BuildingType.Towncenter)
        {
            sprite = TextureVault.townCenter;
        }
        else
        {
            sprite = null;
        }
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

    public Texture getSprite() {
        return sprite;
    }

    public void setSprite(Texture sprite) {
        this.sprite = sprite;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean getSelected() { return selected; }

    public Texture getSelectedSprite() {
        return selectedSprite;
    }

    public void setSelectedSprite(Texture selectedSprite) {
        this.selectedSprite = selectedSprite;
    }

    public String getUIInfo(){
        return "Building Type : " + buildingtype + "\n" +
                "Health : " + health;
    }
}
