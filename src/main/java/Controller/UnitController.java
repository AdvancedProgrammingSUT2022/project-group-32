package Controller;

import Model.Improvement;
import Model.Map;
import Model.Road;
import Model.Tile;
import Model.Units.Troop;
import Model.Units.Unit;
import enums.*;
import enums.Responses.InGameResponses;

import java.util.Arrays;

public class UnitController {
    static final int INF = 9999;

    // updates all there is about a unit accordingly
    public static void updateUnit(Unit unit) {
        unit.setMP(unit.getMovement());
        UnitController.moveToDestination(unit);
        if (unit.getOrderType() == OrderType.BUILDING) {
            Improvement improvement = unit.getTile().getImprovement();
            if (improvement != null) {
                improvement.setRemainingTurns(improvement.getRemainingTurns() - 1);
                if (improvement.getRemainingTurns() <= 0) {
                    unit.setOrderType(OrderType.AWAKE);
                }
            }
        }
        if (unit.getOrderType() == OrderType.ROAD_BUILDING) {
            Road road = unit.getTile().getRoad();
            if (road != null) {
                road.setRemainingTurns(road.getRemainingTurns() - 1);
                if (road.getRemainingTurns() <= 0) {
                    unit.setOrderType(OrderType.AWAKE);
                }
            }
        }
        if (Arrays.asList(OrderType.ALERT, OrderType.FORTIFY).contains(unit.getOrderType())) {
            if (((Troop) unit).getFortifyBonus() < 50) {
                ((Troop) unit).setFortifyBonus(((Troop) unit).getFortifyBonus() + 25);
            }
        }
        if (unit.getOrderType() == OrderType.ALERT) {
            for (Tile tile : GameController.getMap().lookAroundInRange(unit.getTile(), unit.getSightRange())) {
                if (tile.getTroop() != null || tile.getUnit() != null) {
                    unit.setOrderType(OrderType.AWAKE);
                }
            }
        }
        if (unit.getOrderType() == OrderType.HEAL) {
            unit.setHP(unit.getHP() + 1);
            if (unit.getHP() == 10) {
                unit.setOrderType(OrderType.AWAKE);
            }
        }
    }

    // moves the selected unit to chosen destination
    public static void moveToDestination(Unit unit) {
        if (unit.getDestination() == null || unit.getDestination() == unit.getTile()) {
            unit.setOrderType(OrderType.AWAKE);
            return;
        }
        if (unit instanceof Troop) ((Troop) unit).setFortifyBonus(0); // setting fortify bonus to 0 when moving
        Map map = GameController.getMap();
        Tile destination = unit.getDestination();
        while (unit.getMP() > 0 && unit.getTile() != destination) {
            Tile currentTile = unit.getTile();
            Tile nextTile = map.getNextMoveTo(currentTile, destination);
            if (nextTile.getMP(currentTile) > unit.getMP() && !nextTile.canFit(unit)) {
                System.err.println("The path is blocked");
                for (Tile tile : currentTile.getNeighbouringTiles(GameController.getGame().getMap())) {
                    if (map.getDistanceTo(currentTile, destination) < map.getDistanceTo(tile, destination)) {
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
        if (unit.getDestination() == unit.getTile()) {
            unit.setOrderType(OrderType.AWAKE);
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
        if (!map.getTile(row, column).canFit(unit)) {
            return InGameResponses.Unit.TILE_IS_FILLED;
        }
        unit.setDestination(map.getTile(row, column));
        unit.setOrderType(OrderType.MOVING);
        moveToDestination(unit);
        return InGameResponses.Unit.MOVETO_SUCCESSFUL;
    }

    // these functions should affect isDone
    public static InGameResponses.Unit sleep() {
        Unit unit = GameController.getSelectedUnitOrTroop();
        if (unit == null) {
            return InGameResponses.Unit.NO_UNIT_SELECTED;
        }
        if (unit.getOwner() != GameController.getCurrentPlayer()) {
            return InGameResponses.Unit.UNIT_NOT_IN_POSSESS;
        }
        if (unit.getMP() <= 0) {
            return InGameResponses.Unit.UNIT_IS_TIRED;
        }
        unit.setOrderType(OrderType.ASLEEP);
        return InGameResponses.Unit.SLEEP_SUCCESSFUL;
    }

    public static InGameResponses.Unit alert() {
        Unit unit = GameController.getSelectedUnitOrTroop();
        if (unit == null) {
            return InGameResponses.Unit.NO_UNIT_SELECTED;
        }
        if (unit.getOwner() != GameController.getCurrentPlayer()) {
            return InGameResponses.Unit.UNIT_NOT_IN_POSSESS;
        }
        if (unit.getMP() <= 0) {
            return InGameResponses.Unit.UNIT_IS_TIRED;
        }
        if (!UnitType.getUnitsByCombatType(CombatType.RANGED, CombatType.MELEE).contains(unit.getUnitType())) {
            return InGameResponses.Unit.UNIT_CANT_FORTIFY;
        }
        unit.setOrderType(OrderType.ALERT);
        return InGameResponses.Unit.ALERT_SUCCESSFUL;
    }

    public static InGameResponses.Unit fortify() {
        Unit unit = GameController.getSelectedUnitOrTroop();
        if (unit == null) {
            return InGameResponses.Unit.NO_UNIT_SELECTED;
        }
        if (unit.getOwner() != GameController.getCurrentPlayer()) {
            return InGameResponses.Unit.UNIT_NOT_IN_POSSESS;
        }
        if (unit.getMP() <= 0) {
            return InGameResponses.Unit.UNIT_IS_TIRED;
        }
        if (!UnitType.getUnitsByCombatType(CombatType.RANGED, CombatType.MELEE).contains(unit.getUnitType())) {
            return InGameResponses.Unit.UNIT_CANT_FORTIFY;
        }
        unit.setOrderType(OrderType.FORTIFY);
        return InGameResponses.Unit.FORTIFY_SUCCESSFUL;
    }

    public static InGameResponses.Unit heal() {
        Unit unit = GameController.getSelectedUnitOrTroop();
        if (unit == null) {
            return InGameResponses.Unit.NO_UNIT_SELECTED;
        }
        if (unit.getOwner() != GameController.getCurrentPlayer()) {
            return InGameResponses.Unit.UNIT_NOT_IN_POSSESS;
        }
        if (unit.getMP() <= 0) {
            return InGameResponses.Unit.UNIT_IS_TIRED;
        }
        unit.setOrderType(OrderType.HEAL);
        return InGameResponses.Unit.HEAL_SUCCESSFUL;
    }

    public static InGameResponses.Unit setup() {
        // only for siege troops
        Unit unit = GameController.getSelectedUnitOrTroop();
        if (unit == null) {
            return InGameResponses.Unit.NO_UNIT_SELECTED;
        }
        if (unit.getOwner() != GameController.getCurrentPlayer()) {
            return InGameResponses.Unit.UNIT_NOT_IN_POSSESS;
        }
        if (unit.getMP() <= 0) {
            return InGameResponses.Unit.UNIT_IS_TIRED;
        }
        if (!UnitType.getUnitsByCombatType(CombatType.SIEGE).contains(unit.getUnitType())) {
            return InGameResponses.Unit.UNIT_NOT_SIEGE;
        }
        unit.setOrderType(OrderType.SETUP);
        unit.setMP(0);
        return InGameResponses.Unit.SETUP_SUCCESSFUL;
    }

    public static InGameResponses.Unit attack(int x, int y) {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
        // uses combat controller if needed
        // remember to make fortify bonus equal to 0
    }

    public static InGameResponses.Unit foundCity(String name) {
        // only for settlers
        Unit unit = GameController.getSelectedUnitOrTroop();
        if (unit == null) {
            return InGameResponses.Unit.NO_UNIT_SELECTED;
        }
        if (unit.getOwner() != GameController.getCurrentPlayer()) {
            return InGameResponses.Unit.UNIT_NOT_IN_POSSESS;
        }
        if (unit.getMP() <= 0) {
            return InGameResponses.Unit.UNIT_IS_TIRED;
        }
        if (unit.getUnitType() != UnitType.SETTLER) {
            return InGameResponses.Unit.UNIT_NOT_A_SETTLER;
        }
        if (unit.getTile().getCity() != null) {
            return InGameResponses.Unit.CITY_FOUNDATION_NOT_POSSIBLE;
        }
        MapController.BuildCity(unit, name);
        unit.destroy();
        GameController.setSelectedUnit(null);
        PlayerController.updateFieldOfView(unit.getOwner());
        return InGameResponses.Unit.FOUND_SUCCESSFUL;
    }

    public static InGameResponses.Unit cancelOrder() {
        Unit unit = GameController.getSelectedUnitOrTroop();
        if (unit == null) {
            return InGameResponses.Unit.NO_UNIT_SELECTED;
        }
        if (unit.getOwner() != GameController.getCurrentPlayer()) {
            return InGameResponses.Unit.UNIT_NOT_IN_POSSESS;
        }
        unit.setOrderType(OrderType.AWAKE);
        return InGameResponses.Unit.CANCEL_SUCCESSFUL;
    }

    public static InGameResponses.Unit wake() {
        Unit unit = GameController.getSelectedUnitOrTroop();
        if (unit == null) {
            return InGameResponses.Unit.NO_UNIT_SELECTED;
        }
        if (unit.getOwner() != GameController.getCurrentPlayer()) {
            return InGameResponses.Unit.UNIT_NOT_IN_POSSESS;
        }
        if (unit.getMP() <= 0) {
            return InGameResponses.Unit.UNIT_IS_TIRED;
        }
        unit.setOrderType(OrderType.AWAKE);
        return InGameResponses.Unit.WAKE_SUCCESSFUL;
    }

    public static InGameResponses.Unit delete() {
        Unit unit = GameController.getSelectedUnitOrTroop();
        if (unit == null) {
            return InGameResponses.Unit.NO_UNIT_SELECTED;
        }
        if (unit.getOwner() != GameController.getCurrentPlayer()) {
            return InGameResponses.Unit.UNIT_NOT_IN_POSSESS;
        }
        if (unit.getMP() <= 0) {
            return InGameResponses.Unit.UNIT_IS_TIRED;
        }
        unit.getOwner().setGold(unit.getOwner().getGold() + unit.getCost() / 10);
        unit.destroy();
        GameController.setSelectedUnit(null);
        GameController.setSelectedTroop(null);
        PlayerController.updateFieldOfView(unit.getOwner());
        return InGameResponses.Unit.DELETE_SUCCESSFUL;
    }

    public static InGameResponses.Unit buildImprovement(ImprovementType improvementType) {
        Unit unit = GameController.getSelectedUnit();
        if (unit == null) {
            return InGameResponses.Unit.NO_UNIT_SELECTED;
        }
        if (unit.getOwner() != GameController.getCurrentPlayer()) {
            return InGameResponses.Unit.UNIT_NOT_IN_POSSESS;
        }
        if (unit.getMP() <= 0) {
            return InGameResponses.Unit.UNIT_IS_TIRED;
        }
        if (unit.getUnitType() != UnitType.WORKER) {
            return InGameResponses.Unit.UNIT_NOT_A_WORKER;
        }
        if(improvementType == null){
            return InGameResponses.Unit.INVALID_IMPROVEMENT;
        }
        Tile tile = unit.getTile();
        if (!improvementType.canBeOn.contains(tile.getBaseFeature()) && !improvementType.canBeOn.contains(tile.getTerrainFeature())) {
            return InGameResponses.Unit.BUILDING_NOT_POSSIBLE;
        }
        if (tile.getImprovement().getImprovementType() == improvementType) {
            if (tile.getImprovement().getRemainingTurns() <= 0) return InGameResponses.Unit.IMPROVEMENT_ALREADY_EXISTS;
            else {
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

    public static InGameResponses.Unit buildRoad(RoadType roadType) {
        Unit unit = GameController.getSelectedUnit();
        if (unit == null) {
            return InGameResponses.Unit.NO_UNIT_SELECTED;
        }
        if (unit.getOwner() != GameController.getCurrentPlayer()) {
            return InGameResponses.Unit.UNIT_NOT_IN_POSSESS;
        }
        if (unit.getMP() <= 0) {
            return InGameResponses.Unit.UNIT_IS_TIRED;
        }
        if (unit.getUnitType() != UnitType.WORKER) {
            return InGameResponses.Unit.UNIT_NOT_A_WORKER;
        }
        if(roadType == null){
            return InGameResponses.Unit.INVALID_ROAD;
        }
        Tile tile = unit.getTile();
        if (tile.getRoadType() == RoadType.ROAD) {
            return InGameResponses.Unit.ROAD_ALREADY_EXISTS;
        }
        if (tile.getRoadType() == RoadType.RAILROAD) {
            return InGameResponses.Unit.RAILROAD_ALREADY_EXISTS;
        }
        Road road = new Road(roadType);
        tile.setRoad(road);
        unit.setOrderType(OrderType.ROAD_BUILDING);
        unit.setMP(0);
        return InGameResponses.Unit.BUILD_SUCCESSFUL;
    }

    public static InGameResponses.Unit removeForest() {
        Unit unit = GameController.getSelectedUnit();
        if (unit == null) {
            return InGameResponses.Unit.NO_UNIT_SELECTED;
        }
        if (unit.getOwner() != GameController.getCurrentPlayer()) {
            return InGameResponses.Unit.UNIT_NOT_IN_POSSESS;
        }
        if (unit.getMP() <= 0) {
            return InGameResponses.Unit.UNIT_IS_TIRED;
        }
        if (unit.getUnitType() != UnitType.WORKER) {
            return InGameResponses.Unit.UNIT_NOT_A_WORKER;
        }
        Tile tile = unit.getTile();
        if (tile.getTerrainFeature() != TerrainFeature.FOREST) {
            return InGameResponses.Unit.TILE_NOT_FOREST;
        }
        tile.getTerrain().setTerrainFeature(null); // note: deforestation takes 1 turn
        unit.setOrderType(OrderType.AWAKE);
        unit.setMP(0);
        return InGameResponses.Unit.REMOVE_SUCCESSFUL;
    }

    public static InGameResponses.Unit removeRoute() {
        Unit unit = GameController.getSelectedUnit();
        if (unit == null) {
            return InGameResponses.Unit.NO_UNIT_SELECTED;
        }
        if (unit.getOwner() != GameController.getCurrentPlayer()) {
            return InGameResponses.Unit.UNIT_NOT_IN_POSSESS;
        }
        if (unit.getMP() <= 0) {
            return InGameResponses.Unit.UNIT_IS_TIRED;
        }
        if (unit.getUnitType() != UnitType.WORKER) {
            return InGameResponses.Unit.UNIT_NOT_A_WORKER;
        }
        Tile tile = unit.getTile();
        if (tile.getRoadType() == null) {
            return InGameResponses.Unit.ROUTE_NOT_AVAILABLE;
        }
        tile.setRoadType(null); // note: deforestation takes 1 turn
        unit.setOrderType(OrderType.AWAKE);
        unit.setMP(0);
        return InGameResponses.Unit.REMOVE_SUCCESSFUL;
    }

    public static InGameResponses.Unit pillage() {
        Unit unit = GameController.getSelectedUnit();
        if (unit == null) {
            return InGameResponses.Unit.NO_UNIT_SELECTED;
        }
        if (unit.getOwner() != GameController.getCurrentPlayer()) {
            return InGameResponses.Unit.UNIT_NOT_IN_POSSESS;
        }
        if (unit.getMP() <= 0) {
            return InGameResponses.Unit.UNIT_IS_TIRED;
        }
        Tile tile = unit.getTile();
        if(tile.getCity() != null && tile.getCity().getOwner() == unit.getOwner()){
            return InGameResponses.Unit.OWN_IMPROVEMENT;
        }
        Improvement improvement;
        if ((improvement = tile.getImprovement()) != null) {
            Road road;
            if((road = tile.getRoad()) == null) return InGameResponses.Unit.NO_IMPROVEMENT;
            road.setRemainingTurns(Math.max(road.getRemainingTurns() + 1, 3));
        } // todo: can be implemented better
        improvement.setRemainingTurns(Math.max(improvement.getRemainingTurns() + 1, improvement.getRequiredTurns()));
        unit.setOrderType(OrderType.AWAKE);
        unit.setMP(0);
        return InGameResponses.Unit.PILLAGE_SUCCESSFUL;
    }

    public static InGameResponses.Unit repair() {
        Unit unit = GameController.getSelectedUnit();
        if (unit == null) {
            return InGameResponses.Unit.NO_UNIT_SELECTED;
        }
        if (unit.getOwner() != GameController.getCurrentPlayer()) {
            return InGameResponses.Unit.UNIT_NOT_IN_POSSESS;
        }
        if (unit.getMP() <= 0) {
            return InGameResponses.Unit.UNIT_IS_TIRED;
        }
        if (unit.getUnitType() != UnitType.WORKER) {
            return InGameResponses.Unit.UNIT_NOT_A_WORKER;
        }
        Tile tile = unit.getTile();
        if(tile.getImprovement() == null){
            if(tile.getRoad() == null) return InGameResponses.Unit.NO_IMPROVEMENT;
            unit.setOrderType(OrderType.ROAD_BUILDING);
            unit.setMP(0);
            return InGameResponses.Unit.REPAIR_SUCCESSFUL;
        }
        unit.setOrderType(OrderType.BUILDING);
        unit.setMP(0);
        return InGameResponses.Unit.REPAIR_SUCCESSFUL;
    }
}
