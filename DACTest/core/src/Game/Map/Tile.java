package Game.Map;

import Enums.GroundType;
import Game.Resource;

import java.awt.*;

/**
 * Created by Daniel on 26-3-2017.
 */
public class Tile {
    private int tileNumber; //Should be unique across a map
    private boolean isWalkable;
    private boolean isBuildable;
    private boolean isOccupied;
    private GroundType groundType;
    private Resource resource;
    private Point upperLeft;
    private Point bottomRight;

    public Tile(int tileNumber, boolean isWalkable, Point upperLeft, Point bottomRight, boolean isBuildable, boolean isOccupied, GroundType groundType, Resource resource) {
        this.tileNumber = tileNumber;
        this.isWalkable = isWalkable;
        this.upperLeft = upperLeft;
        this.bottomRight = bottomRight;
        this.isBuildable = isBuildable;
        this.isOccupied = isOccupied;
        this.groundType = groundType;
        this.resource = resource;
    }

    public Tile(int tileNumber, Point upperLeft, Point bottomRight){
        this.tileNumber = tileNumber;
        this.upperLeft = upperLeft;
        this.bottomRight = bottomRight;
    }

    public Tile() {

    }

    @Override
    public String toString() {
        return "Tile{" +
                "tileNumber=" + tileNumber +
                ", isWalkable=" + isWalkable +
                ", isBuildable=" + isBuildable +
                ", isOccupied=" + isOccupied +
                ", groundType=" + groundType +
                ", resource=" + resource +
                ", upperLeft=" + upperLeft +
                ", bottomRight=" + bottomRight +
                '}';
    }
}
