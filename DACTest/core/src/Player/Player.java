package Player;

import Building.Building;
import Enums.UnitType;
import Units.OffensiveUnit;
import Units.Unit;

import java.awt.*;
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

    public void addUnit(Unit unit) { this.units.add(unit);}

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public int getAmountGold() {
        return amountGold;
    }

    public void setAmountGold(int amountGold) {
        this.amountGold = amountGold;
    }

    public int getAmountWood() {
        return amountWood;
    }

    public void setAmountWood(int amountWood) {
        this.amountWood = amountWood;
    }

    public int getAmountFood() {
        return amountFood;
    }

    public void setAmountFood(int amountFood) {
        this.amountFood = amountFood;
    }

    public int getAmountStone() {
        return amountStone;
    }

    public void setAmountStone(int amountStone) {
        this.amountStone = amountStone;
    }

    public Player(int playerID, String nickName) {
        this.playerID = playerID;
        this.nickName = nickName;
        this.amountGold = 500;
        this.amountWood = 500;
        this.amountFood = 500;
        this.amountStone = 500;
        this.units =  new ArrayList<Unit>();
        this.buildings = new ArrayList<Building>();
    }

    public void command(){

    }

    public boolean BuyUnit(Unit unit){
        boolean canBuy = false;
        switch(unit.getUnitType())
        {
            case Knight:
                if(amountGold - 100 >= 0 && amountFood - 50 >= 0){
                    canBuy = true;
                    amountGold -= 100;
                    amountFood -= 50;
                }
                break;
            case Archer:
                if(amountGold - 50 >= 0 && amountFood - 35 >= 0 && amountWood - 25 >=0 ){
                    canBuy = true;
                    amountGold -= 50;
                    amountFood -= 35;
                    amountWood -= 25;
                }
                break;
            case PikeMan:
                if(amountGold - 20 >= 0 && amountFood - 50 >= 0 && amountWood - 25 >=0 ){
                    canBuy = true;
                    amountGold -= 20;
                    amountFood -= 50;
                    amountWood -= 25;
                }
                break;
            case Builder:
                if(amountFood - 100 >= 0){
                    canBuy = true;
                    amountFood -= 100;
                }
        }

        if(canBuy){
            System.out.println("canbuy");
            units.add(unit);
        }
        return canBuy;
    }

    public boolean BuyBuilding(Building building){
        boolean canBuy = false;
        switch(building.getBuildingtype())
        {
            case Towncenter:
                if(amountGold - 1000 >= 0 && amountFood - 1000 >= 0 && amountStone - 1000 >= 0){
                    canBuy = true;
                    amountGold -= 1000;
                    amountFood -= 100;
                    amountStone -= 1000;
                }
                break;
            case Archery:
                if(amountWood - 500 >= 0 && amountStone - 500 >=0 ){
                    canBuy = true;
                    amountStone -= 500;
                    amountWood -= 500;
                }
                break;
        }

        if(canBuy){
            System.out.println("canbuy");
            buildings.add(building);
        }
        return canBuy;
    }
}
