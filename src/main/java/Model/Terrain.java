package Model;

import enums.ResourceType;
import enums.TerrainFeature;
import enums.TerrainType;

public class Terrain {
    private final int INF = 9999;

    private final TerrainType terrainType;
    private final TerrainFeature terrainFeature;
    private final TerrainFeature baseFeature; // terrainType and baseFeature are same baseTerrain but in different enums
    private final ResourceType resourceType;

    public Terrain(TerrainType terrainType, TerrainFeature terrainFeature, ResourceType resourceType) {
        this.terrainType = terrainType;
        this.terrainFeature = terrainFeature;
        this.resourceType = resourceType;
        if(terrainType != null) this.baseFeature = terrainType.baseFeature;
        else this.baseFeature = null;
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
        if(terrainType == null) return INF;
        return terrainType.movement + terrainFeature.movement;
    }

    public int getFood() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public int getProduction() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public int getGold() {
        return terrainType.gold + terrainFeature.gold;
    }
}
