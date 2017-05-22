package Units;
import Enums.BuildingType;
import Building.Building;
import Enums.ResourceEnum;
import Enums.UnitType;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Daniel on 26-3-2017.
 */
public class BuilderUnit extends Unit {
    private int amountResource;
    private ResourceEnum resourceEnum;

    public BuilderUnit(int unitid, Point position, UnitType unitType, int health, int speed)
    {
        this.setId(unitid);
        this.setPosition(position);
        this.setUnitType(unitType);
        this.setHealth(health);
        this.setSpeed(speed);
        this.setPath(new ArrayList<Point>());
    }

    public void getResource(){

    }

    public Building build(BuildingType buildingtype){
        return null;
    }
}
