package Units;

import Enums.UnitType;
import Interfaces.Movement;

import java.awt.*;

/**
 * Created by Daniel on 26-3-2017.
 */
public abstract class Unit implements Movement {
    private Point coordinate;
    private UnitType unitType;
    private int health;
    private int speed;
    private int hitPerSecond;
    private int hitDamage;
    private int range;
    private boolean willReturnFire;

}
