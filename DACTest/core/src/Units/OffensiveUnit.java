package Units;

import Enums.UnitType;
import Game.Map.Map;
import Interfaces.Damage;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Daniel on 26-3-2017.
 */
public class OffensiveUnit extends Unit implements Damage{

    public OffensiveUnit(Point position, UnitType unitType, int health, int speed, int hitPerSecond, int hitDamage, int range, boolean willReturnFire, Map map) {
        this.setPosition(position);
        this.setUnitType(unitType);
        this.setHealth(health);
        this.setSpeed(speed);
        this.setHitPerSecond(hitPerSecond);
        this.setHitDamage(hitDamage);
        this.setRange(range);
        this.setWillReturnFire(willReturnFire);
        this.setMap(map);
    }

    //zou moeten werken maar nog niet getest -Nick
    @Override
    public void attack(Unit target) {
        if ((this.getPosition().getX() + (this.getRange() * 16) >= target.getPosition().getX()) &&
            (this.getPosition().getX() - (this.getRange() * 16) <= target.getPosition().getX()) &&
            (this.getPosition().getY() + (this.getRange() * 16) >= target.getPosition().getY()) &&
            (this.getPosition().getY() - (this.getRange() * 16) <= target.getPosition().getY()))
        {
            target.setHealth(target.getHealth() - this.getHitDamage());
        }
    }
}
