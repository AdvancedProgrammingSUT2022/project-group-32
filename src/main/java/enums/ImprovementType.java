package enums;

public enum ImprovementType {
    CAMP("Camp"),
    FARM("Farm"),
    LUMBER_MILL("Lumber Mill"),
    MINE("Mine"),
    PASTURE("Pasture"),
    PLANTATION("Plantation"),
    QUARRY("Quarry"),
    TRADING_POST("Trading Post"),
    MANUFACTORY("Manufactory");

    private final String name;

    ImprovementType(String name) {
        this.name = name;
    }
}
