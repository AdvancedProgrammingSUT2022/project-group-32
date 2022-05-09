package Model;

import enums.RoadType;

public class Road {
    private RoadType type;
    private int remainingTurns;

    public Road(RoadType roadType){
        this.type = roadType;
        this.remainingTurns = 3;
    }

    public RoadType getType() {
        return type;
    }

    public void setType(RoadType type) {
        this.type = type;
    }

    public int getRemainingTurns() {
        return remainingTurns;
    }

    public void setRemainingTurns(int remainingTurns) {
        this.remainingTurns = remainingTurns;
    }
}
