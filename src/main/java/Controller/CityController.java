package Controller;

import Model.Building;
import Model.City;
import Model.Tile;
import enums.BuildingType;
import enums.Responses.InGameResponses;
import enums.UnitType;

import java.util.ArrayList;

public class CityController {

    public static void updateCity(City city) {
        // updates resources, population, ... after a turn has passed.
        // pass a turn in constructions as well
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static ArrayList<Tile> getTilesInRange(int range) {
        // gets tiles close to its border
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static void buildUnit(UnitType unitType) {
        // handles the food production if settler
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static void pauseUnit() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    /**
     * gets a BuildingType and builds it. if this type is half-built continues it. moves currently inProgress building to
     * inComplete buildings of city
     *
     * @param buildingType type of the desired building
     */
    public static InGameResponses.Building buildBuilding(BuildingType buildingType) {
        City city = GameController.getSelectedCity();
        if (city == null) return InGameResponses.Building.CITY_NOT_SELECTED;

        for (Building building : city.getBuildings()) {
            if (building.getBuildingType().name.equals(buildingType.name))
                return InGameResponses.Building.ALREADY_EXISTS;
        }
        // adding previous inProgress to incomplete
        if (city.getBuildingInProgress() != null) {
            city.addIncompleteBuilding(city.getBuildingInProgress());
        }

        // restating previously half-built if exists
        for (Building incompleteBuilding : city.getIncompleteBuildings()) {
            if (incompleteBuilding.getBuildingType().name.equals(buildingType.name)) {
                city.setBuildingInProgress(incompleteBuilding);
                city.removeIncompleteBuilding(incompleteBuilding);
                return InGameResponses.Building.IN_PROGRESS_BUILDING_CHANGED;
            }
        }

        city.setBuildingInProgress(new Building(buildingType));
        return InGameResponses.Building.IN_PROGRESS_BUILDING_CHANGED;
    }

    public static void pauseBuilding(BuildingType buildingType) {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static void assignCitizenToTile() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static void assignCitizenToBuilding() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static void growCity() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }
}
