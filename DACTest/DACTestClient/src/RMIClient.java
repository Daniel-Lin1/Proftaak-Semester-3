import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 * Created by Ixbitz on 5-5-2017 in DACTest
 */
public class RMIClient implements Observer{
    private static final String bindingName = "UserAdmin";
    private static String ipAddress;
    private static int portNumber;
    private IUserAdmin userAdmin = null;


    public RMIClient(String ipAddress, int portNumber){
        // Print IP address and port number for registry
        System.out.println("Client: IP Address: " + ipAddress);
        System.out.println("Client: Port number " + portNumber);

        // Bind student administration using Naming
        try {
            userAdmin = (IUserAdmin) Naming.lookup("rmi://" + ipAddress + ":" + portNumber + "/" + bindingName);//Lookup
        } catch (MalformedURLException e) {
            System.out.println("Client: Cannot bind user administration");
            System.out.println("Client: MalformedURLException: " + e.getMessage());
            userAdmin = null;
        } catch (RemoteException e) {
            System.out.println("Client: Cannot bind user administration");
            System.out.println("Client: RemoteException: " + e.getMessage());
            userAdmin = null;
        } catch (NotBoundException e) {
            System.out.println("Client: Cannot bind user administration");
            System.out.println("Client: NotBoundException: " + e.getMessage());
            userAdmin = null;
        }

        // Print result binding student administration
        if (userAdmin != null) {
            System.out.println("Client: user administration bound");
        } else {
            System.out.println("Client: user administration is null pointer");
        }

        // Test RMI connection
        if (userAdmin != null) {
            //Do client stuff
            testUserAdmin();
        }
    }

    public void testUserAdmin(){
        // Get number of users
        try {
            System.out.println("Client: Number of user: " + userAdmin.getNumberOfUsers());
        } catch (RemoteException e) {
            System.out.println("Client: Cannot get number of users");
            System.out.println("Client: RemoteException: " + e.getMessage());
        }

        for (int i = 0; i < 1; i++) {
            //Add user
            try {
                User test1 = new User(i, "test1", "33223bd43251kv76b52");
                userAdmin.addUser(test1);
                System.out.println("Client: User " + test1.getUserName() + " added to userAdmin");
            } catch (RemoteException e) {
                System.out.println(e.getMessage());
            }
        }


        // Get number of users
        try {
            System.out.println("Client: Number of users: " + userAdmin.getNumberOfUsers());
        } catch (RemoteException e) {
            System.out.println("Client: Cannot get number of users");
            System.out.println("Client: RemoteException: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // write your code here
        System.out.println("Initialized client...");

        Scanner input = new Scanner(System.in);//Dunno what this does //TODO Look this up!

        //Hardcoded for simplicity:
        ipAddress = "127.0.0.1";
        portNumber = 1099;

        RMIClient rmiClient = new RMIClient(ipAddress, portNumber);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
