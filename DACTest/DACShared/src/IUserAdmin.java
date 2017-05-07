import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Ixbitz on 5-5-2017 in DACTest
 */
public interface IUserAdmin extends Remote {
    int getNumberOfUsers() throws RemoteException;
    User getUser(int nr) throws RemoteException;
    User addUser(int id, String userName, String passWord) throws RemoteException;
    User addUser(User user) throws RemoteException;
}
