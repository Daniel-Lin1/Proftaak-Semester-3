package Multiplayer;

import Game.GameManager;
import Units.MoveEvent;
import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.IRemotePublisherForDomain;
import fontyspublisher.IRemotePublisherForListener;

import java.beans.PropertyChangeEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Daniel on 15-5-2017.
 */
public class Communicator extends UnicastRemoteObject implements IRemotePropertyListener {

    private GameManager gameManager;

    private IRemotePublisherForDomain publisherForDomain;
    private IRemotePublisherForListener publisherForListener;
    private static int portNumber = 1099;
    private static String bindingName = "publisher";
    private boolean connected = false;

    protected Communicator(GameManager gameManager) throws RemoteException {
        this.gameManager = gameManager ;

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException {
        String property = evt.getPropertyName();
        //TODO : MovementEvent hardcoded
        MoveEvent moveEvent = (MoveEvent) evt.getNewValue();

    }

    public void connecToPublisher(){
        try{
            Registry registry = LocateRegistry.getRegistry("localhost", portNumber);
            publisherForDomain = (IRemotePublisherForDomain) registry.lookup(bindingName);
            publisherForListener = (IRemotePublisherForListener) registry.lookup(bindingName);
            connected = true;
            System.out.println("Connection with remote publisher established");
        }
        catch (RemoteException | NotBoundException re){

        }
    }
}
