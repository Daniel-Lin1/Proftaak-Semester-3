package Units;
import Building.Building;
import Enums.BuildingType;
import Enums.UnitType;
import Game.Map.Map;
import Game.Map.PathFinding;
import Game.Map.Tile;
import Game.Resource;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Daniel on 26-3-2017.
 */
public class BuilderUnit extends Unit {
    private int amountResource;
    private int maxAmountResource;
    private Tile resourceTile;
    private int mineAmount = 10;
    private float deltaMineTime;

    public BuilderUnit(int unitid, Point position, UnitType unitType, int health, int speed, Map map)
    {
        this.setId(unitid);
        this.setPosition(position);
        this.setUnitType(unitType);
        this.setHealth(health);
        this.setSpeed(speed);
        this.setPath(new ArrayList<Point>());
    }

    public int getAmountResource() {
        return amountResource;
    }

    public void setAmountResource(int amountResource) {
        this.amountResource = amountResource;
    }

    public int getMaxAmountResource() {
        return maxAmountResource;
    }

    public void setMaxAmountResource(int maxAmountResource) {
        this.maxAmountResource = maxAmountResource;
    }

    public Tile getResourceTile() {
        return resourceTile;
    }

    public void setResourceTile(Tile resourceTile) {
        this.resourceTile = resourceTile;
    }

    public int getMineAmount() {
        return mineAmount;
    }

    public void setMineAmount(int mineAmount) {
        this.mineAmount = mineAmount;
    }

    public void setCollectResourceTile(Tile tile, Map map){
        resourceTile = tile;
        ArrayList<Point> path = PathFinding.findPath(map.getSizeX(), map.getSizeY(), this.getTile().getCoordinate().x, this.getTile().getCoordinate().y, tile.getCoordinate().x, tile.getCoordinate().y, (ArrayList<int[]>) map.getFlatMapForPathFinding(this.getPosition()));
        if(path.size() > 0){
            path.remove(path.size()-1);
            setPath(path);
        }
    }

    public boolean mineResource(Resource resource){
        if(resource.getAmount() - mineAmount > 0){
            resource.setAmount(resource.getAmount() - mineAmount);
            amountResource = amountResource + mineAmount;
        }else if(resource.getAmount() > 0){
            amountResource = amountResource + resource.getAmount();
        }
        return true;
    }

    public Building build(BuildingType buildingtype){
        return null;
    }
}
