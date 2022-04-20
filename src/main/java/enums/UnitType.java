package enums;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public enum UnitType {
    ARCHER("Archer", 70, CombatType.ARCHERY, 4, 6, 2, 2, null, TechnologyType.ARCHERY),
    CHARIOT_ARCHER("Chariot Archer", 60, CombatType.MOUNTED, 3, 6, 2, 4, ResourceType.HORSES, TechnologyType.THE_WHEEL),
    SCOUT("Scout", 25, CombatType.RECON, 4, 0, 0, 2, null, null),
    SETTLER("Settler", 89, CombatType.CIVILIAN, 0, 0, 0, 2, null, null),
    SPEARMAN("Spearman", 50, CombatType.MELEE, 7, 0, 0, 2, null, TechnologyType.BRONZE_WORKING),
    WARRIOR("Warrior", 40, CombatType.MELEE, 6, 0, 0, 2, null, null),
    WORKER("Worker", 70, CombatType.CIVILIAN, 0, 0, 0, 2, null, null),
    CATAPULT("Catapult", 100, CombatType.SIEGE, 4, 14, 2, 2, ResourceType.IRON, TechnologyType.MATHEMATICS),
    HORSEMAN("Horseman", 80, CombatType.MOUNTED, 12, 0, 0, 4, ResourceType.HORSES, TechnologyType.HORSEBACK_RIDING),
    SWORDSMAN("Swordsman", 80, CombatType.MELEE, 11, 0, 0, 2, ResourceType.IRON, TechnologyType.IRON_WORKING),
    CROSSBOWMAN("Crossbowman", 120, CombatType.ARCHERY, 6, 12, 2, 2, null, TechnologyType.MACHINERY),
    KNIGHT("Knight", 150, CombatType.MOUNTED, 18, 0, 0, 3, ResourceType.HORSES, TechnologyType.CHIVALRY),
    LONGSWORDSMAN("Longswordsman", 150, CombatType.MELEE, 18, 0, 0, 3, ResourceType.IRON, TechnologyType.STEEL),
    PIKEMAN("Pikeman", 100, CombatType.MELEE, 10, 0, 0, 2, null, TechnologyType.CIVIL_SERVICE),
    TREBUCHET("Trebuchet", 170, CombatType.SIEGE, 6, 20, 2, 2, ResourceType.IRON, TechnologyType.PHYSICS),
    CANON("Canon", 250, CombatType.SIEGE, 10, 26, 2, 2, null, TechnologyType.CHEMISTRY),
    CAVALRY("Cavalry", 260, CombatType.MOUNTED, 25, 0, 0, 3, ResourceType.HORSES, TechnologyType.MILITARY_SCIENCE),
    LANCER("Lancer", 220, CombatType.MOUNTED, 22, 0, 0, 4, ResourceType.HORSES, TechnologyType.METALLURGY),
    MUSKETMAN("Musketman", 120, CombatType.GUNPOWDER, 16, 0, 0, 2, null, TechnologyType.GUNPOWDER),
    RIFLEMAN("Rifleman", 200, CombatType.GUNPOWDER, 25, 0, 0, 2, null, TechnologyType.RIFLING),
    ANTI_TANK_GUN("Anti-Tank Gun", 300, CombatType.GUNPOWDER, 32, 0, 0, 2, null, TechnologyType.REPLACEABLE_PARTS),
    ARTILLERY("Artillery", 420, CombatType.SIEGE, 16, 32, 3, 2, null, TechnologyType.DYNAMITE),
    INFANTRY("Infantry", 300, CombatType.GUNPOWDER, 36, 0, 0, 2, null, TechnologyType.REPLACEABLE_PARTS),
    PANZER("Panzer", 450, CombatType.ARMORED, 60, 0, 0, 5, null, TechnologyType.COMBUSTION),
    TANK("Tank", 450, CombatType.ARMORED, 50, 0, 0, 4, null, TechnologyType.COMBUSTION);

    private final String name;
    private final int cost;
    private final CombatType combatType;
    private final int strength;
    private final int rangedStrength;
    private final int range;
    private final int movement;
    private final ResourceType neededResource;
    private final TechnologyType neededTech;

    UnitType(String name, int cost, CombatType combatType, int strength, int rangedStrength, int range, int movement, ResourceType neededResource, TechnologyType neededTech) {
        this.name = name;
        this.cost = cost;
        this.combatType = combatType;
        this.strength = strength;
        this.rangedStrength = rangedStrength;
        this.range = range;
        this.movement = movement;
        this.neededResource = neededResource;
        this.neededTech = neededTech;
    }

    public static UnitType getUnitTypeByName(String name) {
        for (UnitType unitType : UnitType.values()) {
            if (unitType.name.equals(name)) return unitType;
        }
        return null;
    }

    private String getValue(UnitType unitType, String key) {
        try {
            JsonObject jsonObject = JsonParser.parseReader(new FileReader("src/main/java/t.json")).getAsJsonObject();
            Iterator<JsonElement> iterator = jsonObject.getAsJsonArray("units").iterator();
            while (iterator.hasNext()) {
                JsonObject jsonObject1 = iterator.next().getAsJsonObject();
                if (jsonObject1.getAsJsonPrimitive("Name").getAsString().equals(unitType.name))
                    return jsonObject1.getAsJsonPrimitive(key).getAsString();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("COULDN'T ACCESS DATABASE");
        }
        System.err.println("couldn't find unitType in database");
        return null;
    }

    public int cost() {
        return Integer.parseInt(Objects.requireNonNull(getValue(this, "Cost")));
    }

    public CombatType combatType() {
        return CombatType.getCombatTypeByName(Objects.requireNonNull(getValue(this, "Combat")));
    }

    public int strength() {
        return Integer.parseInt(Objects.requireNonNull(getValue(this, "Strength")));
    }

    public int rangedStrength() {
        return Integer.parseInt(Objects.requireNonNull(getValue(this, "Ranged")));
    }

    public int range() {
        return Integer.parseInt(Objects.requireNonNull(getValue(this, "Range")));
    }

    public int movement() {
        return Integer.parseInt(Objects.requireNonNull(getValue(this, "Movement")));
    }

    public ArrayList<ResourceType> neededResources() {
        ArrayList<ResourceType> resourceTypes = new ArrayList<>();
        if (getValue(this, "Resources").equals("null")) return resourceTypes;
        resourceTypes.add(ResourceType.getResourceTypeByName(getValue(this, "Resources")));
        return resourceTypes;
    }

    public ArrayList<TechnologyType> neededTechnologies() {
        ArrayList<TechnologyType> technologyTypes = new ArrayList<>();
        if (getValue(this, "Technology").equals("null")) return technologyTypes;
        technologyTypes.add(TechnologyType.getEnumByName(getValue(this, "Technology")));
        return technologyTypes;
    }


}
















