/*
 * Copyright (c) 2017.
 */

package Game;

import Enums.GroundType;
import Game.Map.Tile;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Ixbitz on 16-4-2017 in DACTest
 */
class GameManagerTest {
    @Test
    void generateTiles() {
        ArrayList<Tile> tiles = new ArrayList<Tile>();

        int tmpTilesHorizontal = 10;
        int tmpTilesVertical = 10;
        int tmpTotalTiles = tmpTilesHorizontal * tmpTilesVertical;
        int number = 1;


        for (int i = 0; i < tmpTilesVertical; i++) {
            for (int j = tmpTilesHorizontal; j >= 1; j--) {

                System.out.println("#" + number + " | X:" + i*16 + " | Y:" + j*16);
                Tile t = new Tile();
                t.setTileNumber(number);
                t.setUpperLeft(new Point(i*16, j*16));
                t.setBottomRight(new Point(i*16, j*16));
                t.setBuildable(true);
                t.setGroundType(GroundType.Grass);
                t.setWalkable(true);
                t.setOccupied(false);

                tiles.add(t);
                number++;
            }
        }

        System.out.println("TotalTiles: " + tmpTotalTiles);
        System.out.println("ArraySize: " + tiles.size());
        assert tmpTotalTiles == tiles.size();

    }

}