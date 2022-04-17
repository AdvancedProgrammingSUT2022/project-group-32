package enums;

public enum UnitType {
    WORKER("worker");
    private final String name;
    UnitType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
