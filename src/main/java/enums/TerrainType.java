package enums;

public enum TerrainType {
    Dessert(TerrainFeature.DESERT);


    private final TerrainFeature terrainFeature;

    TerrainType(TerrainFeature terrainFeature) {
        this.terrainFeature = terrainFeature;
    }
}
