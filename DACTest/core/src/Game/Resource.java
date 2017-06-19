package game;

import Enums.ResourceEnum;

import java.io.Serializable;

/**
 * Created by Daniel on 26-3-2017.
 */
public class Resource implements Serializable{
    private ResourceEnum resourceEnum;
    private int amount;

    public ResourceEnum getResourceEnum() {
        return resourceEnum;
    }

    public void setResourceEnum(ResourceEnum resourceEnum) {
        this.resourceEnum = resourceEnum;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Resource(ResourceEnum resourceEnum, int amount) {
        this.resourceEnum = resourceEnum;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "resourceEnum=" + resourceEnum +
                ", amount=" + amount +
                '}';
    }
}
