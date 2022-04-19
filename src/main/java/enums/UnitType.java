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
    // NON_TROOPS
    WORKER("Worker"),
    SETTLER("Settler"),
    // TROOPS
    ARCHER("Archer"),
    CHARIOT_ARCHER("Chariot Archer"),
    SCOUT("Scout"),
    SPEARMAN("Spearman"),
    WARRIOR("Warrior"),
    CATAPULT("Catapult"),
    HORSEMAN("Horseman"),
    SWORDSMAN("Swordsman"),
    CROSSBOWMAN("Crossbowman"),
    KNIGHT("Knight"),
    LONGSWORDSMAN("Longswordsman"),
    PIKEMAN("Pikeman"),
    TREBUCHET("Trebuchet"),
    CANON("Canon"),
    CAVALRY("Cavalry"),
    LANCER("Lancer"),
    MUSKETMAN("Musketman"),
    RIFLEMAN("Rifleman"),
    ANTITANK_GUN("Anti-Tank Gun"),
    ARTILLERY("Artillery"),
    INFANTRY("Infantry"),
    PANZER("Panzer"),
    TANK("Tank");


    private final String name;

    UnitType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
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
















