package Controller;

import Model.*;
import Model.Units.Troop;
import Model.Units.Unit;
import enums.BuildingType;
import enums.CombatType;
import enums.Responses.InGameResponses;
import enums.UnitType;

import java.util.ArrayList;

public class CityController {

    // updates all there is about a city accordingly
    public static void updateCity(City city) {
        // todo: updates resources, population, ... after a turn has passed.
        // gold updates is PlayerController.updateGold
        updateInProgressBuildsTurns(city);
        growCity();
    }

    private static void updateInProgressBuildsTurns(City city) {
        Player player = city.getOwner();
        // Building
        Building inProgressBuilding = city.getBuildingInProgress();
        if (inProgressBuilding != null) {
            inProgressBuilding.setRemainingCost(inProgressBuilding.getRemainingCost() - city.getProduction()); // production should be completed
            if (inProgressBuilding.getRemainingCost() <= 0) {
                city.addBuilding(inProgressBuilding);
                city.setBuildingInProgress(null);
                // todo: build finished popup and start new building popUp
            }
        }
        // Unit
        Unit inProgressUnit = city.getUnitInProgress();
        if (inProgressUnit != null) {
            inProgressUnit.setRemainingCost(inProgressUnit.getRemainingCost() - city.getProduction());
            if (inProgressUnit.getRemainingCost() <= 0) {
                player.addUnit(inProgressUnit);
                city.setUnitInProgress(null);
                if(!city.getCapitalTile().canFit(inProgressUnit)){
                    System.err.println("unit can't be made because there exists a unit on the tile already");
                    return;
                }
                city.getCapitalTile().putUnit(inProgressUnit);
                // TODO: 5/2/2022 build finished popup maybe??
            }
        }
    }

    /**
     * gets a UnitType and builds it. if this type is half-built continues it. moves currently inProgress building to
     * inComplete buildings of city
     *
     * @param unitType type of the desired unit
     */
    public static InGameResponses.Unit buildUnit(UnitType unitType) {
        City city = GameController.getSelectedCity();

        // adding the previous one to the unfinished list
        if (city.getUnitInProgress() != null) {
            if(city.getUnitInProgress().getUnitType() == unitType){
                return InGameResponses.Unit.UNIT_ALREADY_IN_MAKING;
            }
            city.addIncompleteUnit(city.getUnitInProgress());
        }

        Unit unit;
        // continue the half-built unit if possible
        unit = city.getIncompleteUnitByType(unitType);
        if (unit != null) {
            city.removeIncompleteUnit(unit);
        } else {
            if (unitType.combatType == CombatType.CIVILIAN) {
                unit = new Unit(null, GameController.getCurrentPlayer(), unitType);
            } else {
                unit = new Troop(null, GameController.getCurrentPlayer(), unitType);
            }
        }

        city.setUnitInProgress(unit);
        return InGameResponses.Unit.UNIT_BUILDING_SUCCESSFUL;
    }

    public static InGameResponses.Unit pauseInProgressUnit() {
        City city = GameController.getSelectedCity();
        Unit unit = city.getUnitInProgress();
        city.addIncompleteUnit(unit);
        return InGameResponses.Unit.UNIT_BUILDING_PAUSED;
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

    public static void growCity() {
        // TODO: 5/1/2022 may not even be necessary
    }

    public static InGameResponses.City buyTile(int row, int column) {
        Map map = GameController.getMap();
        City city = GameController.getSelectedCity();
        if (city == null) {
            return InGameResponses.City.NO_CITY_SELECTED;
        }
        Tile tile = GameController.getMap().getTile(row, column);
        if (tile == null) {
            return InGameResponses.City.LOCATION_NOT_VALID;
        }
        if (tile.getCity() != null) {
            return InGameResponses.City.TILE_ALREADY_BOUGHT;
        }
        boolean isClose = false;
        for (Tile neighbour : tile.getNeighbouringTiles(map)) {
            if (city.getTerritory().contains(neighbour)) {
                isClose = true;
            }
        }
        if (!isClose) {
            return InGameResponses.City.TILE_TOO_FAR;
        }
        tile.setCity(city);
        city.addTile(tile);
        city.getOwner().addTile(tile);
        return InGameResponses.City.BUY_SUCCESSFUL;
    }
}
