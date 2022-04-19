package Controller;

import Model.Units.Unit;
import enums.ImprovementType;
import enums.Responses.Response;
import enums.RouteType;

public class UnitController {
    public static Response.GameMenu moveTo(int x, int y) {
        // TODO: 4/17/2022 handle vision at the end
        // TODO: Handle MP
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static Response.GameMenu upgrade() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    // these functions should affect isDone
    public static Response.GameMenu sleep() {
        Unit unit = GameController.getSelectedUnit();
        if (unit == null) {

        }
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static Response.GameMenu alert() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static Response.GameMenu fortify() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static Response.GameMenu heal() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static Response.GameMenu garrison() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static Response.GameMenu setup() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
        // siege pre attack
    }

    public static Response.GameMenu attack(int x, int y) {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
        // uses combat controller if needed
    }

    public static Response.GameMenu foundCity() {
        // only for settlers
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static Response.GameMenu cancelOrder() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static Response.GameMenu wake() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static Response.GameMenu delete() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static Response.GameMenu buildImprovement(ImprovementType improvement) {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static Response.GameMenu buildRoad(RouteType roadType) {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static Response.GameMenu removeForest() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static Response.GameMenu removeRoute() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static Response.GameMenu repair() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }
}
