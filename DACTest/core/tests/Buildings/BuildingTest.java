package Buildings;

import Building.UnitProducingBuilding;
import Enums.BuildingType;
import Enums.UnitType;
import Units.OffensiveUnit;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import org.junit.jupiter.api.Test;

import java.awt.*;

/**
 * Created by Ixbitz on 19-4-2017 in DACTest
 */
public class BuildingTest {
    Point point1 = new Point(1,1);
    UnitProducingBuilding building = new UnitProducingBuilding(point1, 2, 2, BuildingType.Towncenter, 1000);

    @Test
    void testConstructor() {
        boolean sameBuilding;
        boolean sameHealth;
        boolean sameSizeX;
        boolean sameSizeY;
        boolean samePoint;

        sameBuilding = BuildingType.Towncenter == building.getBuildingtype() ? true : false;
        sameBuilding = uilding.getHealth() == 1000 ? true : false;
        sameSizeX = building.getSizeX() == 2 ? true : false;
        sameSizeY = building.getSizeY() == 2 ? true : false;
        samePoint = building.getCoordinate() == point1 ? true : false;

        assert sameBuilding;
        assert sameHealth;
        assert sameSizeX;
        assert sameSizeY;
        assert samePoint;
    }

    @Test
    void testSearchSprite() {
        boolean sameTexture;

        building.searchSprite();

        sameTexture = building.getSprite() == Texture(Gdx.files.internal("assets/Towncenter.png")) ? true : false

        assert sameTexture
    }

    @Test
    void produceUnitTest(){
        boolean sameUnit;
        OffensiveUnit unit = new OffensiveUnit(new Point(1,1), UnitType.Knight, 100, 1, 1, 10, 1, false);

        sameUnit = building.produceUnit(UnitType.Knight) == unit ? true : false;
        assert sameUnit;
    }
}
