package Game.Map;

import Building.UnitProducingBuilding;
import Enums.UnitType;
import Multiplayer.ObjectIdentifier;
import Units.OffensiveUnit;
import Units.Unit;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import Game.GameManager;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Imre on 4-4-2017.
 */
public class TiledMapClickListener extends ClickListener {
    private TiledMapActor actor;
    private TiledMapStage stage;
    private GameManager gameManager;

    public TiledMapClickListener(TiledMapActor actor, TiledMapStage stage, GameManager gameManager) {
        this.actor = actor;
        this.stage = stage;
        this.gameManager = gameManager;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        Point mousePoint = new Point((int)actor.getX() /16, (int) actor.getY() /16);
        Tile tile = gameManager.getMap().getTileFromCord(mousePoint);

        if(gameManager.getOwnPlayer().getSelectedUnits().size() > 0 ){  // if there are selected units
            if(button == Buttons.RIGHT && Gdx.input.isKeyPressed(Input.Keys.NUM_1)){
                for (int i = 0; i < gameManager.getOwnPlayer().getSelectedUnits().size(); i++) {
                    if(gameManager.getOwnPlayer().getSelectedUnits().get(i) instanceof OffensiveUnit){
                        //todo check if unit is an opponent before attacking.
                        OffensiveUnit attackingUnit = (OffensiveUnit) gameManager.getOwnPlayer().getSelectedUnits().get(i);
                        attackingUnit.setInBattleWith(tile.getUnit());
                    }
                }
            }else if(button == Buttons.RIGHT && Gdx.input.isKeyPressed(Input.Keys.NUM_2)){

            }else if(button == Buttons.RIGHT && Gdx.input.isKeyPressed(Input.Keys.NUM_3)){

            }else if(button == Buttons.RIGHT && Gdx.input.isKeyPressed(Input.Keys.NUM_4)) {

            }else if (button == Buttons.RIGHT){
                if (tile.isWalkable() && !tile.isOccupied() && tile.getResource() == null) {
                    for (int i = 0; i < gameManager.getOwnPlayer().getSelectedUnits().size(); i++) {
                        Unit oldUnit = gameManager.getOwnPlayer().getSelectedUnits().get(i);
                        Unit newUnit = oldUnit;
                        newUnit.moveTo(mousePoint, gameManager.getMap());
                        gameManager.getGmc().broadcastSetUnit("unit", oldUnit, new ObjectIdentifier(gameManager.getOwnPlayer().getPlayerID(),newUnit));
                    }
                }
                //----- //ToDo dit is een cheezy fix. later aanpassen! - Nick
                gameManager.getOwnPlayer().setSelectedUnits(new ArrayList<Unit>());
                //-----
            }
        }

        //todo building stuff input zou in een andere click listener moeten zitten. dan kan je een building selecteren en dan los op <1-2-3-4-5> clicken en een unit maken.
        if(gameManager.getOwnPlayer().getSelectedBuilding() != null && gameManager.getOwnPlayer().getSelectedBuilding() instanceof UnitProducingBuilding){  // if there is a unit producing building
            UnitProducingBuilding uPB = (UnitProducingBuilding)gameManager.getOwnPlayer().getSelectedBuilding();
            if(button == Buttons.RIGHT && Gdx.input.isKeyPressed(Input.Keys.NUM_1)){
                //produce knight
                addObserver(uPB.produceUnit(gameManager.getHighestUnitId(), UnitType.Knight, gameManager.getMap()));
            }else if(button == Buttons.RIGHT && Gdx.input.isKeyPressed(Input.Keys.NUM_2)){
                //produce pikeman
                addObserver(uPB.produceUnit(gameManager.getHighestUnitId(), UnitType.PikeMan, gameManager.getMap()));
            }else if(button == Buttons.RIGHT && Gdx.input.isKeyPressed(Input.Keys.NUM_3)){
                //produce acher
                addObserver(uPB.produceUnit(gameManager.getHighestUnitId(), UnitType.Archer, gameManager.getMap()));
            }else if(button == Buttons.RIGHT && Gdx.input.isKeyPressed(Input.Keys.NUM_4)) {
                //produce builder unit
                addObserver(uPB.produceUnit(gameManager.getHighestUnitId(), UnitType.Builder, gameManager.getMap()));
            }else{
                //no unit created.
            }
        }
        if(button == Buttons.LEFT){
            if(tile.getUnit() != null){
                //todo check if unit on tile is friendly / enemy
                gameManager.getOwnPlayer().addUnitToSelectedUnits(tile.getUnit());
            }
            if(tile.getBuilding() != null){
                //todo check if building on tile is friendly / enemy
                gameManager.getOwnPlayer().setSelectedBuilding(tile.getBuilding());
            }
            if(tile.getBuilding() == null && tile.getUnit() == null){
                gameManager.getOwnPlayer().setSelectedUnits(new ArrayList<Unit>());
                gameManager.getOwnPlayer().setSelectedBuilding(null);
            }
        }
        return true;
    }

    private void addObserver(Unit unit){
        unit.addObserver(gameManager);
        gameManager.getOwnPlayer().BuyUnit(unit);
        gameManager.getGmc().broadcastSetUnit("unit", unit, new ObjectIdentifier(gameManager.getOwnPlayer().getPlayerID(), unit));
    }
}