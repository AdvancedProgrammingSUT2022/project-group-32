package enums;

public enum RoadType {
    ROAD("Road"),
    RAILROAD("Railroad");

    String name;

    RoadType(String name){
        this.name = name;
    }

    public static RoadType getTypeByName(String name){
        for (RoadType type : RoadType.values()) {
            if(type.name.equals(name)){
                return type;
            }
        }
        return null;
    }
}
