import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ixbitz on 5-5-2017 in DACTest
 */
public class UserAdmin extends UnicastRemoteObject implements IUserAdmin{

    private List<User> users;

    protected UserAdmin() throws RemoteException {
        users = new ArrayList<>();
    }

    @Override
    public int getNumberOfUsers() throws RemoteException {
        return users.size();
    }

    @Override
    public User getUser(int nr) throws RemoteException {
        if (nr >= 0 && nr < users.size()) {
            return users.get(nr);
        }
        else {
            return null;
        }
    }

    @Override
    public User addUser(int id, String userName, String passWord) throws RemoteException {
        User user = new User(id, userName, passWord);
        users.add(user);
        System.out.println("Added user to list: " + user.toString());
        return user;
    }

    @Override
    public User addUser(User user) throws RemoteException {
        users.add(user);
        System.out.println("Added user to list: " + user.toString());
        return user;
    }
}
