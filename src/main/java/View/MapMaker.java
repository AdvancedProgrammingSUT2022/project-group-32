package View;

import Model.City;
import Model.Tile;
import enums.Color;

import java.util.HashMap;

public class MapMaker {
    private static final int HORIZONTAL_BORDER = 60;
    private static final int VERTICAL_BORDER = 15;

    public static String[][] getMap(Tile[][] map) {
        String[][] strMap = new String[map.length * 6 + VERTICAL_BORDER * 2][map[0].length * 10 + HORIZONTAL_BORDER * 2];
        initMap(strMap);
        fillMap(map, strMap);
        return strMap;
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

    private static void initMap(String[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = Color.BLUE_BACKGROUND.code + " " + Color.RESET.code;
            }
        }
    }

    public static void fillAHex(String[][] map, int tileRow, int tileColumn, String color, Tile tile) {

        // debug mode:
//        int centerRow = VERTICAL_BORDER + tileRow * 7 - (tileColumn % 2) * 3;
//        int centerColumn = HORIZONTAL_BORDER + (tileColumn) * 13;
        // final mode:
        int centerRow = getTileCenterRow(tileRow, tileColumn);
        int centerColumn = getTileCenterColumn(tileRow, tileColumn);

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
                map[centerRow + 2 - i][centerColumn - 4 - i] = sC(" ", getRiverColor(isRiver.get(8)));
                map[centerRow + 2 - i][centerColumn - 5 - i] = sC(" ", getRiverColor(isRiver.get(8)));
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
            map[centerRow][centerColumn + 1] = sCB(tile.getTroop().getUnitType().name().substring(0, 1), (tile.getTroop().getOwner().getColor()).code, color);
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

    public static String sCB(String text, String color, String background) {
        return background + color + text + Color.RESET.code;
    }

    public static String sC(String text, String color) {
        return color + text + Color.RESET.code;
    }

    public static int getTileCenterRow(int tileRow, int tileColumn) {
        int centerRow = VERTICAL_BORDER + tileRow * 6 - (tileColumn % 2) * 3;
        return centerRow;
    }

    public static int getTileCenterColumn(int tileRow, int tileColumn) {
        int centerColumn = HORIZONTAL_BORDER + (tileColumn) * 10;
        return centerColumn;
    }
}
