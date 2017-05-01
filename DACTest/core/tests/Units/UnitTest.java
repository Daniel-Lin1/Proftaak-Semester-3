package Units;

import Enums.UnitType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.junit.jupiter.api.Test;

import java.awt.*;

/**
 * Created by Ixbitz on 19-4-2017 in DACTest
 */
public class UnitTest {
    Point point1 = new Point(1,1);
    private OffensiveUnit unit = new OffensiveUnit(point1, UnitType.Knight, 100, 1, 1, 10, 1, false);

    @Test
    void testConstructor() {
        boolean sameUnitType;
        boolean sameHealth;
        boolean sameSpeed;
        boolean sameHitDamage;
        boolean sameRange;
        boolean samePoint;

        sameUnitType = unit.getUnitType() == UnitType.Knight ? true : false;
        sameHealth = unit.getHealth() == 100 ? true : false;
        sameSpeed = unit.getSpeed() == 1 ? true : false;
        sameHitDamage = unit.getHitDamage() == 1 ? true : false;
        sameRange = unit.getRange() == 1 ? true : false;
        samePoint = unit.getCoordinate() == point1 ? true : false;

        assert sameUnitType;
        assert sameHealth;
        assert sameSpeed;
        assert sameHitDamage;
        assert sameRange;
        assert samePoint;
    }

    @Test
    void testSearchSprite() throws Exception {
        boolean sameSprite;

        unit.searchSprite();

        sameSprite = unit.getSprite() == new Texture(Gdx.files.internal("assets/Knight.png")) ? true : false;

        assert sameSprite;
    }

    @Test
    void testMoveTo() throws Exception{
        boolean samePoint1;
        boolean samePoint2;

        samePoint1 = unit.getCoordinate() == point1 ? true : false;
        assert samePoint1;

        Point point2 = new Point(2,2);
        unit.moveTo(point2);

        samePoint2 = unit.getCoordinates() == point2 ? true : false;
        assert samePoint2
    }
}
