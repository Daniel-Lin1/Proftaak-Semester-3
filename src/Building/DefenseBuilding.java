package Building;

import Interfaces.Damage;

/**
 * Created by Daniel on 26-3-2017.
 */
public class DefenseBuilding extends Building implements Damage {

    private int hitPerSecond;
    private int Damage;
    private int Range;
    private boolean willReturnFire;

    @Override
    public void attack() {

    }
}
