package building;

import enums.BuildingType;

import java.awt.*;

/**
 * Created by Daniel on 26-3-2017.
 */
public class NormalBuilding extends Building {
    public NormalBuilding(Point coordinate, int sizeX, int sizeY, BuildingType buildingType, int health)
    {
        this.setCoordinate(coordinate);
        this.setSizeX(sizeX);
        this.setSizeY(sizeY);
        this.setBuildingType(buildingType);
        this.setHealth(health);
    }
}
