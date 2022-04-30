package Model.Resources;

import Model.Tile;
import enums.ResourceType;

public class Luxury extends Resource {
    private int happiness;

    public Luxury(ResourceType resourceType, Tile tile) {
        super(resourceType, tile);
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }
}
