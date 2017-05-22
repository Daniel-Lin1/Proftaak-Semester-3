package Units;

import Enums.UnitType;
import Interfaces.Damage;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Daniel on 26-3-2017.
 */
public class OffensiveUnit extends Unit implements Damage{

    public OffensiveUnit(int unitid, Point position, UnitType unitType, int health, int speed, int hitPerSecond, int hitDamage, int range, boolean willReturnFire) {
        this.setId(unitid);
        this.setPosition(position);
        this.setUnitType(unitType);
        this.setHealth(health);
        this.setSpeed(speed);
        this.setHitPerSecond(hitPerSecond);
        this.setHitDamage(hitDamage);
        this.setRange(range);
        this.setWillReturnFire(willReturnFire);
        this.setPath(new ArrayList<Point>());
    }

    //zou moeten werken maar nog niet getest -Nick
    @Override
    public void attack(Unit target) {
        if ((this.getPosition().getX() + (this.getRange()) >= target.getPosition().getX()) &&
            (this.getPosition().getX() - (this.getRange()) <= target.getPosition().getX()) &&
            (this.getPosition().getY() + (this.getRange()) >= target.getPosition().getY()) &&
            (this.getPosition().getY() - (this.getRange()) <= target.getPosition().getY()))
        {
            this.battle(target);
        }
    }

    private void battle(Unit target) {
//        target.setHealth(target.getHealth() - this.getHitDamage());
//        if (target.isWillReturnFire()) {
//            this.setHealth(this.getHealth() - target.getHitDamage());
//        }
//        if (this.getHealth() <= 0) {
//
//        }
//        if (this.getHealth() > 0 && target.getHealth() > 0) {
//            this.battle(target);
//        }
    }
}
