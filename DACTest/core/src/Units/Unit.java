package Units;

import Enums.UnitType;
import Game.TextureVault;
import Interfaces.Movement;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;
import java.io.Serializable;

/**
 * Created by Daniel on 26-3-2017.
 */
public abstract class Unit implements Movement, Serializable {

    private Point position;
    private Point destination;
    private UnitType unitType;
    private int health;
    private int speed;
    private int hitPerSecond;
    private int hitDamage;
    private int range;
    private boolean willReturnFire;
    private boolean selected;

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
    public void moveTo(Point point) {
        this.destination = point;
    }

    public void move() {
        //todo inplement dijkstra :)
        if (destination != null && !destination.equals(position)) {
            Point up = new Point(position.x, position.y + 16);
            Point down = new Point(position.x, position.y - 16);
            Point left = new Point(position.x - 16, position.y);
            Point right = new Point(position.x + 16, position.y);

            Double shortestDistance = up.distance(destination.getX(), destination.getY());
            int nextStep = 1;

            if (shortestDistance > down.distance(destination.getX(), destination.getY())) {
                shortestDistance = down.distance(destination.getX(), destination.getY());
                nextStep = 2;
            }
            if (shortestDistance > left.distance(destination.getX(), destination.getY())) {
                shortestDistance = left.distance(destination.getX(), destination.getY());
                nextStep = 3;
            }
            if (shortestDistance > right.distance(destination.getX(), destination.getY())) {
                nextStep = 4;
            }

            switch (nextStep) {
                case 1:
                    moveUP();
                    break;
                case 2:
                    moveDown();
                    break;
                case 3:
                    moveLeft();
                    break;
                case 4:
                    moveRight();
                    break;
                default:
                    //niets. geen manier om er heen te gaan.
                    System.out.println("default case in move algeritme");
                    break;
            }
        }
    }

    public void moveUP() {
        position.y = position.y + 16;

        //oude tile = occupide = false;
        //nieuwe tile = occupide = true;
    }
    public void moveRight() {
        position.x = position.x + 16;
    }
    public void moveDown() {
        position.y = position.y - 16;
    }
    public void moveLeft() {
        position.x = position.x - 16;
    }

    @Override
    public void cancelMove() {
        destination = null;
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
