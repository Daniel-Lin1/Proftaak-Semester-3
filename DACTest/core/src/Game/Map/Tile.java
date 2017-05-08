package Game.Map;

import Enums.GroundType;
import Game.Resource;

import java.awt.*;

/**
 * Created by Daniel on 26-3-2017.
 */
public class Tile {
    private boolean isWalkable;
    private boolean isBuildable;
    private boolean isOccupied;
    private GroundType groundType;
    private Resource resource;
    private Point cordinate;

    public boolean isWalkable() {
        return this.isWalkable;
    }

    public void setWalkable(boolean walkable) {
        this.isWalkable = walkable;
    }

    public boolean isBuildable() {
        return this.isBuildable;
    }

    public void setBuildable(boolean buildable) {
        this.isBuildable = buildable;
    }

    public boolean isOccupied() {
        return this.isOccupied;
    }

    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }

    public GroundType getGroundType() {
        return this.groundType;
    }

    public void setGroundType(GroundType groundType) {
        this.groundType = groundType;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Point getCordinate() {
        return cordinate;
    }

    public void setCordinate(Point cordinate) {
        this.cordinate = cordinate;
    }

    public Tile(boolean isWalkable, boolean isBuildable, boolean isOccupied, GroundType groundType, Resource resource) {
        this.isWalkable = isWalkable;
        this.isBuildable = isBuildable;
        this.isOccupied = isOccupied;
        this.groundType = groundType;
        this.resource = resource;
    }

    public Tile() {
    }

    @Override
    public String toString() {
        return "Tile{" +
                "cordinate=" + getCordinate().toString() +
                ", isWalkable=" + isWalkable +
                ", isBuildable=" + isBuildable +
                ", isOccupied=" + isOccupied +
                ", groundType=" + groundType +
                ", resource=" + resource +
                '}';
    }
}
