package Controller;

import Model.*;
import Model.Units.Troop;
import Model.Units.Unit;
import enums.BuildingType;
import enums.FogState;
import enums.Responses.Response;
import enums.TerrainType;
import enums.UnitType;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

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
        // TODO: 4/27/2022 decrease ramaining turns in in progress units, techs, buildings
        Player player = GameController.getGame().getCurrentPlayer();
        UpdateInProgressBuildsTurns();
        for (Unit unit : player.getUnits()) {
            unit.setMP(unit.getMovement());
            UnitController.moveToDestination(unit);
        }
        updateFieldOfView();
    }

    private static void UpdateInProgressBuildsTurns() {
        Player player = GameController.getGame().getCurrentPlayer();

        for (City city : player.getCities()) {
            // Building
            Building inProgressBuilding = city.getBuildingInProgress();
            if (inProgressBuilding != null) {
                inProgressBuilding.setRemainingTurns(inProgressBuilding.getRemainingTurns() - 1);
                if (inProgressBuilding.getRemainingTurns() == 0) {
                    city.addBuilding(inProgressBuilding);
                    city.setBuildingInProgress(null);
                    // todo: build finished popup and start new building popUp
                }
            }
            // Unit
            Unit inProgressUnit = city.getUnitInProgress();
            if (inProgressUnit != null) {
                inProgressUnit.setRemainingTurn(inProgressUnit.getRemainingTurn() - 1);
                if (inProgressUnit.getRemainingTurn() == 0) {
                    player.addUnit(inProgressUnit);
                    city.setUnitInProgress(null);
                    // TODO: new unit creation logic, unit build finished popUp and new buildRequired popUp
                }
            }
        }

        // Tech
        Technology inProgressTechnology = player.getTechnologyInProgress();
        if (inProgressTechnology != null) {
            inProgressTechnology.setRemainingTurns(inProgressTechnology.getRemainingTurns() - 1);
            if (inProgressTechnology.getRequiredTurns() == 0) {
                player.addTechnology(inProgressTechnology);
                player.setTechnologyInProgress(null);
                // TODO: 4/27/2022 tech fininsh popup and Logic, new build required
            }
        }

    }


    public static void endTurn() {
        // TODO: 4/23/2022 does the necessary stuff at the end of the turn
        GameController.setSelectedCity(null);
        GameController.setSelectedUnit(null);
        GameController.setSelectedTroop(null);
    }

    public static Response.GameMenu nextTurn() {
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
        Map gameMap = GameController.getMap();
        /*map = new Map(GameController.getGame().getMap());
        return;*/
        clearView(map);
        ArrayList<Tile> inSight = new ArrayList<>();
        for (Unit unit : player.getUnits()) {
            inSight.addAll(gameMap.lookAroundInRange(unit.getTile(), unit.getSightRange()));
        }
        for (City city : player.getCities()) {
            for (Tile tile : city.getTerritory()) {
                inSight.addAll(gameMap.lookAroundInRange(tile, city.getSightRange()));
            }
        }
        for (Tile tile : inSight) {
            map.setTile(tile.getRow(), tile.getColumn(), new Tile(tile));
            map.getTile(tile.getRow(), tile.getColumn()).setFogState(FogState.VISIBLE);
        }
    }

    public static void updateFieldOfView() {
        Player player = GameController.getGame().getCurrentPlayer();
        updateFieldOfView(player);
    }

    public static void updateSupplies() {
        // TODO: 4/17/2022
    }

    private static void updateGold() {
        // handles turn based coin changes - NOT HANDLING TRADES, BUYS, ...
        double goldChange = 0;
        for (City city : GameController.getGame().getCurrentPlayer().getCities()) {
            // todo: the building effects are applied to gross city gold production not net!, MINT EFFECT NOT APPLIED!
            double cityGrossGold = city.getTerritory().stream().mapToInt(Tile::getGold).sum();
            ArrayList<BuildingType> buildingTypes = city.getBuildings().stream().map(Building::getBuildingType).collect(Collectors.toCollection(ArrayList::new));
            if (buildingTypes.contains(BuildingType.MARKET)) cityGrossGold *= 1.25;
            if (buildingTypes.contains(BuildingType.BANK)) cityGrossGold *= 1.25;
            if (buildingTypes.contains(BuildingType.SATRAP_COURT)) cityGrossGold *= 1.5;
            if (buildingTypes.contains(BuildingType.STOCK_EXCHANGE)) cityGrossGold *= 1.33;
            for (Building building : city.getBuildings()) {
                cityGrossGold -= building.getCost();
            }
            goldChange += cityGrossGold;
        }
        // +: terrains, terrain Features, resources, buildings cost __ not handling route  and unit cost
    }

    public static boolean checkIfLost() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static boolean checkIfWon() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

}
