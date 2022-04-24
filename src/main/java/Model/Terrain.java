package Model;

import enums.ResourceType;
import enums.TerrainFeature;
import enums.TerrainType;

public class Terrain {
    private final TerrainType terrainType;
    private final TerrainFeature terrainFeature;
    private final TerrainFeature baseFeature; // terrainType and baseFeature are same baseTerrain but in different enums
    private final ResourceType resourceType;

    public Terrain(TerrainType terrainType, TerrainFeature terrainFeature, ResourceType resourceType) {
        this.terrainType = terrainType;
        this.terrainFeature = terrainFeature;
        this.resourceType = resourceType;
        this.baseFeature = terrainType.baseFeature;
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
        return terrainType.movement + terrainFeature.movement;
    }

    public int getFood() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public int getProduction() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public int getGold() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }
}
