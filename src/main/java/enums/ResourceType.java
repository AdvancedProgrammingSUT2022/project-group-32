package enums;

public enum ResourceType {
    BANANAS("Bananas"),
    CATTLE("Cattle"),
    DEER("Deer"),
    SHEEP("Sheep"),
    WHEAT("Wheat"),
    COAL("Coal"),
    HORSES("Horses"),
    IRON("Iron"),
    COTTON("Cotton"),
    DYES("Dyes"),
    FURS("Furs"),
    GEMS("Gems"),
    GOLD("Gold"),
    INCENSE("Incense"),
    IVORY("Ivory"),
    MARBLE("Marble"),
    SILK("Silk"),
    SILVER("Silver"),
    SUGAR("Sugar");

    private final String name;

    ResourceType(String name) {
        this.name = name;
    }

    public static ResourceType getResourceTypeByName(String resources) {
        for (ResourceType resourceType : ResourceType.values()) {
            if (resourceType.name.equals(resources)) return resourceType;
        }
        System.err.println("Resource Not Found!");
        return null;
    }
}
