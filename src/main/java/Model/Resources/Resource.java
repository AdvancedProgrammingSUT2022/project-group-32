package Model.Resources;

import enums.ImprovementType;
import enums.ResourceType;
import enums.TerrainFeature;

import java.util.ArrayList;

public class Resource {
    private final ResourceType resourceType; // TODO: 4/16/2022 handle resource type and resource group type with enumSet???
    private String name;
    private int food, production, gold;
    private final ArrayList<TerrainFeature> canBeFoundOns = new ArrayList<>();
    private final ArrayList<ImprovementType> neededImprovements = new ArrayList<>();

    public Resource(ResourceType resourceType) {
        this.resourceType = resourceType;

    }
}
