package building;

import Enums.BuildingType;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Daniel on 29-5-2017.
 */
class BuildingTest {
    Point coordinate = new Point(16,16);
    DefenseBuilding building = new DefenseBuilding(coordinate,32,32, BuildingType.Archery,500, 1,50,500);

    @Test
    void getSprite() {

    }

    @Test
    void getID() {

    }

    @Test
    void getSelectedSprite() {

    }

    @Test
    void getCoordinate() {
        assertEquals(coordinate, building.getCoordinate());
    }

    @Test
    void setCoordinate() {
        Point p = new Point(32,32);
        building.setCoordinate(p);
        assertEquals(p, building.getCoordinate());
    }

    @Test
    void getSizeX() {
        assertEquals(32,building.getSizeX());
    }

    @Test
    void setSizeX() {
        building.setSizeX(64);
        assertEquals(64, building.getSizeX());
    }

    @Test
    void getSizeY() {
        assertEquals(32,building.getSizeY());
    }

    @Test
    void setSizeY() {
        building.setSizeY(64);
        assertEquals(64,building.getSizeY());
    }

    @Test
    void getBuildingType() {
        assertEquals(BuildingType.Archery, building.getBuildingType());
    }

    @Test
    void getHealth() {
        assertEquals(500, building.getHealth());
    }

    @Test
    void setHealth() {
        building.setHealth(200);
        assertEquals(200, building.getHealth());
    }

    @Test
    void setSelected() {
        building.setSelected(true);
        assertEquals(true,building.getSelected());
    }

    @Test
    void getSelected() {
        assertEquals(false, building.getSelected());
    }
}