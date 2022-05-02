package Model;

import enums.BuildingType;
import enums.TechnologyType;

import java.util.ArrayList;

public class Building {
    private String name;
    private int cost;
    private final BuildingType buildingType;
    private int maintenanceCost;
    private final ArrayList<TechnologyType> neededTechs = new ArrayList<>();
    private int remainingCost;

    public Building(BuildingType buildingType) {
        this.buildingType = buildingType;
        setFieldsFromDatabase(this.buildingType);
    }

    private void setFieldsFromDatabase(BuildingType buildingType) {
        // TODO: 4/16/2022 sets cost, name, maintenance, ...
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public int getMaintenanceCost() {
        return maintenanceCost;
    }

    public ArrayList<TechnologyType> getNeededTechs() {
        return neededTechs;
    }

    public int getRemainingCost() {
        return remainingCost;
    }

    public void setRemainingCost(int remainingTurns) {
        this.remainingCost = remainingTurns;
    }
}
