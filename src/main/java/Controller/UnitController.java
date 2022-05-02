package Controller;

import Model.Improvement;
import Model.Map;
import Model.Tile;
import Model.Units.Troop;
import Model.Units.Unit;
import enums.ImprovementType;
import enums.OrderType;
import enums.Responses.InGameResponses;
import enums.Responses.Response;
import enums.RouteType;
import enums.UnitType;
import jdk.jshell.execution.Util;

public class UnitController {
    static final int INF = 9999;

    // updates all there is about a unit accordingly
    public static void updateUnit(Unit unit){
        unit.setMP(unit.getMovement());
        UnitController.moveToDestination(unit);
        if(unit.getOrderType() == OrderType.BUILDING){
            Improvement improvement = unit.getTile().getImprovement();
            if(improvement != null){
                improvement.setRemainingTurns(improvement.getRemainingTurns() - 1);
                if(improvement.getRemainingTurns() <= 0){
                    unit.setOrderType(OrderType.AWAKE);
                }
            }
        }
        // TODO: 5/1/2022 health regeneration, garrison, fortify logic
    }
    // moves the selected unit to chosen destination
    public static void moveToDestination(Unit unit){
        if (unit.getDestination() == null || unit.getDestination() == unit.getTile()){
            return;
        }
        Map map = GameController.getMap();
        Tile destination = unit.getDestination();
        while (unit.getMP() > 0 && unit.getTile() != destination) {
            Tile currentTile = unit.getTile();
            Tile nextTile = map.getNextMoveTo(currentTile, destination);
            if(nextTile.getMP(currentTile) > unit.getMP() && !nextTile.canFit(unit)){
                System.err.println("The path is blocked");
                for (Tile tile : currentTile.getNeighbouringTiles(GameController.getGame().getMap())) {
                    if(map.getDistanceTo(currentTile, destination) < map.getDistanceTo(tile, destination)){
                        unit.placeIn(tile);
                        PlayerController.updateFieldOfView();
                        break;
                    }
                }
                break;
            }
            unit.placeIn(nextTile);
            PlayerController.updateFieldOfView();
        }
    }

    public static InGameResponses.Unit moveTo(int row, int column) {
        Unit unit = GameController.getSelectedUnitOrTroop();
        Map map = GameController.getMap();
        if (unit == null) {
            return InGameResponses.Unit.UNIT_NOT_AVAILABLE;
        }
        if (!unit.getOwner().equals(GameController.getGame().getCurrentPlayer())) {
            return InGameResponses.Unit.UNIT_NOT_IN_POSSESS;
        }
        if (map.getDistanceTo(unit.getTile(), map.getTile(row, column)) == INF) {
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
        Unit unit = GameController.getSelectedUnitOrTroop();
        if (unit == null) {
            return InGameResponses.Unit.NO_UNIT_SELECTED;
        }
        if (unit.getOwner() != GameController.getCurrentPlayer()){
            return InGameResponses.Unit.UNIT_NOT_IN_POSSESS;
        }
        unit.setOrderType(OrderType.ASLEEP);
        return InGameResponses.Unit.SLEEP_SUCCESSFUL;
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

    public static InGameResponses.Unit foundCity(String name) {
        // only for settlers
        Unit unit = GameController.getSelectedUnitOrTroop();
        if (unit == null) {
            return InGameResponses.Unit.NO_UNIT_SELECTED;
        }
        if(unit.getOwner() != GameController.getCurrentPlayer()){
            return InGameResponses.Unit.UNIT_NOT_IN_POSSESS;
        }
        if(unit.getUnitType() != UnitType.SETTLER){
            return InGameResponses.Unit.UNIT_NOT_A_SETTLER;
        }
        if(unit.getTile().getCity() != null){
            return InGameResponses.Unit.CITY_FOUNDATION_NOT_POSSIBLE;
        }
        MapController.BuildCity(unit, name);
        unit.destroy();
        GameController.setSelectedUnit(null);
        PlayerController.updateFieldOfView(unit.getOwner());
        return InGameResponses.Unit.FOUND_SUCCESSFUL;

    }

    public static InGameResponses.Unit cancelOrder() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static InGameResponses.Unit wake() {
        Unit unit = GameController.getSelectedUnitOrTroop();
        if (unit == null) {
            return InGameResponses.Unit.NO_UNIT_SELECTED;
        }
        if (unit.getOwner() != GameController.getCurrentPlayer()){
            return InGameResponses.Unit.UNIT_NOT_IN_POSSESS;
        }
        unit.setOrderType(OrderType.AWAKE);
        return InGameResponses.Unit.WAKE_SUCCESSFUL;
    }

    public static InGameResponses.Unit delete() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static InGameResponses.Unit buildImprovement(ImprovementType improvementType) {
        Unit unit = GameController.getSelectedUnit();
        if (unit == null) {
            return InGameResponses.Unit.NO_UNIT_SELECTED;
        }
        if(unit.getOwner() != GameController.getCurrentPlayer()){
            return InGameResponses.Unit.UNIT_NOT_IN_POSSESS;
        }
        if(unit.getUnitType() != UnitType.WORKER){
            return InGameResponses.Unit.UNIT_NOT_A_WORKER;
        }
        Tile tile = unit.getTile();
        if(!improvementType.canBeOn.contains(tile.getBaseFeature()) && !improvementType.canBeOn.contains(tile.getTerrainFeature())){
            return InGameResponses.Unit.BUILDING_NOT_POSSIBLE;
        }
        if(tile.getImprovement().getImprovementType() == improvementType){
            if(tile.getImprovement().getRemainingTurns() <= 0){
                return InGameResponses.Unit.IMPROVEMENT_ALREADY_EXISTS;
            } else {
                unit.setOrderType(OrderType.BUILDING);
                unit.setMP(0);
                return InGameResponses.Unit.CONTINUING_BUILDING;
            }
        }
        Improvement improvement = new Improvement(improvementType, tile);
        unit.setOrderType(OrderType.BUILDING);
        unit.setMP(0);
        unit.getTile().setImprovement(improvement);
        return InGameResponses.Unit.BUILD_SUCCESSFUL;
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
