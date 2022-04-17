package Controller;

import Model.*;
import Model.Units.Unit;

import java.util.ArrayList;

public class GameController {
    private static Game game;
    private static Unit selectedUnit;
    private static Tile selectedTile;
    private static City selectedCity;

    private static Game gameGenerator(ArrayList<User> users, int mapW, int mapH){

    }

    public static Unit getSelectedUnit() {
        return selectedUnit;
    }

    public static void setSelectedUnit(Unit selectedUnit) {
        GameController.selectedUnit = selectedUnit;
    }

    public static Tile getSelectedTile() {
        return selectedTile;
    }

    public static void setSelectedTile(Tile selectedTile) {
        GameController.selectedTile = selectedTile;
    }

    public static City getSelectedCity() {
        return selectedCity;
    }

    public static void setSelectedCity(City selectedCity) {
        GameController.selectedCity = selectedCity;
    }


}
