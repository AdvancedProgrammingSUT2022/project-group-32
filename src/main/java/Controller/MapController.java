package Controller;

import Model.*;
import enums.FogState;
import enums.ResourceType;
import enums.Responses.Response;
import enums.TerrainFeature;
import enums.TerrainType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MapController {
    public static Map generateRandomMap(Map map, ArrayList<Player> players) {
        Tile[][] tiles = map.getTiles();
        // TODO: 4/21/2022 NOT RANDOM
        int rows = map.getHeight();
        int columns = map.getWidth();
        // Test map:
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                tiles[row][column] = new Tile(row, column, new Terrain(TerrainType.GRASSLAND, TerrainFeature.GRASSLAND, ResourceType.SHEEP),
                        FogState.VISIBLE, null);
            }
        }

        // assigning players' cities
        for (Player player : players) {
            int randomRow = ThreadLocalRandom.current().nextInt(0, rows);
            int randomColumn = ThreadLocalRandom.current().nextInt(0, columns);
            City city = new City(player.getName(), player, tiles[randomRow][randomColumn],
                    new ArrayList<>(List.of(tiles[randomRow][randomColumn])));
            tiles[randomRow][randomColumn].setCity(city);
            player.addCity(city);
            map.addCity(city);
        }

        // generates randomMap and assigns tile values
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static int getDistanceTo(Tile start, Tile finish) {
        Map map = GameController.getGame().getMap();
        // uses Dijkstra
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static Tile getNextMoveTo(Tile start, Tile finish) {
        // returns first move from start to finish
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static ArrayList<Tile> getTilesInRange(Tile tile, int range) {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static Response.GameMenu BuildCity() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

}
