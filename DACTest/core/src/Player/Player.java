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


    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public Player(int playerID, String nickName) {
        this.playerID = playerID;
        this.nickName = nickName;
        this.amountGold = 500;
        this.amountWood = 500;
        this.amountFood = 500;
        this.amountStone = 500;
        this.units = new ArrayList<Unit>();
        this.buildings = new ArrayList<Building>();
    }

    public void command(){

    }
}
