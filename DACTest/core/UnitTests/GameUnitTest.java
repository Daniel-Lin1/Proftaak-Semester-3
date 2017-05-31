import Enums.State;
import Enums.UnitType;
import Game.GameManager;
import Game.Map.Map;
import Game.TextureVault;
import Game.UserInterface.UIManager;
import Player.*;
import Units.OffensiveUnit;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.game.DistressAndConflict;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Daniel on 29-5-2017.
 */
class GameUnitTest {
    DistressAndConflict dac = new DistressAndConflict(new Account());
    ArrayList<Player> players= new ArrayList();
    Point p = new Point(16,16);

    TiledMap tiledMap = new TmxMapLoader().load("assets/TestMap3.tmx");
    Map map = new Map(tiledMap, "tmpNaam");
    OffensiveUnit unit = new OffensiveUnit(1, p, UnitType.Knight, 100, 1, 1, 10, 1, false, map);

    @Test
    void getSprite() {
       /* dac.create();
        GameManager gm = dac.getGameManager();
        UIManager um = dac.getUiManager();
        gm.create();
        um.create();
        OffensiveUnit unit = new OffensiveUnit(1, p, UnitType.Knight, 100, 1, 1, 10, 1, false, gm.getMap());
        assertEquals(TextureVault.knight, unit.getSprite());*/
    }

    @Test
    void getSelectedSprite() {

    }

    @Test
    void moveTo() {

    }

    @Test
    void move() {

    }

    @Test
    void cancelMove() {

    }

    @Test
    void getDestination() {

    }

    @Test
    void getPosition() {
        assertEquals(16,unit.getPosition().getX());
        assertEquals(16, unit.getPosition().getY());

    }

    @Test
    void getPosition1() {

    }

    @Test
    void setPosition() {

    }

    @Test
    void getUnitType() {

    }

    @Test
    void setUnitType() {

    }

    @Test
    void getHealth() {

    }

    @Test
    void setHealth() {

    }

    @Test
    void getSpeed() {

    }

    @Test
    void setSpeed() {

    }

    @Test
    void getHitPerSecond() {

    }

    @Test
    void setHitPerSecond() {

    }

    @Test
    void getHitDamage() {

    }

    @Test
    void setHitDamage() {

    }

    @Test
    void getRange() {

    }

    @Test
    void setRange() {

    }

    @Test
    void isWillReturnFire() {

    }

    @Test
    void setWillReturnFire() {

    }

    @Test
    void setSelected() {

    }

    @Test
    void getSelected() {

    }

    @Test
    void setDestination() {

    }

    @Test
    void isSelected() {

    }

    @Test
    void getPath() {

    }

    @Test
    void setPath() {

    }

    @Test
    void getId() {

    }

    @Test
    void setId() {

    }

    @Test
    void getDeltaMoveTime() {

    }

    @Test
    void setDeltaMoveTime() {

    }

    @Test
    void getTile() {

    }

    @Test
    void setTile() {

    }

    @Test
    void getUIInfo() {

    }

}