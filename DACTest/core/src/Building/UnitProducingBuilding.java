package Building;

import Enums.BuildingType;
import Enums.UnitType;
import Game.Map.Map;
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
        this.getBuildingType(buildingType);
        this.setHealth(health);
    }

    public OffensiveUnit produceUnit(int unitId, UnitType unittype){
        OffensiveUnit unit = null;
        Point point = new Point( (int)this.getCoordinate().getX(), (int)this.getCoordinate().getY() - 1);
        if (unittype == UnitType.Knight)
        {
            unit = new OffensiveUnit(unitId, point, UnitType.Knight, 100, 1, 1, 10, 1, false);
        }
        else if (unittype == UnitType.PikeMan)
        {
            unit = new OffensiveUnit(unitId, point, UnitType.PikeMan, 80, 1, 1, 8, 1, false);
        }
        else if (unittype == UnitType.Archer)
        {
            unit = new OffensiveUnit(unitId, point, UnitType.Archer, 60, 1, 1, 8, 4, false);
        }
        return unit;
    }
}
