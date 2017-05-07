package Player;

import Building.Building;
import Units.Unit;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Daniel on 26-3-2017.
 */
public class Player implements Serializable {
    private int playerID;
    private String nickName;
    private int amountGold;
    private int amountWood;
    private int amountFood;
    private int amountStone;
    private ArrayList<Unit> units;
    private ArrayList<Building>buildings;

    public void command(){

    }
}
