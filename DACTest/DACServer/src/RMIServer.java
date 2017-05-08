import java.util.Observable;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by Ixbitz on 5-5-2017 in DACTest
 */
public class RMIServer extends Observable {

    private static final int portNumber = 1099;
    private static final String bindingName = "UserAdmin";
    private UserAdmin userAdmin = null;

    public RMIServer(){
        System.out.println("Port number: " + portNumber);

        //Create new userAdmin
        try {
            userAdmin = new UserAdmin();
            System.out.println("Server: Created UserAdmin");
        }catch (RemoteException e){
            System.out.println("Server: Cannot create user administration");
            System.out.println("Server: RemoteException: " + e.getMessage());
            userAdmin = null;
        }

        // Bind user adiministration using Naming
        if (userAdmin != null) {
            try {
                LocateRegistry.createRegistry(portNumber);//Create new registry
                Naming.rebind(bindingName, userAdmin);//Bind userAdmin to name "UserAdmin"
            } catch (MalformedURLException e) {
                System.out.println("Server: Cannot bind user administration");
                System.out.println("Server: MalformedURLException: " + e.getMessage());
            } catch (RemoteException e) {
                System.out.println("Server: Cannot bind user administration");
                System.out.println("Server: RemoteException: " + e.getMessage());
            }
            System.out.println("Server: user administration bound to " + bindingName);
            System.out.println("Server: Running....");
        } else {
            System.out.println("Server: user administration not bound");
        }
    }

    public static void main(String[] args) {
        // write your code here
        RMIServer server = new RMIServer();//Start RMI server
    }

}