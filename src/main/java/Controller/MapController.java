package Controller;

import Model.Map;
import Model.Player;
import Model.Terrain;
import Model.Tile;
import enums.FogState;
import enums.ResourceType;
import enums.Responses.Response;
import enums.TerrainFeature;
import enums.TerrainType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MapController {
    private static final int INF = 9999;

    private static Tile nearestOcean(Tile tile1){
        Map map = GameController.getGame().getMap();
        int height = map.getHeight(), width = map.getWidth();
        Tile tile2 = map.getTile(0, 0);
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if(map.getTile(i, j).getTerrain().getTerrainType().equals(TerrainType.OCEAN)){
                    if(getDistanceTo(tile1, map.getTile(i, j)) <= getDistanceTo(tile1, tile2)){
                        tile2 = map.getTile(i, j);
                    }
                }
            }
        }
        return tile2;
    }

    private static void drawRiverFromTo(Tile tile1, Tile tile2){
        Map map = GameController.getGame().getMap();
        Tile currentTile = getNextMoveTo(tile1, tile2);
        int lastDirection = (tile1.getDirectionTo(currentTile) + 6) % 12;
        while(currentTile != tile2){
            Tile nextTile = getNextMoveTo(currentTile, tile2);
            int direction = currentTile.getDirectionTo(nextTile);
            lastDirection = (lastDirection + 2) % 12;
            while(lastDirection != direction){
                currentTile.setRiverInDirection(lastDirection, 1); // river value is 1
                Tile oppositeTile = currentTile.getTileInDirection(map, lastDirection);
                oppositeTile.setRiverInDirection((lastDirection + 6) % 2, 1);
                lastDirection = (lastDirection + 2) % 12;
            }
            currentTile = nextTile;
        }
    }

    public static void randomizeRivers() {
        Map map = GameController.getGame().getMap();
        Tile[][] tiles = map.getTiles();
        int width = map.getWidth(), height = map.getHeight();
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                Tile tile1 = tiles[row][column];
                if(tile1.getTerrain().getTerrainType().equals(TerrainType.MOUNTAIN)){
                    Tile tile2 = nearestOcean(tile1);
                    drawRiverFromTo(tile1, tile2);
                }
            }
        }
    }

    public static Map randomMap(int height, int width) {
        Map map = new Map(height, width);
        Tile[][] tiles = map.getTiles();
        // surrounding the map with ocean
        for (int i = 0; i < height; i++) {
            tiles[i][0] = new Tile(i, 0, new Terrain(TerrainType.OCEAN, TerrainFeature.NULL, ResourceType.NULL), FogState.VISIBLE, null);
            tiles[i][width - 1] = new Tile(i, width - 1, new Terrain(TerrainType.OCEAN, TerrainFeature.NULL, ResourceType.NULL), FogState.VISIBLE, null);
        }
        for (int i = 0; i < width; i++) {
            tiles[0][i] = new Tile(0, i, new Terrain(TerrainType.OCEAN, TerrainFeature.NULL, ResourceType.NULL), FogState.VISIBLE, null);
            tiles[height - 1][i] = new Tile(height - 1, i, new Terrain(TerrainType.OCEAN, TerrainFeature.NULL, ResourceType.NULL), FogState.VISIBLE, null);
        }

        Random random = new Random(System.currentTimeMillis());
        for (int row = 1; row < height - 1; row++) {
            for (int column = 1; column < width - 1; column++) {
                // sets Terrain randomly

                ArrayList<TerrainType> possibleTerrains = new ArrayList<>(Arrays.asList(TerrainType.values()));
                ArrayList<Tile> neighbours = map.getNeighbouringTiles(row, column);
                for (Tile tile : neighbours) {
                    if (tile == null) continue;
                    // increasing the chance of a tile being one of the neighbouring terrains
                    possibleTerrains.add(tile.getTerrain().getTerrainType());
                    if (!tile.getTerrain().getTerrainType().equals(TerrainType.OCEAN)) {
                        possibleTerrains.add(tile.getTerrain().getTerrainType());
                    }
                }
                TerrainType terrainType = possibleTerrains.get(random.nextInt(possibleTerrains.size()));

                TerrainFeature terrainFeature = TerrainFeature.NULL;
                if (random.nextInt(2) == 0) {
                    ArrayList<TerrainFeature> possibleTerrainFeatures = terrainType.possibleTerrainFeatures;
                    if (!possibleTerrainFeatures.isEmpty()) {
                        terrainFeature = possibleTerrainFeatures.get(random.nextInt(possibleTerrainFeatures.size()));
                    }
                }

                ResourceType resourceType = ResourceType.NULL;
                if (random.nextInt(4) == 0) {
                    ArrayList<ResourceType> possibleResources = terrainType.baseFeature.possibleResources;
                    if (terrainFeature != null) possibleResources.addAll(terrainFeature.possibleResources);
                    if (!possibleResources.isEmpty()) {
                        resourceType = possibleResources.get(random.nextInt(possibleResources.size()));
                    }
                }

                Terrain terrain = new Terrain(terrainType, terrainFeature, resourceType);
                tiles[row][column] = new Tile(row, column, terrain, FogState.VISIBLE, null); // TODO: 4/22/2022 do we have ruins?
            }
        }


        return map;
    }

    public static int getDistanceTo(Tile start, Tile finish) {
        Map map = GameController.getGame().getMap();
        int height = map.getHeight(), width = map.getWidth();
        // uses Dijkstra
        int[][] distance = new int[height][width];
        boolean[][] marked = new boolean[height][width];
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                distance[row][column] = INF + INF;
                marked[row][column] = false;
            }
        }
        distance[start.getRow()][start.getColumn()] = 0;
        for (int t = 0; t < width * height; t++) {
            Tile tile1 = null;
            int minDistance = INF + INF;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (!marked[i][j] && distance[i][j] <= minDistance) {
                        minDistance = distance[i][j];
                        tile1 = map.getTile(i, j);
                    }
                }
            }
            ArrayList<Tile> neighbours = map.getNeighbouringTiles(tile1.getRow(), tile1.getColumn()); // won't cause RT error
            for (Tile tile2 : neighbours) {
                if (minDistance + tile2.getMP(tile1) < distance[tile2.getRow()][tile2.getColumn()]) {
                    distance[tile2.getRow()][tile2.getColumn()] = minDistance + tile2.getMP(tile1); // TODO: stoppages to be handled
                }
            }
            marked[tile1.getRow()][tile1.getColumn()] = true;
        }
        return distance[finish.getRow()][finish.getColumn()];
    }

    public static Tile getNextMoveTo(Tile start, Tile finish) {
        if(start.equals(finish)){
            return start;
        }
        Map map = GameController.getGame().getMap();
        int height = map.getHeight(), width = map.getWidth();
        // uses Dijkstra
        int[][] distance = new int[height][width];
        boolean[][] marked = new boolean[height][width];
        Tile[][] parent = new Tile[height][width];
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                distance[row][column] = INF + INF;
                marked[row][column] = false;
            }
        }
        distance[start.getRow()][start.getColumn()] = 0;
        for (int t = 0; t < width * height; t++) {
            Tile tile1 = null;
            int minDistance = INF + INF;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (!marked[i][j] && distance[i][j] <= minDistance) {
                        minDistance = distance[i][j];
                        tile1 = map.getTile(i, j);
                    }
                }
            }
            ArrayList<Tile> neighbours = map.getNeighbouringTiles(tile1.getRow(), tile1.getColumn()); // won't cause RT error
            for (Tile tile2 : neighbours) {
                if (minDistance + tile2.getMP(tile1) < distance[tile2.getRow()][tile2.getColumn()]) {
                    distance[tile2.getRow()][tile2.getColumn()] = minDistance + tile2.getMP(tile1); // TODO: stoppages to be handled
                    parent[tile2.getRow()][tile2.getColumn()] = tile1;
                }
            }
            marked[tile1.getRow()][tile1.getColumn()] = true;
        }
        Tile currentTile = finish;
        while(true){
            if(parent[currentTile.getRow()][currentTile.getColumn()].equals(start)){
                return currentTile;
            }
            currentTile = parent[currentTile.getRow()][currentTile.getColumn()];
        }
    }

    public static ArrayList<Tile> getTilesInRange(Tile tile, int range) {
        Map map = GameController.getGame().getMap();
        int height = map.getHeight(), width = map.getWidth();
        ArrayList<Tile> tiles = new ArrayList<>();
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (getDistanceTo(tile, map.getTile(row, column)) <= range) {
                    tiles.add(map.getTile(row, column));
                }
            }
        }
        return tiles;
    }

    public static Response.GameMenu BuildCity() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

}
