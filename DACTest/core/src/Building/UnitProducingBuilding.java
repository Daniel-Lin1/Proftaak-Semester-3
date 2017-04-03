package Building;

import Building.Building;
import Enums.BuildingType;
import Enums.UnitType;

import java.awt.*;

/**
 * Created by Daniel on 26-3-2017.
 */
public class UnitProducingBuilding extends Building {

    public UnitProducingBuilding(Point coordinate, int sizeX, int sizeY, BuildingType buildingType, int health)
    {
        this.setCoordinate(coordinate);
        this.setSizeX(sizeX);
        this.setSizeY(sizeY);
        this.setBuildingtype(buildingType);
        this.setHealth(health);
        searchSprite();
    }

    public void produceUnit(UnitType unittype){

    }
}
