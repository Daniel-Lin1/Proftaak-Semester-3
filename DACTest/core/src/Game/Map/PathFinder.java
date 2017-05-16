package Game.Map;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by Marc-Antoine on 5/16/2017.
 */
public class PathFinder {

    final static int ISOPEN = 1;
    final static int NONWALKABLE = 0;
    final static int TRIED = 2;
    final static int PATH = 3;

    private int[][] grid;
    private int height;
    private int width;

    private Point destination;

    private int[][] grid2;

    public PathFinder(Map map) {
        this.height = map.getSizeY();
        this.width = map.getSizeX();
        this.grid = new int[height][width];
        for (int x=0; x<map.getSizeX(); x++) {
            for (int y=0; y<map.getSizeY(); y++) {
                if (map.checkTileIfWalkable(new Point(x, y))){
                    grid[x][y] = ISOPEN;
                }else{
                    grid[x][y] = NONWALKABLE;
                }
            }
        }
        System.out.println(toString());
        this.grid2 = new int[height][width];
    }

    public boolean solve(Point position, Point destination) {
        //this.destination = destination;
        return traverse(position.x,position.y);
    }

    private boolean traverse(int i, int j) {
        if (!isValid(i,j)) {
            return false;
        }

        if ( isEnd(i, j) ) {
            grid2[i][j] = PATH;
            return true;
        } else {
            grid2[i][j] = TRIED;
        }

        // North
        if (traverse(i - 1, j)) {
            grid2[i-1][j] = PATH;
            return true;
        }
        // East
        if (traverse(i, j + 1)) {
            grid2[i][j + 1] = PATH;
            return true;
        }
        // South
        if (traverse(i + 1, j)) {
            grid2[i + 1][j] = PATH;
            return true;
        }
        // West
        if (traverse(i, j - 1)) {
            grid2[i][j - 1] = PATH;
            return true;
        }

        return false;
    }

    private boolean isEnd(int i, int j) {
        return i == destination.y - 1 && j == destination.x - 1;
    }

    private boolean isValid(int i, int j) {
        if (inRange(i, j) && isOpen(i, j) && !isTried(i, j)) {
            return true;
        }

        return false;
    }

    private boolean isOpen(int i, int j) {
        return grid[i][j] == 1;
    }

    private boolean isTried(int i, int j) {
        return grid2[i][j] == TRIED;
    }

    private boolean inRange(int i, int j) {
        return inHeight(i) && inWidth(j);
    }

    private boolean inHeight(int i) {
        return i >= 0 && i < height;
    }

    private boolean inWidth(int j) {
        return j >= 0 && j < width;
    }

    public String toString() {
        String s = "";
        for (int[] row : grid) {
            s += Arrays.toString(row) + "\n";
        }
        return s;
    }
}
