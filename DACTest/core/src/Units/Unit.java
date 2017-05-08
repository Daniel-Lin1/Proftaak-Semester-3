package Units;

import Enums.UnitType;
import Interfaces.Movement;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

/**
 * Created by Daniel on 26-3-2017.
 */
public abstract class Unit implements Movement {

    private Point position;
    private Point destination;
    private UnitType unitType;
    private int health;
    private int speed;
    private int hitPerSecond;
    private int hitDamage;
    private int range;
    private boolean willReturnFire;
    private Texture sprite;
    private boolean selected;
    private Texture selectedSprite;

    public void searchSprite()
    {
        if (unitType == UnitType.Knight)
        {
            System.out.println(unitType.toString());
            sprite = new Texture(Gdx.files.internal("assets/Knight.png"));
            selectedSprite = new Texture(Gdx.files.internal("assets/Selected.png"));
        }
        else
        {
            sprite = null;
        }
    }

    @Override
    public void moveTo(Point point) {
        this.destination = point;
    }

//    public void move() {
//        if (destination != null && destination != position) {
//            if (destination.getX() == position.getX()) {
//                if (destination.getY() > position.getY()) {
//                    moveUP();
//                }
//                else if (destination.getY() < position.getY()) {
//                    moveDown();
//                }
//            }
//            else if (destination.getY() == position.getY()) {
//                if (destination.getX() > position.getX()) {
//                    moveRight();
//                }
//                else if (destination.getX() < position.getX()) {
//                    moveLeft();
//                }
//            }
//        }
//    }

//    public void moveUP() { position.y = position.y + 16; }
//    public void moveRight() { position.x = position.x + 16; }
//    public void moveDown() { position.y = position.y - 16; }
//    public void moveLeft() { position.x = position.x - 16; }

    @Override
    public void cancelMove() {
        destination = null;
    }

    public Texture getSprite() {
        return sprite;
    }

    public void setSprite(Texture sprite) {
        this.sprite = sprite;
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

    public Texture getSelectedSprite() {
        return selectedSprite;
    }

    public void setSelectedSprite(Texture selectedSprite) {
        this.selectedSprite = selectedSprite;
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
