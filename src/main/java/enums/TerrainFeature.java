package enums;

import java.util.ArrayList;
import java.util.Arrays;

public enum TerrainFeature {
    NULL(" Null", 0, 0, 0, 0, 0, new ArrayList<>()),
    FLOOD_PLAINS("Flood Plains", 2, 0, 0, -0.33, 1, new ArrayList<>(Arrays.asList(ResourceType.WHEAT, ResourceType.SUGAR))),
    FOREST("Forest", 1, 1, 0, 0.25, 2, new ArrayList<>(Arrays.asList(ResourceType.DEER, ResourceType.FURS, ResourceType.DYES, ResourceType.SILK))),
    ICE("Ice", 0, 0, 0, 0.0, 9999, new ArrayList<>(Arrays.asList())),
    JUNGLE("Jungle", 1, -1, 0, 0.25, 2, new ArrayList<>(Arrays.asList(ResourceType.BANANAS, ResourceType.GEMS, ResourceType.DYES))),
    MARSH("Marsh", -1, 0, 0, -0.33, 2, new ArrayList<>(Arrays.asList(ResourceType.SUGAR))),
    OASIS("Oasis", 3, 0, 1, -0.33, 1, new ArrayList<>(Arrays.asList())),
    RIVERS("Rivers", 0, 0, 1, 0.0, 9999, new ArrayList<>(Arrays.asList())),
    COAST("Coast", 1, 0, 1, 0.0, 1, new ArrayList<>(Arrays.asList())),
    DESERT("Desert", 0, 0, 0, -0.33, 1, new ArrayList<>(Arrays.asList(ResourceType.IRON, ResourceType.GOLD, ResourceType.SILVER, ResourceType.GEMS, ResourceType.MARBLE, ResourceType.COTTON, ResourceType.INCENSE, ResourceType.SHEEP))),
    GRASSLAND("Grassland", 2, 0, 0, -0.33, 1, new ArrayList<>(Arrays.asList(ResourceType.IRON, ResourceType.HORSES, ResourceType.COAL, ResourceType.CATTLE, ResourceType.GOLD, ResourceType.GEMS, ResourceType.MARBLE, ResourceType.COTTON, ResourceType.SHEEP))),
    HILL("Hill", 0, 2, 0, 0.25, 2, new ArrayList<>(Arrays.asList(ResourceType.IRON, ResourceType.COAL, ResourceType.DEER, ResourceType.GOLD, ResourceType.SILVER, ResourceType.GEMS, ResourceType.MARBLE, ResourceType.SHEEP))),
    MOUNTAIN("Mountain", 0, 0, 0, 0.25, 9999, new ArrayList<>(Arrays.asList())),
    OCEAN("Ocean", 1, 0, 1, 0.0, 1, new ArrayList<>(Arrays.asList())),
    PLAINS("Plains", 1, 1, 0, -0.33, 1, new ArrayList<>(Arrays.asList(ResourceType.IRON, ResourceType.HORSES, ResourceType.COAL, ResourceType.WHEAT, ResourceType.GOLD, ResourceType.GEMS, ResourceType.MARBLE, ResourceType.IVORY, ResourceType.COTTON, ResourceType.INCENSE, ResourceType.SHEEP))),
    SNOW("Snow", 0, 0, 0, -0.33, 1, new ArrayList<>(Arrays.asList(ResourceType.IRON)));

    public final String name;
    public final int food, production, gold;
    public final double combat;
    public final int movement;
    public final ArrayList<ResourceType> possibleResources;

    TerrainFeature(String name, int food, int production, int gold, double combat, int movement, ArrayList<ResourceType> possibleResources) {
        this.name = name;
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.combat = combat;
        this.movement = movement;
        this.possibleResources = possibleResources;
    }
}
