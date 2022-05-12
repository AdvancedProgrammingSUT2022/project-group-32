package Model;

import enums.ResourceType;
import enums.TerrainFeature;
import enums.TerrainType;

public class Terrain {
    private final int INF = 9999;

    private final TerrainType terrainType;
    private TerrainFeature terrainFeature;
    private final TerrainFeature baseFeature; // terrainType and baseFeature are same baseTerrain but in different enums
    private final ResourceType resourceType;
    private int featureHP;

    public Terrain(TerrainType terrainType, TerrainFeature terrainFeature, ResourceType resourceType) {
        this.terrainType = terrainType;
        this.terrainFeature = terrainFeature;
        this.resourceType = resourceType;
        if(terrainType != null) this.baseFeature = terrainType.baseFeature;
        else this.baseFeature = null;
        if(terrainFeature == TerrainFeature.FOREST) featureHP = 4;
        else if(terrainFeature == TerrainFeature.JUNGLE) featureHP = 7;
        else if(terrainFeature == TerrainFeature.MARSH) featureHP = 6;
        else featureHP = INF;
    }

    public Terrain(Terrain terrain){
        this.terrainType = terrain.terrainType;
        this.terrainFeature = terrain.terrainFeature;
        this.baseFeature = terrain.baseFeature;
        this.resourceType = terrain.resourceType;
    }

    public TerrainType getTerrainType() {
        return terrainType;
    }

    public TerrainFeature getTerrainFeature() {
        return terrainFeature;
    }

    public void setTerrainFeature(TerrainFeature terrainFeature) {
        this.terrainFeature = terrainFeature;
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
        return terrainType.food + terrainFeature.food;
    }

    public int getProduction() {
        return terrainType.production + terrainFeature.production;
    }

    public int getGold() {
        return terrainType.gold + terrainFeature.gold;
    }

    public int getFeatureHP() {
        return featureHP;
    }

    public void setFeatureHP(int featureHP) {
        this.featureHP = featureHP;
    }

    public double getCombat(){
        return terrainType.combat + terrainFeature.combat;
    }
}
