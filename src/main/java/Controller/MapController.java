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
    public static void RandomizeMap(Map map, ArrayList<Player> players) {
        Tile[][] tiles = map.getTiles();
        int height = map.getHeight();
        int width = map.getWidth();
        // Test map:
        // surrounding the map with ocean
        for(int i=0;i<height;i++){
            tiles[i][0] = new Tile(i, 0, new Terrain(TerrainType.OCEAN, null, null), FogState.VISIBLE, null);
            tiles[i][width - 1] =  new Tile(i, width - 1, new Terrain(TerrainType.OCEAN, null, null), FogState.VISIBLE, null);
        }
        for(int i=0;i<width;i++){
            tiles[0][i] = new Tile(0, i, new Terrain(TerrainType.OCEAN, null, null), FogState.VISIBLE, null);
            tiles[height - 1][i] =  new Tile(height - 1, i, new Terrain(TerrainType.OCEAN, null, null), FogState.VISIBLE, null);
        }

        for (int row = 1; row < height-1; row++) {
            for (int column = 1; column < width-1; column++) {
                // sets Terrain randomly
                Random random = new Random(System.currentTimeMillis());

                ArrayList<TerrainType> possibleTerrains = new ArrayList<>(Arrays.asList(TerrainType.values()));
                ArrayList<Tile> neighbours = map.getNeighbouringTiles(row, column);
                for (Tile tile : neighbours) {
                    if(tile != null){
                        // increasing the chance of a tile being one of the neighbouring terrains
                        possibleTerrains.add(tile.getTerrain().getTerrainType());
                        possibleTerrains.add(tile.getTerrain().getTerrainType());
                        possibleTerrains.add(tile.getTerrain().getTerrainType());
                        possibleTerrains.add(tile.getTerrain().getTerrainType());
                        possibleTerrains.add(tile.getTerrain().getTerrainType());
                    }
                }
                TerrainType terrainType = possibleTerrains.get(random.nextInt(possibleTerrains.size()));

                TerrainFeature terrainFeature = null;
                if(random.nextInt(3) == 0){
                    ArrayList<TerrainFeature> possibleTerrainFeatures = terrainType.possibleTerrainFeatures;
                    terrainFeature = possibleTerrainFeatures.get(random.nextInt(possibleTerrainFeatures.size()));
                }

                ResourceType resourceType = null;
                if(random.nextInt(4) == 0){
                    ArrayList<ResourceType> possibleResources = terrainType.baseFeature.possibleResources;
                    if(terrainFeature != null) possibleResources.addAll(terrainFeature.possibleResources);
                    resourceType = possibleResources.get(random.nextInt(possibleResources.size()));
                }

                // TODO: 4/22/2022 rivers to be handled

                Terrain terrain = new Terrain(terrainType, terrainFeature, resourceType);
                tiles[row][column] = new Tile(row, column, terrain, FogState.VISIBLE, null); // TODO: 4/22/2022 do we have ruins?
            }
        }
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
