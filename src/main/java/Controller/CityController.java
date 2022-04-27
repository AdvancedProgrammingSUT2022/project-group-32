package Controller;

import Model.*;
import Model.Units.Unit;
import enums.BuildingType;
import enums.Responses.InGameResponses;
import enums.UnitType;

import java.util.ArrayList;

public class CityController {

    public static void updateCity(City city) {
        // todo: updates resources, population, ... after a turn has passed.
        UpdateInProgressBuildsTurns();
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    private static void UpdateInProgressBuildsTurns() {
        Player player = GameController.getGame().getCurrentPlayer();

        for (City city : player.getCities()) {
            // Building
            Building inProgressBuilding = city.getBuildingInProgress();
            if (inProgressBuilding != null) {
                inProgressBuilding.setRemainingTurns(inProgressBuilding.getRemainingTurns() - 1);
                if (inProgressBuilding.getRemainingTurns() == 0) {
                    city.addBuilding(inProgressBuilding);
                    city.setBuildingInProgress(null);
                    // todo: build finished popup and start new building popUp
                }
            }
            // Unit
            Unit inProgressUnit = city.getUnitInProgress();
            if (inProgressUnit != null) {
                inProgressUnit.setRemainingTurn(inProgressUnit.getRemainingTurn() - 1);
                if (inProgressUnit.getRemainingTurn() == 0) {
                    player.addUnit(inProgressUnit);
                    city.setUnitInProgress(null);
                    // TODO: new unit creation logic, unit build finished popUp and new buildRequired popUp
                }
            }
        }

        // Tech
        Technology inProgressTechnology = player.getTechnologyInProgress();
        if (inProgressTechnology != null) {
            inProgressTechnology.setRemainingTurns(inProgressTechnology.getRemainingTurns() - 1);
            if (inProgressTechnology.getRequiredTurns() == 0) {
                player.addTechnology(inProgressTechnology);
                player.setTechnologyInProgress(null);
                // TODO: 4/27/2022 tech fininsh popup and Logic, new build required
            }
        }

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
            if (building.getBuildingType() == buildingType)
                return InGameResponses.Building.ALREADY_EXISTS;
        }
        // adding previous inProgress to incomplete
        if (city.getBuildingInProgress() != null) {
            city.addIncompleteBuilding(city.getBuildingInProgress());
        }

        // restating previously half-built if exists
        for (Building incompleteBuilding : city.getIncompleteBuildings()) {
            if (incompleteBuilding.getBuildingType() == buildingType) {
                city.setBuildingInProgress(incompleteBuilding);
                city.removeIncompleteBuilding(incompleteBuilding);
                return InGameResponses.Building.IN_PROGRESS_BUILDING_CHANGED;
            }
        }

        city.setBuildingInProgress(new Building(buildingType));
        return InGameResponses.Building.IN_PROGRESS_BUILDING_CHANGED;
    }

    public static InGameResponses.Building pauseInProgressBuilding() {
        City city = GameController.getSelectedCity();
        if (city == null) return InGameResponses.Building.CITY_NOT_SELECTED;

        if (city.getBuildingInProgress() == null) return InGameResponses.Building.NO_BUILDING_IN_PROGRESS;
        city.addIncompleteBuilding(city.getBuildingInProgress());
        city.setBuildingInProgress(null);
        return InGameResponses.Building.BUILDING_PAUSED;
    }

    public static void assignCitizenToTile() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }


    // specialists removed, right?
    public static void assignCitizenToBuilding() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static void growCity() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }
}
