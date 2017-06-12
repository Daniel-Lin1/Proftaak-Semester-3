package multiplayer;

import java.io.Serializable;

/**
 * Created by Yannick on 22-5-2017.
 */
public class ObjectIdentifier implements Serializable{
    private int playerId;
    private Object object;

    /*
    Param @player player of whom the object is
    Param @object building or unit

     */
    public ObjectIdentifier(int playerId, Object object){
        this.playerId = playerId;
        this.object = object;
    }

    public int getPlayerId(){
        return this.playerId;
    }

    public Object getObject(){
        return this.object;
    }
}
