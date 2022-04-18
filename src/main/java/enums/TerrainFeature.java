package enums;

public enum TerrainFeature {
    // TerrainFeatures
    FLOOD_PLAINS("Flood Plains"),
    FOREST("Forest"),
    ICE("Ice"),
    JUNGLE("Jungle"),
    MARSH("Marsh"),
    OASIS("Oasis"),
    RIVERS("Rivers"),

    // base terrains
    COAST("Coast"),
    DESERT("Desert"),
    GRASSLAND("Grassland"),
    HILL("Hill"),
    MOUNTAIN("Mountain"),
    OCEAN("Ocean"),
    PLAINS("Plains"),
    SNOW("Snow");

    private final String name;

    TerrainFeature(String name) {
        this.name = name;
    }
}
