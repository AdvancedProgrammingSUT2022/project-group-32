package enums;

public enum TerrainType {
    COAST(TerrainFeature.COAST),
    DESERT(TerrainFeature.DESERT),
    GRASSLAND(TerrainFeature.GRASSLAND),
    HILL(TerrainFeature.HILL),
    MOUNTAIN(TerrainFeature.MOUNTAIN),
    OCEAN(TerrainFeature.OCEAN),
    PLAINS(TerrainFeature.PLAINS),
    SNOW(TerrainFeature.SNOW);


    private final TerrainFeature terrainFeature;

    TerrainType(TerrainFeature terrainFeature) {
        this.terrainFeature = terrainFeature;
    }

    public static TerrainFeature getTerrainFeatureByTerrainType(TerrainType terrainType) {
        return terrainType.terrainFeature;
    }

    public static TerrainType getTerrainTypeByTerrainFeature(TerrainFeature terrainFeature) {
        for (TerrainType terrainType : TerrainType.values())
            if (terrainType.terrainFeature.equals(terrainFeature)) return terrainType;
        return null;
    }

    public static boolean isBaseTerrain(TerrainFeature terrainFeature) {
        return getTerrainTypeByTerrainFeature(terrainFeature) != null;
    }

    public static boolean isTerrainFeature(TerrainFeature terrainFeature) {
        return getTerrainTypeByTerrainFeature(terrainFeature) == null;
    }
}
