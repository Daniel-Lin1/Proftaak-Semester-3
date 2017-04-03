package Game;

import Enums.GroundType;

/**
 * Created by Daniel on 26-3-2017.
 */
public class Tile {
    private boolean isWalkable;
    private int positionX;
    private int positionY;
    private boolean isBuildable;
    private boolean isOccupied;
    private GroundType groundType;
    private Resource resource;

    public Tile(boolean isWalkable, int positionX, int positionY, boolean isBuildable, boolean isOccupied, GroundType groundType, Resource resource) {
        this.isWalkable = isWalkable;
        this.positionX = positionX;
        this.positionY = positionY;
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
                "isWalkable=" + isWalkable +
                ", positionX=" + positionX +
                ", poistionY=" + positionY +
                ", isBuildable=" + isBuildable +
                ", isOccupied=" + isOccupied +
                ", groundType=" + groundType +
                ", resource=" + resource +
                '}';
    }
}
