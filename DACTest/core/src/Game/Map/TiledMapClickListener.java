package Game.Map;

import Building.*;
import Enums.UnitType;
import Game.GameManager;
import Units.OffensiveUnit;
import Units.Unit;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;

/**
 * Created by Imre on 4-4-2017.
 */
public class TiledMapClickListener extends ClickListener {
    private TiledMapActor actor;
    private TiledMapStage stage;

    public TiledMapClickListener(TiledMapActor actor, TiledMapStage stage) {
        this.actor = actor;
        this.stage = stage;
    }

    // ToDo: clickevents die right click en left click onderscheiden
    // ToDo: Units moven met right click event

    @Override
    public void clicked(InputEvent event, float x, float y) {

        //stage.createUnit(actor); //om units te spawnen om te testen pas dit aan

        ArrayList<Unit> units = GameManager.units;
        ArrayList<Building> buildings = GameManager.buildings;
        System.out.println("X:" + actor.getX() + " Y:" + actor.getY() + " has been clicked.");
        for (int i = 0; i < units.size() && units.size() != 0; i++) {
            if (actor.getX() == units.get(i).getCoordinate().getX() && actor.getY() == units.get(i).getCoordinate().getY()) {
                units.get(i).setSelected(true);
                System.out.println(units.get(i).toString());
            } else {
                units.get(i).setSelected(false);
            }
        }

        for (int i = 0; i < buildings.size() && buildings.size() != 0; i++)
        {
            if (actor.getX() == buildings.get(i).getCoordinate().getX() && actor.getY() == buildings.get(i).getCoordinate().getY())
            {
                // ToDo: dit moet via UI gebeuren en niet via dit clickEvent
                UnitProducingBuilding uPB = (UnitProducingBuilding)buildings.get(i);
                units.add(uPB.produceUnit(UnitType.Knight));
                //buildings.get(i).setSelected(true);
            }
            else
            {
                buildings.get(i).setSelected(false);
            }
        }
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        //System.out.println("Hovering over: " + "X:" + actor.getX() + " Y:" + actor.getY());
    }
}
