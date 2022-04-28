package Model;

import enums.TechnologyType;

import java.util.ArrayList;

public class Technology {
    private int cost;
    private final ArrayList<TechnologyType> neededTechs = new ArrayList<>();
    private final ArrayList<TechnologyType> unlockingTechs = new ArrayList<>();
    private final String name;
    private final TechnologyType technologyType;
    private int remainingTurns;
    private int requiredTurns;

    public Technology(TechnologyType technologyType) {
        this.technologyType = technologyType;
        this.name = this.technologyType.name;
        setFieldsFromDataBase(this.technologyType);
    }

    private void setFieldsFromDataBase(TechnologyType technologyType) {
        // TODO: 4/16/2022 set cost, neededTechs, ...
        this.remainingTurns = this.requiredTurns;
    }

    public void setRequiredTurns(int requiredTurns) {
        this.requiredTurns = requiredTurns;
    }

    public int getCost() {
        return cost;
    }

    public ArrayList<TechnologyType> getNeededTechs() {
        return neededTechs;
    }

    public ArrayList<TechnologyType> getUnlockingTechs() {
        return unlockingTechs;
    }

    public String getName() {
        return name;
    }

    public TechnologyType getTechnologyType() {
        return technologyType;
    }

    public int getRemainingTurns() {
        return remainingTurns;
    }

    public int getRequiredTurns() {
        return requiredTurns;
    }

    public void setRemainingTurns(int remainingTurns) {
        this.remainingTurns = remainingTurns;
    }
}
