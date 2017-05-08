package Building;

import Building.Building;
import Enums.BuildingType;
import Enums.UnitType;
import Units.OffensiveUnit;

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

    public OffensiveUnit produceUnit(UnitType unittype){
        OffensiveUnit unit = null;
        Point point = new Point( (int)this.getCoordinate().getX(), (int)this.getCoordinate().getY() - 16);
        if (unittype == UnitType.Knight)
        {
            unit = new OffensiveUnit(point, UnitType.Knight, 100, 1, 1, 10, 1, false);
        }
        else if (unittype == UnitType.PikeMan)
        {
            unit = new OffensiveUnit(point, UnitType.PikeMan, 80, 1, 1, 8, 1, false);
        }
        else if (unittype == UnitType.Archer)
        {
            unit = new OffensiveUnit(point, UnitType.Archer, 60, 1, 1, 8, 4, false);
        }
        return unit;
    }
}
