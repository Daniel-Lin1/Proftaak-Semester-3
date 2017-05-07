package game;

import Enums.ResourceEnum;

/**
 * Created by Daniel on 26-3-2017.
 */
public class Resource {
    private ResourceEnum resourceEnum;
    private int amount;

    public Resource(ResourceEnum resourceEnum, int amount) {
        this.resourceEnum = resourceEnum;
        this.amount = amount;
    }
}
