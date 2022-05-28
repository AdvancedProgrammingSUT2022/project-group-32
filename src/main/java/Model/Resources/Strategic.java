package Model.Resources;

import Model.Tile;
import enums.Types.ResourceType;
import enums.Types.TechnologyType;

public class Strategic extends Resource {
    private TechnologyType neededTech;

    public Strategic(ResourceType resourceType, Tile tile) {
        super(resourceType, tile);
    }

    public TechnologyType getNeededTech() {
        return neededTech;
    }

    public void setNeededTech(TechnologyType neededTech) {
        this.neededTech = neededTech;
    }
}
