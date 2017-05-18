package Game.Map;

import Enums.GroundType;
import Enums.ResourceEnum;
import Game.Resource;
import Game.TextureVault;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.awt.*;

import static Enums.ResourceEnum.Gold;

/**
 * Created by Daniel on 26-3-2017.
 */
public class Tile {
    private int id;
    private boolean isWalkable;
    private boolean isBuildable;
    private boolean isOccupied;
    private GroundType groundType;
    private Resource resource;
    private Point coordinate;

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

    public Point getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    public Tile(int id, boolean isWalkable, boolean isBuildable, boolean isOccupied, GroundType groundType, Resource resource) {
        this.id = id;
        this.isWalkable = isWalkable;
        this.isBuildable = isBuildable;
        this.isOccupied = isOccupied;
        this.groundType = groundType;
        this.resource = resource;
        this.coordinate = new Point();
    }

    public Tile() {
    }

    public void render(Batch batch, int mapHight){
        if(resource != null){
            switch (this.resource.getResourceEnum()){
                //todo Fix dit MARC_ANTOINE PIERE MARIE LOUIS WOLTERS (-1* (((coordinate.y)+1) - 150) * 16) plez maak dat het niet hoeft omgerekent te worden.
                //also zorg dat de correcte textures ook nog worder getekent
                case Stone:
                    batch.draw(TextureVault.knight, coordinate.x *16, (-1* (((coordinate.y)+1) - mapHight) * 16), 16, 16);
                    break;
                case Gold:
                    batch.draw(TextureVault.knight, coordinate.x*16, (-1* (((coordinate.y)+1) - mapHight) * 16), 16, 16);
                    break;
                case Food:
                    batch.draw(TextureVault.pikeMan, coordinate.x*16, (-1* (((coordinate.y)+1) - mapHight) * 16), 16, 16);
                    break;
                case Wood:
                    batch.draw(TextureVault.townCenter, coordinate.x*16, (-1* (((coordinate.y)+1) - mapHight) * 16), 16, 16);
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
                ", isOccupied=" + isOccupied +
                ", groundType=" + groundType +
                ", resource=" + resource +
                '}';
    }
}
