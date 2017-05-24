package Multiplayer;

import Player.Player;
import Units.Unit;

import java.io.Serializable;

/**
 * Created by Yannick on 22-5-2017.
 */
public class ObjectIdentifier implements Serializable{
    private Player player;
    private Object object;

    /*
    Param @player player of whom the object is
    Param @object building or unit

     */
    public ObjectIdentifier(Player player, Object object){
        this.player = player;
        this.object = object;
    }

    public Player getPlayer(){
        return this.player;
    }

    public Object getObject(){
        return this.object;
    }
}
