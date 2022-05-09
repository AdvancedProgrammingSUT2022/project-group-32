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
    private final ArrayList<TerrainFeature> canBeFoundOns;
    private ImprovementType neededImprovement;
    private final Tile tile;

    // TODO: 4/17/2022 visibility & collectablity must be handled in Controller
    public Resource(ResourceType resourceType, Tile tile) {
        this.resourceType = resourceType;
        this.tile = tile;
        this.food = resourceType.food;
        this.gold = resourceType.gold;
        this.production = resourceType.production;
        this.name = resourceType.name;
        this.neededImprovement = resourceType.neededImprovement;
        if(resourceType.possibleFeatures != null){
            this.canBeFoundOns = new ArrayList<>(resourceType.possibleFeatures);
        } else{
            this.canBeFoundOns = new ArrayList<>();
        }
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

    public ImprovementType getNeededImprovement() {
        return neededImprovement;
    }

    public void setNeededImprovement(ImprovementType neededImprovement) {
        this.neededImprovement = neededImprovement;
    }

    public Tile getTile() {
        return tile;
    }

    public boolean canBeFoundOn(TerrainFeature terrainFeature) {
        for (TerrainFeature canBeFoundOn : canBeFoundOns) {
            if (canBeFoundOn.equals(terrainFeature)) {
                return true;
            }
        }
        return false;
    }
}
