package Model;

import Model.Units.Unit;

import java.util.ArrayList;

public class Map {
    private final int width;
    private final int height;
    private Tile[][] tiles;
    private final ArrayList<City> cities = new ArrayList<>();
    private final ArrayList<Unit> units = new ArrayList<>();

    public Map(int width, int height) {
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
}
