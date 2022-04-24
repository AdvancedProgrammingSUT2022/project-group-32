package Controller;

import Model.*;
import Model.Units.Unit;
import enums.FogState;
import enums.Responses.Response;

import java.util.ArrayList;

public class PlayerController {

    public static Response.GameMenu moveCamera(char direction, int value) {
        // gets current player from game
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    // overloading
    public static Response.GameMenu moveCamera(char direction) {
        return moveCamera(direction, 0);
    }


    public static Response.GameMenu setCameraByXY(int x, int y) {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static Response.GameMenu setCameraByCityName(String cityName) {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    // TODO: 4/17/2022 War declaration needs confirmation
    public static Response.GameMenu startWarWith(Player player) {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static Response.GameMenu endWarWith(Player player) {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static Response.GameMenu offerTrade(Player player, Trade trade) {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String citiesPanelInfo() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String dealsPanelInfo() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String demographicsPanelInfo() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String diplomaticPanelInfo() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String diplomacyPanelInfo() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String economyPanelInfo() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String militaryPanelInfo() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String notificationsPanelInfo() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String researchPanelInfo() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String unitsPanelInfo() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static String victoryPanelInfo() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static void startTurn(){
        // TODO: 4/23/2022 does the necessary stuff at the start of the turn
        Player player = GameController.getGame().getCurrentPlayer();
        for (Unit unit : player.getUnits()) {
            UnitController.moveToDestination(unit);
        }
    }

    public static void endTurn(){
        // TODO: 4/23/2022 does the necessary stuff at the end of the turn
        GameController.setSelectedCity(null);
        GameController.setSelectedUnit(null);
        GameController.setSelectedTile(null);
    }

    public static Response.GameMenu nextTurn(){
        endTurn();
        GameController.getGame().nextTurn();
        startTurn();
        return Response.GameMenu.TURN_PASSED;
    }

    // basically makes the visible tiles visited
    private static void clearView(Map map){
        for (int row = 0; row < map.getHeight(); row++) {
            for (int column =  0; column < map.getWidth(); column++){
                Tile tile = map.getTile(row, column);
                if(tile.getFogState() != FogState.UNKNOWN){
                    tile.setTroop(null);
                    tile.setUnit(null);
                    tile.setImprovement(null);
                }
            }
        }
    }

    public static void updateFieldOfView() {
        Player player = GameController.getGame().getCurrentPlayer();
        Map map = player.getMap();
        clearView(map);
        ArrayList<Tile> inSight = new ArrayList<>();
        for (Unit unit : player.getUnits()) {
            inSight.addAll(MapController.getTilesInRange(unit.getTile(), unit.getSightRange()));
        }
        for (City city : player.getCities()) {
            for (Tile tile : city.getTerritory()) {
                inSight.addAll(MapController.getTilesInRange(tile, city.getSightRange()));
            }
        }
        for (Tile tile : inSight) {
            map.setTile(tile.getRow(), tile.getColumn(), new Tile(tile));
        }
    }

    public static void updateSupplies() {
        // TODO: 4/17/2022
    }

    public static boolean checkIfLost() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static boolean checkIfWon() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

}
