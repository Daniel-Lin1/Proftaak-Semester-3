package Units;
import Building.Building;
import Enums.BuildingType;
import Enums.ResourceEnum;
import Enums.UnitType;
import Game.Map.Map;
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
    private ResourceEnum resourceEnum;
    private int mineAmount = 10;

    public BuilderUnit(int unitid, Point position, UnitType unitType, int health, int speed, Map map)
    {
        this.setId(unitid);
        this.setPosition(position);
        this.setUnitType(unitType);
        this.setHealth(health);
        this.setSpeed(speed);
        this.setPath(new ArrayList<Point>());
    }

    public void CollectResource(Tile tile){
        // check 4 positions around tile.
        resourceEnum = tile.getResource().getResourceEnum();
        Point coords = tile.getCoordinate();
        Point tileAbove = new Point(coords.x, coords.y++);
        Point tileUnder = new Point(coords.x, coords.y--);
        Point tileLeft = new Point(coords.x--, coords.y);
        Point tileRight = new Point(coords.x++, coords.y);

        ArrayList<ArrayList<Point>> paths = new ArrayList<ArrayList<Point>>();
        paths.add(new ArrayList<Point>());
        //paths.add(findPath(tileLeft, getMap()));
        //paths.add(findPath(tileRight, getMap()));
        //paths.add(findPath(tileAbove,getMap()));
        //paths.add(findPath(tileUnder, getMap()));

        for (int i = 1; i < paths.size() -1; i++) {
            if(paths.get(i).size() > paths.get(0).size() && paths.get(i).size() != 0){
                paths.set(0, paths.get(i));
            }
        }
        //move to closest
        if(paths.get(0).size() > 0){
            this.setDestination(paths.get(0).get((paths.get(0).size()-1)));
            setPath(paths.get(0));
            this.setChanged();
            notifyObservers(this);
        }
        //if there
        Point pos = getTile().getCoordinate();
        if(pos == tileLeft || pos == tileRight || pos == tileAbove || pos == tileUnder){
            if(tile.getResource() != null && !(amountResource > maxAmountResource)){
                mineResource(tile.getResource());
            }

            //keep mining till full or resource empty
            //if full go back resource diliver point,
            //if resource empty, collectResource(closest tile from here with same resource)




        }


        //get amount from resource to inventory of builder

        //move to closest resource deliver point

        //move to resource
              //if resource is null, find closest same resource.

        //repeat
    }

    private void mineResource(Resource resource){
        if(resource.getAmount() - mineAmount > 0){
            resource.setAmount(resource.getAmount() - mineAmount);
            amountResource = amountResource + mineAmount;
        }else if(resource.getAmount() > 0){
            amountResource = amountResource + resource.getAmount();
        }
    }

    public Building build(BuildingType buildingtype){
        return null;
    }
}
