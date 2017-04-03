package Game;

import Enums.BuildingType;

import java.util.ArrayList;

/**
 * Created by Daniel on 26-3-2017.
 */
public class Resource {
    private Enums.Resource resource;
    private int amount;

    public Resource(Enums.Resource resource, int amount) {
        this.resource = resource;
        this.amount = amount;
    }
}
