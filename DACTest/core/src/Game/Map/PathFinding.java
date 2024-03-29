package game.map;

/**
 * Created by Marc-Antoine on 5/17/2017.
 */
/**
 * Created by Marc-Antoine on 5/17/2017.
 * this has been taken from the interweeeeebz :)
 * Site :
 * http://www.codebytes.in/2015/02/a-shortest-path-finding-algorithm.html
 */

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;

public abstract class PathFinding {
    public static final int DIAGONAL_COST = 14;
    public static final int V_H_COST = 10;

    private static final Logger LOGGER = Logger.getLogger(Map.class.getName());

    static class Cell{
        int heuristicCost = 0; //Heuristic cost
        int finalCost = 0; //G+H
        int i;
        int j;
        Cell parent;

        Cell(int i, int j){
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString(){
            return "["+this.i+", "+this.j+"]";
        }
    }

    //Blocked cells are just null Cell values in grid
    static Cell [][] grid = new Cell[5][5];

    static PriorityQueue<Cell> open;

    static boolean closed[][];
    static int startI;
    static int startJ;
    static int endI;
    static int endJ;

    public static void setBlocked(int i, int j){
        grid[i][j] = null;
    }

    public static void setStartCell(int i, int j){
        startI = i;
        startJ = j;
    }

    public static void setEndCell(int i, int j){
        endI = i;
        endJ = j;
    }

    static void checkAndUpdateCost(Cell current, Cell t, int cost){
        if(t == null || closed[t.i][t.j]){
            return;
        }
        int tFinalCost = t.heuristicCost+cost;

        boolean inOpen = open.contains(t);
        if(!inOpen || tFinalCost<t.finalCost){
            t.finalCost = tFinalCost;
            t.parent = current;
            if(!inOpen){
                open.add(t);
            }
        }
    }

    public static void aStar(){

        //add the start location to open list.
        open.add(grid[startI][startJ]);

        Cell current;

        while(true){
            current = open.poll();
            if(current==null){
                break;
            }
            closed[current.i][current.j]=true;

            if(current.equals(grid[endI][endJ])){
                return;
            }

            Cell t;
            if(current.i-1>=0){
                t = grid[current.i-1][current.j];
                checkAndUpdateCost(current, t, current.finalCost+V_H_COST);

                if(current.j-1>=0){
                    t = grid[current.i-1][current.j-1];
                    checkAndUpdateCost(current, t, current.finalCost+DIAGONAL_COST);
                }

                if(current.j+1<grid[0].length){
                    t = grid[current.i-1][current.j+1];
                    checkAndUpdateCost(current, t, current.finalCost+DIAGONAL_COST);
                }
            }

            if(current.j-1>=0){
                t = grid[current.i][current.j-1];
                checkAndUpdateCost(current, t, current.finalCost+V_H_COST);
            }

            if(current.j+1<grid[0].length){
                t = grid[current.i][current.j+1];
                checkAndUpdateCost(current, t, current.finalCost+V_H_COST);
            }

            if(current.i+1<grid.length){
                t = grid[current.i+1][current.j];
                checkAndUpdateCost(current, t, current.finalCost+V_H_COST);

                if(current.j-1>=0){
                    t = grid[current.i+1][current.j-1];
                    checkAndUpdateCost(current, t, current.finalCost+DIAGONAL_COST);
                }

                if(current.j+1<grid[0].length){
                    t = grid[current.i+1][current.j+1];
                    checkAndUpdateCost(current, t, current.finalCost+DIAGONAL_COST);
                }
            }
        }
    }

    /*
    Params :
    x, y = Board's dimensions
    si, sj = start location's x and y coordinates
    ei, ej = end location's x and y coordinates
    int[][] blocked = array containing inaccessible cell coordinates
    */
    public static List<Point> findPath(int x, int y, int si, int sj, int ei, int ej, List<int[]> blocked){
        //Reset
        grid = new Cell[x][y];
        closed = new boolean[x][y];
        open = new PriorityQueue<>((Object o1, Object o2) -> {
            Cell c1 = (Cell)o1;
            Cell c2 = (Cell)o2;

            return c1.finalCost<c2.finalCost?-1:
                    c1.finalCost>c2.finalCost?1:0;
        });
        //Set start position
        setStartCell(si, sj);  //Setting to 0,0 by default. Will be useful for the UI part

        //Set End Location
        setEndCell(ei, ej);

        for(int i=0;i<x;++i){
            for(int j=0;j<y;++j){
                grid[i][j] = new Cell(i, j);
                grid[i][j].heuristicCost = Math.abs(i-endI)+Math.abs(j-endJ);
            }
        }
        grid[si][sj].finalCost = 0;

           /*
             Set blocked cells. Simply set the cell values to null
             for blocked cells.
           */
        for(int i=0;i<blocked.size();++i){
            setBlocked(blocked.get(i)[0],blocked.get(i)[1]);
        }

        aStar();
        if(closed[endI][endJ]){
            ArrayList<Point> path = new ArrayList<>();
            //Trace back the path
            Cell current = grid[endI][endJ];
            while(current.parent!=null){
                current = current.parent;
                path.add(new Point(current.i,current.j));
            }
            return path;
        }else {
            LOGGER.info("No possible path!");
            return new ArrayList<>();
        }
    }
}