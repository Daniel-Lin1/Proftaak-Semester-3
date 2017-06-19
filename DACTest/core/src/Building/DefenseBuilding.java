package building;

import enums.BuildingType;
import interfaces.Damage;
import units.Unit;

import java.awt.*;

/**
 * Created by Daniel on 26-3-2017.
 */
public class DefenseBuilding extends Building implements Damage {

    private int hitPerSecond;
    private int damage;
    private boolean willReturnFire;
    private int maxhealth;
    private double speed;
    private int hitDamage;
    private int range;
    private Unit inBattleWith;
    private float deltaMoveTime;


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

    public int getHitPerSecond() {
        return hitPerSecond;
    }

    public void setHitPerSecond(int hitPerSecond) {
        this.hitPerSecond = hitPerSecond;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public boolean isWillReturnFire() {
        return willReturnFire;
    }

    public void setWillReturnFire(boolean willReturnFire) {
        this.willReturnFire = willReturnFire;
    }

    @Override
    public void attack(Unit target) {
        if (((this.getPosition().getX() + (this.getRange())) >= target.getPosition().getX()) &&
                ((this.getPosition().getX() - (this.getRange())) <= target.getPosition().getX()) &&
                ((this.getPosition().getY() + (this.getRange())) >= target.getPosition().getY()) &&
                ((this.getPosition().getY() - (this.getRange())) <= target.getPosition().getY()))
        {
            target.setHealth(target.getHealth() - this.getHitDamage());
            //TODO Needs fixing
            /*if (target.isWillReturnFire()) {
                target.setInBattleWith(this);
            }*/
        }
    }
}
