package enums;

import java.util.ArrayList;
import java.util.Arrays;

public enum ResourceType {
    BANANAS("Bananas", 1, 0, 0, new ArrayList<>(Arrays.asList(TerrainFeature.JUNGLE)), ImprovementType.PLANTATION),
    CATTLE("Cattle", 1, 0, 0, new ArrayList<>(Arrays.asList(TerrainFeature.GRASSLAND)), ImprovementType.PASTURE),
    DEER("Deer", 1, 0, 0, new ArrayList<>(Arrays.asList(TerrainFeature.FOREST, TerrainFeature.HILL)), ImprovementType.CAMP),
    SHEEP("Sheep", 1, 0, 0, new ArrayList<>(Arrays.asList(TerrainFeature.GRASSLAND, TerrainFeature.PLAINS, TerrainFeature.DESERT, TerrainFeature.HILL)), ImprovementType.PASTURE),
    WHEAT("Wheat", 1, 0, 0, new ArrayList<>(Arrays.asList(TerrainFeature.FLOOD_PLAINS, TerrainFeature.PLAINS)), ImprovementType.FARM),
    COAL("Coal", 0, 1, 0, new ArrayList<>(Arrays.asList(TerrainFeature.GRASSLAND, TerrainFeature.PLAINS, TerrainFeature.HILL)), ImprovementType.MINE),
    HORSES("Horses", 0, 1, 0, new ArrayList<>(Arrays.asList(TerrainFeature.GRASSLAND, TerrainFeature.PLAINS)), ImprovementType.PASTURE),
    IRON("Iron", 0, 1, 0, new ArrayList<>(Arrays.asList(TerrainFeature.GRASSLAND, TerrainFeature.PLAINS, TerrainFeature.DESERT, TerrainFeature.SNOW, TerrainFeature.HILL)), ImprovementType.MINE),
    COTTON("Cotton", 0, 0, 2, new ArrayList<>(Arrays.asList(TerrainFeature.GRASSLAND, TerrainFeature.PLAINS, TerrainFeature.DESERT)), ImprovementType.PLANTATION),
    DYES("Dyes", 0, 0, 2, new ArrayList<>(Arrays.asList(TerrainFeature.JUNGLE, TerrainFeature.FOREST)), ImprovementType.PLANTATION),
    FURS("Furs", 0, 0, 2, new ArrayList<>(Arrays.asList(TerrainFeature.FOREST)), ImprovementType.CAMP),
    GEMS("Gems", 0, 0, 3, new ArrayList<>(Arrays.asList(TerrainFeature.JUNGLE, TerrainFeature.GRASSLAND, TerrainFeature.PLAINS, TerrainFeature.DESERT, TerrainFeature.HILL)), ImprovementType.MINE),
    GOLD("Gold", 0, 0, 2, new ArrayList<>(Arrays.asList(TerrainFeature.GRASSLAND, TerrainFeature.PLAINS, TerrainFeature.DESERT, TerrainFeature.HILL)), ImprovementType.MINE),
    INCENSE("Incense", 0, 0, 2, new ArrayList<>(Arrays.asList(TerrainFeature.DESERT, TerrainFeature.PLAINS)), ImprovementType.PLANTATION),
    IVORY("Ivory", 0, 0, 2, new ArrayList<>(Arrays.asList(TerrainFeature.PLAINS)), ImprovementType.CAMP),
    MARBLE("Marble", 0, 0, 2, new ArrayList<>(Arrays.asList(TerrainFeature.GRASSLAND, TerrainFeature.PLAINS, TerrainFeature.DESERT, TerrainFeature.HILL)), ImprovementType.QUARRY),
    SILK("Silk", 0, 0, 2, new ArrayList<>(Arrays.asList(TerrainFeature.FOREST)), ImprovementType.PLANTATION),
    SILVER("Silver", 0, 0, 2, new ArrayList<>(Arrays.asList(TerrainFeature.DESERT, TerrainFeature.HILL)), ImprovementType.MINE),
    SUGAR("Sugar", 0, 0, 2, new ArrayList<>(Arrays.asList(TerrainFeature.FLOOD_PLAINS, TerrainFeature.MARSH)), ImprovementType.PLANTATION);

    private final String name;
    public final int food, production, gold;
    public final ArrayList<TerrainFeature> possibleFeatures;
    public final ImprovementType neededImprovement;

    ResourceType(String name, int food, int production, int gold, ArrayList<TerrainFeature> possibleFeatures, ImprovementType neededImprovement) {
        this.name = name;
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.possibleFeatures = possibleFeatures;
        this.neededImprovement = neededImprovement;
    }

    public static ResourceType getResourceTypeByName(String resources) {
        for (ResourceType resourceType : ResourceType.values()) {
            if (resourceType.name.equals(resources)) return resourceType;
        }
        System.err.println("Resource Not Found!");
        return null;
    }
}
