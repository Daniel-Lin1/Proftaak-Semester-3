package Units;

import Enums.UnitType;
import Game.Map.Map;
import Game.Map.PathFinding;
import Game.TextureVault;
import Interfaces.Movement;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Daniel on 26-3-2017.
 */
public abstract class Unit implements Movement, Serializable {

    private Point position;
    private Point destination;
    private ArrayList<Point> path;

    ArrayList<Point> dirtySolution = new ArrayList<>();

    private UnitType unitType;
    private int health;
    private int speed;
    private int hitPerSecond;
    private int hitDamage;
    private int range;
    private boolean willReturnFire;
    private boolean selected;
    private Map map;

    public Texture getSprite()
    {
        if (unitType == UnitType.Knight)
        {
            return TextureVault.knight;
        }
        else if (unitType == UnitType.PikeMan)
        {
            return TextureVault.pikeMan;
        }
        else if (unitType == UnitType.Archer) {
            return TextureVault.archer;
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

    @Override
    public void moveTo(Point destination) {
        this.destination = destination;
        int[][] grid;
        int count = 0;
        //grid = new int[][]{};
        for (int x=0; x<map.getSizeX(); x++) {
            for (int y=0; y<map.getSizeY(); y++) {
                if (!map.checkTileIfWalkable(new Point(x, y))){
                    count++;
                }
            }
        }
        grid = new int[count][2];
        count = 0;
        for (int x=0; x<map.getSizeX(); x++) {
            for (int y=0; y<map.getSizeY(); y++) {
                if (!map.checkTileIfWalkable(new Point(x, y))){
                    grid[count][0] = x;
                    grid[count][1] = y;
                    count++;
                }
            }
        }
        path = PathFinding.test(map.getSizeX(), map.getSizeY(), destination.x, destination.y, position.x, position.y, grid);
    }

    public void move() {
        if(path.size() > 0){
            this.position = path.get(0);
            path.remove(0);
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

    public void setPosition(Point position) {
        this.position = position;
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

    public void setMap(Map map) {
        this.map = map;
    }

    public ArrayList<Point> getPath() {
        return path;
    }

    public void setPath(ArrayList<Point> path) {
        this.path = path;
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
