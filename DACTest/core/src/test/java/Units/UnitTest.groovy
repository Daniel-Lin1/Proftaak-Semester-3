package Units

import Enums.UnitType
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import org.junit.jupiter.api.Test
import java.awt.Point

/**
 * Created by Nick on 19-Apr-17.
 */
class UnitTest {
    Point point1 = new Point(1,1);
    private OffensiveUnit unit = new OffensiveUnit(point1, UnitType.Knight, 100, 1, 1, 10, 1, false);

    @Test
    void testConstructor() {
        assert UnitType.Knight == unit.getUnitType();
        assert 100 == unit.getHealth();
        assert 1 == unit.getSpeed();
        assert 1 == unit.hitDamage();
        assert 1 == unit.range();
        assert point1 == unit.getCoordinate();
    }

    @Test
    void testSearchSprite() throws Exception {
        unit.searchSprite();
        assert Texture(Gdx.files.internal("assets/Knight.png")) == unit.getSprite();
    }

    @Test
    void testMoveTo() throws Exception{
        assert point1 == unit.getCoordinate();
        Point point2 = new Point(2,2);
        unit.moveTo(point2)
        assert point2 == unit.getCoordinate();
    }
}
