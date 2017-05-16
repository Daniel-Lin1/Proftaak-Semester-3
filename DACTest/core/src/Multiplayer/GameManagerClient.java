package Multiplayer;

import Game.GameManager;
import Units.Unit;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nico Kuijpers
 */
public class GameManagerClient extends Application {

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
    }

    @Override
    public void start(Stage primaryStage) {
        connectToPublisherActionPerformed();
    }

    // Broadcast draw event to other white boards
    private void broadcastSetUnit (String property, Unit unit) {

        communicator.broadcast(property,unit);
    }

    // Draw dot on white board
    private void setUnit(ArrayList<Unit> units) {
        gameManager.getOwnPlayer().setUnits(units);
    }

    /**
     * Request to draw dot on white board.
     * @param units the units meant to be dislocated
     */
    public void requestSetUnits(final ArrayList<Unit> units) {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                gameManager.getOwnPlayer().setUnits(units);
            }
        });
    }



    public void connectToPublisherActionPerformed() {
        //TODO: Call wanneer er geconnect moet worden
        // Establish connection with remote publisherForDomain
        communicator.connectToPublisher();

        // Register properties to be published
        for (String property : properties) {
            communicator.register(property);
            communicator.subscribe(property);
        }
    }

    @Override
    public void stop() {
        try {
            super.stop();
        } catch (Exception ex) {
            Logger.getLogger(GameManagerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        communicator.stop();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
