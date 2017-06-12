package Building;

import Enums.BuildingType;
import Interfaces.Damage;
import Units.Unit;

import java.awt.*;

/**
 * Created by Daniel on 26-3-2017.
 */
public class DefenseBuilding extends Building implements Damage {

    private int hitPerSecond;
    private int Damage;
    private int Range;
    private boolean willReturnFire;


    public DefenseBuilding(Point coordinate, int sizeX, int sizeY, BuildingType buildingType, int health, int hitPerSecond, int damage, int range)
    {
        this.setCoordinate(coordinate);
        this.setSizeX(sizeX);
        this.setSizeY(sizeY);
        this.setBuildingType(buildingType);
        this.setHealth(health);
        this.setHitPerSecond(hitPerSecond);
        this.setDamage(damage);
        this.setRange(range);
    }

    @Override
    public void attack(Unit target) {

    }

    public int getHitPerSecond() {
        return hitPerSecond;
    }

    public void setHitPerSecond(int hitPerSecond) {
        this.hitPerSecond = hitPerSecond;
    }

    public int getDamage() {
        return Damage;
    }

    public void setDamage(int damage) {
        Damage = damage;
    }

    public int getRange() {
        return Range;
    }

    public void setRange(int range) {
        Range = range;
    }

    public boolean isWillReturnFire() {
        return willReturnFire;
    }

    public void setWillReturnFire(boolean willReturnFire) {
        this.willReturnFire = willReturnFire;
    }
}
