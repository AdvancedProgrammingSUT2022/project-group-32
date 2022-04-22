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

    public static Map RandomMap(ArrayList<Player> players, int height, int width) {
        Map map = new Map(height, width);
        Tile[][] tiles = map.getTiles();
        // Test map:
        // surrounding the map with ocean
        for(int i=0;i<height;i++){
            tiles[i][0] = new Tile(i, 0, new Terrain(TerrainType.OCEAN, TerrainFeature.NULL, ResourceType.NULL), FogState.VISIBLE, null);
            tiles[i][width - 1] =  new Tile(i, width - 1, new Terrain(TerrainType.OCEAN, TerrainFeature.NULL, ResourceType.NULL), FogState.VISIBLE, null);
        }
        for(int i=0;i<width;i++){
            tiles[0][i] = new Tile(0, i, new Terrain(TerrainType.OCEAN, TerrainFeature.NULL, ResourceType.NULL), FogState.VISIBLE, null);
            tiles[height - 1][i] =  new Tile(height - 1, i, new Terrain(TerrainType.OCEAN, TerrainFeature.NULL, ResourceType.NULL), FogState.VISIBLE, null);
        }

        Random random = new Random(System.currentTimeMillis());
        for (int row = 1; row < height-1; row++) {
            for (int column = 1; column < width-1; column++) {
                // sets Terrain randomly

                ArrayList<TerrainType> possibleTerrains = new ArrayList<>(Arrays.asList(TerrainType.values()));
                ArrayList<Tile> neighbours = map.getNeighbouringTiles(row, column);
                for (Tile tile : neighbours) {
                    if(tile != null){
                        // increasing the chance of a tile being one of the neighbouring terrains
                        possibleTerrains.add(tile.getTerrain().getTerrainType());
                        possibleTerrains.add(tile.getTerrain().getTerrainType());
                        possibleTerrains.add(tile.getTerrain().getTerrainType());
                    }
                }
                TerrainType terrainType = possibleTerrains.get(random.nextInt(possibleTerrains.size()));

                TerrainFeature terrainFeature = TerrainFeature.NULL;
                if(random.nextInt(2) == 0){
                    System.err.print("!");
                    ArrayList<TerrainFeature> possibleTerrainFeatures = terrainType.possibleTerrainFeatures;
                    if(!possibleTerrainFeatures.isEmpty()){
                        terrainFeature = possibleTerrainFeatures.get(random.nextInt(possibleTerrainFeatures.size()));
                    }
                }

                ResourceType resourceType = ResourceType.NULL;
                if(random.nextInt(4) == 0){
                    ArrayList<ResourceType> possibleResources = terrainType.baseFeature.possibleResources;
                    if(terrainFeature != null) possibleResources.addAll(terrainFeature.possibleResources);
                    if(!possibleResources.isEmpty()){
                        resourceType = possibleResources.get(random.nextInt(possibleResources.size()));
                    }
                }

                // TODO: 4/22/2022 rivers to be handled

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
        for(int row = 0; row < height; row++){
            for(int column = 0; column < width; column++){
                distance[row][column] = INF;
                marked[row][column] = false;
            }
        }
        distance[start.getRow()][start.getColumn()] = 0;
        for(int t = 0; t < width * height; t++){
            Tile tile1 = null;
            int minDistance = INF;
            for(int i=0;i<height;i++){
                for(int j=0;j<width;j++){
                    if(!marked[i][j] && distance[i][j] <= minDistance){
                        minDistance = distance[i][j];
                        tile1 = map.getTile(i, j);
                    }
                }
            }
            ArrayList<Tile> neighbours = map.getNeighbouringTiles(tile1.getRow(), tile1.getColumn()); // won't cause RT error
            for (Tile tile2 : neighbours) {
                if(minDistance + tile2.getMP() < distance[tile2.getRow()][tile2.getColumn()]){
                    distance[tile2.getRow()][tile2.getColumn()] = minDistance + tile2.getMP(); // TODO: rivers and stoppages to be handled
                }
            }
            marked[tile1.getRow()][tile1.getRow()] = true;
        }
        return distance[finish.getRow()][finish.getColumn()];
    }

    public static Tile getNextMoveTo(Tile start, Tile finish) {
        Map map = GameController.getGame().getMap();
        int height = map.getHeight(), width = map.getWidth();
        // uses Dijkstra
        int[][] distance = new int[height][width];
        boolean[][] marked = new boolean[height][width];
        Tile[][] parent = new Tile[height][width];
        for(int row = 0; row < height; row++){
            for(int column = 0; column < width; column++){
                distance[row][column] = INF;
                marked[row][column] = false;
            }
        }
        distance[start.getRow()][start.getColumn()] = 0;
        for(int t = 0; t < width * height; t++){
            Tile tile1 = null;
            int minDistance = INF;
            for(int i=0;i<height;i++){
                for(int j=0;j<width;j++){
                    if(!marked[i][j] && distance[i][j] <= minDistance){
                        minDistance = distance[i][j];
                        tile1 = map.getTile(i, j);
                    }
                }
            }
            ArrayList<Tile> neighbours = map.getNeighbouringTiles(tile1.getRow(), tile1.getColumn()); // won't cause RT error
            for (Tile tile2 : neighbours) {
                if(minDistance + tile2.getMP() < distance[tile2.getRow()][tile2.getColumn()]){
                    distance[tile2.getRow()][tile2.getColumn()] = minDistance + tile2.getMP(); // TODO: rivers and stoppages to be handled
                    parent[tile2.getRow()][tile2.getColumn()] = tile1;
                }
            }
            marked[tile1.getRow()][tile1.getRow()] = true;
        }
        return parent[finish.getRow()][finish.getColumn()];
    }

    public static ArrayList<Tile> getTilesInRange(Tile tile, int range) {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

    public static Response.GameMenu BuildCity() {
        throw new RuntimeException("NOT IMPLEMENTED FUNCTION");
    }

}
