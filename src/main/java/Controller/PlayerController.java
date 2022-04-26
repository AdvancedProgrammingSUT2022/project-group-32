package Controller;

import Model.*;
import Model.Units.Troop;
import Model.Units.Unit;
import enums.FogState;
import enums.Responses.Response;
import enums.TerrainType;
import enums.UnitType;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class PlayerController {

    public static void initializePlayers(ArrayList<Player> players){
        Game game = GameController.getGame();
        //setting camera to an initial tile
        for (Player player : players) {
            // fixme: initialTile conflict is possible
            int randomRow = ThreadLocalRandom.current().nextInt(0, game.getMap().getWidth());
            int randomColumn = ThreadLocalRandom.current().nextInt(0, game.getMap().getHeight());
            Tile initialTile = game.getMap().getTile(randomRow, randomColumn);
            while(initialTile.getTerrainType() == TerrainType.OCEAN || initialTile.getTerrainType() == TerrainType.MOUNTAIN){
                randomRow = ThreadLocalRandom.current().nextInt(0, game.getMap().getWidth());
                randomColumn = ThreadLocalRandom.current().nextInt(0, game.getMap().getHeight());
                initialTile = game.getMap().getTile(randomRow, randomColumn);
            }
            player.setCamera(initialTile); // setting camera to capital
            Unit unit = new Unit(initialTile, player, UnitType.SETTLER);
            Troop troop = new Troop(initialTile, player, UnitType.WARRIOR);
            initialTile.putUnit(unit);
            initialTile.putUnit(troop);
            player.addUnit(unit); // adding initial units
            player.addUnit(troop);
            player.setMap(new Map(game.getMap())); // deep copying map
            PlayerController.updateFieldOfView(player);
        }
    }

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
            unit.setMP(unit.getMovement());
            UnitController.moveToDestination(unit);
        }
        updateFieldOfView();
    }

    public static void endTurn(){
        // TODO: 4/23/2022 does the necessary stuff at the end of the turn
        GameController.setSelectedCity(null);
        GameController.setSelectedUnit(null);
        GameController.setSelectedTroop(null);
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
                    tile.setFogState(FogState.VISITED);
                }
                else {
                    tile.setTroop(null);
                    tile.setUnit(null);
                    tile.setImprovement(null);
                    tile.setTerrain(new Terrain(null, null, null));
                    tile.setCity(null);
                    tile.setRoadType(null);
                }
            }
        }
    }

    public static void updateFieldOfView(Player player) {
        Map map = player.getMap();
        /*map = new Map(GameController.getGame().getMap());
        return;*/
        clearView(map);
        ArrayList<Tile> inSight = new ArrayList<>();
        for (Unit unit : player.getUnits()) {
            inSight.addAll(MapController.lookAroundInRange(unit.getTile(), unit.getSightRange()));
        }
        for (City city : player.getCities()) {
            for (Tile tile : city.getTerritory()) {
                inSight.addAll(MapController.lookAroundInRange(tile, city.getSightRange()));
            }
        }
        for (Tile tile : inSight) {
            map.setTile(tile.getRow(), tile.getColumn(), new Tile(tile));
            map.getTile(tile.getRow(), tile.getColumn()).setFogState(FogState.VISIBLE);
        }
    }

    public static void updateFieldOfView(){
        Player player = GameController.getGame().getCurrentPlayer();
        updateFieldOfView(player);
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
