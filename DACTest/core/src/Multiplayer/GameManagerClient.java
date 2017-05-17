package Multiplayer;

import Game.GameManager;
import Units.Unit;
import Player.Player;
import com.badlogic.gdx.Gdx;
import javafx.application.Platform;
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
    public void broadcastSetUnit(String property, Unit oldUnit, Unit newUnit) {
        communicator.broadcast(property, oldUnit, newUnit);
    }



    public void requestSetUnits(final Unit oldUnit, Unit newUnit) {
        new Thread(() -> {
            // do something important here, asynchronously to the rendering thread
            int count = 0;

            //int position;
            //Player correctPlayer;



            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    // process the result, e.g. add it to an Array<Result> field of the ApplicationListener.
                    int count = 0;

                    for (Player player : gameManager.getPlayers()) {
                        for (Unit unit : player.getUnits()) {
                            if (unit.getPosition().getX() == oldUnit.getPosition().getX() && unit.getPosition().getY() == oldUnit.getPosition().getY()) {
                                player.getUnits().set(count, newUnit);
                            }
                            else{
                                player.getUnits().add(newUnit);
                            }
                            count++;
                        }
                    }


                }
            });
        }).start();

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

    public void stop() {
        try {
            //super.stop();
        } catch (Exception ex) {
            Logger.getLogger(GameManagerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        communicator.stop();
    }
}

