package Controller;

import Model.City;
import Model.Tile;
import Model.Units.Unit;

public class CombatController {
    public static void attackUnit(Unit unit) {

    }

    public static void attackCity(Unit unit) {

    }

    public static void attackTile(Tile tile) {
        // decides whether selected unit should attack a tile or city
    }

    public static void captureCity(City enemyCity) {
        Unit unit = GameController.getSelectedUnit();
    }

    public static void captureUnit(Unit enemyUnit) {

    }
}
