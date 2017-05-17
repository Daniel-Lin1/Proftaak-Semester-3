package Interfaces;

import Game.Map.Map;

import java.awt.*;

/**
 * Created by Daniel on 26-3-2017.
 */
public interface Movement {
    void moveTo(Point point, Map map);
    void cancelMove();
}
