package Controller;

import Model.City;
import Model.Tile;
import enums.BuildingType;
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

    public static void buildBuilding(BuildingType buildingType) {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static void pauseBuilding() {
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
