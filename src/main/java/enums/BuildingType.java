package enums;

public enum BuildingType {
    BARRACKS("Barracks"),
    GRANARY("Granary"),
    LIBRARY("Library"),
    MONUMENT("Monument"),
    WALLS("Walls"),
    WATER_MILL("Water Mill"),
    ARMORY("Armory"),
    BURIAL_TOMB("Burial Tomb"),
    CIRCUS("Circus"),
    COLOSSEUM("Colosseum"),
    COURTHOUSE("Courthouse"),
    STABLE("Stable"),
    TEMPLE("Temple"),
    CASTLE("Castle"),
    FORGE("Forge"),
    GARDEN("Garden"),
    MARKET("Market"),
    MINT("Mint"),
    MONASTERY("Monastery"),
    UNIVERSITY("University"),
    WORKSHOP("Workshop"),
    BANK("Bank"),
    MILITARY_ACADEMY("Military Academy"),
    MUSEUM("Museum"),
    OPERA_HOUSE("Opera House"),
    PUBLIC_SCHOOL("Public School"),
    SATRAP_COURT("Satrap's Court"),
    THEATER("Theater"),
    WINDMILL("Windmill"),
    ARSENAL("Arsenal"),
    BROADCAST_TOWER("Broadcast Tower"),
    FACTORY("Factory"),
    HOSPITAL("Hospital"),
    MILITARY_BASE("Military Base"),
    STOCK_EXCHANGE("Stock Exchange");

    private final String name;

    BuildingType(String name) {
        this.name = name;
    }
}
