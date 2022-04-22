package View;

import Controller.GameController;
import Model.City;
import Model.Tile;
import enums.Color;
import enums.Responses.Response;

import java.util.Scanner;

public class GameMenu extends Menu {
    public enum PanelType {
        CITY_SELECTED
    }

    private static final PanelType panelType = null;

    public static void run(Scanner scanner) {
        String command;
        while (true) {
            command = scanner.nextLine();
            if (command.startsWith("show map")){
                showMap(command);
            }
            else if (command.startsWith("move map")){
                moveMap(command);
            }
            else if (command.startsWith("select unit")){
                selectUnit(command);
            }
            else if (command.startsWith("select tile")){
                selectTile(command);
            }
            else if (command.startsWith("select city")){
                selectCity(command);
            }
            else if (command.startsWith("end game")){
                endGame(command);
            }
            else if (command.startsWith("open panel")){
                openPanel(command);
            }
            else if (command.startsWith("run panel")){
                runPanel(command);
            } else if (command.startsWith("show current panel")) {
                showCurrentPanel(command);
            } else {
                System.out.println(Response.LoginMenu.INVALID_COMMAND);
            }
        }
    }

    private static void showMap(String command) {

        Tile[][] map = GameController.getCurrentPlayerMap().getTiles();
        String[][] stringMap = new String[map.length * 7][map[0].length * 11];
        initMap(stringMap);
        fillMap(map, stringMap);
        printMap(stringMap);
    }

    private static void fillMap(Tile[][] map, String[][] stringMap) {
        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                Tile tile = map[row][column];
                City city = map[row][column].getCity();
//                fillAHex(stringMap, row, column, Color.RED_BACKGROUND.name());
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
        int centerRow = VERTICAL_BORDER + tileRow * 6 - (tileColumn % 2) * 3;
        int centerColumn = HORIZONTAL_BORDER + (tileColumn) * 9;
        for (int i = centerColumn - 5; i < centerColumn + 6; i++) {
            map[centerRow][i] = sC(" ", color);
            map[centerRow - 1][i] = sC(" ", color);
        }


        map[centerRow - 1][centerColumn - 1] = sC("" + tileRow, color);
        map[centerRow - 1][centerColumn] = sC(",", color);
        map[centerRow - 1][centerColumn + 1] = sC("" + tileColumn, color);

        for (int i = centerColumn - 4; i < centerColumn + 5; i++) {
            map[centerRow - 2][i] = sC(" ", color);
            map[centerRow + 1][i] = sC(" ", color);
        }
        for (int i = centerColumn - 3; i < centerColumn + 4; i++) {
            map[centerRow - 3][i] = sC(" ", color);
            map[centerRow + 2][i] = sC(" ", color);
        }
        // UNIT
        if (tile.getUnit() != null) {
            map[centerRow][centerColumn - 1] = sCB(tile.getUnit().getUnitType().name().substring(0, 1), (tile.getUnit().getOwner().getColor()).code, color);
        }

        // TROOP
        if (tile.getTroop() != null) {
            map[centerRow][centerColumn + 1] = sCB(tile.getTroop().getUnitType().name().substring(0, 1), (tile.getUnit().getOwner().getColor()).code, color);
        }
        map[centerRow + 1][centerColumn - 1] = sC(tile.getTerrain().getTerrainType().name.substring(0, 1), Color.RED_BACKGROUND.code);
        map[centerRow + 1][centerColumn] = sC(",", Color.RED_BACKGROUND.code);
        map[centerRow + 1][centerColumn + 1] = sC(tile.getTerrain().getTerrainFeature().name.substring(0, 1), Color.RED_BACKGROUND.code);

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

    private static void endGame(String command) {

    }

    private static void openPanel(String command) {

    }

    private static void runPanel(String command) {

    }

    public static void showCurrentPanel(String command) {
        System.out.println(panelType);
    }
}
