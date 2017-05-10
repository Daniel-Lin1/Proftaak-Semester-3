package Game.Map;

import Building.Building;
import Building.UnitProducingBuilding;
import Enums.UnitType;
import Units.Unit;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import Game.GameManager;

import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by Imre on 4-4-2017.
 */
public class TiledMapClickListener extends ClickListener {
    private TiledMapActor actor;
    private TiledMapStage stage;
    private GameManager gameManager;

    private ArrayList<Unit> units = new ArrayList<Unit>();
    private ArrayList<Building> buildings = new ArrayList<Building>();

    public TiledMapClickListener(TiledMapActor actor, TiledMapStage stage, GameManager gameManager) throws RemoteException {
        this.actor = actor;
        this.stage = stage;
        this.gameManager = gameManager;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        ArrayList<Unit> units = gameManager.getUnits();
        ArrayList<Building> buildings = gameManager.getBuildings();
//        int tilex = /*150 -*/ ((int) actor.getX() /16);
//        int tiley = /*150 -*/ ((int) actor.getY() /16);
//
//        int tmp = (-1* ((tiley+1) - map.getTiles().get(0).size()));
//        System.out.println("eerste x :"+ tilex+" y :"+tiley + " tweede x :" + tilex + " y :" + tmp);
//        System.out.println(map.getTiles().get(tilex).get(tmp));
//        System.out.println("**********************************");


        Tile tile = gameManager.getMap().getTiles().get(((int) actor.getX() /16)).get((-1* ((((int) actor.getY() /16)+1) - gameManager.getMap().getTiles().get(0).size())));
        System.out.println(tile);


        switch (button) {
            case Buttons.RIGHT:
                for (int i = 0; i < units.size() && !units.isEmpty(); i++) {
                    if (units.get(i).getSelected() == true) {
                        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {

                        } else if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {

                        } else if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {

                        } else if (Gdx.input.isKeyPressed(Input.Keys.NUM_4)) {

                        } else {
                            if(tile.isWalkable() && !tile.isOccupied() && tile.getResource() == null){
                                units.get(i).moveTo(new Point((int) actor.getX(), (int) actor.getY()));
                            }else{
                                System.out.println("kan niet moven.");
                            }

                        }
                    }
                }
                for (int i = 0; i < buildings.size() && !buildings.isEmpty(); i++)
                {
                    //ToDo spawn units without having to rightclick
                    if (buildings.get(i).getSelected() == true) {

                        Boolean canSpawn = true;
                        for (int i2 = 0; i2 < units.size() && !units.isEmpty(); i2++) {
                            if (units.get(i2).getPosition().getX() == buildings.get(i).getCoordinate().getX() && units.get(i2).getPosition().getY() == buildings.get(i).getCoordinate().getY() - 16 ) {
                                canSpawn = false;
                            }
                        }
                        if (canSpawn == true)
                        {
                            UnitProducingBuilding uPB = (UnitProducingBuilding)buildings.get(i);
                            if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
                                units.add(uPB.produceUnit(UnitType.Knight));
                            } else if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
                                units.add(uPB.produceUnit(UnitType.PikeMan));
                            } else if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
                                units.add(uPB.produceUnit(UnitType.Archer));
                            }
                        }
                    }
                }
                break;
            case Buttons.LEFT:
                for (int i = 0; i < units.size() && !units.isEmpty(); i++) {
                    if (actor.getX() == units.get(i).getPosition().getX() && actor.getY() == units.get(i).getPosition().getY()) {
                        units.get(i).setSelected(true);
                    } else {
                        units.get(i).setSelected(false);
                    }
                }
                for (int i = 0; i < buildings.size() && !buildings.isEmpty(); i++)
                {
                    if (actor.getX() == buildings.get(i).getCoordinate().getX() && actor.getY() == buildings.get(i).getCoordinate().getY())
                    {
                        buildings.get(i).setSelected(true);
                    }
                    else
                    {
                        buildings.get(i).setSelected(false);
                    }
                }
                break;
            default:
                System.out.println("Default switch statement reached in " + Thread.currentThread().getStackTrace()[1]);
                return false;
        }
        return true;
    }
}
