package units;

import enums.UnitType;
import game.map.Map;
import game.map.PathFinding;
import game.map.Tile;
import game.TextureVault;
import interfaces.Movement;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;
import java.io.Serializable;
import java.util.*;
import java.util.List;

/**
 * Created by Daniel on 26-3-2017.
 */
public abstract class Unit implements Movement, Serializable {

    private Point position;
    private Point destination;
    private ArrayList<Point> path;
    private UnitType unitType;
    private int id;
    private int maxhealth;
    private int health;
    private double speed;
    private int hitPerSecond;
    private int hitDamage;
    private int range;
    private Unit inBattleWith;
    private boolean willReturnFire;
    private boolean selected;
    private float deltaMoveTime;
    private Tile tile;

    public Texture getSprite()
    {
        if (unitType == UnitType.KNIGHT)
        {
            maxhealth = 100;
            return TextureVault.knight;
        }
        else if (unitType == UnitType.PIKE_MAN)
        {
            maxhealth = 80;
            return TextureVault.pikeMan;
        }
        else if (unitType == UnitType.ARCHER) {
            maxhealth = 60;
            return TextureVault.archer;
        }
        else if (unitType == UnitType.BUILDER) {
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
        this.destination = destination;


        ArrayList<int[]> grid = (ArrayList<int[]>) map.getFlatMapForPathFinding(position);
        path = (ArrayList<Point>) PathFinding.findPath(map.getSizeX(), map.getSizeY(), destination.x, destination.y, position.x, position.y, grid);
    }

    public void move(Map map) {
        if(path != null && !path.isEmpty()){
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
        this.path = new ArrayList<>();
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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
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

    public java.util.List<Point> getPath() {
        return path;
    }

    public void setPath(List<Point> path) {
        this.path = (ArrayList<Point>) path;
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
