package enums;

import java.util.ArrayList;
import java.util.Arrays;

public enum TerrainType {
    TUNDRA("Tundra", 1, 0, 1, 0.0, 1, new ArrayList<>(Arrays.asList(TerrainFeature.FOREST)), TerrainFeature.TUNDRA),
    DESERT("Desert", 0, 0, 0, -0.33, 1, new ArrayList<>(Arrays.asList(TerrainFeature.OASIS, TerrainFeature.FLOOD_PLAINS)), TerrainFeature.DESERT),
    GRASSLAND("Grassland", 2, 0, 0, -0.33, 1, new ArrayList<>(Arrays.asList(TerrainFeature.FOREST, TerrainFeature.MARSH)), TerrainFeature.GRASSLAND),
    HILL("Hill", 0, 2, 0, 0.25, 2, new ArrayList<>(Arrays.asList(TerrainFeature.FOREST, TerrainFeature.JUNGLE)), TerrainFeature.HILL),
    MOUNTAIN("Mountain", 0, 0, 0, 0.25, 9999, new ArrayList<>(Arrays.asList()), TerrainFeature.MOUNTAIN),
    OCEAN("Ocean", 1, 0, 1, 0.0, 9999, new ArrayList<>(Arrays.asList(TerrainFeature.ICE)), TerrainFeature.OCEAN),
    PLAINS("Plains", 1, 1, 0, -0.33, 1, new ArrayList<>(Arrays.asList(TerrainFeature.FOREST, TerrainFeature.JUNGLE)), TerrainFeature.PLAINS),
    SNOW("Snow", 0, 0, 0, -0.33, 1, new ArrayList<>(Arrays.asList()), TerrainFeature.SNOW);

    public final String name;
    public final int food, production, gold;
    public final double combat;
    public final int movement;
    public final ArrayList<TerrainFeature> possibleTerrainFeatures;
    public final TerrainFeature baseFeature;

    TerrainType(String name, int food, int production, int gold, double combat, int movement, ArrayList<TerrainFeature> possibleTerrainFeatures, TerrainFeature baseFeature) {
        this.name = name;
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.combat = combat;
        this.movement = movement;
        this.possibleTerrainFeatures = possibleTerrainFeatures;
        this.baseFeature = baseFeature;
    }
}


