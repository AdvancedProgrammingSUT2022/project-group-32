package enums;

import java.util.ArrayList;
import java.util.Arrays;

public enum TerrainType {
    COAST("Coast", 1, 0, 1, 0.0, 1, new ArrayList<>(Arrays.asList(TerrainFeature.ICE)), new ArrayList<>(Arrays.asList())),
    DESERT("Desert", 0, 0, 0, -0.33, 1, new ArrayList<>(Arrays.asList(TerrainFeature.OASIS, TerrainFeature.FLOOD_PLAINS)), new ArrayList<>(Arrays.asList(ResourceType.IRON, ResourceType.GOLD, ResourceType.SILVER, ResourceType.GEMS, ResourceType.MARBLE, ResourceType.COTTON, ResourceType.INCENSE, ResourceType.SHEEP))),
    GRASSLAND("Grassland", 2, 0, 0, -0.33, 1, new ArrayList<>(Arrays.asList(TerrainFeature.FOREST, TerrainFeature.MARSH)), new ArrayList<>(Arrays.asList(ResourceType.IRON, ResourceType.HORSES, ResourceType.COAL, ResourceType.CATTLE, ResourceType.GOLD, ResourceType.GEMS, ResourceType.MARBLE, ResourceType.COTTON, ResourceType.SHEEP))),
    HILL("Hill", 0, 2, 0, 0.25, 2, new ArrayList<>(Arrays.asList(TerrainFeature.FOREST, TerrainFeature.JUNGLE)), new ArrayList<>(Arrays.asList(ResourceType.IRON, ResourceType.COAL, ResourceType.DEER, ResourceType.GOLD, ResourceType.SILVER, ResourceType.GEMS, ResourceType.MARBLE, ResourceType.SHEEP))),
    MOUNTAIN("Mountain", 0, 0, 0, 0.25, 9999, new ArrayList<>(Arrays.asList()), new ArrayList<>(Arrays.asList())),
    OCEAN("Ocean", 1, 0, 1, 0.0, 1, new ArrayList<>(Arrays.asList(TerrainFeature.ICE)), new ArrayList<>(Arrays.asList())),
    PLAINS("Plains", 1, 1, 0, -0.33, 1, new ArrayList<>(Arrays.asList(TerrainFeature.FOREST, TerrainFeature.JUNGLE)), new ArrayList<>(Arrays.asList(ResourceType.IRON, ResourceType.HORSES, ResourceType.COAL, ResourceType.WHEAT, ResourceType.GOLD, ResourceType.GEMS, ResourceType.MARBLE, ResourceType.IVORY, ResourceType.COTTON, ResourceType.INCENSE, ResourceType.SHEEP))),
    SNOW("Snow", 0, 0, 0, -0.33, 1, new ArrayList<>(Arrays.asList()), new ArrayList<>(Arrays.asList(ResourceType.IRON)));

    public final String name;
    public final int food, production, gold;
    public final double combat;
    public final int movement;
    public final ArrayList<TerrainFeature> possibleTerrainFeatures;
    public final ArrayList<ResourceType> possibleResources;

    TerrainType(String name, int food, int production, int gold, double combat, int movement, ArrayList<TerrainFeature> possibleTerrainFeatures, ArrayList<ResourceType> possibleResources) {
        this.name = name;
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.combat = combat;
        this.movement = movement;
        this.possibleTerrainFeatures = possibleTerrainFeatures;
        this.possibleResources = possibleResources;
    }
}


