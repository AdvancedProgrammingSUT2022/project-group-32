package Controller;

import Model.Map;
import Model.Tile;
import Model.Units.Troop;
import Model.Units.Unit;
import enums.ImprovementType;
import enums.Responses.InGameResponses;
import enums.Responses.Response;
import enums.RouteType;
import enums.UnitType;

public class UnitController {
    static final int INF = 9999;

    public static void moveToDestination(Unit unit){
        if (unit.getDestination() == null || unit.getDestination() == unit.getTile()){
            return;
        }
        while (unit.getMP() > 0) {
            Tile currentTile = unit.getTile();
            Tile nextTile = MapController.getNextMoveTo(currentTile, unit.getDestination());
            if(nextTile.getMP(currentTile) > unit.getMP()){
                if(!nextTile.canFit(unit)){
                    System.err.println("The path is blocked");
                    break;
                }
            }
            currentTile.takeUnit(unit);
            nextTile.putUnit(unit);
            unit.setTile(nextTile);
            PlayerController.updateFieldOfView();
            unit.setMP(unit.getMP() - nextTile.getMP(currentTile));
        }
    }

    public static InGameResponses.Unit moveTo(int row, int column) {
        Unit unit = GameController.getSelectedUnit();
        Map map = GameController.getGame().getMap();
        if (unit == null) {
            return InGameResponses.Unit.UNIT_NOT_AVAILABLE;
        }
        if (!unit.getOwner().equals(GameController.getGame().getCurrentPlayer())) {
            return InGameResponses.Unit.UNIT_NOT_IN_POSSESS;
        }
        if (MapController.getDistanceTo(unit.getTile(), map.getTile(row, column)) == INF) {
            return InGameResponses.Unit.TILE_NOT_REACHABLE;
        }
        if (!map.getTile(row, column).canFit(unit)){
            return InGameResponses.Unit.TILE_IS_FILLED;
        }
        unit.setDestination(map.getTile(row, column));
        moveToDestination(unit);
        return InGameResponses.Unit.MOVETO_SUCCESSFUL;
    }

    public static InGameResponses.Unit upgrade() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    // these functions should affect isDone
    public static InGameResponses.Unit sleep() {
        Unit unit = GameController.getSelectedUnit();
        if (unit == null) {

        }
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static InGameResponses.Unit alert() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static InGameResponses.Unit fortify() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static InGameResponses.Unit heal() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static InGameResponses.Unit garrison() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static InGameResponses.Unit setup() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
        // siege pre attack
    }

    public static InGameResponses.Unit attack(int x, int y) {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
        // uses combat controller if needed
    }

    public static InGameResponses.Unit foundCity() {
        // only for settlers
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static InGameResponses.Unit cancelOrder() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static InGameResponses.Unit wake() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static InGameResponses.Unit delete() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static InGameResponses.Unit buildImprovement(ImprovementType improvement) {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static InGameResponses.Unit buildRoad(RouteType roadType) {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static InGameResponses.Unit removeForest() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static InGameResponses.Unit removeRoute() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static InGameResponses.Unit repair() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }
}
