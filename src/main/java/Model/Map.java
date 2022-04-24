package Model;

import Model.Units.Unit;

import java.util.ArrayList;

public class Map {
    private final int width;
    private final int height;
    private Tile[][] tiles;
    private final ArrayList<City> cities = new ArrayList<>();
    private final ArrayList<Unit> units = new ArrayList<>();

    public Map(int height, int width) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height]; // TODO: 4/21/2022 how are these tiles initialized??
        // TODO: 4/17/2022 initialized tiles , ....
    }

    // gets a map and deep copies it, a copy with different reference is created
    public Map(Map map) {
        this.width = map.width;
        this.height = map.height;
        this.tiles = map.tiles.clone();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public City getCityByName(String name) {
        for (City city : cities) {
            if (city.getName().equals(name)) {
                return city;
            }
        }
        return null;
    }

    public void addCity(City city) {
        this.cities.add(city);
    }

    public void setTile(int row, int column, Tile tile){
        tiles[row][column] = tile;
    }

    public Tile getTile(int row, int column) {
        return tiles[row][column];
    }

    public ArrayList<Tile> getNeighbouringTiles(int row, int column){
        ArrayList<Tile> neighbours = new ArrayList<>();
        if(row > 0){
            neighbours.add(tiles[row - 1][column]);
        }
        if(row < height - 1){
            neighbours.add(tiles[row + 1][column]);
        }
        if(column > 0){
            neighbours.add(tiles[row][column - 1]);
        }
        if(column < width - 1){
            neighbours.add(tiles[row][column + 1]);
        }
        if(column % 2 == 0){
            if(column > 0 && row < height - 1){
                neighbours.add(tiles[row + 1][column - 1]);
            }
            if(column < width -1 && row < height - 1){
                neighbours.add(tiles[row + 1][column + 1]);
            }
        }
        else{
            if(column > 0 && row > 0){
                neighbours.add(tiles[row - 1][column - 1]);
            }
            if(column < width -1 && row > 0){
                neighbours.add(tiles[row - 1][column + 1]);
            }
        }
        return neighbours;
    }
}
