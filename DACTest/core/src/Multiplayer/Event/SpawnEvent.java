package Multiplayer.Event;

import Enums.UnitType;

import java.io.Serializable;

/**
 * Created by Daniel on 17-5-2017.
 */
public class SpawnEvent implements Serializable {
    private final double xPos;
    private final double yPos;
    private final UnitType type;

    public SpawnEvent(UnitType type, double xPos, double yPos) {
        this.type = type;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public double getXpos() {
        return xPos;
    }

    public double getYpos() {
        return yPos;
    }

    public UnitType getType (){
        return type;
    }
}

