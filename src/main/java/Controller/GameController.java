package Controller;

import Model.*;
import Model.Units.Troop;
import Model.Units.Unit;
import enums.BuildingType;
import enums.Responses.Response;
import enums.UnitType;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class GameController {
    private static Game game;
    // fixme: each player has below selecteds or the whole game?
    private static Unit selectedUnit;
    private static Tile selectedTile;
    private static City selectedCity;

    private static Game gameGenerator(ArrayList<User> users, int mapW, int mapH) {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static Map getCurrentPlayerMap() {
        return game.getCurrentPlayer().getMap();
    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game game) {
        GameController.game = game;
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

    public static Response.GameMenu newGame(ArrayList<User> users) {
        Map mainMap = new Map(3, 3);
        ArrayList<Player> players = users.stream().map(user -> new Player(user, 1, 1)).collect(Collectors.toCollection(ArrayList::new));
        // TODO: initial gold, food, production, happiness, city population, .. must be set
        setGame(new Game(mainMap, players));
        MapController.generateRandomMap(game.getMap(), game.getPlayers());
        //setting camera to city
        for (Player player : players) {
            // fixme: initialTile conflict is possible
            int randomRow = ThreadLocalRandom.current().nextInt(0, game.getMap().getWidth());
            int randomColumn = ThreadLocalRandom.current().nextInt(0, game.getMap().getHeight());
            Tile initialTile = game.getMap().getTile(randomRow, randomColumn);
            player.setCamera(initialTile); // setting camera to capital
            player.setMap(new Map(game.getMap())); // deep copying map
            player.addUnit(new Unit(initialTile, player, UnitType.SETTLER)); // adding initial units
            player.addUnit(new Troop(initialTile, player, UnitType.WARRIOR));
        }
        // starts a new game between users and responds accordingly
        return Response.GameMenu.GAME_CREATED;
    }

    public static Response.GameMenu selectTile(int x, int y) {
        // selects the x,y tile (unselects everything else)
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static Response.GameMenu selectCity(int x, int y) {
        // selects the city on the x,y tile (unselects everything else)
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static Response.GameMenu selectUnit(int x, int y) {
        // selects the unarmed unit on x,y
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    public static Response.GameMenu selectTroop(int x, int y) {
        // selects the armed unit on x,y
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");

    }

    ///         CHEAT CODES

    public static void cheatIncreaseTurn(int amount) {

    }

    public static void cheatIncreaseMoney(int amount) {

    }

    public static void cheatPutUnit(UnitType unitType) {
        // puts a unit on a selected tile for free if possible(?)
    }

    public static void cheatBuildBuilding(BuildingType buildingType) {
        // builds a building in the selected city for free
    }

    public static void cheatBuildCity(String name) {
        // builds a city in the selected tile with the given name
    }

    public static void cheatManipulateUnit(int health, int strength) {
        // sets the health and strength of the selected unit

    }

    // TODO: 4/17/2022 there is a lot of cheat codes to be added

    public static void cheatBecomeAGod() {
        // basically sets every fucking thing to infinity :)

    }

    public static void cheatWin() {

    }

    public static void cheatLose() {
    }

}
