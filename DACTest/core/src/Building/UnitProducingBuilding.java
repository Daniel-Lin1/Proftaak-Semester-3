package building;

import enums.BuildingType;
import enums.UnitType;
import game.map.Map;
import units.OffensiveUnit;
import units.Unit;

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
        this.setBuildingType(buildingType);
        this.setHealth(health);
    }

    public Unit produceUnit(int unitId, UnitType unittype, Map map){
        Point point = new Point(getCoordinate().x,getCoordinate().y - 1);
        Unit unit;
        switch (unittype) {
            case KNIGHT:
                unit = new OffensiveUnit(unitId, point, UnitType.KNIGHT, 100, 1.5, 1, 10, 1, true, map);
                break;
            case PIKE_MAN:
                unit = new OffensiveUnit(unitId, point, UnitType.PIKE_MAN, 80, 1.3, 1, 8, 1, true, map);
                break;
            case ARCHER:
                unit = new OffensiveUnit(unitId, point, UnitType.ARCHER, 60, 1, 1, 8, 4, true, map);
                break;
            case BUILDER:
                unit = new OffensiveUnit(unitId, point, UnitType.BUILDER, 50, 1.2, 0, 0, 0, false, map);
                break;
            default:
                throw new IllegalArgumentException("default switchcase reached in unitProducing building : produceUnit");
        }
        map.getTileFromCord(point).setUnit(unit);
        return unit;
    }
}
