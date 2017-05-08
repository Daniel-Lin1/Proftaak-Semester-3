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

    //toDo alle data uit gamemanager halen en in deze lists zetten
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
        switch (button) {
            case Buttons.LEFT:
                System.out.println("LEFT button");
                for (int i = 0; i < units.size() && !units.isEmpty(); i++) {
                    if (units.get(i).getSelected() == true)
                    {
                        if (Gdx.input.isKeyPressed(Input.Keys.Q)){

                        }
                        if(Gdx.input.isKeyPressed(Input.Keys.W)){

                        }
                        if(Gdx.input.isKeyPressed(Input.Keys.E)){

                        }
                        if(Gdx.input.isKeyPressed(Input.Keys.R)){

                        }
                        else{
                            units.get(i).moveTo(new Point((int)actor.getX(), (int)actor.getY()));
                            units.get(i).setSelected(false);
                        }
                    }
                }
                break;
            case Buttons.RIGHT:
                System.out.println("RIGHT button");
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
                        UnitProducingBuilding uPB = (UnitProducingBuilding)buildings.get(i);
                        units.add(uPB.produceUnit(UnitType.Knight));
                        //buildings.get(i).setSelected(true);
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

    @Override
    public void clicked(InputEvent event, float x, float y) {

    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        //System.out.println("Hovering over: " + "X:" + actor.getX() + " Y:" + actor.getY());
    }
}
