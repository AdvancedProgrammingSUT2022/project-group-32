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

    // TODO: 4/17/2022 visibility & collectablity must be handled in Controller
    public Resource(ResourceType resourceType, Tile tile) {
        this.resourceType = resourceType;
        this.tile = tile;
        setFieldsFromDatabase();
    }


    protected void setFieldsFromDatabase() {
        // TODO: 4/17/2022
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getProduction() {
        return production;
    }

    public void setProduction(int production) {
        this.production = production;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public ArrayList<TerrainFeature> getCanBeFoundOns() {
        return canBeFoundOns;
    }

    public ImprovementType getNeededImprovements() {
        return neededImprovements;
    }

    public void setNeededImprovements(ImprovementType neededImprovements) {
        this.neededImprovements = neededImprovements;
    }

    public Tile getTile() {
        return tile;
    }
}
