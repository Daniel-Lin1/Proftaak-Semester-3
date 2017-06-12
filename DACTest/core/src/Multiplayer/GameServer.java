package Multiplayer;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fontyspublisher.IRemotePublisherForDomain;
import fontyspublisher.IRemotePublisherForListener;
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
    private static final Logger LOGGER = Logger.getLogger(Multiplayer.GameServer.class.getName());
    private static final String ipProperty = "java.rmi.server.hostname";

    public static void main(String[] args) throws RemoteException, UnknownHostException, NotBoundException {
        // Create an instance of RemotePublisher
        RemotePublisher remotePublisher = null;
        try {
            remotePublisher = new RemotePublisher();
            Registry registry = LocateRegistry.createRegistry(portNumber);
            System.setProperty(ipProperty, String.valueOf(InetAddress.getLocalHost().getHostAddress()));
            registry.rebind("publisher", remotePublisher);
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }

        String ip =  System.setProperty(ipProperty, String.valueOf(InetAddress.getLocalHost().getHostAddress()));
        System.out.println(ip);

        System.setProperty(ipProperty, String.valueOf(InetAddress.getLocalHost().getHostAddress()));
        Registry registry = LocateRegistry.getRegistry(ip, portNumber);
        IRemotePublisherForDomain publisherForDomain = (IRemotePublisherForDomain) registry.lookup(bindingName);
        IRemotePublisherForListener publisherForListener = (IRemotePublisherForListener) registry.lookup(bindingName);


        // Remote publisher registered
        System.out.println("Remote publisher registered.");
        System.out.println("Port number  : " + portNumber);
        System.out.println("Binding name : " + bindingName);
    }
}
