package Units;

import Enums.UnitType;
import Game.Map.Map;
import Game.Map.PathFinding;
import Game.Map.Tile;
import Game.TextureVault;
import Interfaces.Movement;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Daniel on 26-3-2017.
 */
public abstract class Unit extends Observable implements Movement, Serializable {

    private Point position;
    private Point destination;
    private ArrayList<Point> path;
    private UnitType unitType;
    private int id;
    private int maxhealth;
    private int health;
    private int speed;
    private int hitPerSecond;
    private int hitDamage;
    private int range;
    private Unit inBattleWith;
    private boolean willReturnFire;
    private boolean selected;
    private float deltaMoveTime;
    private float deltaBattleTime;
    private Tile tile;

    public Texture getSprite()
    {
        if (unitType == UnitType.Knight)
        {
            maxhealth = 100;
            return TextureVault.knight;
        }
        else if (unitType == UnitType.PikeMan)
        {
            maxhealth = 80;
            return TextureVault.pikeMan;
        }
        else if (unitType == UnitType.Archer) {
            maxhealth = 60;
            return TextureVault.archer;
        }
        else if (unitType == UnitType.Builder) {
            maxhealth = 50;
            return TextureVault.builder;
        }
        else
        {
            return null;
        }
    }

    public Texture getSelectedSprite()
    {
        return TextureVault.selected;
    }

    public void moveTo(Point destination, Map map) {
        this.setChanged();
        notifyObservers(this);
        this.destination = destination;
        ArrayList<int[]> grid = new ArrayList<int[]>();
        for (int x=0; x<map.getSizeX(); x++) {
            for (int y=0; y<map.getSizeY(); y++) {
                if (!map.checkTileIfWalkable(new Point(x, y)) && !(x == position.x && y == position.y)){
                    int[] point = new int[2];
                    point[0] = x;
                    point[1] = y;
                    grid.add(point);
                }
            }
        }
        //grid
        path = PathFinding.findPath(map.getSizeX(), map.getSizeY(), destination.x, destination.y, position.x, position.y, grid);
    }

    public void move(Map map) {
        if(path.size() > 0){
            Tile tileToMove = map.getTileFromCord(path.get(0));
            if(tileToMove.isWalkable() && !tileToMove.isOccupied()){
                map.getTileFromCord(position).setUnit(null);
                this.position = path.get(0);
                map.getTileFromCord(position).setUnit(this);
                this.tile = map.getTileFromCord(position);
                path.remove(0);
            }
        }
    }

    @Override
    public void cancelMove() {
        destination = null;
        this.path = new ArrayList<Point>();
    }

    public Point getDestination() {
        return destination;
    }

    public Point getPosition(Point position) {
        return position;
    }

    public Point getPosition() {
        return position;
    }

    public int getMaxhealth() {
        return maxhealth;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Unit getInBattleWith() {
        return inBattleWith;
    }

    public void setInBattleWith(Unit unit) {
        this.inBattleWith = unit;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHitPerSecond() {
        return hitPerSecond;
    }

    public void setHitPerSecond(int hitPerSecond) {
        this.hitPerSecond = hitPerSecond;
    }

    public int getHitDamage() {
        return hitDamage;
    }

    public void setHitDamage(int hitDamage) {
        this.hitDamage = hitDamage;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public boolean isWillReturnFire() {
        return willReturnFire;
    }

    public void setWillReturnFire(boolean willReturnFire) {
        this.willReturnFire = willReturnFire;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean getSelected() { return selected; }

    public void setDestination(Point destination) {
        this.destination = destination;
    }

    public boolean isSelected() {
        return selected;
    }

    public ArrayList<Point> getPath() {
        return path;
    }

    public void setPath(ArrayList<Point> path) {
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getDeltaMoveTime() {
        return deltaMoveTime;
    }

    public void setDeltaMoveTime(float deltaMoveTime) {
        this.deltaMoveTime = deltaMoveTime;
    }

    public float getDeltaBattleTime() {
        return deltaBattleTime;
    }

    public void setDeltaBattleTime(float BattleTime) {
        this.deltaBattleTime = BattleTime;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public String getUIInfo(){
        return  "UnitType : " + unitType.toString() + "\n" +
                "Health : " + health + "\n" +
                "Speed : " + speed + "\n" +
                "HitPerSecond : " + hitDamage + "\n" +
                "HitDamage : " + hitDamage + "\n"+
                "Range : " + range + "\n" +
                "Returns fire : " + willReturnFire;
    }
}
