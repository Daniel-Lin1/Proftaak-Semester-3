package Game.Map;

import Building.Building;
import Building.UnitProducingBuilding;
import Enums.UnitType;
import Multiplayer.GameManagerClient;
import Units.OffensiveUnit;
import Units.Unit;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
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

    public TiledMapClickListener(TiledMapActor actor, TiledMapStage stage, GameManager gameManager) {
        this.actor = actor;
        this.stage = stage;
        this.gameManager = gameManager;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        units = gameManager.getOwnPlayer().getUnits();
        ArrayList<Building> buildings = gameManager.getOwnPlayer().getBuildings();

        Tile tile = gameManager.getMap().getTileFromCord((int)actor.getX() /16, (int) actor.getY() /16);
        //System.out.println(tile);

        switch (button) {
            case Buttons.RIGHT:
                for (int i = 0; i < units.size() && !units.isEmpty(); i++) {
                    if (units.get(i).getSelected() == true) {
                        Unit oldUnit = units.get(i);
                        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
                            for (int a = 0; a < units.size() && !units.isEmpty(); a++) {
                                System.out.println(units.get(a).getPosition().getX() + ", " + units.get(a).getPosition().getY());
                                System.out.println((int)x + ", " + (int)y);
                                if (units.get(a).getPosition().getX() == actor.getX()/16 && units.get(a).getPosition().getY() == actor.getY()/16) {
                                   ((OffensiveUnit) units.get(i)).attack(units.get(a));
                                }
                            }
                        } else if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {

                        } else if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {

                        } else if (Gdx.input.isKeyPressed(Input.Keys.NUM_4)) {

                        } else {
                            if (tile.isWalkable() && !tile.isOccupied() && tile.getResource() == null) {
                                units.get(i).moveTo(new Point((int) actor.getX() /16, (int) actor.getY()/16), gameManager.getMap());
                            } else {
                                System.out.println("kan niet moven.");
                            }
                            gameManager.getGmc().broadcastSetUnit("unit", oldUnit, units.get(i));
                        }
                    }
                }
                for (int i = 0; i < buildings.size() && !buildings.isEmpty(); i++)
                {
                    //ToDo spawn units without having to right click
                    if (buildings.get(i).getSelected() == true) {

                        Boolean canSpawn = true;
                        for (int i2 = 0; i2 < units.size() && !units.isEmpty(); i2++) {
                            if (units.get(i2).getPosition().getX() == buildings.get(i).getCoordinate().getX() && units.get(i2).getPosition().getY() == buildings.get(i).getCoordinate().getY() - 1 ) {
                                canSpawn = false;
                            }
                        }
                        if (canSpawn == true)
                        {
                            UnitProducingBuilding uPB = (UnitProducingBuilding)buildings.get(i);
                            if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
                                gameManager.getOwnPlayer().BuyUnit(uPB.produceUnit(gameManager.getHighestUnitIDPlus1(), UnitType.Knight));
                            } else if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
                                gameManager.getOwnPlayer().BuyUnit(uPB.produceUnit(gameManager.getHighestUnitIDPlus1(), UnitType.PikeMan));
                            } else if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
                                gameManager.getOwnPlayer().BuyUnit(uPB.produceUnit(gameManager.getHighestUnitIDPlus1(), UnitType.Archer));
                            }
                        }
                    }
                }
                break;
            case Buttons.LEFT:
                for (int i = 0; i < units.size() && !units.isEmpty(); i++) {
                    if (actor.getX()/16 == units.get(i).getPosition().getX() && actor.getY()/16 == units.get(i).getPosition().getY()) {
                        units.get(i).setSelected(true);
                    } else {
                        units.get(i).setSelected(false);
                    }
                }
                for (int i = 0; i < buildings.size() && !buildings.isEmpty(); i++)
                {
                    if (actor.getX() /16 == buildings.get(i).getCoordinate().getX() && actor.getY()/16 == buildings.get(i).getCoordinate().getY())
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
