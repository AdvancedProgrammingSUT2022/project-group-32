package Model.Resources;

import Model.Tile;
import enums.ImprovementType;
import enums.ResourceType;
import enums.TerrainFeature;

import java.util.ArrayList;

public class Resource {
    private final ResourceType resourceType; // TODO: 4/16/2022 handle resource type and resource group type with enumSet???
    private String name;
    private int food, production, gold;
    private final ArrayList<TerrainFeature> canBeFoundOns = new ArrayList<>();
    private ImprovementType neededImprovements;
    private final Tile tile;

    // TODO: 4/17/2022 visibility & gettability must be handled in Controller
    public Resource(ResourceType resourceType, Tile tile) {
        this.resourceType = resourceType;
        this.tile = tile;
        setFieldsFromDatabase();
    }


    private void setFieldsFromDatabase() {
        // TODO: 4/17/2022
    }

}
