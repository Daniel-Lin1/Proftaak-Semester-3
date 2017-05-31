import Building.UnitProducingBuilding;
import Enums.BuildingType;
import Enums.State;
import Enums.UnitType;
import Game.GameManager;
import Player.Player;
import Units.OffensiveUnit;
import Units.Unit;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Daniel on 29-5-2017.
 */
class UnitProducingBuildingTest {
    ArrayList<Player> players = new ArrayList<Player>();
    GameManager gm =  new GameManager(State.Started, 1, "", players, 0);
    Point p = new Point(16,16);
    UnitProducingBuilding upb = new UnitProducingBuilding(p, 16, 16, BuildingType.Archery,500, gm.getMap());
/*
    @Test
    void produceUnit() {
        Unit knight = upb.produceUnit(1, UnitType.Knight);
        Unit unit = new OffensiveUnit(1, p, UnitType.Knight, 100, 1, 1, 10, 1, false, gm.getMap());
        assertEquals(unit, knight);
    }
*/

}