package Units;

import Enums.UnitType;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;

/**
 * Created by Daniel on 26-3-2017.
 */
public class OffensiveUnit extends Unit{

    public OffensiveUnit(Point coordinate, UnitType unitType, int health, int speed, int hitPerSecond, int hitDamage, int range, boolean willReturnFire) {
        this.setCoordinate(coordinate);
        this.setUnitType(unitType);
        this.setHealth(health);
        this.setSpeed(speed);
        this.setHitPerSecond(hitPerSecond);
        this.setHitDamage(hitDamage);
        this.setRange(range);
        this.setWillReturnFire(willReturnFire);
        searchSprite();
    }
}
