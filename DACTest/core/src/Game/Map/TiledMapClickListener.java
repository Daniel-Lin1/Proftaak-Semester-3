package Game.Map;

import Building.Building;
import Units.Unit;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import javafx.scene.control.Button;

import java.util.ArrayList;

/**
 * Created by Imre on 4-4-2017.
 */
public class TiledMapClickListener extends ClickListener {
    private TiledMapActor actor;
    private TiledMapStage stage;

    //toDo alle data uit gamemanager halen en in deze lists zetten
    private ArrayList<Unit> units = new ArrayList<Unit>();
    private ArrayList<Building> buildings = new ArrayList<Building>();

    public TiledMapClickListener(TiledMapActor actor, TiledMapStage stage) {
        this.actor = actor;
        this.stage = stage;
    }

    // ToDo: clickevents die right click en left click onderscheiden

    @Override
    public void clicked(InputEvent event, float x, float y) {
        System.out.println("X:" + actor.getX() + " Y:" + actor.getY() + " has been clicked.");

        //ToDo: om units te spawnen om te testen pas dit aan
        //stage.createUnit(actor);
        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            for (int i = 0; i < units.size() && units.size() != 0; i++) {
                if (actor.getX() == units.get(i).getCoordinate().getX() && actor.getY() == units.get(i).getCoordinate().getY()) {
                    units.get(i).setSelected(true);
                } else {
                    units.get(i).setSelected(false);
                }


        //stage.createUnit(actor); // gebruik dit om te debuggen en units onClick te spawnen.


            }

            for (int i = 0; i < buildings.size() && buildings.size() != 0; i++)
            {
                if (actor.getX() == units.get(i).getCoordinate().getX() && actor.getY() == units.get(i).getCoordinate().getY())
                {
                    buildings.get(i).setSelected(true);
                }
                else
                {
                    buildings.get(i).setSelected(false);
                }
            }
        }

    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        //System.out.println("Hovering over: " + "X:" + actor.getX() + " Y:" + actor.getY());
    }
}
