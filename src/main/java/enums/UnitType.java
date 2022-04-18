package enums;

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
}
