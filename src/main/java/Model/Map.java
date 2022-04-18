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
        // TODO: 4/17/2022 initialized tiles , ....
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

    public City getCityByName(String name){
        // TODO: 4/17/2022 returns city by name
        throw new RuntimeException("NOT IMPLEMENTED!");

    }
}
