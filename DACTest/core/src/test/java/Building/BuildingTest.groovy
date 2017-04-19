package Building

import Enums.BuildingType
import Enums.UnitType
import Units.OffensiveUnit
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import org.junit.jupiter.api.Test
import java.awt.Point

/**
 * Created by Nick on 19-Apr-17.
 */
class BuildingTest {
    Point point1 = new Point(1,1);
    UnitProducingBuilding building = new UnitProducingBuilding(point1, 2, 2, BuildingType.Towncenter, 1000);

    @Test
    void testConstructor() {
        assert BuildingType.Towncenter == building.buildingtype;
        assert 1000 == building.getHealth();
        assert 2 == building.getSizeX();
        assert 2 == building.getSizeY();
        assert point1 == building.getCoordinate();
    }

    @Test
    void testSearchSprite() {
        building.searchSprite();
        assert Texture(Gdx.files.internal("assets/Towncenter.png")) == building.getSprite();
    }

    @Test
    void produceUnitTest(){
        assert OffensiveUnit(new Point(1,1), UnitType.Knight, 100, 1, 1, 10, 1, false) == building.produceUnit(UnitType.Knight);
    }
}
