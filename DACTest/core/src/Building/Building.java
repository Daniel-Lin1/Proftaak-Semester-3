package Building;

import Enums.BuildingType;
import Enums.UnitType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import javax.xml.soap.Text;
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

    public void searchSprite() {
        if (buildingtype == BuildingType.Stable)
        {
            sprite = new Texture(Gdx.files.internal("assets/Stable.png"));
        }
        else if (buildingtype == BuildingType.Towncenter)
        {
            sprite = new Texture(Gdx.files.internal("assets/Towncenter.png"));
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
}