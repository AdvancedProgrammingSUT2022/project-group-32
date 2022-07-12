package Controller;

import Model.*;
import Model.Units.Troop;
import Model.Units.Unit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import enums.Responses.Response;
import enums.Types.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GameController {
    private static Game game;
    private static Unit selectedUnit;
    private static City selectedCity;

    private static void gameGenerator(ArrayList<Player> players, int mapH, int mapW) {
        Map randomMap = MapController.randomMap(mapH, mapW);
        while (!randomMap.isConnected()) {
            randomMap = MapController.randomMap(mapH, mapW);
        }
        game = new Game(randomMap, players);
        MapController.randomizeRivers();
    }

    public static Player getCurrentPlayer() {
        return game.getCurrentPlayer();
    }

    public static Map getCurrentPlayerMap() {
        return game.getCurrentPlayer().getMap();
    }

    public static Map getMap() {
        return game.getMap();
    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        GameController.game = game;
    }

    public static void saveGame() {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("src/main/resources/lastGame.json");
            fileWriter.write(new Gson().toJson(game));
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Saving game failed!!!");
            e.printStackTrace();
        }
    }

    public static void loadGame(){
        try {
            String json = new String(Files.readAllBytes(Paths.get("src/main/resources/lastGame.json")));
            game = new Gson().fromJson(json, new TypeToken<Game>(){}.getType());
            game.refillData();
        } catch (IOException e) {
            System.err.println("Error while loading game");
            e.printStackTrace();
        }
    }

    public static int getTurn() {
        return game.getTurnCount();
    }

    public static Unit getSelectedUnit() {
        return selectedUnit;
    }

    public static void setSelectedUnit(Unit selectedUnit) {
        GameController.selectedUnit = selectedUnit;
    }

    public static City getSelectedCity() {
        return selectedCity;
    }

    public static void setSelectedCity(City selectedCity) {
        GameController.selectedCity = selectedCity;
    }

    public static Response.GameMenu newGame(ArrayList<User> users) {
        Map mainMap = new Map(3, 3);

        ArrayList<Player> players = users.stream().map(user -> new Player(user, 1, 1)).collect(Collectors.toCollection(ArrayList::new));

        // TODO: initial gold, food, production, happiness, city population, .. must be set
        gameGenerator(players, users.size() * 5 + 5, users.size() * 5 + 5); // width and height chosen randomly

        PlayerController.initializePlayers(players);

        // starts a new game between users and responds accordingly
        return Response.GameMenu.GAME_CREATED;
    }

    public static Response.GameMenu selectCity(int row, int column) {
        City city = game.getMap().getTile(row, column).getCity();
        if (city == null) {
            return Response.GameMenu.NO_CITY_IN_TILE;
        }
        if (city.getCapitalTile() != game.getMap().getTile(row, column)) {
            return Response.GameMenu.NO_CITY_IN_TILE;
        }
        setSelectedUnit(null);
        setSelectedCity(city);
        return Response.GameMenu.CITY_SELECTED;
    }

    public static Response.GameMenu selectUnit(int row, int column) {
        Tile tile = game.getMap().getTile(row, column);
        if (tile.getUnit() == null) {
            return Response.GameMenu.NO_UNIT_IN_TILE;
        }
        setSelectedUnit(tile.getUnit());
        setSelectedCity(null);
        return Response.GameMenu.UNIT_SELECTED;
    }

    public static Response.GameMenu selectTroop(int row, int column) {
        Tile tile = game.getMap().getTile(row, column);
        if (tile.getTroop() == null) {
            return Response.GameMenu.NO_TROOP_IN_TILE;
        }
        setSelectedUnit(tile.getTroop());
        setSelectedCity(null);
        return Response.GameMenu.TROOP_SELECTED;
    }

    public static Response.GameMenu changeCamera(String direction, int amount) {
        Player player = game.getCurrentPlayer();
        int row = game.getCurrentPlayer().getCameraRow();
        int column = game.getCurrentPlayer().getCameraColumn();
        if (amount <= 0) return Response.GameMenu.AMOUNT_IS_NOT_POSITIVE;
        if (direction.equals("r") && column + amount <= game.getMap().getWidth()) {
            player.setCameraColumn(column + amount);
            return Response.GameMenu.SUCCESSFUL_MOVE;
        }
        if (direction.equals("l") && column - amount >= 0) {
            player.setCameraColumn(column - amount);
            return Response.GameMenu.SUCCESSFUL_MOVE;
        }
        if (direction.equals("u") && row - amount >= 0) {
            player.setCameraRow(row - amount);
            return Response.GameMenu.SUCCESSFUL_MOVE;
        }
        if (direction.equals("d") && row + amount <= game.getMap().getHeight()) {
            player.setCameraRow(row + amount);
            return Response.GameMenu.SUCCESSFUL_MOVE;
        }
        return Response.GameMenu.MOVEMENT_OUT_OF_RANGE;

    }

    // same as above basically overriding
    public static Response.GameMenu changeCamera(String direction) {
        return changeCamera(direction, 1);
    }

    public static void moveCameraToCity(City city) {
        GameController.getCurrentPlayer().setCamera(city.getCapitalTile().getRow(), city.getCapitalTile().getColumn());
    }
    ///         CHEAT CODES

    public static Response.GameMenu cheatEyeOfAgamotto(int amount) {
        while (amount-- > 0) {
            PlayerController.nextTurn();
        }
        return Response.GameMenu.CHEAT_SUCCESSFUL;
    }

    public static Response.GameMenu cheatIncreaseGold(int amount) {
        Player player = getCurrentPlayer();
        player.setGold(player.getGold() + amount);
        return Response.GameMenu.CHEAT_SUCCESSFUL;
        // increases the current gold of the player
    }

    public static Response.GameMenu cheatPutUnit(UnitType unitType, int row, int column) {
        Player player = getCurrentPlayer();
        Tile tile = getMap().getTile(row, column);
        Unit unit;
        if (unitType.combatType == CombatType.CIVILIAN) {
            unit = new Unit(tile, player, unitType);
        } else {
            unit = new Troop(tile, player, unitType);
        }
        unit.setRemainingCost(0);
        player.addUnit(unit);
        PlayerController.updateFieldOfView(player);
        return Response.GameMenu.CHEAT_SUCCESSFUL;
        // puts a unit in specified coordinates for free
    }

    public static void cheatBuildBuilding(BuildingType buildingType) {
        // builds a building in the selected city for free
    }

    public static Response.GameMenu cheatBuildCity(String name, int row, int column) {
        cheatPutUnit(UnitType.SETTLER, row, column);
        Tile tile = getMap().getTile(row, column);
        MapController.BuildCity(tile.getUnit(), name);
        getCurrentPlayer().addCity(tile.getCity());
        tile.getUnit().destroy();
        PlayerController.updateFieldOfView();
        return Response.GameMenu.CHEAT_SUCCESSFUL;
        // builds a city in the selected tile with the given name
    }

    public static Response.GameMenu cheatBuildImprovement(ImprovementType improvementType, int row, int column) {
        Tile tile = GameController.getMap().getTile(row, column);
        Improvement improvement = new Improvement(improvementType, tile);
        improvement.setRemainingTurns(0);
        tile.setImprovement(improvement);
        PlayerController.updateFieldOfView();
        return Response.GameMenu.CHEAT_SUCCESSFUL;
        // builds a finished improvement instantly on the selected tile
    }

    public static Response.GameMenu cheatClearLand(int row, int column) {
        Tile tile = GameController.getMap().getTile(row, column);
        if (Arrays.asList(TerrainFeature.FOREST, TerrainFeature.JUNGLE, TerrainFeature.MARSH).contains(tile.getTerrainFeature())) {
            tile.getTerrain().setTerrainFeature(TerrainFeature.NULL);
        }
        return Response.GameMenu.CHEAT_SUCCESSFUL;
        // removes annoying terrain features instantly
    }

    public static Response.GameMenu cheatResearchTech(TechnologyType technologyType) {
        Player player = getCurrentPlayer();
        Technology technology = new Technology(technologyType);
        technology.setRemainingCost(0);
        player.addTechnology(technology);
        return Response.GameMenu.CHEAT_SUCCESSFUL;
    }

    public static Response.GameMenu cheatInstantHeal(int health) {
        Unit unit = getSelectedUnit();
        if (unit == null) return Response.GameMenu.NO_UNIT_SELECTED;
        unit.setHP(health);
        return Response.GameMenu.CHEAT_SUCCESSFUL;
        // sets the HP of the selected unit to health
    }

    public static Response.GameMenu cheatRainWorker() {
        Map map = GameController.getMap();
        for (int row = 0; row < map.getHeight(); row++) {
            for (int column = 0; column < map.getWidth(); column++) {
                Tile tile = map.getTile(row, column);
                if (tile.getTerrainType() == TerrainType.OCEAN || tile.getTerrainType() == TerrainType.MOUNTAIN)
                    continue;
                if (tile.getUnit() == null) {
                    cheatPutUnit(UnitType.WORKER, row, column);
                }
            }
        }
        return Response.GameMenu.CHEAT_SUCCESSFUL;
    }

    public static Response.GameMenu eyeOfSauron() {
        Player player = getCurrentPlayer();
        Map map = getMap();
        Tile[][] tiles = player.getMap().getTiles();
        for (int row = 0; row < map.getHeight(); row++) {
            for (int column = 0; column < map.getWidth(); column++) {
                tiles[row][column] = new Tile(map.getTile(row, column));
                tiles[row][column].setFogState(FogState.VISIBLE);
            }
        }
        return Response.GameMenu.CHEAT_SUCCESSFUL;
        // makes the whole map visible to a player (temporarily)
    }

    public static void cheatBecomeAGod() {
        // basically sets every fucking thing to infinity :)

    }

    public static void cheatWin() {

    }

    public static void cheatLose() {

    }

    // TODO: 4/17/2022 there is a lot of cheat codes to be added

}
