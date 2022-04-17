package Model;

import enums.ImprovementType;
import enums.ResourceType;
import enums.TechnologyType;
import enums.TerrainFeature;

import java.util.ArrayList;

public class Improvement {
    private final ImprovementType improvementType;
    private String name;
    private final Tile tile;
    private int addedFood, addedGold, addedProduction;
    private final ArrayList<TechnologyType> neededTechs = new ArrayList<>();
    private final ArrayList<ResourceType> unlockingResources = new ArrayList<>();
    private final ArrayList<TerrainFeature> possibleTerrainFeatures = new ArrayList<>();
    private int remainingTurns;
    private int requiredTurns;

    public Improvement(ImprovementType improvementType, Tile tile) {
        this.improvementType = improvementType;
        this.tile = tile;
        setFieldsFromDataBase();
    }

    private void setFieldsFromDataBase() {
        // TODO: 4/17/2022 `
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRemainingTurns() {
        return remainingTurns;
    }

    public void setRemainingTurns(int remainingTurns) {
        this.remainingTurns = remainingTurns;
    }

    public ImprovementType getImprovementType() {
        return improvementType;
    }

    public Tile getTile() {
        return tile;
    }

    public int getAddedFood() {
        return addedFood;
    }

    public int getAddedGold() {
        return addedGold;
    }

    public int getAddedProduction() {
        return addedProduction;
    }

    public ArrayList<TechnologyType> getNeededTechs() {
        return neededTechs;
    }

    public ArrayList<ResourceType> getUnlockingResources() {
        return unlockingResources;
    }

    public ArrayList<TerrainFeature> getPossibleTerrainFeatures() {
        return possibleTerrainFeatures;
    }

    public int getRequiredTurns() {
        return requiredTurns;
    }
}
