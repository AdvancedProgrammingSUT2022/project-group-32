package enums.Types;

public enum BuildingType {
    BARRACKS("Barracks", TechnologyType.BRONZE_WORKING),
    GRANARY("Granary", TechnologyType.POTTERY),
    LIBRARY("Library", TechnologyType.WRITING),
    MONUMENT("Monument", null),
    WALLS("Walls", TechnologyType.MASONRY),
    WATER_MILL("Water Mill", TechnologyType.THE_WHEEL),
    ARMORY("Armory", TechnologyType.IRON_WORKING),
    BURIAL_TOMB("Burial Tomb", TechnologyType.PHILOSOPHY),
    CIRCUS("Circus", TechnologyType.HORSEBACK_RIDING),
    COLOSSEUM("Colosseum", TechnologyType.CONSTRUCTION),
    COURTHOUSE("Courthouse", TechnologyType.MATHEMATICS),
    STABLE("Stable", TechnologyType.HORSEBACK_RIDING),
    TEMPLE("Temple", TechnologyType.PHILOSOPHY),
    CASTLE("Castle", TechnologyType.CHIVALRY),
    FORGE("Forge", TechnologyType.METAL_CASTING),
    GARDEN("Garden", TechnologyType.THEOLOGY),
    MARKET("Market", TechnologyType.CURRENCY),
    MINT("Mint", TechnologyType.CURRENCY),
    MONASTERY("Monastery", TechnologyType.THEOLOGY),
    UNIVERSITY("University", TechnologyType.EDUCATION),
    WORKSHOP("Workshop", TechnologyType.METAL_CASTING),
    BANK("Bank", TechnologyType.BANKING),
    MILITARY_ACADEMY("Military Academy", TechnologyType.MILITARY_SCIENCE),
    MUSEUM("Museum", TechnologyType.ARCHAEOLOGY),
    OPERA_HOUSE("Opera House", TechnologyType.ACOUSTICS),
    PUBLIC_SCHOOL("Public School", TechnologyType.SCIENTIFIC_THEORY),
    SATRAP_COURT("Satrap’s Court", TechnologyType.BANKING),
    THEATER("Theater", TechnologyType.PRINTING_PRESS),
    WINDMILL("Windmill", TechnologyType.ECONOMICS),
    ARSENAL("Arsenal", TechnologyType.RAILROAD),
    BROADCAST_TOWER("Broadcast Tower", TechnologyType.RADIO),
    FACTORY("Factory", TechnologyType.STEAM_POWER),
    HOSPITAL("Hospital", TechnologyType.BIOLOGY),
    MILITARY_BASE("Military Base", TechnologyType.TELEGRAPH),
    STOCK_EXCHANGE("Stock Exchange", TechnologyType.ELECTRICITY);


    public final String name;
    public final TechnologyType technologyType;

    BuildingType(String name, TechnologyType technologyType) {
        this.name = name;
        this.technologyType = technologyType;
    }
}
