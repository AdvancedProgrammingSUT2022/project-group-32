package Model;

import enums.ResourceType;
import enums.TerrainFeature;
import enums.TerrainType;

public class Terrain {
    private final TerrainType terrainType;
    private final TerrainFeature terrainFeature;
    private TerrainFeature baseFeature; // terrainType and baseFeature are same baseTerrain but in different enums
    private final ResourceType resourceType;
    private int MP, food, production, gold;

    public Terrain(TerrainType terrainType, TerrainFeature terrainFeature, ResourceType resourceType) {
        this.terrainType = terrainType;
        this.terrainFeature = terrainFeature;
        this.resourceType = resourceType;
        setFieldsFromDatabase();
    }

    private void setFieldsFromDatabase() {
        // TODO: 4/17/2022 sets baseFeature, MP, food , ...
        // TODO: 4/17/2022  first the baseTerrain must be applied and then TerrainFeature's effect
    }

    public TerrainType getTerrainType() {
        return terrainType;
    }

    public TerrainFeature getTerrainFeature() {
        return terrainFeature;
    }

    public TerrainFeature getBaseFeature() {
        return baseFeature;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public int getMP() {
        return MP;
    }

    public int getFood() {
        return food;
    }

    public int getProduction() {
        return production;
    }

    public int getGold() {
        return gold;
    }
}
