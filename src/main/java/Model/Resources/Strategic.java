package Model.Resources;

import Model.Tile;
import enums.ResourceType;
import enums.TechnologyType;

public class Strategic extends Resource {
    private TechnologyType neededTech;

    public Strategic(ResourceType resourceType, Tile tile) {
        super(resourceType, tile);
    }

    @Override
    protected void setFieldsFromDatabase() {
        // TODO: 4/17/2022
        // asd asd
    }

    public TechnologyType getNeededTech() {
        return neededTech;
    }

    public void setNeededTech(TechnologyType neededTech) {
        this.neededTech = neededTech;
    }
}
