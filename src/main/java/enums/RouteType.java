package enums;

public enum RouteType {
    // costs are set DELLY
    ROAD(1),
    RAILROAD(2);

    private final int maintenanceCost;

    RouteType(int maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }

    public int getMaintenanceCost() {
        return maintenanceCost;
    }
}
