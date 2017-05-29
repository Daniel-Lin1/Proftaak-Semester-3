package Game.Map;

import Building.Building;
import Enums.GroundType;
import Game.Resource;
import Game.TextureVault;
import Units.Unit;
import com.badlogic.gdx.graphics.g2d.Batch;
import java.awt.*;
import java.io.Serializable;

/**
 * Created by Daniel on 26-3-2017.
 */
public class Tile implements Serializable {
    private int id;
    private boolean isWalkable;
    private boolean isBuildable;
    private GroundType groundType;
    private Resource resource;
    private Point coordinate;
    private Unit unit;
    private Building building;

    public int getId() {
        return id;
    }

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
        return !(building == null && unit == null);
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

    public Point getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Tile(int id, boolean isWalkable, boolean isBuildable, GroundType groundType, Resource resource) {
        this.id = id;
        this.isWalkable = isWalkable;
        this.isBuildable = isBuildable;
        this.groundType = groundType;
        this.resource = resource;
        this.coordinate = new Point();
        this.unit = null;
        this.building = null;
    }

    public void render(Batch batch, int mapHight){
        if(resource != null){
            switch (this.resource.getResourceEnum()){
                case Stone:
                    batch.draw(TextureVault.stone, coordinate.x *16, (-1* (((coordinate.y)+1) - mapHight) * 16), 16, 16);
                    break;
                case Gold:
                    batch.draw(TextureVault.gold, coordinate.x*16, (-1* (((coordinate.y)+1) - mapHight) * 16), 16, 16);
                    break;
                case Food:
                    batch.draw(TextureVault.berries, coordinate.x*16, (-1* (((coordinate.y)+1) - mapHight) * 16), 16, 16);
                    break;
                case Wood:
                    batch.draw(TextureVault.tree, coordinate.x*16, (-1* (((coordinate.y)+1) - mapHight) * 16), 16, 16);
                    break;
            }
        }
    }

    @Override
    public String toString() {
        return "Tile{" + id +
                ", coordinate=" + coordinate.toString() +
                ", isWalkable=" + isWalkable +
                ", isBuildable=" + isBuildable +
                ", isOccupied=" + isOccupied() +
                ", groundType=" + groundType +
                ", resource=" + resource +
                ", unit=" + unit +
                ", building=" + building +
                '}';
    }
}
