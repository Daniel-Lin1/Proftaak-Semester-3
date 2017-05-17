package Multiplayer.Event;

import Enums.UnitType;
import Units.Unit;

import java.io.Serializable;

/**
 * Created by Daniel on 17-5-2017.
 */
public class SpawnEvent implements Serializable {
    Unit unit;

    public SpawnEvent (Unit unit) {
        this.unit = unit;
    }

    public Unit getUnit() {
        return unit;
    }
}


