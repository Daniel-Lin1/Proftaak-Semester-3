package Interfaces;

import java.rmi.Remote;

/**
 * Created by Daniel on 1-5-2017.
 */
public interface GameManagerable extends Remote {
    void create ();
    void render();

}
