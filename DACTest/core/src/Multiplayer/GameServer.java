package multiplayer;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fontyspublisher.RemotePublisher;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Logger;


public class GameServer {

    private static int portNumber = 1099;
    private static String bindingName = "publisher";
    private static final Logger LOGGER = Logger.getLogger(multiplayer.GameServer.class.getName());
    private static final String IP_PROPERTY = "java.rmi.server.hostname";

    public static void main(String[] args) throws RemoteException, UnknownHostException, NotBoundException {
        // Create an instance of RemotePublisher
        RemotePublisher remotePublisher;
        try {
            remotePublisher = new RemotePublisher();
            Registry registry = LocateRegistry.createRegistry(portNumber);
            System.setProperty(IP_PROPERTY, String.valueOf(InetAddress.getLocalHost().getHostAddress()));
            registry.rebind("publisher", remotePublisher);
        } catch (Exception e) {
            LOGGER.info(String.valueOf(e));
        }

        System.setProperty(IP_PROPERTY, String.valueOf(InetAddress.getLocalHost().getHostAddress()));



        // Remote publisher registered
        System.out.println("Remote publisher registered.");
        System.out.println("Port number  : " + portNumber);
        System.out.println("Binding name : " + bindingName);
    }
}
