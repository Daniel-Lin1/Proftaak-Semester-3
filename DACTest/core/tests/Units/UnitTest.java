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
        assert UnitType.Knight == unit.getUnitType();
        assert 100 == unit.getHealth();
        assert 1 == unit.getSpeed();
        assert 1 == unit.getHitDamage();
        assert 1 == unit.getRange();
        assert point1 == unit.getCoordinate();
    }

    @Test
    void testSearchSprite() throws Exception {
        unit.searchSprite();
        assert new Texture(Gdx.files.internal("assets/Knight.png")) == unit.getSprite();
    }

    @Test
    void testMoveTo() throws Exception{
        assert point1 == unit.getCoordinate();
        Point point2 = new Point(2,2);
        unit.moveTo(point2);
        assert point2 == unit.getCoordinate();
    }
}
