package enums;

public enum RoadType {
    // costs are set DELLY
    ROAD(1),
    RAILROAD(2);

    private final int maintenanceCost;

    RoadType(int maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }

    public int getMaintenanceCost() {
        return maintenanceCost;
    }
}
