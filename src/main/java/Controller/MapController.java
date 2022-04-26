package Controller;

import Model.Map;
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

    private static Tile nearestOcean(Tile tile1) {
        Map map = GameController.getMap();
        int height = map.getHeight(), width = map.getWidth();
        Tile tile2 = map.getTile(0, 0);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map.getTile(i, j).getTerrain().getTerrainType().equals(TerrainType.OCEAN)) {
                    if (map.getDistanceTo(tile1, map.getTile(i, j)) <= map.getDistanceTo(tile1, tile2)) {
                        tile2 = map.getTile(i, j);
                    }
                }
            }
        }
        return tile2;
    }

    private static void drawRiverFromTo(Tile tile1, Tile tile2) {
        Map map = GameController.getMap();
        Tile currentTile = map.getNextMoveTo(tile1, tile2);
        int lastDirection = (tile1.getDirectionTo(currentTile) + 6) % 12;
        while (currentTile != tile2) {
            Tile nextTile = map.getNextMoveTo(currentTile, tile2);
            int direction = currentTile.getDirectionTo(nextTile);
            lastDirection = (lastDirection + 2) % 12;
            while (lastDirection != direction) {
                currentTile.setRiverInDirection(lastDirection, 1); // river value is 1
                Tile oppositeTile = currentTile.getTileInDirection(map, lastDirection);
                oppositeTile.setRiverInDirection((lastDirection + 6) % 12, 1);
                lastDirection = (lastDirection + 2) % 12;
            }
            lastDirection = (lastDirection + 6) % 12;
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
                if (tile1.getTerrain().getTerrainType().equals(TerrainType.MOUNTAIN)) {
                    Tile tile2 = nearestOcean(tile1);
                    drawRiverFromTo(tile1, tile2);
                }
            }
        }
    }

    private static void surroundWithOcean(Map map, int height, int width) {
        Tile[][] tiles = map.getTiles();
        // surrounding the map with ocean
        for (int i = 0; i < height; i++) {
            tiles[i][0] = new Tile(i, 0, new Terrain(TerrainType.OCEAN, TerrainFeature.NULL, ResourceType.NULL), FogState.UNKNOWN, null);
            tiles[i][width - 1] = new Tile(i, width - 1, new Terrain(TerrainType.OCEAN, TerrainFeature.NULL, ResourceType.NULL), FogState.UNKNOWN, null);
        }
        for (int i = 0; i < width; i++) {
            tiles[0][i] = new Tile(0, i, new Terrain(TerrainType.OCEAN, TerrainFeature.NULL, ResourceType.NULL), FogState.UNKNOWN, null);
            tiles[height - 1][i] = new Tile(height - 1, i, new Terrain(TerrainType.OCEAN, TerrainFeature.NULL, ResourceType.NULL), FogState.UNKNOWN, null);
        }
    }

    private static ArrayList<TerrainType> getPossibleTerrains(ArrayList<Tile> neighbours){
        ArrayList<TerrainType> possibleTerrains = new ArrayList<>(Arrays.asList(TerrainType.values()));
        for (Tile tile : neighbours) {
            if (tile == null) continue;
            // increasing the chance of a tile being one of the neighbouring terrains
            possibleTerrains.add(tile.getTerrain().getTerrainType());
            if (!tile.getTerrainType().equals(TerrainType.OCEAN)) {
                possibleTerrains.add(tile.getTerrain().getTerrainType());
            }
        }
        for (Tile tile : neighbours) {
            if (tile == null) continue;
            // making the terrain logical
            if(tile.getTerrainType().equals(TerrainType.DESERT)){
                possibleTerrains.removeIf(TerrainType.SNOW::equals);
                possibleTerrains.removeIf(TerrainType.TUNDRA::equals);
            }
            if(tile.getTerrainType().equals(TerrainType.SNOW)){
                possibleTerrains.removeIf(TerrainType.DESERT::equals);
            }
            if(tile.getTerrainType().equals(TerrainType.TUNDRA)){
                possibleTerrains.removeIf(TerrainType.DESERT::equals);
            }
        }
        return possibleTerrains;
    }

    public static Map randomMap(int height, int width) {
        Map map = new Map(height, width);
        Tile[][] tiles = map.getTiles();

        surroundWithOcean(map, height, width);

        Random random = new Random(System.currentTimeMillis());
        for (int row = 1; row < height - 1; row++) {
            for (int column = 1; column < width - 1; column++) {
                // sets Terrain randomly

                ArrayList<TerrainType> possibleTerrains = getPossibleTerrains(map.getNeighbouringTiles(row, column));
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
                tiles[row][column] = new Tile(row, column, terrain, FogState.UNKNOWN, null); // TODO: 4/22/2022 do we have ruins?
            }
        }


        return map;
    }

    public static Response.GameMenu BuildCity() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

}
