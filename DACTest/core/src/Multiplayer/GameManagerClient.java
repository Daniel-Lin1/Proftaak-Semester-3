package Multiplayer;

import Building.Building;
import Game.GameManager;
import Player.Player;
import Units.Unit;
import com.badlogic.gdx.Gdx;
import javafx.stage.Stage;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Nico Kuijpers
 */
public class GameManagerClient {

    private GameManager gameManager;
    private GameManagerCommunicator communicator;

    private final String[] properties = {"unit", "movement", "building", "map", "status"};


    public GameManagerClient(GameManager gameManager) {
        this.gameManager = gameManager;

        // Create communicator to communicate with other white boards
        try {
            this.communicator = new GameManagerCommunicator(this);
        } catch (RemoteException ex) {
            Logger.getLogger(GameManagerClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        start(null);
    }

    public void start(Stage primaryStage) {
        connectToPublisherActionPerformed();
    }

    // Broadcast draw event to other white boards
    public void broadcastSetUnit(String property, Unit oldUnit, ObjectIdentifier newUnit) {
        communicator.broadcast(property, oldUnit, newUnit);
    }


    public void connectToPublisherActionPerformed() {
        // Establish connection with remote publisherForDomain
        communicator.connectToPublisher();

        // Register properties to be published
        for (String property : properties) {
            communicator.register(property);
            communicator.subscribe(property);
        }
    }

    public void requestBuildingAction(Building newBuilding, Building oldBuilding){
        new Thread(() -> {
            newBuilding.setSelected(false);
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                  boolean found = false;

                  for(Player player : gameManager.getPlayers()){
                      for(Building building : player.getBuildings()){
                          if(building.getID() == oldBuilding.getID()){
                              player.getBuildings().set(player.getBuildings().indexOf(building), newBuilding);
                              found = true;
                          }
                      }
                  }
                  if (found == false)
                  {
                      gameManager.getOwnPlayer().getBuildings().add(newBuilding);
                  }
                }
            });
        }).start();
    }

    public void requestUnitAction(String property, Unit oldUnit, ObjectIdentifier objectIdentifier) {
        new Thread(() -> {
            Unit newUnit = (Unit) objectIdentifier.getObject();
            newUnit.setSelected(false);

            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    boolean found = false;


                    for(Player player : gameManager.getPlayers()){
                        if(player.getUnits().isEmpty() == false){
                            for(Unit unit : player.getUnits()){
                                if(unit.getId() == oldUnit.getId()){
                                    player.getUnits().set(player.getUnits().indexOf(unit),newUnit);


                                    //player.getUnits().set(objectIdentifier.getPlayer().getUnits().indexOf(newUnit), newUnit);
                                    //objectIdentifier.getPlayer().getUnits().set(player.getUnits().indexOf(newUnit), newUnit);
                                    found = true;
                                }
                            }
                        }
                    }
                    if(found == false){
                        gameManager.getPlayers().get(objectIdentifier.getPlayerId()).getUnits().add(newUnit);
                    }
                }
            });
        }).start();

    }


    public void stop() {
        try {
            //super.stop();
        } catch (Exception ex) {
            Logger.getLogger(GameManagerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        communicator.stop();
    }
}

