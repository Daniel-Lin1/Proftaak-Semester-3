package Multiplayer;

import Game.GameManager;
import Units.MoveEvent;
import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.IRemotePublisherForDomain;
import fontyspublisher.IRemotePublisherForListener;

import java.beans.PropertyChangeEvent;
import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private final int nrThreads = 10;
    private ExecutorService threadPool = null;

    protected Communicator(GameManager gameManager) throws RemoteException {
        this.gameManager = gameManager ;
        threadPool = Executors.newFixedThreadPool(nrThreads);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException {
        String property = evt.getPropertyName();
        //TODO : MovementEvent hardcoded
        MoveEvent moveEvent = (MoveEvent) evt.getNewValue();
        //TODO : requestmethod

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
            connected = false;
            System.err.println("Cannot establish connection to remote publisher");
            System.err.println("Run WhiteBoardServer to start remote publisher");
        }
    }

    public void register(String property){
        if (connected) {
            try {
                // Nothing changes at remote publisher in case property was already registered
                publisherForDomain.registerProperty(property);
            } catch (RemoteException ex) {
                Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void subscribe( String property) {
        if (connected) {
            final IRemotePropertyListener listener = this;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        publisherForListener.subscribeRemoteListener(listener, property);
                    } catch (RemoteException ex) {
                        Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
    }

    public void broadcast( String property, MoveEvent moveEvent) {
        if (connected) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        publisherForDomain.inform(property,null,moveEvent);
                    } catch (RemoteException ex) {
                        Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
    }
    
    public void stop() {
        if (connected) {
            try {
                publisherForListener.unsubscribeRemoteListener(this, null);
            } catch (RemoteException ex) {
                Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            UnicastRemoteObject.unexportObject(this, true);
        } catch (NoSuchObjectException ex) {
            Logger.getLogger(Communicator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
