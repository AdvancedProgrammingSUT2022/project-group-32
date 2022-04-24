package View;

import Controller.GameController;
import Controller.PlayerController;
import Model.City;
import Model.Tile;
import View.Panels.*;
import enums.Color;
import enums.Responses.Response;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;

public class GameMenu extends Menu {
    public enum PanelType {
        CITIES_PANEL("cities", x -> CitiesPanel.run(x)),
        CITY_SELECTED_PANEL("citySelected", x -> CitySelectedPanel.run(x)),
        DEALS_PANEL("deals", x -> DealsPanel.run(x)),
        DEMOGRAPHICS_PANEL("demographics", x -> DemographicsPanel.run(x)),
        DIPLOMACY_PANEL("diplomacy", x -> DiplomacyPanel.run(x)),
        DIPLOMATIC_PANEL("diplomatic", x -> DiplomaticPanel.run(x)),
        ECONOMY_PANEL("economy", x -> EconomyPanel.run(x)),
        MILITARY_PANEL("military", x -> MilitaryPanel.run(x)),
        NOTIFICATIONS_PANEL("notifications", x -> NotificationsPanel.run(x)),
        RESEARCH_PANEL("research", x -> ResearchPanel.run(x)),
        UNIT_SELECTED_PANEL("unitSelected", x -> UnitSelectedPanel.run(x)),
        UNITS_PANEL("units", x -> UnitsPanel.run(x)),
        VICTORY_PANEL("victory", x -> VictoryPanel.run(x));

        String name;
        Consumer<String> function;

        PanelType(String name, Consumer<String> consumer){
            this.function = consumer;
            this.name = name;
        }
    }

    private static final PanelType currentPanel = null;

    public static void run(Scanner scanner) {
        String command;
        while (true) {
            command = scanner.nextLine();
            if (command.startsWith("show map")) {
                showMap(command);
            } else if (command.startsWith("move map")) {
                moveMap(command);
            } else if (command.startsWith("select unit")) {
                selectUnit(command);
            } else if (command.startsWith("select tile")) {
                selectTile(command);
            } else if (command.startsWith("select city")) {
                selectCity(command);
            } else if (command.startsWith("end game")) {
                endGame(command);
            } else if (command.startsWith("open panel")) {
                openPanel(command);
            } else if (command.startsWith("show current turn")) {
                showTurn(command);
            } else if (command.startsWith("pass turn")) {
                passTurn(command);
            } else if (command.startsWith("show current panel")) {
                showCurrentPanel(command);
            } else {
                runPanel(command);
            }
        }
    }

    private static void showMap(String command) {

        Tile[][] map = GameController.getCurrentPlayerMap().getTiles();
        String[][] stringMap = new String[map.length * 8 + 10][map[0].length * 12 + 100]; //fixme
        initMap(stringMap);
        fillMap(map, stringMap);
        printMap(stringMap);
    }

    private static void fillMap(Tile[][] map, String[][] stringMap) {
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                Tile tile = map[row][column];
                City city = map[row][column].getCity();
                fillAHex(stringMap, row, column, (city == null) ? Color.BLACK_BACKGROUND_BRIGHT.code : city.getOwner().getBackgroundColor().code, map[row][column]);
            }
        }
    }

    private static void printMap(String[][] map) {
        for (String[] strings : map) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }
    }

    private static void initMap(String[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = Color.BLUE_BACKGROUND.code + " " + Color.RESET.code;
            }
        }
    }

    public static void fillAHex(String[][] map, int tileRow, int tileColumn, String color, Tile tile) {
        int HORIZONTAL_BORDER = 8;
        int VERTICAL_BORDER = 8;
        // debug mode:
        int centerRow = VERTICAL_BORDER + tileRow * 7 - (tileColumn % 2) * 3;
        int centerColumn = HORIZONTAL_BORDER + (tileColumn) * 13;
        // final mode:
//        int centerRow = VERTICAL_BORDER + tileRow * 6 - (tileColumn % 2) * 3;
//        int centerColumn = HORIZONTAL_BORDER + (tileColumn) * 10;

        // TILE BACKGROUND
        fillPartOfRow(map, centerRow, centerColumn - 5, centerColumn + 5, color);
        fillPartOfRow(map, centerRow - 1, centerColumn - 4, centerColumn + 4, color);
        fillPartOfRow(map, centerRow + 1, centerColumn - 4, centerColumn + 4, color);
        fillPartOfRow(map, centerRow - 2, centerColumn - 3, centerColumn + 3, color);
        fillPartOfRow(map, centerRow + 2, centerColumn - 3, centerColumn + 3, color);

        // TILE COORDINATES
        if (tileRow > 9) map[centerRow - 1][centerColumn - 2] = sC("" + tileRow / 10, color);
        map[centerRow - 1][centerColumn - 1] = sC("" + tileRow % 10, color);
        map[centerRow - 1][centerColumn] = sC(",", color);
        if (tileColumn > 9) map[centerRow - 1][centerColumn + 1] = sC("" + tileColumn / 10, color);
        map[centerRow - 1][centerColumn + 2 - ((tileColumn > 9) ? 0 : 1)] = sC("" + tileColumn % 10, color);


        // UNIT
        if (tile.getUnit() != null) {
            map[centerRow][centerColumn - 1] = sCB(tile.getUnit().getUnitType().name().substring(0, 1), (tile.getUnit().getOwner().getColor()).code, color);
        }

        // RIVER
        {
            HashMap<Integer, Integer> isRiver = tile.getIsRiver();
            // UP-RIGHT
            for (int i = 0; i < 3; i++) {
                map[centerRow - 2 + i][centerColumn + 4 + i] = sC(" ", getRiverColor(isRiver.get(2)));
                map[centerRow - 2 + i][centerColumn + 5 + i] = sC(" ", getRiverColor(isRiver.get(2)));
            }
            // DOWN-RIGHT
            for (int i = 0; i < 3; i++) {
                map[centerRow + i][centerColumn + 6 - i] = sC(" ", getRiverColor(isRiver.get(4)));
                map[centerRow + i][centerColumn + 7 - i] = sC(" ", getRiverColor(isRiver.get(4)));
            }
            // UP-LEFT
            for (int i = 0; i < 3; i++) {
                map[centerRow - 2 + i][centerColumn - 4 - i] = sC(" ", getRiverColor(isRiver.get(10)));
                map[centerRow - 2 + i][centerColumn - 5 - i] = sC(" ", getRiverColor(isRiver.get(10)));
            }
            // DOWN-LEFT
            for (int i = 0; i < 3; i++) {
                map[centerRow + 2 - i][centerColumn - 4 - i] = sC(" ", getRiverColor(isRiver.get(4)));
                map[centerRow + 2 - i][centerColumn - 5 - i] = sC(" ", getRiverColor(isRiver.get(4)));
            }
            // UP
            fillPartOfRow(map, centerRow - 3, centerColumn - 3, centerColumn + 3, getRiverColor(isRiver.get(0)));
            // DOWN
            fillPartOfRow(map, centerRow + 3, centerColumn - 3, centerColumn + 3, getRiverColor(isRiver.get(6)));

            // RIGHT_JOINT
            if (isRiver.get(2) == 1 || isRiver.get(4) == 1)
                fillPartOfRow(map, centerRow, centerColumn + 6, centerColumn + 7, getRiverColor(1));
            // LEFT-JOINT
            if (isRiver.get(8) == 1 || isRiver.get(10) == 1)
                fillPartOfRow(map, centerRow, centerColumn - 6, centerColumn - 7, getRiverColor(1));
        }

        // TROOP
        if (tile.getTroop() != null) {
            map[centerRow][centerColumn + 1] = sCB(tile.getTroop().getUnitType().name().substring(0, 1), (tile.getUnit().getOwner().getColor()).code, color);
        }
        map[centerRow + 1][centerColumn - 1] = sC(tile.getTerrain().getTerrainType().name.substring(0, 1), Color.BLUE_BOLD_BRIGHT.code);
        map[centerRow + 1][centerColumn] = sC(",", Color.RED_BACKGROUND.code);
        map[centerRow + 1][centerColumn + 1] = sC(tile.getTerrain().getTerrainFeature().name.substring(0, 1), Color.RED_BACKGROUND.code);

    }

    private static void fillPartOfRow(String[][] map, int row, int startingColumn, int endingColumn, String color) {
        for (int column = startingColumn; column <= endingColumn; column++) {
            map[row][column] = sC(" ", color);
        }
    }

    private static String getRiverColor(Integer hasRiver) {
        return (hasRiver == 1) ? Color.BLUE_BACKGROUND_BRIGHT.code : Color.YELLOW_BACKGROUND.code;
    }

    private static void fillPartOfColumn(String[][] map, int column, int startingRow, int endingRow, String color) {
        for (int row = startingRow; row <= endingRow; row++) {
            map[row][column] = sC(" ", color);
        }
    }

    private static void fillDiag(String[][] map, int row1, int column1, int length) {
    }

    public static String sCB(String text, String color, String background) {
        return background + color + text + Color.RESET.code;
    }

    public static String sC(String text, String color) {
        return color + text + Color.RESET.code;
    }

    private static void moveMap(String command) {

    }

    private static void selectUnit(String command) {

    }

    private static void selectTile(String command) {

    }

    private static void selectCity(String command) {

    }

    private static void showTurn(String command){
        System.out.println(GameController.getGame().getCurrentPlayer().getName());
    }

    private static void passTurn(String command) {
        System.out.println(PlayerController.nextTurn().getString());
    }

    private static void endGame(String command) {

    }

    private static void openPanel(String command) {
        // to whoever implementing this . . .
        // cycle through panel enums and finding the right name
    }

    // supposed to run the current panel
    private static void runPanel(String command) {
        if(currentPanel == null){
            System.out.println(Response.GameMenu.INVALID_COMMAND.getString());
            return;
        }
        currentPanel.function.accept(command);
    }

    public static void showCurrentPanel(String command) {
        System.out.println(currentPanel.name);
    }
}
